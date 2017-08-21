package com.sksanwar.builditbigger.libJokes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LibJokes {
    // Add Jokes to a List
    private static final String[] jokesList = {
            "Joke1",
            "Joke2",
            "Joke3",
            "Joke4",
            "Joke5"};

    private static final List<String> jokesArrayList = new ArrayList<>(Arrays.asList(jokesList));
    private static Random random = new Random();

    public String getJokes() {
        int randomInt = random.nextInt(jokesArrayList.size());
        return jokesArrayList.get(randomInt);
    }
}
