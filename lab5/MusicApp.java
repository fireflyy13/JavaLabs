import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Демонстраційний клас для перевірки роботи програми.
 * Показує: наслідування, поліморфізм, сортування, пошук
 * та взаємодію з користувачем у терміналі.
 */
public class MusicApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Створення композицій
            MusicComposition[] tracks = {
                    new RockSong("Catch The Rainbow", "Rainbow", 396, true),
                    new PopSong("Chiquitita", "ABBA", 326, true),
                    new ClassicalComposition("I'll Always Remember", "Toshifumi Hinata", 196, "Сучасна класика"),
                    new RockSong("Always Somewhere", "Scorpions", 297, true),
                    new JazzSong("Cheek to Cheek", "Frank Sinatra", 186, false),
                    new PopSong("Say Say Say", "Paul McCartney, Michael Jackson", 234, true),
                    new ClassicalComposition("Prelude", "Toshifumi Hinata", 170, "Сучасна класика"),
                    new JazzSong("Autumn Leaves", "Nat King Cole", 161, false),
            };

            Album album = new Album("Winter Collection", tracks);

            // Вивід всіх композицій
            System.out.println("\nПочатковий альбом:\n");
            album.printTracks();

            System.out.println("\nСумарна тривалість альбому: " + album.getTotalDuration() + " сек");

            album.sortByStyle();
            System.out.println("\nАльбом після сортування за жанром:\n");
            album.printTracks();

            // Ввід діапазону тривалості користувачем
            int min, max;

            System.out.println("\nПошук композицій за тривалістю:");

            while (true) {
                try {
                    System.out.print("Введіть мінімальну тривалість (сек): ");
                    min = scanner.nextInt();

                    System.out.print("Введіть максимальну тривалість (сек): ");
                    max = scanner.nextInt();

                    if (min < 0 || max < 0 || min > max) {
                        System.out.println("Неправильний діапазон! Спробуйте ще раз.\n");
                        continue;
                    }
                    break; // дані коректні — виходимо з циклу
                } catch (InputMismatchException ex) {
                    System.out.println("Помилка: потрібно вводити лише числа!\n");
                    scanner.nextLine(); // очищення буфера
                }
            }

            // Пошук композицій у діапазоні
            System.out.println("\nЗнайдені композиції: \n");
            MusicComposition[] found = album.findTracksByLengthRange(min, max);

            for (MusicComposition c : found) {
                System.out.println(c);
                c.play(); // демонстрація поліморфізму
            }

        } catch (TrackNotFoundException e) {
            System.err.println("Не знайдено жодної композиції: " + e.getMessage());
        } catch (InvalidDurationException e) {
            System.err.println("Помилка тривалості: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Несподівана помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
