/**
 * This class has the rules for the tetris game.
 */
public class Tetris
{
    /**
     * This method checks if the block can move left. It iterates through the block and checks if the block can move left 
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can move left, false otherwise
     */
    public static boolean canMoveLeft(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canMoveLeft_helper(board, block, y, x)) {
                    return false; // Block cannot move left
                }
            }
        }
        return true;
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the left side of the board, and if there is a tile to the left of the block.
     * returns False if there is a wall or a tile to the left of the block.
     * @param board the board
     * @param block the block
     * @param tempy the y coordinate of the tile
     * @param tempx the x coordinate of the tile
     * @return true if the block can move left, false otherwise
     */
    private static boolean canMoveLeft_helper(Board board, Block block, int tempy, int tempx) {
        
        if(block.getTile(tempy, tempx) == null)
        {
            return true;
        }
        if (block.getTile(tempy, tempx) != null) {
            int row = block.getY() + tempy;
            int col = block.getX() + tempx;

            if (col == 0) {
                return false;
            }
            if (board.getTile(row, col - 1) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the block can move right. It iterates through the block and checks if the block can move right 
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can move right, false otherwise
     */
    public static boolean canMoveRight(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canMoveRight_helper(board, block, y, x)) {
                    return false; // Block cannot move right
                }
            }
        }
        return true;
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the right side of the board, and if there is a tile to the right of the block.
     * returns False if there is a wall or a tile to the right of the block.
     * @param board the board
     * @param block the block
     * @param tempy the y coordinate of the tile
     * @param tempx the x coordinate of the tile
     * @return true if the block can move right, false otherwise
     */
    private static boolean canMoveRight_helper(Board board, Block block, int tempy, int tempx) {
        
        if(block.getTile(tempy, tempx) == null)
        {
            return true;
        }

        if (block.getTile(tempy, tempx) != null) {
            int row = block.getY() + tempy;
            int col = block.getX() + tempx;

            if (col + 1 >= board.getWidth()) {//check if block is at the right side of the board
                return false;
            }
            if (board.getTile(row, col + 1) != null) {//check if there is a tile to the right of the block
                return false;
            }
        }
        return true;//nothing is blocking the block from moving right
    }

    /**
     * This method checks if the block can move down. It iterates through the block and checks if the block can be flipped vertically
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can move down, false otherwise
     */
    public static boolean canflipVertical(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canflipVertical_helper(board, block, y, x)) {
                    return false; // Block cannot flip vertically
                }
            }
        }
        return true;//nothing is blocking the block from flipping vertically
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the bottom of the board, and if there is a tile below the block.
     * returns False if there is a wall or a tile below the block.
     * @param board the board
     * @param block the block
     * @param tempy the y coordinate of the tile
     * @param tempx the x coordinate of the tile
     * @return true if the block can flip vertically, false otherwise
     */
    private static boolean canflipVertical_helper(Board board, Block block, int tempy, int tempx) {
        
        if(block.getTile(tempy, tempx) == null)
        {
            return true;//nothing is blocking the block from flipping vertically
        }
        if (block.getTile(tempy, tempx) != null) {
            int index = block.getSize() - 1 - tempy;

            if (block.getY() + index >= board.getHeight()) {
                return false;//the block is at the bottom of the board
            }
            if (board.getTile(block.getY() + index, block.getX() + tempx) != null) {
                return false;//there is a tile below the block
            }
        }
        return true;
    }

    /**
     * This method checks if the block can flip horizontally. It iterates through the block and checks if the block can be flipped horizontally
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can flip horizontally, false otherwise
     */
    public static boolean canflipHorizontal(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canflipHorizontal_helper(board, block, y, x)) {
                    return false; // Block cannot flip horizontally
                }
            }
        }
        return true;//nothing is blocking the block from flipping horizontally
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the right side of the board, and if there is a tile to the right of the block.
     * returns False if there is a wall or a tile to the right of the block.
     * @param board the board
     * @param block the block
     * @param tempY the y coordinate of the tile
     * @param tempX the x coordinate of the tile
     * @return true if the block can flip horizontally, false otherwise
     */
    private static boolean canflipHorizontal_helper(Board board, Block block, int tempY, int tempX) {
        
        if(block.getTile(tempY, tempX) == null)
        {
            return true;//nothing is blocking the block from flipping horizontally
        }

        if (block.getTile(tempY, tempX) != null) {
            
            int index = block.getSize() - 1 - tempX;

            if (block.getX() + index >= board.getWidth() || block.getX() + index < 0) {
                return false;//the block is at the right side of the board
            }
            if (board.getTile(block.getY() + tempY, block.getX() + index) != null) {
                return false;//there is a tile to the right of the block
            }
        }
        return true;
    }

    /**
     * This method checks if the block can rotate. It iterates through the block and checks if the block can be rotated
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can rotate, false otherwise
     */
    public static boolean canRotate(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canRotate_helper(board, block, y, x)) {
                    return false; // Block cannot rotate
                }
            }
        }
        return true;//nothing is blocking the block from rotating
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the bottom or right side of the board, and if there is a tile below the block.
     * returns False if there is a wall or a tile below the block.
     * @param board the board
     * @param block the block
     * @param tempY the y coordinate of the tile
     * @param tempX the x coordinate of the tile
     * @return true if the block can rotate, false otherwise
     */
    private static boolean canRotate_helper(Board board, Block block, int tempY, int tempX) {
        
        if(block.getTile(tempY, tempX) == null)
        {
            return true;//nothing is blocking the block from rotating
        }
        if (block.getTile(tempY, tempX) != null) {

            int index = block.getSize() - 1 - tempY;
            if (block.getY() + tempX >= board.getHeight() || block.getX() + index >= board.getWidth()) {
                return false; // The block is at the bottom or right side of the board
            }

            if (board.getTile(block.getY() + tempX, block.getX() + index) != null) {
                return false; // There is a tile below the block
            }
        }
        return true;
    }


    /**
     * This method checks if the block can scale down. It checks if the block can be scaled down
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can scale down, false otherwise
     */
    public static boolean canScaleDown(Board board, Block block) // O(board_size)
    {
        int size = block.getSize() / 2;
        if(size < 2)
        {
            return false;
        }
        return true;//placeholder
    }
    /**
     * This method checks if the block can scale up. It iterates through the block and checks if the block can be scaled up
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can scale up, false otherwise
     */

    public static boolean canScaleUp(Board board, Block block) // O(board_size)
    {
        int size = block.getSize() * 2;
        for(int y = 0; y < size; y++)
        {
            for(int x = 0; x < size; x++)
            {
                if (!canScaleUp_helper(board, block, y, x)) {
                    return false; // Block cannot scale up
                }
            }
        }
        return true;//nothing is blocking the block from scaling up
    }
    /**
     * This method is the helper method. It checks if the tile is null,
     * if the block is at the bottom or right side of the board, and if there is a tile below the block.
     * returns False if there is a wall or a tile below the block.
     * @param board the board
     * @param block the block
     * @param tempY the y coordinate of the tile
     * @param tempX the x coordinate of the tile
     * @return true if the block can scale up, false otherwise
     */
    private static boolean canScaleUp_helper(Board board, Block block, int tempY, int tempX) 
    {
        if (tempY < block.getSize() && tempX < block.getSize()) {//check if the tile is in the original block
            Tile tile = block.getTile(tempY, tempX);//get the tile from the original block
            if (tile != null) {//check if the tile is null

                int bigY = block.getY() + (tempY * 2);
                int bigX = block.getX() + (tempX * 2);

                if (!isFree(board, bigY, bigX)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method checks the 2x2 area around the tile to see if it is free. It checks if adding
     * a tile to the board will cause a collision or if the tile is out of bounds.
     * If its not free, it returns false.
     * @param board the board
     * @param y the y coordinate of the tile
     * @param x the x coordinate of the tile
     * @return true if the 2x2 area around the tile is free, false otherwise
     */
    private static boolean isFree(Board board, int y, int x) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {//check the 2x2 area around the tile

                // Boundary check
                if (y + i >= board.getHeight() || x + j >= board.getWidth()) {//check if the tile is out of bound
                    return false; // Part of the 2x2 area is out of bounds
                }
                // Collision check
                if (board.getTile(y + i, x + j) != null) {//check if there is a tile in the 2x2 area
                    return false; // Part of the 2x2 area collides with an existing tile
                }
            }
        }
        return true;
    }
    /**
     * This method checks if the block can drop. It iterates through the block and checks if the block can be dropped
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the block can drop, false otherwise
     */
    public static boolean canDrop(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (!canDrop_helper(board, block, y, x)) {
                    return false; // Block cannot drop
                }
            }
        }
        return true;
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * It also checks if the block is at the bottom of the board, and if there is a tile below the block.
     * returns False if there is a floor or a tile below the block.
     * @param board the board
     * @param block the block
     * @param tempY the y coordinate of the tile
     * @param tempX the x coordinate of the tile
     * @return true if the block can drop, false otherwise
     */
    private static boolean canDrop_helper(Board board, Block block, int tempY, int tempX) {
        
        if(block.getTile(tempY, tempX) == null)
        {
            return true;
        }
        if (block.getTile(tempY, tempX) != null) {
            int row = block.getY() + tempY;
            int col = block.getX() + tempX;

            if (row + 1 >= board.getHeight()) {
                return false;
            }
            if (board.getTile(row + 1, col) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the game is over. It iterates through the block and checks if the game is over
     * (using a helper method).
     * @param board the board
     * @param block the block
     * @return true if the game is over, false otherwise
     */
    public static boolean isGameOver(Board board, Block block) // O(board_size)
    {
        for(int y = 0; y < block.getSize(); y++)
        {
            for(int x = 0; x < block.getSize(); x++)
            {
                if (isGameOver_helper(board, block, y, x)) {
                    return true; // Game is over
                }
            }
        }
        for(int x = 0; x < board.getWidth(); x++)
        {
            if(board.getTile(0, x) != null)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is the helper method. It checks if the tile is null,
     * if there is a tile where the block is, and if the block is at the top of the board.
     * returns True if there is a tile where the block is.
     * @param board the board
     * @param block the block
     * @param y the y coordinate of the tile
     * @param x the x coordinate of the tile
     * @return true if the game is over, false otherwise
     */
    private static boolean isGameOver_helper(Board board, Block block, int y, int x) {
        Tile tile = block.getTile(y, x);

        if(tile == null)
        {
            return false;//the tile is null, so the game is not over
        }
        if (tile != null) {
            if (board.getTile(block.getY() + y, block.getX() + x) != null){
                return true;//there is a tile in the same location as the block
            }
        }
        return false;//the game is not over

    }


}
