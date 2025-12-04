import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Власна реалізація колекції типу Set,
 * що зберігає елементи у двозв'язному списку.
 *
 * @param <T> тип елементів, що зберігаються в множині
 */
public class DoublyLinkedSet<T> implements Set<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    /**
     * Порожній конструктор — створює порожню множину.
     */
    public DoublyLinkedSet() {
    }

    /**
     * Конструктор, що додає один елемент.
     */
    public DoublyLinkedSet(T element) {
        add(element);
    }

    /**
     * Конструктор, що приймає стандартну колекцію.
     */
    public DoublyLinkedSet(Collection<? extends T> collection) {
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Перевірка наявності елемента.
     */
    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Додає елемент у множину.
     * Не додає дублікати!
     */
    @Override
    public boolean add(T value) {
        if (contains(value)) return false;

        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        return true;
    }

    /**
     * Видаляє елемент зі множини.
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = head;

        while (current != null) {
            if (current.value.equals(o)) {

                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // видаляємо перший
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // видаляємо останній
                }

                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Ітератор по елементах множини.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = current.value;
                current = current.next;
                return val;
            }
        };
    }

    /**
     * Перетворення множини на масив.
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;

        for (T el : this) arr[i++] = el;

        return arr;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException("Не реалізовано.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el : c) {
            if (!contains(el)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T el : c) {
            if (add(el)) changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Не реалізовано.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object el : c) {
            if (remove(el)) removed = true;
        }
        return removed;
    }
}
