import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;


class SuperListTest {


    @Test
    void shouldAddElementToCollection() {
        try {
            //given
            Field array = SuperList.class.getDeclaredField("array");
            array.setAccessible(true);
            SuperList<Integer> underTest = new SuperList<>();
            array.set(underTest, new Integer[10]);
            //when
            underTest.add(10);
            Integer[] actualList = (Integer[]) array.get(underTest);
            int actualValue = actualList[0];
            //then
            Assertions.assertEquals(10, actualValue);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void shouldIncreaseSizeWhenNewElementInserted() {
        try {
            //given
            Field size = SuperList.class.getDeclaredField("size");
            size.setAccessible(true);
            //when
            SuperList<Integer> superList = new SuperList<>();
            Integer actualSize = (Integer) size.get(superList);
            //then
            Assertions.assertEquals(0, actualSize.intValue());
            //when
            superList.add(0);
            actualSize = (Integer) size.get(superList);
            //then
            Assertions.assertEquals(1, actualSize.intValue());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void shouldIncreaseLengthWhenArrayOverflow() {
        try {
            //given
            Field array = SuperList.class.getDeclaredField("array");
            Field size = SuperList.class.getDeclaredField("size");
            Field DEFAULT_SIZE = SuperList.class.getDeclaredField("DEFAULT_SIZE");
            array.setAccessible(true);
            size.setAccessible(true);
            DEFAULT_SIZE.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[10]);
            size.set(superList, 10);
            //when
            superList.add(1);
            Integer[] actualArray = (Integer[]) array.get(superList);
            //then
            int expected = 10 + (Integer) DEFAULT_SIZE.get(superList);
            Assertions.assertEquals(expected, actualArray.length);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void shouldInsertElementIntoSelectedIndex() {
        try {
            //given
            Field array = SuperList.class.getDeclaredField("array");
            Field size = SuperList.class.getDeclaredField("size");
            array.setAccessible(true);
            size.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
            size.set(superList, 10);
            //when
            superList.add(1, 111);
            Integer[] actualArray = (Integer[]) array.get(superList);
            int expected = actualArray[1];
            //then
            Assertions.assertEquals(111, expected);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"0,0",
            "1,1",
            "2,2"})
    void shouldGetElementsBySelectedIndex(int index, int value) {
        try {
            //given
            Field array = SuperList.class.getDeclaredField("array");
            Field size = SuperList.class.getDeclaredField("size");
            array.setAccessible(true);
            size.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
            size.set(superList, 10);
            //when
            int actual = superList.get(index);
            //then
            Assertions.assertEquals(value, actual);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldReturnSize() {
        try {
            //given
            Field size = SuperList.class.getDeclaredField("size");
            size.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            size.set(superList, 1000);
            Assertions.assertEquals(1000, superList.size());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"1, true",
            "2, false"})
    void shouldRemoveElementFromCollection(Integer element, boolean expected) {
        try {
            //given
            Field size = SuperList.class.getDeclaredField("size");
            Field array = SuperList.class.getDeclaredField("array");
            size.setAccessible(true);
            array.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[]{0, 1, 3});
            size.set(superList, 3);
            //when
            boolean actual = superList.remove(element);
            Assertions.assertEquals(expected, actual);
            Integer[] actualArray = (Integer[]) array.get(superList);

            for (Integer integer : actualArray) {
                Assertions.assertNotEquals(integer, element);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"0","1","2"})
    void shouldRemoveElementFromCollectionByIndex(int index) {
        try {
            //given
            Field size = SuperList.class.getDeclaredField("size");
            Field array = SuperList.class.getDeclaredField("array");
            size.setAccessible(true);
            array.setAccessible(true);
            SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[]{0, 1, 3});
            size.set(superList, 3);
            size.setAccessible(false);
            //when
            Integer element = superList.remove(index);
            //then
            for (Integer integer : superList) {
                Assertions.assertNotEquals(integer, element);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void shouldReturnArray() {
        try {
            //given
            Field size = SuperList.class.getDeclaredField("size");
            Field array = SuperList.class.getDeclaredField("array");
            size.setAccessible(true);
            array.setAccessible(true);
                        SuperList<Integer> superList = new SuperList<>();
            array.set(superList, new Integer[]{0, 1, 3});
            size.set(superList, 3);
            size.setAccessible(false);
            array.setAccessible(false);
            //when
            Integer[] expected = superList.toArray(new Integer[]{});
            //then
            Assertions.assertEquals(3, expected.length);
            Assertions.assertEquals(0, expected[0]);
            Assertions.assertEquals(1, expected[1]);
            Assertions.assertEquals(3, expected[2]);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}