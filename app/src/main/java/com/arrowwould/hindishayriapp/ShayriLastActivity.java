package com.arrowwould.hindishayriapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ShayriLastActivity extends AppCompatActivity {

    int pos;
    String[] shayri;
    TextView textView;
    Button next, previous, share, copy;
    AlertDialog.Builder builder;
    Animation animation;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayri_last);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation2);
        textView = findViewById(R.id.shayri_last_view);
        builder = new AlertDialog.Builder(this);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        share = findViewById(R.id.share);
        copy = findViewById(R.id.copy);

        pos = getIntent().getIntExtra("position", 0);
        shayri = getIntent().getStringArrayExtra("shayri");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        textView.setText("" + shayri[pos]);
        textView.setAnimation(animation);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos--;
                try {
                    textView.setAnimation(animation);
                    textView.setText("" + shayri[pos]);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos++;
                try {
                    textView.setAnimation(animation);
                    textView.setText("" + shayri[pos]);

                } catch (Exception e) {
                }
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = textView.getText().toString();
                ClipboardManager clipbord = (ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("test", ss);
                clipbord.setPrimaryClip(clip);
                showtoast();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = textView.getText().toString();
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\n" + ss + "\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.arrowwould.hindishayriapp" + "\n\n" + "download this ";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));

                } catch (Exception e) {

                }

            }
        });


    }

    void showtoast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
