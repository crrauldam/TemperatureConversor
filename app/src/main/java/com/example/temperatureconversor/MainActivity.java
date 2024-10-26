package com.example.temperatureconversor;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private boolean isCLastEdited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView title = findViewById(R.id.title);
        EditText cImput = findViewById(R.id.cImput);
        EditText fImput = findViewById(R.id.fImput);
        Button convert = findViewById(R.id.convert);


        // comprueba si el ultimo campo editado ha sido el de celsius
        cImput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isCLastEdited = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // comprueba si el ultimo campo editado ha sido el de fahrenheit
        fImput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isCLastEdited = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // al pulsar para convertir
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // comprueba si el imput esta vacio, de serlo entonces el valor es 0.0
                // de lo contrario es el valor que tenga el imput
                double c = 0d;
                double f = 0d;

                try {
                    if (isCLastEdited) {
                        if (!cImput.getText().toString().isEmpty()) {
                            c = Double.parseDouble(cImput.getText().toString());
                            f = (c * 9/5) + 32;
                        }
                    } else {
                        if (!fImput.getText().toString().isEmpty()) {
                            f = Double.parseDouble(fImput.getText().toString());
                            c = (f - 32) * 5/9;
                        }
                    }

                    cImput.setText(String.valueOf(c));
                    fImput.setText(String.valueOf(f));
                } catch (Exception e) {
                    if (isCLastEdited) {
                        cImput.setText("Número inválido");
                    } else {
                        fImput.setText("Número inválido");
                    }
                }
            }
        });

    }
}