package com.javadeepdive.heaps3;

public class Main {

    public static void main(String[] args) {

    // ======= MaxHeap =======
    System.out.println("MaxHeap");
    System.out.println("=======");
	MaxHeap maxheap = new MaxHeap(10);

	System.out.println("Insertion");
    maxheap.insert(80);
    maxheap.insert(75);
    maxheap.insert(60);
    maxheap.insert(68);
    maxheap.insert(55);
    maxheap.insert(40);
    maxheap.insert(52);
    maxheap.insert(67);
    maxheap.printHeap();

    System.out.println("Delete Index 2");
    System.out.println(maxheap.deleteIndex(2));
    maxheap.printHeap();

    System.out.println("Poll");
    System.out.println(maxheap.poll());
    maxheap.printHeap();

    System.out.println("Delete value 67");
    maxheap.deleteValue(67);
    maxheap.printHeap();

    System.out.println("Peek");
    System.out.println(maxheap.peek());

    System.out.println("Sort");
    maxheap.sort();
    maxheap.printHeap();

    // ======= MinHeap =======
    System.out.println("\nMinHeap");
    System.out.println("=======");
    MinHeap minheap = new MinHeap(10);
    System.out.println("Insertion");
    minheap.insert(80);
    minheap.insert(75);
    minheap.insert(60);
    minheap.insert(68);
    minheap.insert(55);
    minheap.insert(40);
    minheap.insert(52);
    minheap.insert(67);
    minheap.printHeap();

    System.out.println("Delete Index 2");
    System.out.println(minheap.deleteIndex(2));
    minheap.printHeap();

    System.out.println("Poll (40)");
    System.out.println(minheap.poll());
    minheap.printHeap();

    System.out.println("Delete value 68");
    minheap.deleteValue(68);
    minheap.printHeap();

    System.out.println("Peek");
    System.out.println(minheap.peek());

    System.out.println("Sort");
    minheap.sort();
    minheap.printHeap();
    }
}
