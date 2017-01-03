package org.broomer.testapps.wordcounter;

/**
 * Application launcher
 */
public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
        }



    }

    private static void printUsage() {
        System.out.println("usage:");
        System.out.println("java -jar wcounter.jar <filename> <topN>");
    }
}
