package com.sksanwar.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sksho.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by POLASH on 21-Aug-17.
 */

public class FetchJokes extends AsyncTask<Void, Void, String> {

    private Listner mListner;

    //default constructor
    public FetchJokes(Listner listner) {
        this.mListner = listner;
    }

    /**
     * Async task
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(Void... params) {
        JokeApi myApiService = null;

        if (myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            // end options for devappserver
            myApiService = builder.build();
        }
        try {
            return myApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        mListner.onJokeLoaded(joke);
    }
}
