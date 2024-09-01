/**
 * This class represents the tetris board. It is a 2D array of tiles.
 * It is responsible for consolidating the dropping block into the well, clearing complete rows, and applying rewards and penalties.
 */
public class Board
{
    /**
     * The board is a 2D array of tiles. The first index is the row, the second index is the column. This is the full board.
     */
    private DynamicArray<DynamicArray<Tile>> board; 
    /**
     * The height and width of the board.
     */
    private int height;
    /**
     * The height and width of the board.
     */
    private int width;

    /**
     * This is the constructor for the Board class, creates a 2D array of tiles with height: height and width: width.
     * Fills the array with null tiles.
     * @param height The height of the board.
     * @param width The width of the board.
     */

    public Board(int height, int width) // this contructor creates a 2D placeholder of null values; these values will be populated later with calls to setTile() -- O(height * width)
    {//@throws RuntimeException if the height or width is less than 0
        if(height <= 0 || width <= 0)
        {
            throw new RuntimeException("height or width cant be less than 0");
        }
        this.height = height;
        this.width = width;
        this.board = new DynamicArray<DynamicArray<Tile>>(height);
        
        for(int i = 0; i < height; i++)
        {
            board.set(i, new DynamicArray<Tile>(width));
            for(int j = 0; j < width; j++)
            {
                board.get(i).set(j, null);
            }
        }

    }

    /**
     * Returns the width of the board.
     * @return the width of the board
     */
    public int getWidth() // returns the width of the board -- O(1)
    {
        return width;
    }

    /**
     * Returns the height of the board.
     * @return the height of the board
     */
    public int getHeight() // returns the height of the board -- O(1)
    {
        return height;
    }

    /**
     * This method sets the tile at location y,x.
     * @param y the y coordinate of the tile
     * @param x the x coordinate of the tile
     * @param t the tile to be set at location y,x
     */
    public void setTile(int y, int x, Tile t) // sets the tile at location y,x -- O(1)
    {
        if(y < 0 || x < 0 || y >= height || x >= width)
        {
            throw new IndexOutOfBoundsException("Index error");
        }
        board.get(y).set(x, t);

    }

    /**
     * Returns the tile at location y,x.
     * @param y the y coordinate of the tile
     * @param x the x coordinate of the tile
     * @return the tile at location y,x
     */
    public Tile getTile(int y, int x) // gets the tile from location y,x -- O(1)
    {
        if(y < 0 || x < 0 || y >= height || x >= width)
        {
            throw new IndexOutOfBoundsException("Index error");
        }
        return board.get(y).get(x);
    }

    /**
     * This method consolidates the dropping block into the board. It gets the block location.
     * It then iterates through the block and sets the tiles in the block to the tiles in the board.
     * Once it finds a tile in the block, it checks if the tile is within the bounds of the board.
     * If it is, it sets the tile in the board to the tile in the block.
     * @param block the dropping block
     */
    public void consolidate(Block block) // when the dropping block has reached its final location, this method will consolidate it into the tetris well -- O(block_size)
    {
        int bx = block.getX();
        int by = block.getY();

        for(int i = 0; i < block.getSize(); i++)
        {
            for(int j = 0; j < block.getSize(); j++)
            {
                if(block.getTile(i, j) != null)
                {
                    if(by + i < height && bx + j < width)//if the block is within the bounds of the board, set the tile
                    {
                        setTile(by + i, bx + j, block.getTile(i, j));
                    }
                }
            }
        }

    }

    /**
     * This method clears any/all rows that are complete and shifts the above tiles down.
     * It uses the isRowComplete method to check if the row is complete.
     * If it is, it uses the clearRow method to clear the row.
     * It then uses the shiftRowsDown method to shift the rows down.
     */

    public void clearRows() // clear any/all rows that are complete and shifts the above tiles down -- O(board_size)
    {
        for(int row = 0; row < height; row++)
        {
            if(isRowComplete(row))
            {
                clearRow(row);
                shiftRowsDown(row);
            }
        }

    }
    /**
     * This method checks if the row is complete.
     * @param row the row to be checked
     * @return true if the row is complete; false otherwise
     */
    private boolean isRowComplete(int row) // returns true if the row is complete; false otherwise
    {
        for(int col = 0; col < width; col++)
        {
            if(getTile(row, col) == null)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * This method clears the row by setting all tiles to null.
     * @param row the row to be cleared
     */
    private void clearRow(int row) // clears the row by setting all tiles to null
    {
        for(int col = 0; col < width; col++)
        {
            setTile(row, col, null);
        }
    }
    /**
     * This method shifts all rows above the given row down by one.
     * @param row the row to be shifted down
     */
    private void shiftRowsDown(int row) // shifts all rows above the given row down by one
    {
        for(int i = row; i > 0; i--)
        {
            for(int j = 0; j < width; j++)
            {
                setTile(i, j, getTile(i - 1, j));
            }
        }
    }

    /**
     * This method applies the reward by removing the row with the most tiles when user scales.
     * It uses the mostTiles method to find the row with the most tiles.
     * It then uses the clearRow method to clear the row.
     * It then uses the shiftRowsDown method to shift the rows down.
     */
    public void reward() // applies the reward as explained in the project description -- O(board_size)
    {
        int row = mostTiles();
        if(row != -2){
            clearRow(row);
            shiftRowsDown(row);
        }
        else{
            return;
        }
    }

    /**
     * This method finds the row with the most tiles.
     * @return the row with the most tiles
     */
    private int mostTiles() // returns the row with the most tiles -- O(board_size)
    {
        int max = 0;
        int highestRow = -2;
        for(int row = 0; row < height; row++)
        {
            int count = 0;
            for(int col = 0; col < width; col++)
            {
                if(getTile(row, col) != null)
                {
                    count++;
                }
            }
            if(count >= max)
            {
                max = count;
                highestRow = row;
            }
        }
        return highestRow;
    }

    /**
     * This method applies the penalty by removing the row with the least tiles when user scales down.
     * It uses the leastTiles method to find the row with the least tiles.
     */
    public void penalize() // applies the penalty as explained in the project description -- O(board_size)
    {
        int row = leastTiles();
        //System.out.println(row);
        if(row == -1){
            return;
        }
        if(row > 0) {
            for(int i = 0; i < row; i++) {
                for(int j = 0; j < width; j++) {
                    setTile(i, j, getTile(i + 1, j));
                }
            }
        }

    }

    /**
     * This method finds the row with the least tiles. 
     * @return the row with the least tiles
     */
    private int leastTiles() // returns the row with the least tiles -- O(board_size)
    {
        int minRow = -1;
        int min = Integer.MAX_VALUE;

        for(int row = 0; row < height; row++)
        {
            int count = 0;
            for(int col = 0; col < width; col++)
            {
                if(getTile(row, col) != null)
                {
                    count++;
                }
            }
            if(count < min && count != 0)
            {
                min = count;
                minRow = row;
            }
        }
        return minRow;
    }

}
