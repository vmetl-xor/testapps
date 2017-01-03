package org.broomer.testapps.wordcounter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Application launcher
 */
public class Main {

    public static void main(String[] args) {
        if (!checkArguments(args)) {
            return;
        }

        WordCounter wordCounter = new WordCounter(args[0]);
        Map<String, Integer> mostFrequentWords =
                wordCounter.getMostFrequentWords(Integer.valueOf(args[1]));

        mostFrequentWords.entrySet().forEach(s -> System.out.println(s.getKey() + " - " + s.getValue()));

    }

    private static boolean checkArguments(String[] args) {
        if (args.length != 2) {
            printUsage();
            return false;
        }

        String fileName = args[0];
        if (!Files.exists(Paths.get(fileName))) {
            System.out.println("File " + fileName + " could not be found");
            return false;
        }

        int topAmount;
        try {
            topAmount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("top words count must be a number");
            return false;
        }

        if (topAmount <= 0) {
            System.out.println("topN must be positive and greater that zero");
            return false;
        }

        return true;
    }

    private static void printUsage() {
        System.out.println("usage:");
        System.out.println("java -jar wcounter.jar <filename> <topN>");
    }
}
