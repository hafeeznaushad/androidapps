package com.example.tasneem.alphabetpuzzle;

import java.util.Random;

/**
 * Created by tasneem on 23/02/2015.
 */
public class Word {
    private String word;
    private char[] shuffledLetters;
    private char[] wordLetters;
    private int length;
    public Word(String w)
    {
        word = w;
        length = word.length();
        wordLetters = new char[length];
        shuffledLetters = new char[length];
        for (int i = 0; i < length; i++)
        {
            wordLetters[i] = word.charAt(i);
            shuffledLetters[i] = word.charAt(i);
        }
        shuffleArray();
    }


    public int length()
    {
        return length;
    }
    protected void shuffleArray()
    {
        Random rnd = new Random();
        for (int i = length - 1; i >= 0; i--)
        {
            int index = rnd.nextInt(i + 1);

            char x = shuffledLetters[index];
            shuffledLetters[index] = shuffledLetters[i];
            shuffledLetters[i] = x;
        }
    }

    public boolean isValid(int position, char chr)
    {
        return position >= 0 && position < length
                && wordLetters[position] == chr;
    }

    public char[] getJumbledLetters()
    {
        return shuffledLetters;
    }

    public char[] getWordLetters()
    {
        return wordLetters;
    }
}
