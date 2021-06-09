package ru.geekbrains.accoun;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Constants {
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;
    private EditText txtName;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = new Account();
        initViews();
    }

    private void initViews() {
        Button btnGreetings = findViewById(R.id.btnGreetings);
        txtName = findViewById(R.id.textName);
        TextView txtGreetings = findViewById(R.id.textHello);
        btnGreetings.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            String sayHello = getString(R.string.say_hello) + name;
            txtGreetings.setText(sayHello);
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            populateAccount();
            // Чтобы стартовать активити, надо подготовить интент
            // В данном случае это будет явный интент, поскольку здесь передаётся класс активити
            Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
            runSettings.putExtra(YOUR_ACCOUNT, account);
            // Метод стартует активити, указанную в интенте
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            account = data.getParcelableExtra(YOUR_ACCOUNT);
            populateView();
        }
    }

    private void populateView(){
        txtName.setText(account.getName());
    }

    private void populateAccount(){
        account.setName(txtName.getText().toString());
    }
}