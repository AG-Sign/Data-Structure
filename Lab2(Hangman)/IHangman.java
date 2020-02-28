package com.company;
//package eg.edu.alexu.csd.datastructure.hangman;

public interface IHangman {
    public void setDictionary(String [] words);
    public String selectRandomSecretWord();
    public String guess(Character c) throws Exception;
    public void setMaxWrongGuesses(Integer max);
}
