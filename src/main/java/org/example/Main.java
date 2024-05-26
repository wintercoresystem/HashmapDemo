package org.example;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>(2);
        MyHashMap<String, String> myHashMap1 = new MyHashMap<>();

        myHashMap.put(1, 4);
        myHashMap.put(6, 9);
        myHashMap.put(6, 10);
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(6));

        myHashMap1.put("First", "Mercury");
        myHashMap1.put("Second", "Venus");
        System.out.println(myHashMap1.get("Second"));
        myHashMap1.put("Third", "Mars");
        myHashMap1.put("Second", "Pandora");
        System.out.println(myHashMap1.get("Second"));


    }
}