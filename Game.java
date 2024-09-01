import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

/**
 * This class implements the game of Tetris. It is the main class of the project.
 * It contains the main method that creates the window and starts the game.
 */
public final class Game extends JPanel
{
    /**
     * creates a private static Board variable.
     */
    private static Board board;
    /**
     * creates a private static Block variable.
     */
    private static Block block;
    /**
     * creates a private static Random variable.
     */
    private static Random rand = new Random();
    /**
     * creates a private static Color array.
     */
    private final static Color[] color = {
        Color.black, Color.red, Color.green, Color.blue, Color.cyan, Color.magenta, Color.orange, Color.yellow, Color.pink, Color.white
    };

    @Override 
    public void paintComponent(Graphics g)
    {
        drawBoard(g);
        drawBlock(g);
    }

    /**
     * This method draws the board. It loops through the board and draws each tile.
     * It uses the color array to determine the color of the tile.
     * It uses the fillRect method to draw the tile.
     * @param g the Graphics object
     */
    private void drawBoard(Graphics g)
    {
        for (int y=0; y<board.getHeight(); y++)
        {
            for (int x=0; x<board.getWidth(); x++)
            {
                Tile tile = board.getTile(y,x);              
                int colorIndex = tile == null ? 0 : tile.getColor();
                g.setColor(color[colorIndex]);
                g.fillRect(20*x, 20*y, 20, 20);
            }
        }
    }

    /**
     * This method draws the block. It loops through the block and draws each tile. 
     * It uses the color array to determine the color of the tile. 
     * It uses the fillRect method to draw the tile.
     * @param g the Graphics object
     */
    private void drawBlock(Graphics g)
    {
        for (int y=0; y<block.getSize(); y++)
            for (int x=0; x<block.getSize(); x++)
            {
                Tile tile = block.getTile(y,x);
                if (tile != null)
                {
                    g.setColor(color[tile.getColor()]);
                    g.fillRect(20*(x+block.getX()), 20*(y+block.getY()), 20, 20);
                }
            }
    }

    /**
     * This is the main method of the project. It creates the window and starts the game.
     * It takes in the height and width of the board as command line arguments.
     * It creates a JFrame object and sets the title of the window.
     * It creates a Board object and a Block object. It sets the size of the window.
     * It creates a Game object and adds it to the window. It adds a KeyListener to the window.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Usage: java Game <height> <width>");
            return;
        }

        JFrame window = new JFrame("CS310 - Spring 2024 - Project 1");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new Board(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        block = new Block(0, 3, 4, (byte)(1 + rand.nextInt(color.length-1)));
        window.setSize(board.getWidth()*20, board.getHeight()*20+29);
        window.setVisible(true);
        final Game tetris = new Game();
        window.add(tetris);

        window.addKeyListener(new KeyListener()
        {
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (Tetris.canMoveLeft(board, block))
                            block.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Tetris.canMoveRight(board, block))
                            block.moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        if (Tetris.canflipVertical(board, block))
                            block.flipVertical();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (Tetris.canflipHorizontal(board, block))
                            block.flipHorizontal();
                        break;
                    case KeyEvent.VK_R:
                        if (Tetris.canRotate(board, block))
                            block.rotate();
                        break;
                    case KeyEvent.VK_B://VK_OPEN_BRACKET
                        if (Tetris.canScaleDown(board, block))
                        {
                            block = block.scaleDown();
                            board.penalize();
                        }
                        break;
                    case KeyEvent.VK_A://VK_CLOSE_BRACKET
                        if (Tetris.canScaleUp(board, block))
                        {
                            block = block.scaleUp();
                            board.reward();
                        }
                        break;
                    case KeyEvent.VK_PAGE_DOWN:
                        if (Tetris.canDrop(board, block))
                            block.drop();
                        break;
                }

                tetris.repaint();
            }
            
            public void keyReleased(KeyEvent e) { /* do nothing */ }

            public void keyTyped(KeyEvent e) { /* do nothing */ }

        });
        
        new Thread()
        {
            @Override
            public void run()
            {
                while (true) {
                    try
                    {
                        Thread.sleep(1000);

                        if (Tetris.canDrop(board, block))
                            block.drop();
                        else
                        {
                            board.consolidate(block);
                            board.clearRows();
                            block = new Block(0, 3, 4, (byte)(1 + rand.nextInt(color.length-1)));
                        }

                        tetris.repaint();

                        if (Tetris.isGameOver(board, block))
                            break;
                    }
                    catch(InterruptedException e )
                    {
                    }
                }
            }
        }.start();
    }
}
