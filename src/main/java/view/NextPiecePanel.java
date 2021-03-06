/* 
 * Thomas Nunn
 * 
 * TCSS 305A - Spring 2012
 * Project Tetris
 */

package view;

import controller.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Piece;

/**
 * A JPanel class that displays the next Piece to drop.
 * 
 * @author Thomas Nunn
 * @version 6/1/2012
 */
@SuppressWarnings("serial")
public class NextPiecePanel extends JPanel implements Observer {

  /**
   * The default size of this panel.
   */
  private static final Dimension PIECE_PANEL_DIMENSION = new Dimension(0, 100);

  /**
   * Determines the grid spacing.
   */
  private static final int GRID_SPACING = 25;

  /**
   * A Tetris Board for displaying the next piece in 
   * the currently running game.
   */
  private Board my_next_piece_board;

  /**
   * The JPanel that holds the main Tetris Board.
   */
  private final TetrisPanel my_panel;

  /**
   * Constructs a NextPiecePanel.
   * 
   * @param the_panel The Tetris panel.
   */
  public NextPiecePanel(final TetrisPanel the_panel) {
    super();
    my_panel = the_panel;
    //setPreferredSize(PIECE_PANEL_DIMENSION);
    setBackground(Color.GRAY);
    setBorder(createBorder());
    createBoard();
  }
  
  /**
   * Creates a new Board and moves the current Piece into view.
   */
  private void createBoard() {
    my_next_piece_board = new Board();
    my_next_piece_board.setCurrentPiece(my_panel.getNextPiece());
    
    my_next_piece_board.step();
    my_next_piece_board.step();
    my_next_piece_board.step();
    my_next_piece_board.step();
    my_next_piece_board.step();
    my_next_piece_board.moveLeft();
    my_next_piece_board.moveLeft();
    my_next_piece_board.moveLeft();
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
        createTitledBorder(matte_border, "Next Piece", TitledBorder.DEFAULT_JUSTIFICATION, 
                           TitledBorder.DEFAULT_POSITION, title_font, Color.BLACK);
    final Border compound_border = BorderFactory.
        createCompoundBorder(matte_border, title_border);
    return compound_border;
  }

  /**
   * Receives an update from the playing Board that a Piece has been
   * frozen and a new Piece generated.
   * 
   * @param the_o The observable.
   * @param the_arg The data.
   */
  @Override
  public void update(final Observable the_o, final Object the_arg) {
    
    if (the_arg instanceof Piece) {
      createBoard();
      repaint();
    }
  }

  /**
   * Paints the JPanel with a Tetris Board for displaying the
   * next Piece to drop.
   * 
   * @param the_graphics The Graphics object.
   */
  @Override
  public void paintComponent(final Graphics the_graphics) {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;

    int x = 0;
    int y = 0;
 
    for (int i = 0; i < my_next_piece_board.getMyBoard().length; i++) {
      for (int j = 0; j < my_next_piece_board.getMyBoard()[i].length; j++) {

        g2d.setColor(my_next_piece_board.getMyBoard()[i][j]);
        if (g2d.getColor().equals(Color.BLACK)) {
          g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fill(new Rectangle2D.Double(x, y, GRID_SPACING, GRID_SPACING));
        x += GRID_SPACING;
        
      }
      y += GRID_SPACING;
      x = 0;
    }
    drawGrid(g2d);
  }
  
  /**
   * Helper method for paintCompenent that draws a grid.
   * 
   * @param the_graphics The graphics object for drawing.
   */
  private void drawGrid(final Graphics2D the_graphics) {
    final Graphics2D g2d = (Graphics2D) the_graphics;
    g2d.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i < this.getHeight(); i += GRID_SPACING) {
      g2d.drawLine(i, 0, i, this.getHeight());
      g2d.drawLine(0, i, this.getWidth(), i);
    }
  }

}
