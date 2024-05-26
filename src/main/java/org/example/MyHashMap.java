package org.example;

import java.util.LinkedList;

/**
 * This class represents a custom implementation of a HashMap.
 * It provides all the functionalities of a HashMap including methods to add, retrieve, and remove key-value pairs.
 * It also includes methods to check if the HashMap is empty, if it contains a specific key or value, and to resize
 * the HashMap when it becomes too full.
 * The HashMap maintains performance even with an increasing number of elements by automatically resizing itself when
 * the number of elements exceeds a certain threshold (the load factor).
 * This ensures effective memory utilization.
 * The load factor of 0.7 denotes the threshold at which the HashMap will resize itself.
 * When the number of elements exceeds 70 percent of the capacity, the HashMap automatically increases its size to ensure efficient operation by redistributing the elements and maintaining performance.
 *
 * @param <KEY> the type of keys maintained by this map
 * @param <VALUE> the type of mapped values
 */
public class MyHashMap <KEY, VALUE> {
    protected int capacity = 16;
    protected LinkedList<KEY> keys = new LinkedList<>();
    protected VALUE[] values;

    /**
     * Constructs an empty HashMap with the default initial capacity of 16.
     */
    public MyHashMap() {
        this.values = (VALUE[]) new Object[this.capacity];
    }


    /**
     * Constructs an empty HashMap with the specified initial capacity.
     *
     * @param capacity the initial capacity of the HashMap
     */
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.values = (VALUE[]) new Object[this.capacity];
    }


    /**
     * Calculate the hash for the given key.
     *
     * @param key The key to be hashed.
     * @param capacity The current capacity of the HashMap.
     * @return The calculated hash for the given key.
     */
    public int getHash(KEY key, int capacity) {
        return Math.abs(key.hashCode()) % (capacity);
    }


    /**
     * Insert the key-value pair into the HashMap.
     *
     * @param key The key of the entry.
     * @param value The value to be associated with the key.
     */
    public void put(KEY key, VALUE value) {
        int index = this.getHash(key, this.capacity);

        // Check if we need to resize
        if (this.keys.size() >= this.capacity * 0.7F) { // adjust the load factor as needed
            this.resize();
            index = this.getHash(key, this.capacity); // rehash after resizing
        }

        // Avoiding collisions via linear collision resolution
        while (this.values[index] != null && this.get(key) != value && !this.containsKey(key)) {
            this.resize();
            index = this.getHash(key, this.capacity); // rehash after resizing
        }

        this.keys.add(key);
        this.values[index] = value;
    }


    /**
     * Retrieve the value associated with the given key.
     *
     * @param key The key to get the value for.
     * @return The value associated with the key or null if the key doesn't exist.
     */
    public VALUE get(KEY key) {
        int index = getHash(key, this.capacity);

        return this.values[index];
    }


    /**
     * Resize the HashMap to accommodate more entries.
     */
    public void resize() {
        this.capacity = this.capacity * 2; // Double the capacity
        VALUE[] oldValues = this.values.clone();
        this.values = (VALUE[]) new Object[this.capacity];
        KEY[] allKeys = (KEY[]) this.keys.toArray();
        this.keys.clear();

        // Rehashes all values
        for (int i = 0; i < allKeys.length; i++) {
            KEY oldKey = allKeys[i];
            VALUE oldValue = oldValues[this.getHash(oldKey, this.capacity / 2)];
            this.put(oldKey, oldValue);
        }
        System.out.printf("Resized to %d\n", this.capacity);

    }


    /**
     * Remove the entry with the given key from the HashMap.
     *
     * @param key The key of the entry to be removed.
     * @return The value associated with the removed key or null if the key doesn't exist.
     */
    public VALUE remove(KEY key) {
        int index = getHash(key, this.capacity);
        VALUE data = get(key);

        this.keys.remove(key);
        this.values[index] = null;

        return data;
    }


    /**
     * Check if the HashMap is empty.
     *
     * @return True if the HashMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.keys.isEmpty();
    }


    /**
     * Check if the HashMap contains the given value.
     *
     * @param value The value to be checked.
     * @return True if the HashMap contains the value, false otherwise.
     */
    public boolean hasValue(VALUE value) {
        for (KEY key : this.keys) {
            if (this.get(key).equals(value)) return true;
        }
        return false;
    }


    /**
     * Check if the HashMap contains the given key.
     *
     * @param key The key to be checked.
     * @return True if the HashMap contains the key, false otherwise.
     */
    public boolean containsKey(KEY key) {
        return this.keys.contains(key);
    }

}
