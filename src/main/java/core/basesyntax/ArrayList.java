package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private Object[] grow() {
        Object[] newData = new Object[elementData.length + elementData.length / 2];
        System.arraycopy(elementData, 0, newData, 0, size);
        return newData;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        if (index == size + 1) {
            add(value);
        }
        if (index < size + 1) {
            if (size == elementData.length) {
                elementData = grow();
            }
            Object[] newData = new Object[elementData.length];
            System.arraycopy(elementData, 0, newData, 0, index + 1);
            newData[index] = value;
            System.arraycopy(elementData, index, newData, index + 1, size + 1 - index);
            elementData = newData;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrList = new Object[list.size()];
        while (size == elementData.length || size
                + list.size() > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            arrList[i] = list.get(i);
        }
        System.arraycopy(arrList, 0, elementData, size, arrList.length);
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Object removedObject = null;
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        removedObject = elementData[index];
        Object[] newData = new Object[elementData.length];
        System.arraycopy(elementData, 0, newData, 0, index + 1);
        System.arraycopy(elementData, index + 1, newData, index, size - index + 1);
        elementData = newData;
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        int tempCount = 0;
        if (element == null) {
            throw new NoSuchElementException("There is no such element in the array");
        }
        for (Object o : elementData) {
            if (!o.equals(element)) {
                tempCount++;
            }
        }
        if (tempCount == size) {
            throw new NoSuchElementException("There is no such element in the array");
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                Object[] newData = new Object[elementData.length];
                System.arraycopy(elementData, 0, newData, 0, i + 1);
                System.arraycopy(elementData, i + 1, newData, i, size - i + 1);
                elementData = newData;
                size--;
                return (T) elementData[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
