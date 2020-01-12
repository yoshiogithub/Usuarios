package com.isil.usuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isil.usuarios.personas;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MainActivity extends AppCompatActivity {
    EditText nombre,apellido,password,sexo,email,edad;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=findViewById(R.id.txtnombre);
        apellido=findViewById(R.id.txtapellido);
        edad=findViewById(R.id.txtedad);
        sexo=findViewById(R.id.txtsexo);
        email=findViewById(R.id.txtemail);
        password=findViewById(R.id.txtpass);
        salida=findViewById(R.id.txtsalida);


    }

    public void ingresar(View view) {
        if (nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() || edad.getText().toString().isEmpty() ||
            sexo.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Complete los Campos", Toast.LENGTH_SHORT).show();
        }else{
            int age = Integer.parseInt(edad.getText().toString());
            String pass = password.getText().toString();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference Usuario = database.getReference(FirebaseReferences.TUTORIAL_REFERENCE);
            personas personas = new personas(nombre.getText().toString(), apellido.getText().toString(),
            age, sexo.getText().toString(), email.getText().toString(), pass);
            Usuario.child(FirebaseReferences.PERSONAS_REFERENCE).push().setValue(personas);
            limpiar();
            Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
        }
    }

    void limpiar(){
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        sexo.setText("");
        email.setText("");
        password.setText("");
        salida.setText("");
    }

}
