package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Adaptador.AdamptadorDias;

public class ListaDiasAsignarComida extends AppCompatActivity {
    private String Correo;
    private String Contraseña;
    final String[] listaDias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};

    private ListView ltvDias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dias_asignar_comida);

        Correo = getIntent().getStringExtra("Correo");
        Contraseña = getIntent().getStringExtra("Contraseña");

        ltvDias = (ListView) findViewById(R.id.ltvDias);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListaDiasAsignarComida.this,android.R.layout.simple_list_item_1,listaDias);
        ltvDias.setAdapter(arrayAdapter);

        final AdamptadorDias miadaptador = new AdamptadorDias(ListaDiasAsignarComida.this,listaDias);
        ltvDias.setAdapter(miadaptador);

        ltvDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaDiasAsignarComida.this, LitaDiasHorarioAsiganarComida.class);
                intent.putExtra("dia", listaDias[position]);
                intent.putExtra("Correo",Correo);
                intent.putExtra("Contraseña",Contraseña);
                startActivity(intent);

            }
        });


    }
}
