/* 
 * Thomas Nunn
 * 
 * TCSS 305A - Spring 2012
 * Project Tetris
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * A JPanel class for housing a Start/Pause button and increasing the
 * speed that Pieces drop based on the users current level.
 * 
 * @author Thomas Nunn
 * @version 6/1/2012
 */
@SuppressWarnings("serial")
public class PausePanel extends JPanel implements Observer {

  /**
   * The name for the Pause button when game is running.
   */
  private static final String START_TITLE = "Start";

  /**
   * A constant for sizing the panel.
   */
  private static final int PANEL_HEIGHT = 45;
  
  /**
   * The number of lines cleared at which the timer speeds up.
   */
  private static final int TIMER_LEVEL_MARKER = 10;

  /**
   * The JPanel that holds the main Tetris Board.
   */
  private final TetrisPanel my_panel;

  /**
   * The Score JPanel.
   */
  private final Score my_score;

  /**
   * The action that contains the timer.
   */
  private final TetrisAction my_tetris_action;

  /**
   * The Start/Pause button.
   */
  private final JButton my_start_pause_button;

  /**
   * Stores the value that represents lines cleared.
   */
  private int my_lines;
  
  /**
   * Temporary storage for the current level.
   */
  private int my_lines_holder;

  /**
   * Constructs a PausePanel.
   * 
   * @param the_panel The TetrisPanel JPanel.
   * @param the_score The Score JPanel.
   */
  public PausePanel(final TetrisPanel the_panel, final Score the_score) {
    super();
    my_panel = the_panel;
    my_tetris_action = new TetrisAction(my_panel);
    my_start_pause_button = new JButton(new StopStartAction());
    my_score = the_score;
    my_lines = 0;
    my_lines_holder = TIMER_LEVEL_MARKER;
    buildPanel();
  }

  /**
   * Builds the JPanel.
   */
  private void buildPanel() {
    setBackground(Color.LIGHT_GRAY);
    setBorder(createBorder());
    //setPreferredSize(new Dimension(0, PANEL_HEIGHT));

    my_start_pause_button.setText(START_TITLE);
    my_start_pause_button.setBackground(Color.GREEN);
    my_start_pause_button.setMnemonic(KeyEvent.VK_S);
    my_start_pause_button.setSelected(false);
    my_start_pause_button.setPreferredSize(new Dimension(PANEL_HEIGHT * 2, PANEL_HEIGHT));
    
    add(my_start_pause_button);
  }

  /**
   * Creates a custom border for this panel.
   * 
   * @return A compound border.
   */
  private Border createBorder() {

    final Font title_font = new Font("sansserif", Font.BOLD, 12);
    final Border matte_border = BorderFactory.
        createMatteBorder(5, 5, 5, 5, Color.BLACK);
    final Border title_border = BorderFactory.
        createTitledBorder(matte_border, "Start / Pause", TitledBorder.DEFAULT_JUSTIFICATION, 
                           TitledBorder.DEFAULT_POSITION, title_font, Color.BLACK);
    final Border compound_border = BorderFactory.
        createCompoundBorder(matte_border, title_border);
    return compound_border;
  }

  /**
   * Calls the getLevel method from the Score class and stores
   * the value in the field my_level and decreases the game timer
   * delay based on the current level.
   * 
   * @param the_observable The observable object.
   * @param the_arg The observable's parameter.
   */
  @Override
  public void update(final Observable the_observable, final Object the_arg) {

    if (the_arg instanceof Integer) {
      
      my_lines = my_score.getLines();
      final int current_delay = my_tetris_action.getTimer().getDelay();

      if (my_lines >= my_lines_holder) {
        my_tetris_action.getTimer().
            setDelay(current_delay - (TIMER_LEVEL_MARKER * TIMER_LEVEL_MARKER * 2));
        my_lines_holder += TIMER_LEVEL_MARKER;

      }
    }
  }
  
  /**
   * @return The TetrisAction field.
   */
  public TetrisAction getTetrisAction() {
    return my_tetris_action;
  }
  
  /**
   * @return The start/pause button.
   */
  public JButton getStartPauseButton() {
    return my_start_pause_button;
  }

  /**
   * An action class for starting and stopping the Tetris timer.
   * 
   * @author Thomas Nunn
   * @version 5/24/2012
   */
  private class StopStartAction extends AbstractAction {

    /**
     * Stops the timer and renders the TetrisPanel invisible while
     * paused. Pushing the button again restarts the game.
     * 
     * @param the_event The Action Event.
     */
    @Override
    public void actionPerformed(final ActionEvent the_event) {

      if (my_start_pause_button.getBackground().equals(Color.GREEN)) {
        my_tetris_action.getTimer().start();
        my_start_pause_button.setText("Pause");
        my_start_pause_button.setBackground(Color.RED);
        my_panel.setVisible(true);
        my_start_pause_button.setMnemonic(KeyEvent.VK_P);
        TETRISWAVES.BACKGROUND_MUSIC.loop();

      } else {
        my_tetris_action.getTimer().stop();
        my_start_pause_button.setText(START_TITLE);
        my_start_pause_button.setBackground(Color.GREEN);
        my_panel.setVisible(false);
        my_start_pause_button.setMnemonic(KeyEvent.VK_S);
        TETRISWAVES.BACKGROUND_MUSIC.stop();
      }

      my_panel.requestFocus();
    }
  }
}
