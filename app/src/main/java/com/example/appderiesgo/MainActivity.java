package com.example.appderiesgo;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //Variables Para los Botones
    Button Guardar, Consultar, Actualizar, Borrar;
    //Variables para los contenedores de texto
    EditText Nombre,Documento,Contraseña,Email;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación de variables con el activity
        Documento= findViewById(R.id.txtDoc);
        Nombre = findViewById(R.id.txtName);
        Contraseña = findViewById(R.id.txtPassword);
        Email = findViewById(R.id.txtEmail);

        //Relación de botones con el activity
        Guardar= findViewById(R.id.btnGuardar);
        Consultar= findViewById(R.id.btnConsultar);
        Actualizar=findViewById(R.id.btnActualizar);
        Borrar=findViewById(R.id.btnBorrar);


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "Login_Usuario", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();


                if (Documento.getText().toString().equals("")) {
                    Documento.setError("Ingrese documento");
                    return;
                }
                if (Nombre.getText().toString().equals("")) {
                    Nombre.setError("Ingrese nombre");
                    return;
                }
                if (Contraseña.getText().toString().equals("")) {
                    Contraseña.setError("Ingrese una contraseña");
                    return;
                }
                if (Email.getText().toString().equals("")) {
                    Email.setError("Ingrese su email");
                    return;
                }


                int Doc = Integer.parseInt(Documento.getText().toString());
                String Nom = Nombre.getText().toString();
                String Pass = Contraseña.getText().toString();
                String Mail = Email.getText().toString();

                //Contenedor de valores
                ContentValues datos = new ContentValues();
                datos.put("Documento", Doc);
                datos.put("Nombre", Nom );
                datos.put("Contraseña", Pass );
                datos.put("Email", Mail );

                try {
                    db.insert("Usuario", null, datos);
                    db.close();
                    Documento.setText("");
                    Nombre.setText("");
                    Contraseña .setText("");
                    Email.setText("");
                    Toast.makeText(MainActivity.this, "Usuario Creado exitosamente", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error creando el usuario", Toast.LENGTH_SHORT).show();
                }

            }

        });
        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "Login_Usuario", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                int Doc = Integer.parseInt(Documento.getText().toString());

                Cursor fila = db.rawQuery("select * from usuario where pascode=" + Doc, null);

                if (fila.moveToFirst()) {
                    Nombre.setText(fila.getString(1));
                    Contraseña.setText(fila.getString(2));

                } else {
                    Toast.makeText(MainActivity.this, "EL usuario no existe", Toast.LENGTH_SHORT).show();
                    db.close();


                }
            }
        });


        Borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "Login_Usuario", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                int Doc = Integer.parseInt(Documento.getText().toString());
                try{
                int c = db.delete("Usuario", "Documento " +Doc, null);
                if (c > 0) {
                    Toast.makeText(MainActivity.this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "EL usuario no existe", Toast.LENGTH_SHORT).show();
                }
                Documento.setText("");
                Nombre.setText("");
                Contraseña.setText("");
                Email.setText("");

                db.close();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "No existe usuario para elimiar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "Login_Usuario", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();


                int Doc = Integer.parseInt(Documento.getText().toString());
                String Nom = Nombre.getText().toString();
                String Pass = Contraseña.getText().toString();
                String Mail = Email.getText().toString();

                //Contenedor de valores
                ContentValues updateuser = new ContentValues();
                updateuser.put("Nombre", Nom );
                updateuser.put("Contraseña", Pass );
                updateuser.put("Email", Mail );

                try{
                    db.update("Usuario",updateuser,"Documento="+Doc,null);
                db.close();
    }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error en consulta", Toast.LENGTH_SHORT).show();              }
            } }); } }