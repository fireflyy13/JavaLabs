/**
 * Демонстраційний клас до лабораторної роботи №6.
 * Показує роботу множини, що реалізована на двозв'язному списку.
 */

public class MusicApp6 {

    public static void main(String[] args) {

        // Створення множини
        DoublyLinkedSet<MusicComposition> set = new DoublyLinkedSet<>();

        // Додавання елементів
        set.add(new RockSong("Citizen Erased", "Muse", 371, true));
        set.add(new JazzSong("And The Melody Still Lingers On (Night In Tunisia)", "Chaka Khan", 304, true));
        set.add(new ClassicalComposition("Ballade No. 3 in A-Flat Major, Op. 47", "Frederic Chopin", 451, "Романтизм"));
        set.add(new JazzSong("Summer In The City", "Quincy Jones", 244, false));

        // Спроба додати дублікат
        boolean added = set.add(new JazzSong("And The Melody Still Lingers On (Night In Tunisia)", "Chaka Khan", 304, true));
        System.out.println("Чи додано дублікат? - " + added);

        // Виведення елементів
        System.out.println("\nВміст множини:\n");
        for (MusicComposition c : set) {
            System.out.println(c);
        }

        // Видалення елемента
        set.remove(new JazzSong("Autumn Leaves", "Nat King Cole", 190, false));

        System.out.println("\nВміст множини після видалення:\n");
        for (MusicComposition c : set) {
            System.out.println(c);
        }

        // Виведення розміру
        System.out.println("\nРозмір множини: " + set.size());
    }
}
