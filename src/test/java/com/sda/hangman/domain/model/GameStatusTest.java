package com.sda.hangman.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import com.sda.hangman.domain.model.GameStatus.GameStatusHelper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class GameStatusTest {

    @Test
    public void preparePhraseState() {


    }

    @Test
    public void isFinished_should_return_true_when_failureAttempts_equals_maxAttempts() {

        //given
        GameStatus gameStatus = new GameStatus("JAREK", "ala ma kota", 1);
        //when
        gameStatus.updateSuccesAttemprs();

        //then
        Assert.assertTrue(gameStatus.isGameFinished());

    }

    @Test
    public void isFinished_should_return_false_when_maxAttempts_is_bigger_than_failureAttempts() {
        //given
        GameStatus gameStatus = new GameStatus("JAREK", "ala ma kota", 5);
        //when
        gameStatus.updateSuccesAttemprs();

        //then
        Assert.assertFalse(gameStatus.isGameFinished());
    }

    @Test
    public void isFinished_should_return_true_when_all_letters_are_guessed() {
        //given
        GameStatus gameStatus = new GameStatus("JAREK", "ala ma kota", 5);
        //when
        gameStatus.setPhraseState("ale ma kota".chars().mapToObj(
                c -> (char) c).toArray(Character[]::new));

        //then
        Assert.assertTrue(gameStatus.isGameFinished());

    }

    public static class GameStatusHelperTest {
        @Test
        public void should_return_array_with_null_and_special_signs() {

            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //then
            Character[] retu = gameStatusHelper.preparePhraseState("Ala ma-kota");

            //then
            Assert.assertEquals((Character) null, retu[0]);
            Assert.assertEquals((Character) null, retu[1]);
            Assert.assertEquals((Character) null, retu[2]);
            Assert.assertEquals((Character) ' ', retu[3]);
            Assert.assertEquals((Character) null, retu[4]);
            Assert.assertEquals((Character) null, retu[5]);
            Assert.assertEquals((Character) '-', retu[6]);
        }

        @Test
        public void should_return_array_with_null_values_for_one_word_phrase() {
            //given
            GameStatusHelper gameStatusHelper = new GameStatusHelper();

            //then
            Character[] retu = gameStatusHelper.preparePhraseState("ala");

            //then
            Assert.assertEquals(null, retu[0]);
            Assert.assertEquals(null, retu[1]);
            Assert.assertEquals(null, retu[2]);


            assertThat(retu).containsOnlyNulls();
        }
    }

}