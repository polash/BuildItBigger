package com.sksanwar.builditbigger.libJokes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LibJokes {
    // Add Jokes to a List
    private static final String[] jokesList = {
            "Anton, do you think I’m a bad mother? My name is Paul.",
            "Can a kangaroo jump higher than a house? - Of course, a house doesn’t jump at all.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "What is the difference between a snowman and a snowwoman?-Snowballs."};

    private static final List<String> jokesArrayList = new ArrayList<>(Arrays.asList(jokesList));
    private static Random random = new Random();

    public String getJokes() {
        int randomInt = random.nextInt(jokesArrayList.size());
        return jokesArrayList.get(randomInt);
    }
}
