package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final static int DEFAULT_CAPACITI = 10;
    private Object[] elementData;
    private int count;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITI];
        count = 0;
        size = DEFAULT_CAPACITI;
    }

    private Object[] grow() {
        size = size + size / 2;
        Object[] newData = new Object[size];
        System.arraycopy(elementData, 0, newData, 0, count);
        return newData;
    }

    @Override
    public void add(T value) {
        if (count == elementData.length) {
            elementData = grow();
        }
        elementData[count] = value;
        count++;
    }

    @Override
    public void add(T value, int index) {
        if (index < count + 1) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        if (index == count + 1) {
            add(value);
        }
        if (index < count + 1) {
            if (count == elementData.length) {
                elementData = grow();
            }
            Object[] newData = new Object[size];
            System.arraycopy(elementData, 0, newData, 0, index + 1);
            newData[index] = value;
            System.arraycopy(elementData, index, newData, index + 1, count + 1 - index);
            elementData = newData;
            count++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (count == elementData.length || count + list.size() > elementData.length) {
            elementData = grow();
        }
        System.arraycopy(list, 0, elementData, count + 1, list.size());
        count += list.size();
    }

    @Override
    public T get(int index) {
        if (index < count) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < count) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < count) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
        Object[] newData = new Object[size];
        System.arraycopy(elementData, 0, newData, 0, index + 1);
        System.arraycopy(elementData, index + 1, newData, index, count - index + 1);
        elementData = newData;
        count--;
        return (T) elementData[index];
    }

    @Override
    public T remove(T element) {
        for (Object o : elementData) {
            if (!o.equals(element)) {
                throw new NoSuchElementException("There is no such element");
            }
        }
        for (int i = 0; i < count; i++) {
            if (elementData[i].equals(element)) {
                Object[] newData = new Object[size];
                System.arraycopy(elementData, 0, newData, 0, i + 1);
                System.arraycopy(elementData, i + 1, newData, i, count - i + 1);
                elementData = newData;
                return (T) elementData[i];
            }
        }
        count--;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (elementData[0] == null) {
            return true;
        }
        return false;
    }
}
