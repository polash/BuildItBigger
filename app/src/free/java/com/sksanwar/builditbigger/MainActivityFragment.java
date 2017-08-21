package com.sksanwar.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.sksanwar.app.displayjokeslibrary.DisplayJokesActivity;

public class MainActivityFragment extends Fragment {

    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_fragment, container, false);

        //Adview declaration
        AdView mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        //InterstitialAd declaration
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                retrieveJoke();
            }
        });

        Button button = rootView.findViewById(R.id.show_joke_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    retrieveJoke();
                }
            }
        });
        return rootView;
    }

    //method for retrieving jokes
    private void retrieveJoke(){
        new FetchJokes(new Listner() {
            @Override
            public void onJokeLoaded(String joke) {
                Intent intent = new Intent(getActivity(), DisplayJokesActivity.class);
                intent.putExtra(DisplayJokesActivity.JOKE_EXTRA, joke);
                startActivity(intent);
            }
        }).execute();
    }

    //method for request new InterstitialAd
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}
