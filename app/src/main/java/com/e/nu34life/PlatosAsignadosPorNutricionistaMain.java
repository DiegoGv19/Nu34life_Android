package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adaptador.AdaptadorComidas;
import Adaptador.AdaptadorPlan;
import Interface.IPlan;
import Interface.IRecipe;
import Model.ApiClient;
import Model.Plan;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatosAsignadosPorNutricionistaMain extends AppCompatActivity {
    private List<Plan> listPlan;
    private List<Recipe> listRecipe;
    private String dia,turno;
    private String Id;
    private ListView listViewComidas;
    private TextView tvdia, tvhorario;
    private IPlan iPlan;

    private Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos_asignados_por_nutricionista_main);

        iPlan = ApiClient.getRetrofit().create(IPlan.class);


        dia = getIntent().getStringExtra("dia");
        Id = getIntent().getStringExtra("Id");
        turno = getIntent().getStringExtra("horario");

        listViewComidas = (ListView) findViewById(R.id.ltvListaComidaSeleccionada);

        tvdia = (TextView) findViewById(R.id.tvDiaComida);
        tvhorario = (TextView) findViewById(R.id.tvHorarioComida);

        btnAgregar = (Button) findViewById(R.id.btnAgregarPlatoNuevo);

        tvdia.setText(dia);
        tvhorario.setText(turno);
        getPlan();
        listViewComidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plan plan = listPlan.get(position);
                Intent intent = new Intent(PlatosAsignadosPorNutricionistaMain.this, InformacionPlatoSeleccionadoNutricionista.class);
                intent.putExtra("dia", dia);
                intent.putExtra("turno", turno);
                intent.putExtra("Id",Id);
                intent.putExtra("PlatoId",plan.getRecipeId().toString());
                intent.putExtra("PlanId",plan.getId().toString());

                startActivity(intent);

            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosAsignadosPorNutricionistaMain.this, ComidaAComer.class);
                intent.putExtra("dia", dia);
                intent.putExtra("turno", turno);
                intent.putExtra("Id",Id);
                startActivity(intent);
            }
        });
    }

    private void getPlan(){

        Long id = Long.parseLong(Id);
        Call<List<Plan>> call = iPlan.getPlan(id,turno,dia);
        call.enqueue(new Callback<List<Plan>>() {
            @Override
            public void onResponse(Call<List<Plan>> call, Response<List<Plan>> response) {

                if(!response.isSuccessful()){

                    return;
                }

                List<Plan> listplan = response.body();
                listPlan = listplan;
                if (listplan != null) {
                    final AdaptadorPlan miadaptador = new AdaptadorPlan(getApplicationContext(), listplan);
                    listViewComidas.setAdapter(miadaptador);
                }


            }

            @Override
            public void onFailure(Call<List<Plan>> call, Throwable t) {

            }
        });
    }


}