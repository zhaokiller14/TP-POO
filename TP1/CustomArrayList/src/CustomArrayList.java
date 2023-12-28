import java.util.ArrayList; 

public class CustomArrayList {
    private Object[] Custom;
    private int size;

    public CustomArrayList() {
        this(10);
    }

    public CustomArrayList(int initialSize) {
        Custom = new Object[initialSize];
        size = 0;
    }

    public void add(Object obj) {
        ensureCapacity();
        Custom[size++] = obj;
    }

    public void add(int index, Object x) {
        ensureCapacity();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        System.arraycopy(Custom, index, Custom, index + 1, size - index);
        Custom[index] = x;
        size++;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return Custom[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isIn(Object x) {
        return find(x) != -1;
    }

    public int find(Object x) {
        for (int i = 0; i < size; i++) {
            if (Custom[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(Object x) {
        int index = find(x);
        if (index != -1) {
            System.arraycopy(Custom, index + 1, Custom, index, size - index - 1);
            size--;
        }
    }

    private void ensureCapacity() {
        if (size == Custom.length) {
            int newCapacity = Custom.length * 2;
            Custom = java.util.Arrays.copyOf(Custom, newCapacity);
        }
    }
}

