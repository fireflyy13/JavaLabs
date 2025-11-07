import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in) ) {
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
            TextManager.countMaxSentences(text);

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}

class TextManager {
    public static void countMaxSentences(StringBuffer text) {
        if (text == null) {
            throw new IllegalArgumentException("Текст не може бути null!");
        }


        if (text.isEmpty()) {
            throw new IllegalArgumentException("Текст не може бути порожнім!");
        }


        List<StringBuffer> sentences = new ArrayList<>();
        StringBuffer sentence = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            char digit  = text.charAt(i);
            if (digit != '.' && digit != '!' && digit != '?') {
                sentence.append(digit);
            } else {
                String content = sentence.toString().trim();
                if (!content.isEmpty()) {
                    sentences.add(new StringBuffer(content));
                }
                sentence.setLength(0);
            }

        }

        if (!sentence.isEmpty()) {
            String content = sentence.toString().trim();
            if (!content.isEmpty()) {
                sentences.add(new StringBuffer(content));
            }
        }

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
            }
            else if (count == maxCount) {
                StringBuffer copy = new StringBuffer();
                copy.append(w);
                maxWords.add(copy);
            }
        }

        System.out.println("Слово або слова, що з'являються в більшості речень: ");
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
            char digit =  sentence.charAt(i);
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