package com.sda.hangman;

import com.sda.hangman.domain.HandManGameService;
import com.sda.hangman.domain.model.GameStatus;
import com.sda.hangman.domain.port.PhraseRepository;
import com.sda.hangman.infrastructure.memory.InMemoryPhraserepository;

import java.util.Scanner;

public class Application {

    private static Scanner scanner;
    private static PhraseRepository phraseRepository;
    private static HandManGameService handmanGameService;

    public static void main(String[] args) {
        handmanGameService = new HandManGameService();
        phraseRepository = new InMemoryPhraserepository();
        scanner = new Scanner(System.in);

        boolean menuFlag = true;

        do {
            System.out.println("1. Start");
            System.out.println("2. Wyniki");
            System.out.println("   Koniec");

            int decision = scanner.nextInt();

            switch (decision) {
                case 1:
                    System.out.println("Tutaj bedzie logika do start");
                    startGame();
                case 2:
                    System.out.println("Tutaj bedzie logika do wynikow");
                default:
                    System.out.println("KONIEC");
                    menuFlag = false;
            }

        } while (menuFlag);
    }

    public static void startGame(){
        System.out.println("Podaj imie");
        String name = scanner.nextLine();

        System.out.println("Kliknij enter zeby zaczac gre");
        String phrase =phraseRepository.getPhrase();
        GameStatus gameStatus =handmanGameService.createGameStatus(name,phrase);

        while (!gameStatus.isGameFinished()){
            System.out.println("Tutaj bedzie fraza, pozostało 5 prób");
            System.out.println("Podaj kolejny :");
            char letter = scanner.nextLine().charAt(0);

            handmanGameService.processNextLetter(letter,gameStatus);
        }

    }
}