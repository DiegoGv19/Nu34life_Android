package com.e.nu34life;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Adaptador.AdamptadorDias;


public class ComidasFragment extends Fragment {

    private View vista;
    private ListView lvlistaDias;
    private String Id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_comidas,null);
        final String[] listaDias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        Id = getActivity().getIntent().getStringExtra("Id");


        lvlistaDias =  (ListView) vista.findViewById(R.id.lvDiasSemana);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listaDias);
        lvlistaDias.setAdapter(arrayAdapter);

        final AdamptadorDias miadaptador = new AdamptadorDias(getActivity().getApplicationContext(),listaDias);
        lvlistaDias.setAdapter(miadaptador);

        lvlistaDias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PacienteDiasDietas.class);
                intent.putExtra("dia",listaDias[position]);
                intent.putExtra("Id",Id);

                startActivity(intent);
            }
        });


        return vista;

    }


}
