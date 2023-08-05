package com.arrowwould.hindishayriapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import static android.content.ContentValues.TAG;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    String[] title;
    Animation ani;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        listView = findViewById(R.id.listview);
        title = getResources().getStringArray(R.array.title);
        Mainadapter adapter = new Mainadapter(SecondActivity.this, title);

        ani = AnimationUtils.loadAnimation(this, R.anim.animation1);

        listView.setAdapter(adapter);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        loadIn();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent secondIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                secondIntent.putExtra("title", title[i]);
                startActivity(secondIntent);

            }
        });

    }

    public void loadIn() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,  getResources().getString(R.string.in_ad_unit), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.

                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(SecondActivity.this);

                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;

            }
        });

    }
}
