package com.example.mycal;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText usuario,clave;
    TextView lblregistrar;
    Button btnlogin;
    Connection con;

    public LoginActivity(){
        ConnectionBd instanceConnection= new ConnectionBd();
        con= instanceConnection.connect();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_main);
   usuario=(EditText) findViewById(R.id.login_username);
        clave=(EditText) findViewById(R.id.login_password);
        btnlogin=(Button) findViewById(R.id.login_button);
        lblregistrar=(TextView) findViewById(R.id.signupRedirectText);

btnlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       new LoginActivity.login().execute("");

    }
});
        lblregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg =new Intent(getApplicationContext(),Registro.class);
                startActivity(reg);

            }
        });


    }
    public class login extends AsyncTask <String,String,String>{
    String z=  null;
    Boolean exito= false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {

            if (con==null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "verifique su conexion", Toast.LENGTH_SHORT).show();
                    }
                });
                z= "En Conexion";

            } else {
                try {
                    String sql ="SELECT * FROM Usuarios WHERE usuario ='"+usuario.getText()+"' AND clave = '"+clave.getText()+"'";
                    Statement stm= con.createStatement();
                    ResultSet rs=stm.executeQuery(sql);

                    if (rs.next()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Acceso Exitoso", Toast.LENGTH_SHORT).show();
                                Intent menu =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(menu);
                            }
                        });

                        usuario.setText("");
                        clave.setText("");
                    }
 else {
     runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Error Usuario o Contraseña ", Toast.LENGTH_SHORT).show();
                        }
                    });
                        usuario.setText("");
                        clave.setText("");

                    }

                }catch (Exception e){
                    exito=false;
                    Log.e("ERROR DE CONEXION :",e.getMessage());

                }


            }




            return "z";
        }
    }
}