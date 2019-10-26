package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class Recordatorios extends AppCompatActivity {
    ListView LTodo;
    Button  Agregar;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);
        Agregar = (Button) findViewById(R.id.btnRegistrar);

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarRecordatorio.class);
                startActivity(intent);
            }
        });
    }

    public List<String> Listado(){
        dbHelper helper = new dbHelper(this,"recordatorios.sqlite",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = null;
        List<String> lista = new ArrayList();
        String sql = "select * from recordatorios";
        cursor = db.rawQuery(sql, null);
        String cad = "";
        while(cursor.moveToNext()){
            cad=cursor.getInt(0)+ ".- " + cursor.getString(1) +
                    "\n descripcion: " + cursor.getString(2)+"\n fecha: " + cursor.getString(3) +
                    "\n==================\n";
            lista.add(cad);
        }
        return lista;
    }

    public void Lista(View v){
        ListView LTodo = findViewById(R.id.ListAll);
        ArrayAdapter<String> dp = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,Listado());
        LTodo.setAdapter(dp);
    }
}
