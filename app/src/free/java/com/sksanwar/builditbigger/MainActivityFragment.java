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

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
//                Log.v(TAG,getResources().getString(R.string.tag_closeAd));
                requestNewInterstitial();
                retrieveJoke();
            }
        });

        Button button = (Button) rootView.findViewById(R.id.show_joke_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
//                    Log.v(TAG,getResources().getString(R.string.tag_showAd));
                    mInterstitialAd.show();
                } else {
//                    Log.v(TAG,getResources().getString(R.string.tag_notFinishedLoading));
                    retrieveJoke();
                }
            }
        });
        return rootView;
    }

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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
