/* 
 * Thomas Nunn
 * 
 * TCSS 305A - Spring 2012
 * Project Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 * A class for creating a Box that houses various JPanels on the
 * Tetris GUI.
 * 
 * @author Thomas Nunn
 * @version 6/1/2012
 */
@SuppressWarnings("serial")
public class StatsBox extends Box {
  
  /**
   * The default size of this panel.
   */
  private static final Dimension STAT_DIMENSION = new Dimension(175, 0);
  
  /**
   * The JPanel separators height.
   */
  private static final int STRUT_HEIGHT = 5;
  
  /**
   * The JPanel that holds the main Tetris Board.
   */
  private final TetrisPanel my_panel;

  /**
   * The Score JPanel.
   */
  private Score my_score;
  
  /**
   * The next piece JPanel.
   */
  private NextPiecePanel my_piece_panel;
  
  /**
   * The game controls JPanel.
   */
  private ControlsPanel my_controls_panel;
  
  /**
   * The JPanel that houses the Start/Pause button.
   */
  private PausePanel my_pause_panel;
  
  /**
   * Creates a StatsBox object.
   * 
   * @param the_panel The Tetris Panel.
   */
  public StatsBox(final TetrisPanel the_panel) {
    super(BoxLayout.Y_AXIS);
    my_panel = the_panel;
    this.setPreferredSize(STAT_DIMENSION);
    setBackground(Color.BLACK);
    setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY.brighter()));
    assignFields();
    buildPanel();
  }
  
  /**
   * Assigns fields and adds Observers.
   */
  private void assignFields() {
    my_score = new Score();
    my_piece_panel = new NextPiecePanel(my_panel);
    my_controls_panel = new ControlsPanel();
    my_pause_panel = new PausePanel(my_panel, my_score);

    my_panel.my_game_board.addObserver(my_score);
    my_panel.my_game_board.addObserver(my_piece_panel);
    my_panel.my_game_board.addObserver(my_pause_panel);
  }
  
  /**
   * Builds the Box and adds vertical struts.
   */
  private void buildPanel() {
    add(StatsBox.createVerticalStrut(STRUT_HEIGHT));

    my_score.setPreferredSize(getPreferredSize());
    add(my_score);
    add(StatsBox.createVerticalStrut(STRUT_HEIGHT));
    
    my_piece_panel.setPreferredSize(getPreferredSize());
    add(my_piece_panel);
    add(StatsBox.createVerticalStrut(STRUT_HEIGHT));

    my_controls_panel.setPreferredSize(getPreferredSize());
    add(my_controls_panel);
    add(StatsBox.createVerticalStrut(STRUT_HEIGHT));
    
    my_pause_panel.setPreferredSize(getPreferredSize());
    add(my_pause_panel);
    add(StatsBox.createVerticalStrut(STRUT_HEIGHT - 1));
    
  }
  
  /**
   * @return The my_pause_panel field.
   */
  public PausePanel getPausePanel() {
    return my_pause_panel;
  }
}
