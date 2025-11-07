import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Лабораторна робота №3.
 *
 * Завдання:
 * Створити клас із виконавчим методом з масивом, що містить
 * об'єкти класу визначеного варіантом.
 * Відсортувати масив за одним з полів за зростанням,
 * а за іншим — за спаданням, використовуючи стандартні засоби сортування.
 * Після цього знайти в масиві об’єкт, який ідентичний заданому.
 * Варіант: визначити клас спортивний інвентар, який складається з 5 полів.
 *
 * Виконала: студентка групи ІС-34 Романюк Діана
 * Дата: 07.11.2025
 */

public class Lab3 {

    /**
     * Клас SportInventory описує спортивний інвентар.
     * Містить п’ять полів: назва, тип спорту, ціна, вага, бренд.
     */

    static class SportInventory {
        // Поля класу
        private String name;
        private String type;
        private double price;
        private double weight;
        private String brand;

        /**
         * Конструктор класу SportInventory.
         *
         * @param name   назва предмета
         * @param type   тип спорту
         * @param price  ціна
         * @param weight вага
         * @param brand  виробник
         */
        public SportInventory(String name, String type, double price, double weight, String brand) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.weight = weight;
            this.brand = brand;
        }

        // Геттери
        public String getName() { return name; }
        public String getType() { return type; }
        public double getPrice() { return price; }
        public double getWeight() { return weight; }
        public String getBrand() { return brand; }

        /**
         * Метод порівняння двох об'єктів SportInventory.
         * Об'єкти вважаються ідентичними, якщо всі поля збігаються.
         *
         * @param o об'єкт для порівняння
         * @return true, якщо всі поля однакові
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SportInventory)) return false;
            SportInventory that = (SportInventory) o;
            return Double.compare(that.price, price) == 0
                    && Double.compare(that.weight, weight) == 0
                    && Objects.equals(name, that.name)
                    && Objects.equals(type, that.type)
                    && Objects.equals(brand, that.brand);
        }

        /**
         * Перевизначений метод hashCode для узгодженості з equals().
         */
        @Override
        public int hashCode() {
            return Objects.hash(name, type, price, weight, brand);
        }

        /**
         * Метод для виводу інформації про об'єкт у зручному форматі.
         *
         * @return відформатований рядок
         */
        @Override
        public String toString() {
            return String.format(
                    "Name: %-10s | Type: %-10s | Price: %7.2f | Weight: %4.2f | Brand: %-10s",
                    name, type, price, weight, brand);
        }
    }

    /**
     * Головний виконавчий метод.
     */
    public static void main(String[] args) {

        // Створення початкового масиву
        SportInventory[] inventory = {
                new SportInventory("Ball", "Football", 850.00, 0.45, "Adidas"),
                new SportInventory("Racket", "Tennis", 2200.00, 0.35, "Wilson"),
                new SportInventory("Helmet", "Cycling", 1500.00, 0.40, "Giro"),
                new SportInventory("Gloves", "Boxing", 1200.00, 0.50, "Everlast"),
                new SportInventory("Mat", "Yoga", 700.00, 1.20, "Reebok")
        };

        System.out.println("\nПОЧАТКОВИЙ МАСИВ:");
        for (SportInventory item : inventory) {
            System.out.println(item);
        }

        // Сортування за ціною (зростання)
        SportInventory[] byPrice = Arrays.copyOf(inventory, inventory.length);
        Arrays.sort(byPrice, Comparator.comparingDouble(SportInventory::getPrice));

        System.out.println("\nСОРТУВАННЯ ЗА ЦІНОЮ (ЗРОСТАННЯ)");
        for (SportInventory item : byPrice) {
            System.out.println(item);
        }

        // Сортування за вагою (спадання)
        SportInventory[] byWeight = Arrays.copyOf(inventory, inventory.length);
        Arrays.sort(byWeight, Comparator.comparingDouble(SportInventory::getWeight).reversed());

        System.out.println("\nСОРТУВАННЯ ЗА ВАГОЮ (СПАДАННЯ)");
        for (SportInventory item : byWeight) {
            System.out.println(item);
        }

        // Пошук ідентичного об’єкта
        SportInventory searchItem = new SportInventory("Gloves", "Boxing", 1200.00, 0.50, "Everlast");
        boolean found = false;
        for (SportInventory item : inventory) {
            if (item.equals(searchItem)) {
                System.out.println("\nІДЕНТИЧНИЙ ОБ'ЄКТ:");
                System.out.println(item);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nІдентичного об’єкта не знайдено.");
        }
    }
}
