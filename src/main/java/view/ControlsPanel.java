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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * A class for creating a JPanel that displays the Tetris
 * game keyboard controls.
 * 
 * @author Thomas Nunn
 * @version 6/1/2012
 */
public class ControlsPanel extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
   * The default size of this panel.
   */
  private static final Dimension CONTROLS_PANEL_DIMENSION = new Dimension(0, 120);

  /**
   * Constructs a new ControlsPanel.
   */
  public ControlsPanel() {
    super();
    buildPanel();
  }

  /**
   * Builds the JPanel.
   */
  private void buildPanel() {

    final JLabel left_label = new JLabel("LEFT: left arrow key");
    final JLabel right_label = new JLabel("RIGHT: right arrow key");
    final JLabel down_label = new JLabel("DOWN: down arrow key");
    final JLabel rotate_label = new JLabel("ROTATE: up arrow key");
    final JLabel drop_label = new JLabel("DROP: space bar");
    
    left_label.setPreferredSize(down_label.getPreferredSize());
    right_label.setPreferredSize(down_label.getPreferredSize());
    rotate_label.setPreferredSize(down_label.getPreferredSize());
    drop_label.setPreferredSize(down_label.getPreferredSize());

    add(left_label);
    add(right_label);
    add(down_label);
    add(rotate_label);
    add(drop_label);

    //setPreferredSize(CONTROLS_PANEL_DIMENSION);
    setBackground(Color.LIGHT_GRAY);
    setBorder(createBorder());

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
        createTitledBorder(matte_border, "Controls", TitledBorder.DEFAULT_JUSTIFICATION, 
                           TitledBorder.DEFAULT_POSITION, title_font, Color.BLACK);
    final Border compound_border = BorderFactory.
        createCompoundBorder(matte_border, title_border);
    return compound_border;
  }
}
