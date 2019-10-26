package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarRecordatorio extends AppCompatActivity {
    EditText etTlo, etDes, etFec, etCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_recordatorio);

        etCod = findViewById(R.id.tvCodigo);
        etTlo = findViewById(R.id.tvTitulo);
        etDes = findViewById(R.id.tvDescripcion);
        etFec = findViewById(R.id.tvFecha);


    }


    public void Registrar(View view){
        dbHelper helper = new dbHelper(this,"recordatorios.sqlite",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String valor_titulo = etTlo.getText().toString();
        String valor_descripcion = etDes.getText().toString();
        String valor_fecha = etFec.getText().toString();

        if(!valor_titulo.isEmpty() && !valor_descripcion.isEmpty() && !valor_fecha.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("titulo", valor_titulo);
            registro.put("descripcion", valor_descripcion);
            registro.put("fecha", valor_fecha);
            db.insert("recordatorios", null, registro);

            Toast.makeText(getApplicationContext(), "Registro exitoso...", Toast.LENGTH_LONG).show();

            db.close();
            etTlo.setText("");
            etDes.setText("");
            etFec.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Se debe llenar todos los campos...", Toast.LENGTH_LONG).show();
        }
    }

    public void Modificar(View view){
        dbHelper helper = new dbHelper(this,"recordatorios.sqlite",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String valor_codigo = etCod.getText().toString();
        String valor_titulo = etTlo.getText().toString();
        String valor_descripcion = etDes.getText().toString();
        String valor_fecha = etFec.getText().toString();

        if(!valor_codigo.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", valor_codigo);
            registro.put("titulo", valor_titulo);
            registro.put("descripcion", valor_descripcion);
            registro.put("fecha", valor_fecha);
            int cant = db.update("recordatorios",registro, "codigo="+valor_codigo,null);
            db.close();

            if(cant == 1){
                Toast.makeText(getApplicationContext(), "Recordatorio modificado correctamente...", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "No existe el recordatorio...", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Se debe llenar todos los campos...", Toast.LENGTH_LONG).show();
        }

    }

    public void  Eliminar(View view){
        dbHelper helper = new dbHelper(this,"recordatorios.sqlite",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String valor_codigo = etCod.getText().toString();

        if(!valor_codigo.isEmpty()){
            int cant = db.delete("recordatorios","codigo="+valor_codigo,null);

            etCod.setText("");
            etTlo.setText("");
            etDes.setText("");
            etFec.setText("");
            etCod.requestFocus();
            db.close();

            if(cant == 1){
                Toast.makeText(getApplicationContext(), "Recordatorio eliminado correctamente...", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "No existe el recordatorio...", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Debes introducir el codigo del recordatorio...", Toast.LENGTH_LONG).show();
        }

    }
}
