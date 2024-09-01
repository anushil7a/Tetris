/**
 * Tile class for the game of Tetris.
 * Each tile is a 1x1 block in the game.
 */
public class Tile
{
    /**
     * The color of the tile.
     */
    private byte color;

    /**
     * Constructor for the Tile class.
     * @param color the color of the tile
     */
    public Tile(byte color)
    {
        this.color = color;
    }
    /**
     * Gets the color of the tile.
     * @return the color of the tile
     */
    public byte getColor()
    {
        return color;
    }
}
