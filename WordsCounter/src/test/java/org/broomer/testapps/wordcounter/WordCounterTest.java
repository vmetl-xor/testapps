package org.broomer.testapps.wordcounter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Test suite for the words counter
 */
public class WordCounterTest {

    private Map<String, Integer> expectedMap;
    private String testFileName;
    private String emptyFileName;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        expectedMap = new HashMap<>();
        expectedMap.put("jingle", 3);
        expectedMap.put("bells", 2);

        testFileName = resolveTestFileName("test1.txt");
        emptyFileName = resolveTestFileName("empty.txt");
    }

    @Test
    public void testGetMostFrequentWords() throws Exception {
        WordCounter wordCounter = new WordCounter(testFileName);

        Map<String, Integer> actualFrequencyMap = wordCounter.getMostFrequentWords(2);

        Assert.assertEquals("Words Frequency map has not been calculated correctly", expectedMap, actualFrequencyMap);
    }

    @Test
    public void testReadFromNonexistingFile() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("File not found");

        WordCounter wordCounter = new WordCounter("file_never_existed.txt");
        wordCounter.getMostFrequentWords(2);
    }

    @Test
    public void testReadFromEmptyFile() throws Exception {
        WordCounter wordCounter = new WordCounter(emptyFileName);
        Map<String, Integer> mostFrequentWords = wordCounter.getMostFrequentWords(2);

        Assert.assertTrue("Incorrectly counted word frequency: must be empty", mostFrequentWords.isEmpty());
    }



    private String resolveTestFileName(String fileName) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));

        return currentPath.
                resolve("target").
                resolve("test-classes").
                resolve(fileName).toAbsolutePath().toString();
    }


}