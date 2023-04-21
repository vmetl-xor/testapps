package org.broomer.testapps.wordcounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Count the number of words in the text file
 * This class is not thread-safe.
 */
public class WordCounter {

    private static final String WORDS_DELIMITER = "(, | )"; // regex describes 'space or comma followed by space'
    private final Map<String, Integer> wordsCount;
    private final String fileName;

    public WordCounter(String fileName) {
        this.fileName = fileName;
        this.wordsCount = new HashMap<>();
    }

    private void countWords() {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = bf.readLine()) != null) {
                Arrays.stream(s.split(WORDS_DELIMITER)).
                        filter(word -> !word.isEmpty()).
                        forEach(word -> {
                            String wordLoweCase = word.toLowerCase();
                            Integer existingCount = wordsCount.get(wordLoweCase);
                            Integer updatedCount = existingCount == null ? 1 : existingCount + 1;
                            wordsCount.put(wordLoweCase, updatedCount); // todo use collectors here instead of side-var
                        });
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file: " + fileName, e);
        }

    }

    /**
     * count and get N most frequently used words
     *
     * @param topAmount - number of most frequent words, must be positive integer greater than zero
     * @return an ordered map, with most frequent words in descending order
     */
    public Map<String, Integer> getMostFrequentWords(int topAmount) {
        if (topAmount <= 0) {
            throw new IllegalArgumentException("Incorrect number of top frequent words");
        }

        if (wordsCount.isEmpty()) {
            countWords();
        }

        LinkedHashMap<String, Integer> mostFrequentWords = new LinkedHashMap<>();
        wordsCount.entrySet().stream().
                sorted((w1, w2) -> Integer.compare(w2.getValue(), w1.getValue())).
                limit(topAmount).
                forEach(wordEntry -> mostFrequentWords.put(wordEntry.getKey(), wordEntry.getValue())); // todo use collectors here instead of side-var

        return mostFrequentWords;
    }


}
