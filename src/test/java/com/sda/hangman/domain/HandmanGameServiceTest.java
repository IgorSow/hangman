package com.sda.hangman.domain;

import com.sda.hangman.domain.model.GameStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HandmanGameServiceTest {

    private HandManGameService handmanGameService;

    @Before
    public void init() {
        this.handmanGameService = new HandManGameService();
    }

    @Test
    public void should_return_array_with_character_indexes() {
        //given


        //when

        List<Integer> result = handmanGameService.performanceCharacter('A', "Anna");

        //then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 3, result.get(1));
    }

    @Test
    public void should_return_array_with_empty_character() {
        //given


        //when

        List<Integer> result = handmanGameService.performanceCharacter('X', "Anna");

        //then
        Assert.assertEquals(0, result.size());


    }

    @Test
    public void should_return_array_with_space_in_looking_phrase_with_right_character() {
        //given


        //when

        List<Integer> result = handmanGameService.performanceCharacter('a', "Anna ma kota");

        //then

        Assert.assertEquals(4, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 3, result.get(1));
        Assert.assertEquals((Integer) 6, result.get(2));
        Assert.assertEquals((Integer) 11, result.get(3));

    }

    @Test
    public void should_return_array_with_space_in_looking_phrase_with_missing_character() {
        //given


        //when

        List<Integer> result = handmanGameService.performanceCharacter('c', "Anna ma kota");

        //then

        Assert.assertEquals(0, result.size());


    }




    @Test
    public void processNextLetter_should_update_characterState_when_threre_is_letter_in_phrase() {

        //given

        Character letter = 'a';
        Character[] expectedLetter = {'a', null, 'a'};

        GameStatus gameStatus = new GameStatus("Janek", "ala");
        //when

        handmanGameService.processNextLetter(letter, gameStatus);


        //then

       Assert.assertArrayEquals(expectedLetter, gameStatus.getPhraseState());
    }


    @Test
    public void processNextLetter_should_not_update_characterState_when_threre_is_no_letter_in_phrase() {
        //given

        Character letter = 'b';
        Character[] expectedLetter = {null, null, null};

        GameStatus gameStatus = new GameStatus("Janek", "Ala");
        //when

        handmanGameService.processNextLetter(letter, gameStatus);


        //then

        Assert.assertArrayEquals(expectedLetter, gameStatus.getPhraseState());
    }


    @Test
    public void processNextLetter_should_update_successAttepmpts_when_threre_is_letter_in_phrase() {
        //given

        Character letter = 'a';
        Integer expectedSuccesAttempts = 2;

        GameStatus gameStatus = new GameStatus("Janek", "ala");
        //when

        handmanGameService.processNextLetter(letter, gameStatus);


        //then

        Assert.assertEquals(expectedSuccesAttempts, gameStatus.getSuccesAttemprs());
    }



    @Test
    public void processNextLetter_should_update_failureAttepmpts_when_threre_is_no_letter_in_phrase() {

        //given

        Character letter = 'b';
        Integer expectedFailAttempts = 1;

        GameStatus gameStatus = new GameStatus("Janek", "Ala");
        //when

        handmanGameService.processNextLetter(letter, gameStatus);


        //then

        Assert.assertEquals(expectedFailAttempts, gameStatus.getFailAtteempts());
    }


    @Test
    public void processNextLetter_should_update_history_for_new_letter() {

        //given

        Character letter = 'a';


        GameStatus gameStatus = new GameStatus("Janek", "ala");
        //when

        handmanGameService.processNextLetter(letter, gameStatus);


        //then

        Assert.assertEquals(letter,gameStatus.getHistory().get(0));
    }


//    @Test
//    public void processNextLetter_should_not_update_history_for_existing_letter() {
//
//        //given
//        List<Character> history = new ArrayList<>();
//        history.add('a');
//
//        GameStatus gameStatus = GameStatus.builder()
//                .phrase("Ala ma kota")
//                .phraseState(new Character["Ala ma kota".length()])
//                .history(history)
//                .build();
//
//        //when
//
//        handmanGameService.processNextLetter('a',gameStatus);
//
//        //then
//        Assert.assertEquals(1,gameStatus.getHistory().size());
//    }
}

