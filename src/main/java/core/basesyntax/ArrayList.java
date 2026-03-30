package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int countOfElement;
    private int sizeOfArrey;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        countOfElement = 0;
        sizeOfArrey = DEFAULT_CAPACITY;
    }

    private Object[] grow() {
        sizeOfArrey = sizeOfArrey + sizeOfArrey / 2;
        Object[] newData = new Object[sizeOfArrey];
        System.arraycopy(elementData, 0, newData, 0, countOfElement);
        return newData;
    }

    @Override
    public void add(T value) {
        if (countOfElement == elementData.length) {
            elementData = grow();
        }
        elementData[countOfElement] = value;
        countOfElement++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= countOfElement) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        if (index == countOfElement + 1) {
            add(value);
        }
        if (index < countOfElement + 1) {
            if (countOfElement == elementData.length) {
                elementData = grow();
            }
            Object[] newData = new Object[sizeOfArrey];
            System.arraycopy(elementData, 0, newData, 0, index + 1);
            newData[index] = value;
            System.arraycopy(elementData, index, newData, index + 1, countOfElement + 1 - index);
            elementData = newData;
            countOfElement++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arrList = new Object[list.size()];
        while (countOfElement == elementData.length || countOfElement
                + list.size() > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i <= list.size(); i++) {
            arrList[i] = list.get(i);
        }
        System.arraycopy(list, 0, elementData, countOfElement, list.size());
        countOfElement += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > countOfElement) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > countOfElement) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Object removedObject = null;
        if (index >= countOfElement || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        removedObject = elementData[index];
        Object[] newData = new Object[sizeOfArrey];
        System.arraycopy(elementData, 0, newData, 0, index + 1);
        System.arraycopy(elementData, index + 1, newData, index, countOfElement - index + 1);
        elementData = newData;
        countOfElement--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        int tempCount = 0;
        for (Object o : elementData) {
            if (!o.equals(element)) {
                tempCount++;
            }
        }
        if (tempCount == countOfElement || element == null) {
            throw new NoSuchElementException("There is no such element in the array");
        }
        for (int i = 0; i < countOfElement; i++) {
            if (elementData[i].equals(element)) {
                Object[] newData = new Object[sizeOfArrey];
                System.arraycopy(elementData, 0, newData, 0, i + 1);
                System.arraycopy(elementData, i + 1, newData, i, countOfElement - i + 1);
                elementData = newData;
                countOfElement--;
                return (T) elementData[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return countOfElement;
    }

    @Override
    public boolean isEmpty() {
        return countOfElement == 0;
    }
}
