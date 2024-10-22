package com.example.mycal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycal.conexion.ConnectionBd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;


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


//conection base de datos
    private EditText idEquipo;
    private EditText Fecha;
    private Button Enviar;    Connection con;
    public MainActivity(){
        ConnectionBd instanceConnection = new ConnectionBd();
        con = instanceConnection.connect();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //boton de enviar datos sql server
        idEquipo=findViewById(R.id.editTextEquipo);
        Fecha=findViewById(R.id.editTFecha);
        Enviar=findViewById(R.id.btn_enviar_datos);

Enviar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        insertar();
    }
});


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
        Button btnLimpiar = findViewById(R.id.btnLimp);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarResultados(); // Llama al método para limpiar los TextView
            }
        });


    }

    private void limpiarResultados() {
        if (tvResultadoCombinado != null) {
            tvResultadoCombinado.setText(""); // Limpia el TextView
        }
        if (tvResultadoPorcentaje != null) {
            tvResultadoPorcentaje.setText(""); // Limpia el TextView
        }
        if (tvResultadoSuma != null) {
            tvResultadoSuma.setText(""); // Limpia el TextView
        }

        if (tvResultadoSuma2 != null) {
            tvResultadoSuma2.setText(""); // Limpia el TextView
        }

        if (tvResultadoSuma3 != null) {
            tvResultadoSuma3.setText(""); // Limpia el TextView
        }
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

    }

    public void insertar() {
        try {
            // Verificar la conexión a la base de datos
            if (con == null) {
                Toast.makeText(MainActivity.this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar campos vacíos
            if (idEquipo.getText().toString().isEmpty() || Fecha.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtener la fecha ingresada y formatearla
            String fechaInput = Fecha.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFormateada = sdf.parse(fechaInput); // Convertir la cadena a Date
            java.sql.Date fechaSql = new java.sql.Date(fechaFormateada.getTime()); // Convertir a java.sql.Date

            // Preparar la sentencia SQL
            PreparedStatement stm = con.prepareStatement("INSERT INTO Equipo  VALUES (?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, idEquipo.getText().toString());
            stm.setDate(2, fechaSql); // Enviar el objeto java.sql.Date
            stm.setString(3, String.valueOf(resultadoSuma));
            stm.setString(4, String.valueOf(resultadoSuma2));
            stm.setString(5, String.valueOf(resultadoSuma3));

            String tvResultadoCombinadoValue = tvResultadoCombinado.getText().toString();
            if (tvResultadoCombinadoValue.isEmpty()) {
                stm.setNull(6, java.sql.Types.VARCHAR); // O el tipo correspondiente
            } else {
                stm.setString(6, tvResultadoCombinadoValue);
            }
            String tvResultado = tvResultadoPorcentaje.getText().toString();
            if (tvResultado.isEmpty()) {
                stm.setNull(7, java.sql.Types.VARCHAR); // O el tipo correspondiente
            } else {
                stm.setString(7, tvResultado);
            }

            // Ejecutar la actualización
            stm.executeUpdate();
            Toast.makeText(MainActivity.this, "REGISTRO AGREGADO Correctamente", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            idEquipo.setText("");
            Fecha.setText("");



        } catch (Exception e) {
            Log.e("Error de conexion", e.getMessage());
            Toast.makeText(MainActivity.this, "Error al insertar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    }



