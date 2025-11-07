import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Лабораторна робота №4.
 *
 * Модифікувати лабораторну роботу №2 наступним чином:
 * для літер, слів, речень, розділових знаків та тексту створити окремі класи.
 * Слово повинно складатися з масиву літер, речення — з масиву слів
 * та розділових знаків, текст — з масиву речень.
 * Замінити послідовність табуляцій та пробілів одним пробілом.
 *
 * Створити клас, який складається з виконавчого методу, що виконує
 * описану дію з лабораторної роботи №2, але в якості типів використовує створені класи.
 *
 * Виконала: студентка групи ІС-34 Романюк Діана
 * Дата: 07.11.2025
 */
public class Lab4 {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Введіть текст (натисніть Enter, щоб завершити):");
            StringBuilder sb = new StringBuilder();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isBlank()) break;
                sb.append(line).append(" ");
            }

            String inputText = sb.toString();

            if (inputText == null || inputText.trim().isEmpty()) {
                throw new IllegalArgumentException("Ви не ввели текст!");
            }

            StringBuffer text = new StringBuffer(
                    inputText.replaceAll("[\\r\\n]+", " ")
            );

            ManageText.countMaxSentences(text);

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}

class ManageText {
    public static void countMaxSentences(StringBuffer text) {
        if (text == null) {
            throw new IllegalArgumentException("Текст не може бути null!");
        }

        if (text.isEmpty()) {
            throw new IllegalArgumentException("Текст не може бути порожнім!");
        }

        Text structuredText = new Text(text);

        List<StringBuffer> sentences = structuredText.toPlainSentences();

        if (sentences.isEmpty()) {
            throw new IllegalArgumentException("У тексті немає речень!");
        }

        List<StringBuffer> words = new ArrayList<>();
        StringBuffer word = new StringBuffer();

        for (int i = 0; i < sentences.size(); i++) {
            for (int j = 0; j < sentences.get(i).length(); j++) {
                char digit = sentences.get(i).charAt(j);
                if (Character.isLetter(digit) || digit == '’' || digit == '\'') {
                    word.append(digit);
                } else {
                    StringBuffer copy = new StringBuffer();
                    copy.append(word);
                    if (!isDuplicate(words, copy) && copy.length() > 0) {
                        words.add(copy);
                    }
                    word.setLength(0);
                }
            }
            if (words.isEmpty()) {
                throw new IllegalArgumentException("У тексті немає жодного слова!");
            }
        }

        int maxCount = 0;
        List<StringBuffer> maxWords = new ArrayList<>();

        for (StringBuffer w : words) {
            int count = 0;

            for (StringBuffer s : sentences) {
                if (wordInSentence(s, w)) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxWords.clear();
                StringBuffer copy = new StringBuffer();
                copy.append(w);
                maxWords.add(copy);
            } else if (count == maxCount) {
                StringBuffer copy = new StringBuffer();
                copy.append(w);
                maxWords.add(copy);
            }
        }

        System.out.println("Слово або слова, що з'являються в більшості речень:");
        for (StringBuffer w : maxWords) {
            System.out.print(w.toString() + " ");
        }
        System.out.println("\nЗнайдено в " + maxCount + " реченнях.");
    }

    private static boolean isDuplicate(List<StringBuffer> list, StringBuffer word) {
        for (StringBuffer w : list) {
            if (w.length() == word.length()) {
                boolean equal = true;
                for (int i = 0; i < w.length(); i++) {
                    if (Character.toLowerCase(w.charAt(i)) != Character.toLowerCase(word.charAt(i))) {
                        equal = false;
                        break;
                    }
                }
                if (equal) return true;
            }
        }
        return false;
    }

    private static boolean wordInSentence(StringBuffer sentence, StringBuffer word) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < sentence.length(); i++) {
            char digit = sentence.charAt(i);
            if (Character.isLetter(digit) || digit == '\'' || digit == '’') {
                temp.append(sentence.charAt(i));

            } else {
                if (!temp.isEmpty()) {
                    if (temp.toString().equalsIgnoreCase(word.toString())) {
                        return true;
                    }
                    temp.setLength(0);
                }
            }
        }
        if (!temp.isEmpty()) {
            if (temp.toString().equalsIgnoreCase(word.toString())) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Клас Letter — представляє одну літеру.
 */
class Letter {
    private final char value;

    public Letter(char value) {
        this.value = value;
    }

    public char charValue() {
        return value;
    }

    public StringBuffer asText() {
        return new StringBuffer().append(value);
    }
}

/**
 * Клас Punctuation — представляє розділовий знак.
 */
class Punctuation {
    private final char symbol;

    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    public char charValue() {
        return symbol;
    }

    public StringBuffer asText() {
        return new StringBuffer().append(symbol);
    }
}

/**
 * Клас Word — слово, яке складається з масиву літер.
 */
class Word {
    private final Letter[] letters;

    public Word(StringBuffer word) {
        letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    public StringBuffer asText() {
        StringBuffer sb = new StringBuffer();
        for (Letter l : letters) {
            sb.append(l.charValue());
        }
        return sb;
    }
}

/**
 * Клас Sentence — речення, яке складається зі слів та розділових знаків.
 */
class Sentence {
    private final ArrayList<Object> elements = new ArrayList<>();

    public Sentence(StringBuffer rawSentence) {
        String[] tokens = rawSentence.toString().trim().split("\\s+");
        for (String t : tokens) {
            StringBuffer buf = new StringBuffer(t);
            StringBuffer acc = new StringBuffer();
            for (int i = 0; i < buf.length(); i++) {
                char c = buf.charAt(i);
                if (Character.isLetter(c) || Character.isDigit(c) || c == '\'' || c == '’') {
                    acc.append(c);
                } else {
                    if (acc.length() > 0) {
                        elements.add(new Word(new StringBuffer(acc)));
                        acc.setLength(0);
                    }
                    elements.add(new Punctuation(c));
                }
            }
            if (acc.length() > 0) {
                elements.add(new Word(new StringBuffer(acc)));
            }
        }
    }

    /**
     * Повертає представлення речення лише як послідовність слів.
     */
    public StringBuffer asPlainWords() {
        StringBuffer out = new StringBuffer();
        for (Object e : elements) {
            if (e instanceof Word) {
                if (out.length() > 0) out.append(' ');
                out.append(((Word) e).asText());
            }
        }
        return out;
    }
}

/**
 * Клас Text — текст, що складається з масиву речень.
 */
class Text {
    private final ArrayList<Sentence> sentences = new ArrayList<>();

    public Text(StringBuffer input) {
        StringBuffer cleaned = new StringBuffer(
                input.toString()
                        .replaceAll("[\\r\\n]+", " ")
                        .replaceAll("[\\t\\s]+", " ")
                        .trim()
        );

        String[] raw = cleaned.toString().split("(?<=[.!?])\\s+");
        for (String r : raw) {
            String trimmed = r.trim();
            if (!trimmed.isEmpty()) {
                sentences.add(new Sentence(new StringBuffer(trimmed)));
            }
        }
    }

    /**
     * Повертає речення як звичайний текст, щоб використати у ManageText.
     */
    public List<StringBuffer> toPlainSentences() {
        ArrayList<StringBuffer> out = new ArrayList<>();
        for (Sentence s : sentences) {
            out.add(s.asPlainWords());
        }
        return out;
    }
}
