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
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * A class for creating JPanels that calculate and display a Tetris player's
 * score, lines cleared, and level.
 * 
 * @author Thomas Nunn
 * @version 6/1/2012
 */
@SuppressWarnings("serial")
public class Score extends JPanel implements Observer {
  
  /**
   * The default size of this panel.
   */
  private static final Dimension SCORE_BOARD_DIMENSION = new Dimension(0, 60);
  
  /**
   * A constant for determining score.
   */
  private static final int SCORE_MULTIPLIER = 100;
  
  /**
   * A constant for calculating levels.
   */
  private static final int LEVEL_ADDER = 10;

  /**
   * A title for the lines cleared label.
   */
  private static final String LINE_TITLE = "Lines Cleared:  ";
  
  /**
   * A title for the score label.
   */
  private static final String SCORE_TITLE = "Score:  ";
  
  /**
   * A title for the level label.
   */
  private static final String LEVEL_TITLE = "Level:  ";

  /**
   * The score label.
   */
  private final JLabel my_score_label;

  /**
   * A user's total score.
   */
  private Integer my_score = 0;
  
  /**
   * The lines cleared label.
   */
  private final JLabel my_lines_label;
  
  /**
   * A user's lines cleared total.
   */
  private Integer my_lines = 0;
  
  /**
   * The level label.
   */
  private final JLabel my_level_label;
  
  /**
   * The user's current level.
   */
  private Integer my_level = 0;
  
  /**
   * A counter for a user's level.
   */
  private Integer my_level_counter;
  
  /**
   * Constructs a Score object and initializes the labels and calls the
   * method to build the JPanel.
   */
  public Score() {
    super();
    my_level_counter = LEVEL_ADDER;
    my_score_label = new JLabel(SCORE_TITLE + my_score.toString());
    my_lines_label = new JLabel(LINE_TITLE + my_lines.toString());
    my_level_label = new JLabel(LEVEL_TITLE + my_level.toString());
    buildPanel();
  }
  
  /**
   * Builds the JPanel.
   */
  private void buildPanel() {
    final BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
   
    this.setLayout(bl);
    
    //setPreferredSize(bl.preferredLayoutSize(this));
    setBackground(Color.LIGHT_GRAY);
    setBorder(createBorder());

    add(my_score_label, bl);
    add(my_level_label, bl);
    add(my_lines_label, bl);
    this.validate();
    
    //my_score_label.setPreferredSize(my_lines_label.getPreferredSize());
   // my_level_label.setPreferredSize(my_lines_label.getPreferredSize());
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
        createTitledBorder(matte_border, "Score", TitledBorder.DEFAULT_JUSTIFICATION, 
                           TitledBorder.DEFAULT_POSITION, title_font, Color.BLACK);
    final Border compound_border = BorderFactory.
        createCompoundBorder(matte_border, title_border);
    return compound_border;
  }
  
  /**
   * @return The my_lines field.
   */
  public int getLines() {
    return my_lines;
  }

  /**
   * Updates the players stats. The formula for calculating the score
   * is (lines cleared x lines cleared x 100), which results in a bonus for
   * clearing more than one line at a time.
   * 
   * @param the_observable The observable object.
   * @param the_arg The observable's parameter.
   */
  @Override
  public void update(final Observable the_observable, final Object the_arg) {
    
    if (the_arg instanceof Integer) {
      my_score = my_score + (Integer) the_arg * (Integer) the_arg * SCORE_MULTIPLIER;
      my_score_label.setText(SCORE_TITLE + my_score.toString());
      
      my_lines += (Integer) the_arg;
      my_lines_label.setText(LINE_TITLE + my_lines.toString());

    }
    
    // Increases the level for every ten lines cleared.
    if (my_lines >= my_level_counter) {
      my_level++;
      my_level_label.setText(LEVEL_TITLE + my_level.toString());
      my_level_counter += LEVEL_ADDER;
    }
    
  }
}
