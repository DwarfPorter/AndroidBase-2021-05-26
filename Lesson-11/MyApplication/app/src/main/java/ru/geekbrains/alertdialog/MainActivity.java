package ru.geekbrains.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alert1 = findViewById(R.id.alertDialog1);
        alert1.setOnClickListener(clickAlertDialog1);
        Button alert3 = findViewById(R.id.alertDialog3);
        alert3.setOnClickListener(clickAlertDialog3);
        Button alertlist = findViewById(R.id.alertDialogList);
        alertlist.setOnClickListener(clickAlertDialogList);
        Button alertSingleList = findViewById(R.id.alertDialogListSingle);
        alertSingleList.setOnClickListener(clickAlertDialogListSingle);
        Button alertMultiList = findViewById(R.id.alertDialogListMulti);
        alertMultiList.setOnClickListener(clickAlertDialogListMulti);
        Button alertCustom = findViewById(R.id.alertDialogCustom);
        alertCustom.setOnClickListener(clickAlertDialogCustom);
    }

    private View.OnClickListener clickAlertDialogCustom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final View contentView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.exclamation)
                    .setView(contentView)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText editText = contentView.findViewById(R.id.editText);
                            String answer = editText.getText().toString();
                            Toast.makeText(MainActivity.this, answer, Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };

    private View.OnClickListener clickAlertDialogListMulti = view -> {
        final String[] items = getResources().getStringArray(R.array.choose);
        final boolean[] chosen = {false, true, false};
        // Создаём билдер и передаём контекст приложения
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // В билдере указываем заголовок окна (можно указывать как ресурс,
        // так и строку)
        builder.setTitle(R.string.exclamation)
                // Добавляем список элементов; булев chosen — массив
                // с выбранными элементами
                .setMultiChoiceItems(items, chosen, (dialogInterface, i, b) -> {
                    chosen[i] = b; // При переключении обновляем ячейку
                    // в массиве
                })
                .setNegativeButton("Отмена", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show())
                .setPositiveButton("Ок", (dialogInterface, i) -> {
                    // Собираем выбранные элементы в строку
                    StringBuilder sb = new StringBuilder("Ок, выбрано: ");
                    for (int index = 0; index < chosen.length; index++) {
                        if (chosen[index]) {
                            sb.append(items[index]);
                            sb.append("; ");
                        }
                    }
                    Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                });
        AlertDialog alert = builder.create();
        alert.show();
    };

    private int chosen = -1;    // Здесь будет храниться выбранный пункт
    private View.OnClickListener clickAlertDialogListSingle = view -> {
        final String[] items = getResources().getStringArray(R.array.choose);
        // Создаём билдер и передаём контекст приложения
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // В билдере указываем заголовок окна. Можно указывать как ресурс,
        //так и строку
        builder.setTitle(R.string.exclamation)
                // Добавляем список элементов; chosen — выбранный элемент,
                // если = -1, то ни один не выбран
                .setSingleChoiceItems(items, chosen, (dialogInterface, item) -> {
                    chosen = item; // Обновляем выбранный элемент
                })
                .setNegativeButton("Отмена", (dialogInterface, i) -> Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show())
                .setPositiveButton("Ок", (dialogInterface, i) -> {
                    if (chosen == -1) {
                        Toast.makeText(MainActivity.this, "Ок, пункт не выбран!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, String.format("Ок, выбран '%s'!", items[chosen]), Toast.LENGTH_SHORT).show();
                });
        AlertDialog alert = builder.create();
        alert.show();
    };

    private View.OnClickListener clickAlertDialogList = view -> {
        String[] items = getResources().getStringArray(R.array.choose);
        // Создаём билдер и передаём контекст приложения
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // В билдере указываем заголовок окна. Можно указывать как ресурс,
        // так и строку)
        builder.setTitle(R.string.exclamation)
                // Добавим список элементов
                .setItems(items, (dialogInterface, item) -> {
                    Toast.makeText(MainActivity.this, String.format("Выбран пункт %d", item + 1), Toast.LENGTH_SHORT).show();
                });
        AlertDialog alert = builder.create();
        alert.show();
    };


    private View.OnClickListener clickAlertDialog1 = view -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.exclamation);
        builder.setMessage(R.string.press_button)
                .setIcon(R.mipmap.ic_launcher_round)
                .setCancelable(false)
                .setPositiveButton(R.string.button, (dialog, which) -> Toast.makeText(MainActivity.this, "Кнопка нажата", Toast.LENGTH_SHORT).show());
        AlertDialog alert = builder.create();
        alert.show();
        Toast.makeText(MainActivity.this, "Диалог открыт", Toast.LENGTH_SHORT).show();
    };

    private View.OnClickListener clickAlertDialog3 = view -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.exclamation)
                .setMessage("2 + 2 = 4 ?")
                .setCancelable(false)
                .setNegativeButton(R.string.no, (d, w) -> {
                    Toast.makeText(MainActivity.this, "Нет!", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton(R.string.dunno, (d, w) -> {
                    Toast.makeText(MainActivity.this, "Не знаю!", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton(R.string.yes, (dialog, which) -> Toast.makeText(MainActivity.this, "Да!", Toast.LENGTH_SHORT).show());
        AlertDialog alert = builder.create();
        alert.show();
    };
}