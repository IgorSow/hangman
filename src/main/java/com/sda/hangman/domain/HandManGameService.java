package com.sda.hangman.domain;

import com.sda.hangman.domain.model.GameStatus;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HandManGameService {

    public List<Integer> performanceCharacter(char characterToFind, String phrase) {
        char[] charsFromPhrase = phrase.toCharArray();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < charsFromPhrase.length; i++) {
            if (equalseIgnoreCase(charsFromPhrase[i], characterToFind)) {
                result.add(i);
            }
        }
        return result;
    }

    public GameStatus createGameStatus(String name, String phrase) {
        GameStatus gameStatus = new GameStatus(name, phrase);
        return gameStatus;
    }

    private boolean equalseIgnoreCase(char a, char b) {

        return Character.toLowerCase(a) == Character.toLowerCase(b);

    }

    public void processNextLetter(char letter, GameStatus gameStatus) {

        if (!gameStatus.getHistory().contains(letter)) {

            List<Integer> lisfOfLetters = performanceCharacter(letter, gameStatus.getPhrase());
            performCounterIncrement(!lisfOfLetters.isEmpty(),gameStatus );

            Character[] phraseState = gameStatus.getPhraseState();

            lisfOfLetters.forEach(index -> {
                phraseState[index] = gameStatus.getPhrase().charAt(index);

            });
            gameStatus.updateHistory(letter);
            gameStatus.updateSuccesAttemprs();
        }else {
            gameStatus.updateFaildAttemps();
            System.out.println("Litera juz byla");
        }
    }

    private void performCounterIncrement(boolean succes, GameStatus gameStatus){
        if (succes){
            gameStatus.updateSuccesAttemprs();

        }else {
            gameStatus.updateFaildAttemps();
        }
    }


}
