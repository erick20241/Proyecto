package com.example.mycal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultadoCombinado;
    private TextView tvResultadoPorcentaje;
    //conjunto para suma de peso
    private EditText numSuma;
    private Button btnSumar, btnCalcularSuma;
    private TextView tvResultadoSuma;
    private double resultadoSuma = 0;

    //conjunto para suma de Modulo
    private EditText numSuma2;
    private Button btnSumar2, btnCalcularSuma2;
    private TextView tvResultadoSuma2;
    private double resultadoSuma2 = 0;

    //conjunto para suma de Control
    private EditText numSuma3;
    private Button btnSumar3, btnCalcularSuma3;
    private TextView tvResultadoSuma3;
    private double resultadoSuma3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Conjunto 1: Suma peso
        numSuma = findViewById(R.id.edit_peso);
        btnSumar = findViewById(R.id.button_peso);
        btnCalcularSuma = findViewById(R.id.Calcular_peso);
        tvResultadoSuma = findViewById(R.id.result_p);
        tvResultadoCombinado = findViewById(R.id.resultado_combinado);
        tvResultadoPorcentaje = findViewById(R.id.resultado_porcentaje);


        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numValue = Double.parseDouble(numSuma.getText().toString());
                resultadoSuma += numValue;
                numSuma.setText("");
            }
        });

        btnCalcularSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultadoSuma.setText("Resultado: " + resultadoSuma);
                actualizarResultadoCombinado();
            }
        });
        // Conjunto 2: Suma Modulo
        numSuma2 = findViewById(R.id.edit_Modulo);
        btnSumar2 = findViewById(R.id.sumar_modulo);
        btnCalcularSuma2 = findViewById(R.id.calcular_modulo);
        tvResultadoSuma2 = findViewById(R.id.resultado_modulo);

        btnSumar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numValue = Double.parseDouble(numSuma2.getText().toString());
                resultadoSuma2 += numValue;
                numSuma2.setText("");
            }
        });

        btnCalcularSuma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultadoSuma2.setText("Resultado: " + resultadoSuma2);
                actualizarResultadoCombinado();
            }
        });
        // Conjunto 2: Suma Control
        numSuma3 = findViewById(R.id.edit_control);
        btnSumar3 = findViewById(R.id.sumar_control);
        btnCalcularSuma3 = findViewById(R.id.calcular_control);
        tvResultadoSuma3 = findViewById(R.id.resultado_control);



        btnSumar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numValue = Double.parseDouble(numSuma3.getText().toString());
                resultadoSuma3 += numValue;
                numSuma3.setText("");
            }
        });

        btnCalcularSuma3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResultadoSuma3.setText("Resultado: " + resultadoSuma3);
                actualizarResultadoCombinado();
            }
        });


    }

    private void actualizarResultadoCombinado() {
        double peso = resultadoSuma;
        double modulo = resultadoSuma2;
        double control = resultadoSuma3;

        if (peso == 0) {
            tvResultadoCombinado.setText("Error: No se puede realizar la división por cero");
            tvResultadoPorcentaje.setText("Error: No se puede realizar la división por cero");
        } else {
            double resultadoCombinado = peso - modulo / (peso * 100);
            tvResultadoCombinado.setText(String.format("Resultado: %.2f", resultadoCombinado));

            // Cálculo para peso-control/peso*100
            double resultadoPorcentaje = peso - control / (peso * 100);
            tvResultadoPorcentaje.setText(String.format("Resultado: %.2f", resultadoPorcentaje));
    }

    }   }
