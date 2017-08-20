package com.sksanwar.builditbigger.libJokes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LibJokes {
    // Add Jokes to a List
    private static final String[] jokesList = {
            "I find it ironic that the colors red, white, and blue stand for freedom until they are flashing behind you.",
            "Apparently I snore so loudly that it scares everyone in the car I'm driving.",
            "I asked God for a bike, but I know God doesn't work that way. So I stole a bike and asked for forgiveness.",
            "Artificial intelligence is no match for natural stupidity.",
            "If you're not supposed to eat at night, why is there a light bulb in the refrigerator?"};

    private static final List<String> jokesArrayList = new ArrayList<>(Arrays.asList(jokesList));
    private static Random random = new Random();

    public String getJokes() {
        int randomInt = random.nextInt(jokesArrayList.size());
        return jokesArrayList.get(randomInt);
    }
}
