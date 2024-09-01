import java.util.Random;
/**
 * This class represents a block of tiles. It is a 2D matrix of tiles. It has methods to manipulate the block.
 * It has a constructor that creates a 2D placeholder of null values; these values will be populated later with calls to setTile().
 * It has a constructor that creates a 2D matrix with actual tile objects; no need to call setTile afterwards.
 */
public class Block
{
    /**
     * creates a private DynamicArray of type DynamicArray of type Tile variable.
     * @param block the 2D matrix of tiles
     */
    private DynamicArray<DynamicArray<Tile>> block;
    /**
     * creates a private int variable that stores the length of the side of the block.
     * @param size the length of the side of the block
     */
    private int size;
    /**
     * creates a private int variable that stores the top-left Y-coordinate of the block.
     * @param yval the top-left Y-coordinate of the block
     */
    private int yval;
    /**
     * creates a private int variable that stores the top-left X-coordinate of the block.
     * @param xval the top-left X-coordinate of the block
     */
    private int xval;

    /**
     * This is the constructor for the Block class, creates a 2D placeholder of null values; these values will be populated later with calls to setTile().
     * @param y the top-left Y-coordinate of the block
     * @param x the top-left X-coordinate of the block
     * @param size the length of the side of the block
     */
    public Block(int y, int x, int size) // this contructor creates a 2D placeholder of null values; these values will be populated later with calls to setTile() -- O(block_size)
    {
        if(size < 0){
            throw new RuntimeException("size cant be less than 0");
        }
        if(y < 0 || x < 0){
            throw new RuntimeException("y or x cant be less than 0");
        }
        this.yval = y;
        this.xval = x;
        this.size = size;
        this.block = new DynamicArray<DynamicArray<Tile>>(size);
        
        for(int i = 0; i < size; i++)
        {
            block.set(i, new DynamicArray<Tile>(size));
            for(int j = 0; j < size; j++)
            {
                block.get(i).set(j, null);
            }
        }
    }
    /**
     * This is the constructor for the Block class, creates a 2D matrix with actual tile objects; no need to call setTile afterwards.
     * Randomly places tiles in the block. The number of tiles is a random number between 1 and the number of tiles in the block.
     * @param y the top-left Y-coordinate of the block
     * @param x the top-left X-coordinate of the block
     * @param size the length of the side of the block
     * @param color the color of the block
     */
    public Block(int y, int x, int size, byte color) // overloaded constuctor that creates a 2D matrix with actual tile objects; no need to call setTile afterwards -- O(block_size)
    {
        this(y, x, size);
        //this.y = y;
        //this.x = x;
        //this.size = size;
        //this.block = new DynamicArray<DynamicArray<Tile>>(size);
        Random rand = new Random();

        //int numtiles = size * size;
        //int num_tiles = 1;
        int numtiles = rand.nextInt(size * size) + 1;
        int numtilesplaced = 0;

        while(numtilesplaced < numtiles)
        {
            int randy = rand.nextInt(size);
            int randx = rand.nextInt(size);
            if(block.get(randy).get(randx) == null)
            {
                block.get(randy).set(randx, new Tile(color));
                numtilesplaced++;
            }
        }

    }
    /**
     * Gets the length of the side of the block.
     * @return the length of the side of the block
     */
    public int getSize() // returns the length of the side of block -- O(1)
    {
        return size;
    }

    /**
     * Gets the top-left Y-coordinate of the block.
     * @return the top-left Y-coordinate of the block
     */
    public int getY() // returns the top-left Y-coordinate of the block -- O(1)
    {
        return yval;
    }

    /**
     * Gets the top-left X-coordinate of the block.
     * @return the top-left X-coordinate of the block
     */
    public int getX() // returns the top-left X-coordinate of the block -- O(1)
    {
        return xval;
    }

    /**
     * Sets the tile at location y,x.
     * @param y The y-coordinate of the tile
     * @param x The x-coordinate of the tile
     * @param t The tile to be set at the given location
     * @throws IndexOutOfBoundsException if the y or x is less than 0
     */
    public void setTile(int y, int x, Tile t) // sets the tile at location y,x -- O(1)
    {
        if (y < 0 || x <0) {
            throw new IndexOutOfBoundsException("Index error");
        }
        if( y >= size || x >= size){
            throw new IndexOutOfBoundsException("Index error");
        }
        block.get(y).set(x, t);
    }

    /**
     * Gets the tile from location y,x.
     * @param y The y-coordinate of the tile
     * @param x The x-coordinate of the tile
     * @return the tile from location y,x
     * @throws IndexOutOfBoundsException if the y or x is less than 0
     */
    public Tile getTile(int y, int x) // gets the tile from location y,x -- O(1)
    {
        if(y < 0 || x < 0){
            throw new IndexOutOfBoundsException("y or x cant be less than 0");
        }
        if(y >= size || x >= size){
            throw new IndexOutOfBoundsException("y or x cant be greater than or equal to the size of the array");
        }
        return block.get(y).get(x);
    }

    /**
     * Drops the block by one row.
     */
    public void drop() // drops the block by one row -- O(block_size)
    {
        yval = yval + 1;
    }

    /**
     * Moves the block one spot to the left.
     */
    public void moveLeft() // moves the block one spot to the left -- O(block_size)
    {
        xval = xval - 1;
    }

    /**
     * Moves the block one spot to the right.
     */
    public void moveRight() // moves the block one spot to the right -- O(block_size)
    {
        xval = xval + 1;
    }

    /**
     * Rotates the block 90 degrees clockwise. To do this it creates a temporary 2D matrix of tiles,
     * and then copies the tiles from the original block to the temporary block in the correct order.
     * Then it sets the original block to the temporary block. This method is O(block_size).
     */
    public void rotate() // O(block_size)
    {
        DynamicArray<DynamicArray<Tile>> temp = new DynamicArray<DynamicArray<Tile>>(size);
        for(int i = 0; i < size; i++)
        {
            temp.set(i, new DynamicArray<Tile>(size));
            for(int j = 0; j < size; j++)
            {
                temp.get(i).set(j, block.get(size - 1 -j).get(i));
            }
        }
        block = temp;
    }

    /**
     * Flips the block vertically. To do this it swaps the top row with the bottom row,
     * and then the second row with the second to last row, etc. This method is O(block_size).
     */
    public void flipVertical() // flips the block vertically -- O(block_size)
    {
        for(int i = 0; i < size; i++)
        {
            if(i >= size/2){
                break;
            }
            DynamicArray<Tile> top = block.get(i);
            int index = size - 1 - i;
            DynamicArray<Tile> bottom = block.get(index);
            block.set(i, bottom);
            block.set(index, top);
        }
    }

    /**
     * Flips the block horizontally. To do this it swaps the left column with the right column,
     * and then the second column with the second to last column, etc. This method is O(block_size).
     */
    public void flipHorizontal() // flips the block horizontally -- O(block_size)
    {
        for(int i = 0; i < size; i++)
        {
            DynamicArray<Tile> row = block.get(i);
            for(int j = 0; j < size; j++)
            {
                if(j >= size/2){
                    break;
                }
                Tile left = row.get(j);
                int index = size - 1 - j;
                Tile right = row.get(index);
                row.set(j, right);
                row.set(index, left);
            }
        }
    }

    /**
     * Scales up the block (double size). To do this it creates a new block with double the size,
     * and then copies the tiles from the original block to the new block in the correct order. When copying the tiles,
     * it also copies the tiles to the right, below, and below to the right of the original tile. This way the new block
     * will have the same tiles as the original block, but with double the size.
     * Then it sets the original block to the new block. This method is O(block_size).
     * @return the new block with double the size
     */
    public Block scaleUp() // scales up the block (double size) -- O(block_size)
    {
        Block realBlock = new Block(this.yval, this.xval, size * 2);//creates a new block with double the size

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Tile temp = this.getTile(y, x);
                if (temp != null) {//if the tile is not null, then it will be doubled
                    scaleUp_helper(realBlock, y, x, temp);
                }
            }
        }
        return realBlock;
    }

    /**
     * This method is a helper method for scaleUp(). It copies the tiles from the original block to the new block in the correct order.
     * When copying the tiles, it also copies the tiles to the right, below, and below to the right of the original tile.
     * This way the new block will have the same tiles as the original block, but with double the size.
     * @param realBlock the new block with double the size
     * @param y the y-coordinate of the tile
     * @param x the x-coordinate of the tile
     * @param temp the tile to be copied
     */
    public void scaleUp_helper(Block realBlock, int y, int x, Tile temp) {
        if(temp == null)
        {
            return;
        }
        int newY = y * 2;
        int newX = x * 2;

        realBlock.setTile(newY, newX, temp);
        realBlock.setTile(newY, newX + 1, new Tile(temp.getColor()));
        realBlock.setTile(newY + 1, newX, new Tile(temp.getColor()));
        realBlock.setTile(newY + 1, newX + 1, new Tile(temp.getColor()));
    }
    
    /**
     * Scales down the block (half size). To do this it creates a new block with half the size,
     * and then copies the tiles from the original block to the new block in the correct order. When copying the tiles,
     * it also copies the tiles to the right, below, and below to the right of the original tile. This way the new block
     * will have the same tiles as the original block, but with half the size. If the block cannot be scaled down, then
     * the tile will not look good. It uses the what color to find the color of the block. Then uses the scaleDown_helper
     * to check if the tile can be scaled down. If the tile can be scaled down, then the tile will be set to the color.
     * Then it sets the original block to the new block. This method is O(block_size).
     * @return the new block with half the size
     */
    public Block scaleDown() // scales down the block (half size) -- O(block_size)
    {
        Block smallerBlock = new Block(this.yval, this.xval, this.size / 2);
        int color = this.what_color();
        boolean canScaleDown = false;

        for (int y = 0; y < this.size / 2; y++) {
            for (int x = 0; x < this.size / 2; x++) {

                canScaleDown = scaleDown_helper(y*2, x*2);

                if (canScaleDown) {//if the color is -1, then the tile will be null
                    smallerBlock.setTile(y, x, new Tile((byte)color));
                } else {//if the color is not -1, then the tile will be set to the color
                    smallerBlock.setTile(y, x, null);
                }
            }
        }
        return smallerBlock;
    }

    /**
     * This method is a helper method for scaleDown(). It gives the color for the blocks.
     * @return the color of the block. If it is -2, then the tile is a null tile.
     */
    private byte what_color(){
        byte color = -2;
        for(int y = 0; y < this.getSize(); y++){
            for(int x = 0; x < this.getSize(); x++){
                if(this.getTile(y, x) != null){
                    color = this.getTile(y, x).getColor();
                    return color;
                }
            }
        }
        return color;
    }

    /**
     * This method is a helper method for scaleDown(). It checks if the tile can be scaled down. It checks
     * the tile and the tiles to the right, below, and below to the right of the original tile. If any of
     * the tiles are not null, then the tile can be scaled down. If all of the tiles are null, there is a null
     * tile in the block.
     * @param y the y-coordinate of the tile
     * @param x the x-coordinate of the tile
     * @return true if there is tiles there, returns False if a null tile is there
     */
    private boolean scaleDown_helper(int y, int x) {
        for (int dy = 0; dy < 2; dy++) {
            for (int dx = 0; dx < 2; dx++) {
                if(this.getTile(y + dy, x + dx) != null){
                    return true;
                }
            }
        }
        return false;
    }
    
}
