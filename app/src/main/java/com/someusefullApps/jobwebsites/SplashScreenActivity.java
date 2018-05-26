package com.someusefullApps.jobwebsites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashScreenActivity extends Activity {

    static InterstitialAd interstitialAd;
    int SPLASH_SCREEN_TIMEOUT = 6000;
    Runnable runnable;
    Handler handler;

    //-----------------------------------------
    static void showInterstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }

    //--------------------------------
    static void loadInterstitial() {
        // request interstitial ad.
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    //----------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //---------------------------------------------------
        interstitialAd = newInterstitialAd();
        loadInterstitial();
        //----------------------------------------------------
        runnable = new Runnable() {
            @Override
            public void run() {
                if (CustomApplication.isActivityVisible()) {
                    Intent i = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    startActivity(i);
                }
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_SCREEN_TIMEOUT);
        //----------------------------------------------------------

    } //onCreate() closed

    // -------------------------------------------
    private InterstitialAd newInterstitialAd() {
        // Testing add unit  ca-app-pub-3940256099942544/1033173712

        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (CustomApplication.isActivityVisible()) {
                    overridePendingTransition(R.anim.slide_in_from_the_right,
                            R.anim.slide_out_to_the_right);
                    handler.removeCallbacks(runnable);
                    Intent intent = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    showInterstitial();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (CustomApplication.isActivityVisible()) {
                    overridePendingTransition(R.anim.slide_in_from_the_right,
                            R.anim.slide_out_to_the_right);
                    handler.removeCallbacks(runnable);
                    Intent intent = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onAdClosed() {
                Log.i("flow", "onAdClosed: ");
            }
            //--------------------
        });

        return interstitialAd;
    }
    //-------------------------

    @Override
    protected void onResume() {
        super.onResume();
        CustomApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        CustomApplication.activityPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}