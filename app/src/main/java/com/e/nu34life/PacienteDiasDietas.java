package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Adaptador.AdamptadorDias;

public class PacienteDiasDietas extends AppCompatActivity {
    private String dia;
    private ListView lvhorarioDias;
    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_dias_dietas);
        final String[] listahorario = {"Mañana","MedioDia","Tarde","Lonche","Cena"};
        lvhorarioDias = (ListView) findViewById(R.id.lvhorariosDiaPaciente);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listahorario);
        lvhorarioDias.setAdapter(arrayAdapter);

        final AdamptadorDias miadaptador = new AdamptadorDias(getApplicationContext(),listahorario);
        lvhorarioDias.setAdapter(miadaptador);
        dia = getIntent().getStringExtra("dia");
        Id = getIntent().getStringExtra("Id");

        lvhorarioDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PacienteDiasDietas.this,PacienteDietaDeDiaTurno.class);
                intent.putExtra("horario",listahorario[position]);
                intent.putExtra("dia",dia);
                intent.putExtra("Id",Id);

                startActivity(intent);
            }
        });
    }
}
