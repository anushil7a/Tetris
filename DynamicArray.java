/**
 * This is the DynamicArray class. It is a generic class that can be used to create an array of any type.
 * It has a set method that sets the value at a given index, a get method that returns the value at a given index,
 * and a size method that returns the size of the array.
 * @param <T> the type of the array
 */

public class DynamicArray<T>
{
    /**
     * creates a private array of type T variable.
     * creates a private int variable that stores the size of the array
     * @param arr the array of type T
     */
    private T[] arr;

    /**
     * creates a private int variable that stores the size of the array.
     * @param size the size of the array
     * @throws RuntimeException if the size is less than 0
     */
    private int size = 0;
    /**
     * This is the constructor for the DynamicArray class, creates an array of type T with length: size.
     * @param size the size of the array
     * @throws RuntimeException if the size is less than 0
     */
    @SuppressWarnings("unchecked")
    public DynamicArray(int size) // O(1)
    {
        if(size < 0){
            throw new RuntimeException("size cant be less than 0");
        }
        this.arr = (T[]) new Object[size];//create an array of T length: size
        this.size = size;
    }

    /**
     * This method sets the value at a given index.
     * @param index the index of the array
     * @param obj the value to be set at the given index
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the array
     */
    public void set(int index, T obj) // O(1)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index error");
        }
        arr[index] = obj;
    }
    /**
     * Gets the value at the specified index in this array.
     * @param index the index of the array
     * @return the value at the given index
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the array
     */
    public T get(int index) // O(1)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index error");
        }
        return arr[index];
    }

    /**
     * Returns the size of array.
     * @return the size of the array
     */
    public int size() // O(1)
    {
        return size;
    }

}
