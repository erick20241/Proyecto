package com.example.mycal;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.mycal.conexion.ConnectionBd;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Registro extends AppCompatActivity {
    EditText nomapellidos,email,telefono,usuario1,clave1;
    Button registrar;
    TextView ingresar;

Connection con;


public Registro(){
    ConnectionBd instanceConnection = new ConnectionBd();
    con = instanceConnection.connect();
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        nomapellidos=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_email);
        telefono=findViewById(R.id.telefono);
        usuario1=findViewById(R.id.signup_username);
        clave1=findViewById(R.id.signup_password);
        ingresar=findViewById(R.id.loginRedirectText);
        registrar=findViewById(R.id.signup_button);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarUsuario();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingresar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(ingresar);
            }
        });



    }
    public void RegistrarUsuario(){
    try {
        if (con==null){
            Toast.makeText(Registro.this, "Verifique su conexion", Toast.LENGTH_SHORT).show();
            return;

        }
        else {
            PreparedStatement stm=con.prepareStatement( "INSERT INTO Usuarios VALUES(?,?,?,?,?)");
          stm.setString(1,nomapellidos.getText().toString());
          stm.setString(2,email.getText().toString());
          stm.setString(3,telefono.getText().toString());
          stm.setString(4,usuario1.getText().toString());
          stm.setString(5,clave1.getText().toString());


         stm.executeUpdate();
            Toast.makeText(Registro.this, "REGISTRO AGREGADO Correctamente", Toast.LENGTH_SHORT).show();

            nomapellidos.setText("");
            email.setText("");
            telefono.setText("");
            usuario1.setText("");
            clave1.setText("");


      }
    }catch (Exception e){
        Log.e("Error de conexion",e.getMessage());


        }
    }
    }



















