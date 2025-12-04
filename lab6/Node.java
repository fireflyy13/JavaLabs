/**
 * Вузол двозв'язного списку.
 *
 * @param <T> тип даних, що зберігається у вузлі
 */
public class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev;

    public Node(T value) {
        this.value = value;
    }
}
