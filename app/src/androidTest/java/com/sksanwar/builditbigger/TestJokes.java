package com.sksanwar.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by sksho on 21-Aug-17.
 */

public class TestJokes extends AndroidTestCase {
    CountDownLatch signal = new CountDownLatch(1);
    String joke = new FetchJokes(new Listner() {
        @Override
        public void onJokeLoaded(String joke) {
            assertNotNull(joke);
            assertNotSame(joke, "");
            signal.countDown();
        }
    }).execute().get();
    private Listner listner;

    public TestJokes() throws ExecutionException, InterruptedException {
    }

    public void test() {
        new FetchJokes(new Listner() {
            @Override
            public void onJokeLoaded(String joke) {
                assertNotNull(joke);
                assertNotSame(joke, "");
                signal.countDown();
            }
        }).execute();
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
