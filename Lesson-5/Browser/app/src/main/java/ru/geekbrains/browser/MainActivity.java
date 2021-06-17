package ru.geekbrains.browser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText site = findViewById(R.id.editText);
        Button go = findViewById(R.id.button);
        go.setOnClickListener(v -> {
            String url = site.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            //browser.setType("image/jpeg");
            ActivityInfo activityInfo = browser.resolveActivityInfo(getPackageManager(), browser.getFlags());
            if (activityInfo != null) {
                startActivity(browser);
            }
        });
    }
}