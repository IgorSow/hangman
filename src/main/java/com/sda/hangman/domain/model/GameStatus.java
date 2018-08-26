package com.sda.hangman.domain.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GameStatus {

    private Integer maxAttemps;
    private String name;
    private String phrase;
    private Character[] phraseState;
    private Integer succesAttemprs;
    private Integer failAtteempts;
    private List<Character> history;

    public GameStatus(String name, String phrase) {
        this(name, phrase, 5);
    }

    public GameStatus(String name, String phrase, Integer maxAttemps) {
        this.name = name;
        this.phrase = phrase;
        this.phraseState = new GameStatusHelper().preparePhraseState(phrase);
        this.succesAttemprs = 0;
        this.maxAttemps = maxAttemps;
        this.failAtteempts = 0;
        this.history = new ArrayList<>();
    }

    public Integer getMaxAttemps() {
        return maxAttemps;
    }

    public String getName() {
        return name;
    }

    public String getPhrase() {
        return phrase;
    }

    public Character[] getPhraseState() {
        return phraseState;
    }

    public Integer getSuccesAttemprs() {
        return succesAttemprs;
    }

    public Integer getFailAtteempts() {
        return failAtteempts;
    }

    public List<Character> getHistory() {
        return history;
    }

    public void setPhraseState(Character[] phraseState) {
        this.phraseState = phraseState;
    }

    public void updateSuccesAttemprs() {
        this.succesAttemprs++;
    }

    public void updateFaildAttemps() {
        this.failAtteempts++;
    }

    public void updateHistory(Character characterToadd) {
        this.history.add(characterToadd);
    }

    public boolean isGameFinished() {

        if (failAtteempts >= maxAttemps){
            return true;
        }

        for (Character character : phraseState) {
            if (character == null){
                return false;
            }
        }
        return true;
    }

    public static class GameStatusHelper {
        public Character[] preparePhraseState(String phrase) {
            char[] charsFromPhrase = phrase.toCharArray();

            Character[] result = new Character[charsFromPhrase.length];

            for (int i = 0; i < charsFromPhrase.length; i++) {
                if (!Character.isLetter(charsFromPhrase[i])) {
                    result[i] = charsFromPhrase[i];
                }
            }

            return result;
        }
    }
}
