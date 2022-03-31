import java.util.Comparator;

/**
 *  SuperSorter - is singleton class needed to sort SuperList collections
 *  use getInstance() method to get instance of class
 *
 *  @author Ivan Lysikov
 */
public class SuperSorter {

    private static SuperSorter sorter;

    /**
     * returns instance of SuperSorter class
     * @return instance of SuperSorter class
     */
    public static SuperSorter getInstance(){
        if(sorter == null){
            sorter = new SuperSorter();
        }
        return sorter;
    }

    private SuperSorter(){}

    /**
     * Sorts SuperList collection in order which described in comparator
     * @param superList - array to sort
     * @param comparator - comparator
     * @param <E> - parameter of collection and comparator
     */
    public <E> void sort(SuperList<E> superList, Comparator<E> comparator) {
        if (superList.size() <= 1) {
            return;
        }
        int left = 0;
        int right = superList.size() - 1;
        quickSort(left, right, superList, comparator);
    }

    /**
     * Sorts SuperList collection in order.
     * Order depends on realisation of Comparable interface by collection members
     * @param superList - collection
     * @param <E> - must implements Comparable
     */
    public <E extends Comparable> void sort(SuperList<E> superList) {
        quickSort(0, superList.size() - 1, superList);
    }

    private <E> void quickSort(int left,
                               int right,
                               SuperList<E> superList,
                               Comparator<? super E> comparator) {
        if (left >= right) {
            return;
        }

        int l = left;
        int r = right - 1;
        int pivotIndex = right;

        boolean LReadyToSwap = false;
        boolean RReadyToSwap = false;
        while (l <= r) {
            if (comparator.compare(superList.get(l), superList.get(pivotIndex)) >= 0) {
                LReadyToSwap = true;
            } else {
                l++;
            }

            if (comparator.compare(superList.get(r), superList.get(pivotIndex)) <= 0) {
                RReadyToSwap = true;
            } else {
                r--;
            }

            if (LReadyToSwap && RReadyToSwap) {
                LReadyToSwap = false;
                RReadyToSwap = false;
                swap(l, r, superList);
                l++;
                r--;
            }
        }
        swap(l, right, superList);

        quickSort(left, l - 1, superList, comparator);
        quickSort(l + 1, right, superList, comparator);
    }

    private <E extends Comparable> void quickSort(int left, int right, SuperList<E> superList) {
        if (left >= right) {
            return;
        }

        int l = left;
        int r = right - 1;
        int pivotIndex = right;

        boolean LReadyToSwap = false;
        boolean RReadyToSwap = false;
        while (l <= r) {
            if ((superList.get(l).compareTo(superList.get(pivotIndex)) >= 0)) {
                LReadyToSwap = true;
            } else {
                l++;
            }

            if ((superList.get(r).compareTo(superList.get(pivotIndex)) <= 0)) {
                RReadyToSwap = true;
            } else {
                r--;
            }

            if (LReadyToSwap && RReadyToSwap) {
                LReadyToSwap = false;
                RReadyToSwap = false;
                swap(l, r, superList);
                l++;
                r--;
            }
        }
        swap(l, right, superList);

        quickSort(left, l - 1, superList);
        quickSort(l + 1, right, superList);
    }

    private <E> void swap(int a, int b, SuperList<E> superList) {
        E temp = superList.get(a);
        superList.add(a, superList.get(b));
        superList.add(b, temp);
    }
}
