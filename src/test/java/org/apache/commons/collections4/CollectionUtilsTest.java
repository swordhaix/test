package org.apache.commons.collections4;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

public class CollectionUtilsTest {

    @Test // JUnit 4 注解（来自 org.junit.Test）
    public void addAll() { // JUnit 4 要求测试方法为 public void
        Collection<Integer> collection = new ArrayList<>();
        Integer[] elements = {1, 2, 3};
        boolean changed = CollectionUtils.addAll(collection, elements);
        assertTrue("addAll should return true when elements are added", changed); // JUnit 4 断言需显式传递错误信息
        assertEquals("Collection size should be 3 after addAll", 3, collection.size());
    }

    @Test
    public void addIgnoreNull() {
        Collection<Integer> collection = new ArrayList<>();
        boolean added = CollectionUtils.addIgnoreNull(collection, null);
        assertFalse("addIgnoreNull should not add null element", added);
        CollectionUtils.addIgnoreNull(collection, 4);
        assertEquals("Collection should contain non-null element", 1, collection.size());
    }

    @Test
    public void isEmpty() {
        assertTrue("isEmpty should return true for null collection", CollectionUtils.isEmpty(null));
        assertTrue("isEmpty should return true for empty collection", CollectionUtils.isEmpty(new ArrayList<>()));
        assertFalse("isEmpty should return false for non-empty collection", CollectionUtils.isEmpty(Arrays.asList(1, 2)));
    }

    // 其他测试方法需同样添加 public 修饰符，并使用 org.junit.Assert 断言
}
