package ru.geekbrains.accoun;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Constants {
    private EditText editName;
    private EditText editSurname;
    private EditText editAge;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();

        Intent intentParams = this.getIntent();
        Bundle params = intentParams.getExtras();
        if (params != null) {
            Account account = params.getParcelable(YOUR_ACCOUNT);
            this.populateView(account);
        }

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            Account account = this.createAccount();
            intentResult.putExtra(YOUR_ACCOUNT, account);
            this.setResult(RESULT_OK, intentResult);
            // Метод finish() завершает активити
            this.finish();
        });
    }

    private Account createAccount() {
        Account account = new Account();
        account.setName(editName.getText().toString());
        account.setSurName(editSurname.getText().toString());
        account.setAge(Integer.parseInt(editAge.getText().toString()));
        account.setEmail(editEmail.getText().toString());
        return account;
    }

    private void populateView(Account account) {
        editName.setText(account.getName());
        editSurname.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(), "%d", account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private void initViews() {
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
    }
}