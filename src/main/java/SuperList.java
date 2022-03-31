import java.util.Arrays;
import java.util.Iterator;

/**
 *  Probably, the best, fastest and secure collection ever.
 *  Use it, and you will be pretty and healthy!
 *  It uses super secret algorithms, and nobody knows how it works, even author @author Ivan Lysikov
 *
 * @param <E> - any type
 */
public class SuperList<E> implements Iterable<E> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private E[] array;

    public SuperList() {
        array = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }


    /**
     * returns array representation of collection of E type
     * @return E[] array representation of collection
     */
    public E[] toArray(E[] array) {

        if(size != 0) {
            return (E[]) Arrays.copyOf(this.array, size, array.getClass());
        }
        return array;
    }

    /**
     * returns the element from collection by selected index, removes it from collection and
     * shifts all elements to the left
     * @param index - the index of element
     * @return - element that was removed from collection
     */
    public E remove(final int index) {
            if(index>=size){
                throw new ArrayIndexOutOfBoundsException();
            }
            E e = array[index];
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            array[index] = null;
            size--;

        return e;
    }

    /**
     * removes element from collection and shifts all elements to the left
     * return true when element was removed
     * @param e element of collection
     * @return true when element was removed, false - if wasn't
     */
    public boolean remove(final E e) {
        for (int i = 0; i < size; i++) {
            if (e == array[i]) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * returns size of collection
     * @return value of collection size
     */
    public int size() {
        return size;
    }

    /**
     * returns the element from collection by selected index
     * @param index - the index of element
     * @return - element from collection
     */
    public E get(final int index) {
        return array[index];
    }


    /**
     * Appends element to the collection on the last index, returns true when element was added
     * @param e element
     * @return true when element was added
     */
    public boolean add(final E e) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, array.length + DEFAULT_SIZE);
        }
        array[size++] = e;
        return true;
    }

    /**
     * Appends element to the collection on the chosen index
     * @param e element
     * @param index index
     * @return true when element was added
     */
    public void add(final int index, final E e) {
        if(index < size){
            array[index] = e;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                if (current < array.length) {
                    return array[current] != null;
                }
                return false;
            }

            @Override
            public E next() {
                return array[current++];
            }
        };
    }
}
