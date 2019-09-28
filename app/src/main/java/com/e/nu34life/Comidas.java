package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import Adaptador.AdamptadorDias;

public class Comidas extends AppCompatActivity {

    private String dia;
    private ListView lvhorarioDias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);
        final String[] listahorario = {"Mañana","Media Mañana","Tarde","Media Tarde","Cena"};
        lvhorarioDias = (ListView) findViewById(R.id.lvhorariosDia);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listahorario);
        lvhorarioDias.setAdapter(arrayAdapter);

        final AdamptadorDias miadaptador = new AdamptadorDias(getApplicationContext(),listahorario);
        lvhorarioDias.setAdapter(miadaptador);
        dia = getIntent().getStringExtra("dia");

        lvhorarioDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Comidas.this,ComidaAComer.class);
                intent.putExtra("horario",listahorario[position]);
                intent.putExtra("dia",dia);
                startActivity(intent);
            }
        });
    }

}
