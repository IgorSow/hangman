package com.sda.hangman.infrastructure.memory;

import com.sda.hangman.domain.port.PhraseRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InMemoryPhraserepository implements PhraseRepository {


    private List<String> phrase = Arrays.asList(
            "Anna ma kota",
            "Janek ma psa,",
            "Maciek ma papuge"
    );

    @Override
    public String getPhrase() {

        Random random = new Random();
        return  phrase.get(random.nextInt(phrase.size()));
    }
}
