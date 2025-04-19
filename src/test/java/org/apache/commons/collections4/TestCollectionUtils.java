package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestCollectionUtils {

    private static void assertTrue(boolean condition, String errorMessage) {
        if (!condition) {
            throw new RuntimeException(errorMessage);
        }
    }

    private static void assertFalse(boolean condition, String errorMessage) {
        if (condition) {
            throw new RuntimeException(errorMessage);
        }
    }

    private static void assertEquals(Object expected, Object actual, String errorMessage) {
        if ((expected == null && actual != null) || (expected != null && !expected.equals(actual))) {
            throw new RuntimeException(errorMessage);
        }
    }

    public static boolean testAddAll() {
        try {
            Collection<Integer> collection = new ArrayList<>();
            Integer[] elements = {1, 2, 3};
            boolean changed = CollectionUtils.addAll(collection, elements);
            assertTrue(changed, "addAll method did not return true when elements were added.");
            assertEquals(3, collection.size(), "Collection size is not as expected after addAll.");
            assertTrue(collection.containsAll(Arrays.asList(1, 2, 3)), "Elements were not added correctly to the collection.");
            System.out.println("testAddAll passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testAddAll failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean testAddIgnoreNull() {
        try {
            Collection<Integer> collection = new ArrayList<>();
            boolean added = CollectionUtils.addIgnoreNull(collection, null);
            assertFalse(added, "addIgnoreNull method added a null element.");
            assertEquals(0, collection.size(), "Collection size is not as expected after adding null.");

            added = CollectionUtils.addIgnoreNull(collection, 4);
            assertTrue(added, "addIgnoreNull method did not add a non-null element.");
            assertEquals(1, collection.size(), "Collection size is not as expected after adding a non-null element.");
            assertTrue(collection.contains(4), "The added non-null element is not in the collection.");
            System.out.println("testAddIgnoreNull passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testAddIgnoreNull failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean testAddAllWithDefect() {
        try {
            Collection<Integer> collection = new ArrayList<>();
            Integer[] elements = {1, null, 2};
            boolean changed = CollectionUtils.addAll(collection, elements);
            assertTrue(changed, "addAll method did not return true when elements were added.");
            assertEquals(2, collection.size(), "Collection size is not as expected due to ignoring null element.");
            assertTrue(collection.containsAll(Arrays.asList(1, 2)), "Elements were not added correctly to the collection.");
            System.out.println("testAddAllWithDefect passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testAddAllWithDefect failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean testContainsAll() {
        try {
            Collection<Integer> coll1 = Arrays.asList(1, 2, 3);
            Collection<Integer> coll2 = Arrays.asList(1, 2);
            assertTrue(CollectionUtils.containsAll(coll1, coll2), "containsAll method returned false when it should be true.");

            coll2 = Arrays.asList(1, 4);
            assertFalse(CollectionUtils.containsAll(coll1, coll2), "containsAll method returned true when it should be false.");
            System.out.println("testContainsAll passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testContainsAll failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean testContainsAny() {
        try {
            Collection<Integer> coll1 = Arrays.asList(1, 2, 3);
            Collection<Integer> coll2 = Arrays.asList(4, 5, 6);
            assertFalse(CollectionUtils.containsAny(coll1, coll2), "containsAny method returned true when it should be false.");

            coll2 = Arrays.asList(3, 4, 5);
            assertTrue(CollectionUtils.containsAny(coll1, coll2), "containsAny method returned false when it should be true.");
            System.out.println("testContainsAny passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testContainsAny failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean testIsEmpty() {
        try {
            Collection<Integer> nullCollection = null;
            assertTrue(CollectionUtils.isEmpty(nullCollection), "isEmpty method should return true for a null collection.");

            Collection<Integer> emptyCollection = new ArrayList<>();
            assertTrue(CollectionUtils.isEmpty(emptyCollection), "isEmpty method returned false for an empty collection.");

            Collection<Integer> nonEmptyCollection = new ArrayList<>();
            nonEmptyCollection.add(1);
            assertFalse(CollectionUtils.isEmpty(nonEmptyCollection), "isEmpty method returned true for a non-empty collection.");

            System.out.println("testIsEmpty passed.");
            return true;
        } catch (RuntimeException e) {
            System.out.println("testIsEmpty failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        boolean testAddAllResult = testAddAll();
        boolean testAddIgnoreNullResult = testAddIgnoreNull();
        boolean testAddAllWithDefectResult = testAddAllWithDefect();
        boolean testContainsAllResult = testContainsAll();
        boolean testContainsAnyResult = testContainsAny();
        boolean testIsEmptyResult = testIsEmpty();

        System.out.println("testAddAll: " + (testAddAllResult ? "通过" : "未通过"));
        System.out.println("testAddIgnoreNull: " + (testAddIgnoreNullResult ? "通过" : "未通过"));
        System.out.println("testAddAllWithDefect: " + (testAddAllWithDefectResult ? "通过" : "未通过"));
        System.out.println("testContainsAll: " + (testContainsAllResult ? "通过" : "未通过"));
        System.out.println("testContainsAny: " + (testContainsAnyResult ? "通过" : "未通过"));
        System.out.println("testIsEmpty: " + (testIsEmptyResult ? "通过" : "未通过"));

        boolean allPassed = testAddAllResult && testAddIgnoreNullResult && testAddAllWithDefectResult &&
                testContainsAllResult && testContainsAnyResult && testIsEmptyResult;
    }
}