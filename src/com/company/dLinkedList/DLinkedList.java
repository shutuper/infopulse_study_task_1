package com.company.dLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLinkedList<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int size;

    private final String expMessage = "Can not find item!";

    DLinkedList() {
    }

    @SafeVarargs
    DLinkedList(T... values) {
        for (T value : values) {
            this.add(value);
        }
    }

    // Внутрішній клас, що представляє вузли:
    private class Node {

        private Node prev;
        private Node next;
        private T value;

        Node(Node prev, Node next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

    }

    // Ітератор для двозв'язного списку:
    @Override
    public Iterator<T> iterator() {
        return new DLinkedListIterator();
    }

    // Імплементація ітератора:
    private class DLinkedListIterator implements Iterator<T> {

        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException(expMessage);
            T value = currentNode.value;
            this.currentNode = this.currentNode.next;
            return value;
        }
    }

    // Реалізація:

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("DLinkedList:");
        this.forEach(x -> sb.append(", ").append(x));
        return sb.toString();
    }

    // Перевіряємо чи існує елемент із вказаним індексом (та чи є індекс коректним):
    private boolean checkIndex(int index) {
        return index >= 0 && index < this.size;
    }

    public T get(int index) {
        T element;
        if (checkIndex(index)) element = getNodeByIndex(index).value;
        else throw new IllegalArgumentException("Index is incorrect or out of bound!");
        return element;
    }

    public void add(T value) {
        if (this.first == null) {
            first = new Node(null, null, value);
        } else {
            Node prevElement = this.last == null ? this.first : this.last;
            this.last = new Node(prevElement, null, value);
            prevElement.next = this.last;
        }
        this.size++;
    }

    public boolean remove(int index) {
        boolean canBeRemoved = checkIndex(index);
        if (canBeRemoved) {
            canBeRemoved = removeByNode(getNodeByIndex(index));
        }
        return canBeRemoved;
    }

    public boolean add(int index, T value) {
        boolean isAdded = true;
        if (index == this.size) {
            add(value);
        } else if (checkIndex(index)) {
            Node oldElement = getNodeByIndex(index);
            Node newElement = new Node(oldElement.prev, oldElement, value);
            if (oldElement.prev == null) {
                this.first = newElement;
                this.last = oldElement;
            } else {
                oldElement.prev.next = newElement;
                oldElement.prev = newElement;
            }
            this.size++;
        } else isAdded = false;
        return isAdded;
    }

    // Замінюємо елемент за індексом і повертаємо попереднє значення:
    public T replace(int index, T value) {
        T oldValue;
        if (checkIndex(index)) {
            Node temp = getNodeByIndex(index);
            oldValue = temp.value;
            temp.value = value;
        } else throw new NoSuchElementException(this.expMessage);
        return oldValue;
    }

    //    @SafeVarargs
//    public final boolean addAll(T... values) {
//        boolean result = values != null;
//        if (result) {
//            for (T value : values) {
//                add(value);
//            }
//        }
//        return result;
//
//    }
    @SafeVarargs
    public final boolean addAll(T... values) {
        boolean result = values != null;
        if (result) {
            for (T value : values) {
                add(value);
            }
        }
        return result;
    }

    public boolean addFirst(T value) {
        return add(0, value);
    }

    public boolean addLast(T value) {
        return add(this.size - 1, value);

    }

    public boolean removeFirst() {
        return remove(0);
    }

    public boolean removeLast() {
        return remove(this.size - 1);
    }

    // Отримаємо Node перебором циклу в залежності від індексу (рухаємось з початку чи з кінця):
    private Node getNodeByIndex(int index) {
        Node node;
        if ((this.size / 2) >= index) {
            node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.last;
            for (int i = this.size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }


    private boolean removeByNode(Node node) {
        boolean canBeRemoved = node != null;
        if (canBeRemoved) {
            if (node.next == null && node.prev == null) {
                first = null;
                last = null;
            } else if (node.prev == null) {
                first = node.next;
                first.prev = null;
            } else if (node.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
            new Thread(System::gc); // Попытка не пытка)
        }
        return canBeRemoved;
    }

}
