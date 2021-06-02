package ru.geekbrains.activitystate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String COUNTERS = "Counters";

    private Counters counters;

    private TextView textCounter1;
    private TextView textCounter2;
    private TextView textCounter3;
    private TextView textCounter4;

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counters.incrementCounter2();
            setTextCounter(textCounter2, counters.getCounter2());
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(COUNTERS, counters);
    }

    @Override
    protected void onRestoreInstanceState(Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        counters = (Counters) instanceState.getParcelable(COUNTERS);
        setTextCounters();
    }

    private void setTextCounters(){
        setTextCounter(textCounter1, counters.getCounter1());
        setTextCounter(textCounter2, counters.getCounter2());
        setTextCounter(textCounter3, counters.getCounter3());
        setTextCounter(textCounter4, counters.getCounter4());
    }

    private void setTextCounter(TextView textView, int counter){
        textView.setText(String.format(Locale.getDefault(), "%d", counter));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counters = new Counters();
        initTextView();
        initButton();
    }

    private void initButton() {
        ((Button)findViewById(R.id.button2)).setOnClickListener(listener);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    private void initTextView() {
        textCounter1 = findViewById(R.id.textView1);
        textCounter2 = findViewById(R.id.textView2);
        textCounter3 = findViewById(R.id.textView3);
        textCounter4 = findViewById(R.id.textView4);
    }

    public void button1_onClick(View view) {
        counters.incrementCounter1();
        setTextCounter(textCounter1, counters.getCounter1());
    }

    // Не очень хорошее решение, приходится сюда забегать и менять код, каждый раз при добавлении обраьотки кнопки
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            counters.incrementCounter3();
            setTextCounter(textCounter3, counters.getCounter3());
        }
        if (v.getId() == R.id.button4) {
            counters.incrementCounter4();
            setTextCounter(textCounter4, counters.getCounter4());
        }
    }
}