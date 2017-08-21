package com.sksanwar.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by POLASH on 21-Aug-17.
 */

public class TestJokes extends AndroidTestCase {

    public void test() {
        new FetchJokes(new Listner() {
            @Override
            public void onJokeLoaded(String joke) {
                assertNotNull(joke);
                assertNotSame(joke, "");
            }
        }).execute();
    }
}
