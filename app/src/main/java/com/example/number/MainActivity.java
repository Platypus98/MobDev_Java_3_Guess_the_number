package com.example.number;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    Button bReset;
    RadioGroup rgComplication;
    int CountOfAttempts;


    Random rand = new Random();
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);
        bReset = findViewById(R.id.button_reset);
        rgComplication = findViewById(R.id.radio);

        bReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedComplication = rgComplication.indexOfChild(findViewById(rgComplication.getCheckedRadioButtonId()));

                if (selectedComplication == 1){
                    CountOfAttempts = 100;
                } else if (selectedComplication == 2){
                    CountOfAttempts = 8;
                } else if (selectedComplication == 3){
                    CountOfAttempts = 4;
                } else {
                    tvInfo.setText("Выберите сложность");
                    return;
                }

                bReset.setVisibility(View.INVISIBLE);
                rgComplication.setVisibility(View.INVISIBLE);
                tvInfo.setText("Попробуйте угадать число (1 – 100)");
                number = rand.nextInt(100) + 1;
            }
        });
    }

    public void onClick(View view) {

        int value;

        try {
            value = Integer.parseInt(etInput.getText().toString());
        } catch (Throwable b) {
            tvInfo.setText("Введите корректное значение!");
            return;
        }


        if (number == 0){
            tvInfo.setText("Начните игру!");
        } else if (value < 1 || value > 100) {
            tvInfo.setText("Введенное число не входит в диапазон. \n Повторите ввод!");
        } else if (value == number) {
            tvInfo.setText("Верно!");
            number = 0;
            bReset.setVisibility(View.VISIBLE);
            bReset.setText("Сыграть еще!");
            rgComplication.setVisibility(View.VISIBLE);
        } else if (value > number) {
            CountOfAttempts --;

            if (CountOfAttempts == 0){
                tvInfo.setText("Попытки закончились. Вы проиграли");
                bReset.setVisibility(View.VISIBLE);
                rgComplication.setVisibility(View.VISIBLE);
                number = 0;
            } else{
                tvInfo.setText("Перелет!");
            }

        } else if (value < number) {
            CountOfAttempts --;

            if (CountOfAttempts == 0){
                tvInfo.setText("Попытки закончились. Вы проиграли");
                bReset.setVisibility(View.VISIBLE);
                rgComplication.setVisibility(View.VISIBLE);
                number = 0;
            } else{
                tvInfo.setText("Недолет!");
            }

        }
    }
}
