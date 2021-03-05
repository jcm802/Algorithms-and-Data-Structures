package com.javadeepdive.heaps3;

import java.util.NoSuchElementException;

public class MinHeap {

    // the differences to a max heap are in comments

    private final int[] heap;
    private int size;

    public MinHeap(int capacity) {
        heap = new int[capacity];
    }

    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heap[size] = value;
        fixHeapAbove(size);
        size++;
    }

    public int deleteIndex(int index) {
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int root = 0;
        int parent = getParent(index);
        int deletedValue = heap[index];
        heap[index] = heap[size - 1];
        // new value needs to be larger than it's parent if fixing below
        if(index == root || heap[index] > heap[parent]){
            fixHeapBelow(index, size-1);
        } else {
            fixHeapAbove(index);
        }
        size--;
        return deletedValue;
    }

    public void deleteValue(int value) {
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int index = linearSearch(value);
        int root = 0;
        int parent = getParent(index);
        heap[index] = heap[size - 1];
        if(index == root || heap[index] > heap[parent]){
            fixHeapBelow(index, size-1);
        } else {
            fixHeapAbove(index);
        }
        size--;
    }

    public int poll(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int deletedValue = heap[0];
        heap[0] = heap[size - 1];
        fixHeapBelow(0, size-1);
        size--;
        return deletedValue;
    }

    // descending order
    public void sort(){
        int lastHeapIndex = size - 1;
        for (int i = 0; i < lastHeapIndex; i++) {
            int tmp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = tmp;
            fixHeapBelow(0, lastHeapIndex - i - 1);
        }
    }

    private void fixHeapAbove(int index) {
        int newValue = heap[index];
        // swap as long as the new value is less than it's parent
        while (index > 0 && newValue < heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = newValue;
    }

    private void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwap;
        while (index <= lastHeapIndex) {
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);
            if (leftChild <= lastHeapIndex) {
                if (rightChild > lastHeapIndex) {
                    childToSwap = leftChild;
                }
                else {
                    // The least of the two children is to be swapped
                    childToSwap = (heap[leftChild] < heap[rightChild] ? leftChild : rightChild);
                }
                // if the chosen deleted element is greater than the childToSwap, then swap
                if (heap[index] > heap[childToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = temp;
                } else {
                    break;
                }
                index = childToSwap;
            } else {
                break;
            }
        }
    }

    public int peek(){
        if(isEmpty()) throw new IndexOutOfBoundsException("heap is empty");
        return heap[0];
    }

    public void printHeap(){
        for(int i = 0; i < size; i++){
            System.out.print(heap[i]);
            System.out.print(", ");
        }
        System.out.println();
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int getChild(int index, boolean left){
        return 2 * index + (left ? 1 : 2);
    }

    private int linearSearch(int value){
        for(int i = 0; i < heap.length; i++){
            if (value == heap[i]) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    }

