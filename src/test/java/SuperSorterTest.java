import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

class SuperSorterTest {

    SuperList<Integer> superList;
    SuperSorter superSorter;

    @BeforeEach
    void init() {
        //given
        superSorter = SuperSorter.getInstance();
        superList = new SuperList<>();
        Integer[] array = {4, 7, 2, 0, 3, 1, 9, 5, 6, 8, 8, 8, 8};
        Arrays.stream(array).forEach(superList::add);
        }

    @Test
    void shouldSortWithoutComparator() {
        //when
        superSorter.sort(superList);

        //then
        if (superList.size() > 1) {
            for (int i = 0; i < superList.size() - 1; i++) {
                Assertions.assertTrue(superList.get(i) <= superList.get(i + 1));
            }
        }
    }

    @Test
    void shouldSortWithComparator() {
        //when
        superSorter.sort(superList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        //then
        if (superList.size() > 1) {
            for (int i = 0; i < superList.size() - 1; i++) {
                Assertions.assertTrue(superList.get(i) <= superList.get(i + 1));
            }
        }
    }
}