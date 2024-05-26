package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    @DisplayName("Get hash from key, hash should be less than capacity and more than zero")
    void getHash() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        int hash = myHashMap.getHash(1, 16);
        assertTrue(hash >= 0 && hash < 16);

        MyHashMap<String, String> myHashMap1 = new MyHashMap<>();
        hash = myHashMap1.getHash("HashMap", 16);
        assertTrue(hash >= 0 && hash < 16);
    }


    @Test
    @DisplayName("Put key and value to the hashtable")
    void put() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(1, 5);
        myHashMap.put(2, 7);
        assertAll(() -> assertTrue(myHashMap.containsKey(1)),
                () -> assertTrue(myHashMap.hasValue(5)));

        assertAll(() -> assertTrue(myHashMap.containsKey(2)),
                () -> assertTrue(myHashMap.hasValue(7)));
    }

    @Test
    @DisplayName("Lots of data")
    void bigData() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>(100000);

        for (int i = 0; i < 100000; i++) {
            myHashMap.put(i, i + 1);
        }

        for (int i = 0; i < 100000; i++) {
            assertEquals(myHashMap.get(i), i + 1);
        }
    }

    @Test
    @DisplayName("Get value by key")
    void get() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(1, 5);
        assertEquals(myHashMap.get(1), 5);

        MyHashMap<String, String> myHashMap1 = new MyHashMap<>();
        myHashMap1.put("First", "1");
        assertEquals(myHashMap1.get("First"), "1");
    }

    @Test
    @DisplayName("If there are too many values or collisions, hashtable should double its size")
    void resize() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put(1, 999);
        for (int i = 5; i < 100; i++) {
            myHashMap.put(i, i);
        }
        assertTrue(myHashMap.capacity > 32);
        assertEquals(myHashMap.get(1), 999);

    }

    @Test
    @DisplayName("Removing value erases key and value")
    void remove() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put(1, 5);
        myHashMap.remove(1);

        assertNull(myHashMap.get(1));

    }

    @Test
    @DisplayName("Checks if hashtable is empty")
    void isEmpty() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

        assertTrue(myHashMap.isEmpty());

        myHashMap.put(1, 5);
        assertFalse(myHashMap.isEmpty());

        myHashMap.remove(1);
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    @DisplayName("Checks if value is present in the hashmap")
    void hasValue() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put(1, 10);
        assertTrue(myHashMap.hasValue(10));
        myHashMap.remove(1);
        assertFalse(myHashMap.hasValue(10));

    }

    @Test
    @DisplayName("Checks if key is present in the hashmap")
    void containsKey() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put(1, 10);
        myHashMap.put(5, 10);
        myHashMap.remove(5);

        assertTrue(myHashMap.containsKey(1));
        assertFalse(myHashMap.containsKey(4));
        assertFalse(myHashMap.containsKey(5));
    }
}