package ru.geekbrains.resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        AppCompatImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "android.png");
        initList();
    }

    private void initViews() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/19659.ttf");
        TextView descriptionLanguage = findViewById(R.id.textVLang);
        descriptionLanguage.setTypeface(tf);
        descriptionLanguage.setText(getString(R.string.descriptionLanguage));
        TextView textLanguage = findViewById(R.id.textLang);
        textLanguage.setText(getApplicationContext().getString(R.string.language));
    }

    private void loadImageFromAsset(AppCompatImageView image, String fileName) {
        try {
            InputStream inputStream = getAssets().open(fileName);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            image.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initList(){
        LinearLayout layoutList = findViewById(R.id.layoutList);
        String[] versions = getResources().getStringArray(R.array.version_names);

        LayoutInflater ltInflater = getLayoutInflater();

        for(int i = 0; i < versions.length; i++){
            View item = ltInflater.inflate(R.layout.android_item, layoutList, false);
            TextView textAndroid = item.findViewById(R.id.textAndroid);
            AppCompatImageView imgLogo = item.findViewById(R.id.imageAndroid);

            String version = versions[i];
            textAndroid.setText(version);

            TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);
            int img = imgs.getResourceId(i, -1);
            imgLogo.setImageResource(img);

            layoutList.addView(item);
        }
    }
}