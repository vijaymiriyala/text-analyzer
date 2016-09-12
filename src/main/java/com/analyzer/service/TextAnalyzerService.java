package com.analyzer.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextAnalyzerService {

    /*
     * analyze the phrase and order the words as per requirement
     */
    public List<String> analyze(String PHRASE) {

        // word delimiter
        final char DELIMITER = ' ';

        // validate input
        if (PHRASE == null || PHRASE.length() == 0) {
            return null;
        }

        StringBuilder currentWord = new StringBuilder(); // constructs the word while looping through every char
        int currentWordLength = 0;
        List<String> words = new ArrayList<>(); // holds the list of words

        for (int i = 0; i < PHRASE.length(); i++) {
            char currentChar = PHRASE.charAt(i);
            if (currentChar == DELIMITER) {
                if (currentWordLength != 0) words.add(currentWord.toString()); // add only when the length is more than 0
                currentWord.setLength(0); // reset StringBuilder
                currentWordLength = 0;
            } else {
                currentWord.append(currentChar);
                currentWordLength++;
            }
        }
        if (currentWordLength != 0) words.add(currentWord.toString());

        sortWords(words);

        return getWordsWithCounts(words);
    }

    /*
     * Sort words based on length first, second by ASCII order
     */
    private List<String> sortWords(List<String> list) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).length() > list.get(i + 1).length()) {
                    list = swap(list, i, i + 1);
                    swapped = true;
                }
                if ((list.get(i).length() == list.get(i + 1).length()) &&
                        (list.get(i).compareTo(list.get(i + 1)) > 0)) {
                    list = swap(list, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
        return list;
    }

    /*
     * method to swap elements in a list
     */
    private List<String> swap(List<String> list, int indexOne, int indexTwo) {
        String temp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, temp);
        return list;
    }

    /*
     * get words occurrences and format them as per requirement
     */
    private List<String> getWordsWithCounts(List<String> words) {
        List<String> returnValues = new ArrayList<>();
        int count;
        for (int i = 0; i < words.size(); i++) {
            count = 1;
            if (i + 1 < words.size() && (words.get(i).equals(words.get(i + 1)))) {
                while (i + 1 < words.size() && (words.get(i).equals(words.get(i + 1)))) {
                    count++;
                    i++;
                }
            }
            returnValues.add(count + " " + words.get(i));
        }
        return returnValues;
    }

}
