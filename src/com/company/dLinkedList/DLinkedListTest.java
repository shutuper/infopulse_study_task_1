package com.company.dLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DLinkedListTest {

    public static final int million = 1_000_000;

    private DLinkedList<Integer> fillWithInts(int n){
        DLinkedList<Integer> list = new DLinkedList<>();
        list.addAll(IntStream.range(0, n).boxed().toArray(Integer[]::new));
        return list;
    }

    @Test
    void iterator() {
        DLinkedList<Integer> list = fillWithInts(million);
        assertTrue(list.iterator().hasNext());
        int lastElem = -1;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            lastElem = iterator.next();
        }
        assertEquals(lastElem, list.get(million-1));
    }

    @Test
    void clear() {
        DLinkedList<Integer> list = fillWithInts(million);
        list.clear();
        assertTrue(list.isEmpty());
        assertFalse(list.iterator().hasNext());
    }

    @Test
    void get() {
        DLinkedList<Integer> list = fillWithInts(million);
        list.remove(888_888);
        Assertions.assertEquals(million-1,list.get(million-2));
        Assertions.assertEquals(2,list.get(2));
    }

    @Test
    void remove() {
        DLinkedList<String> list = new DLinkedList<>();
        list.addAll("lol", "hah", "joke", "frst", "sec", "done", "hih");
        list.remove(0);
        list.remove(1);
        Assertions.assertEquals("hah", list.get(0));
        Assertions.assertEquals("frst", list.get(1));
        Assertions.assertEquals(5, list.size());

    }

    @Test
    void replace() {
        DLinkedList<String> list = new DLinkedList<>();
        list.addAll("lol", "cool", "nice");
        list.replace(1, "awful");
        Assertions.assertEquals("awful", list.get(1));
    }

    @Test
    void addAll() {
        long fTime = System.currentTimeMillis();
        int to = million;
        DLinkedList<Integer> list = fillWithInts(to);
        long sTime = System.currentTimeMillis();
        System.out.println("Час заповнення " + to + " елементів: " + (sTime - fTime) + " мілісекунд");
        Assertions.assertEquals(list.get(0), 0);
        Assertions.assertEquals(list.get(2), 2);
        Assertions.assertEquals(list.get(to-1), to-1);
    }
}