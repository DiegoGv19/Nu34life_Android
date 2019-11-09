package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

public class PacienteDietaDeDiaTurno extends AppCompatActivity {
    private List<Recipe> listRecipe;
    private List<Plan> listPlan;

    private String dia,turno;
    private String Id;

    private ListView listViewComidas;
    private TextView tvdia, tvhorario;
    private IPlan iPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_dieta_de_dia_turno);
        iPlan = ApiClient.getRetrofit().create(IPlan.class);

        dia = getIntent().getStringExtra("dia");
      turno = getIntent().getStringExtra("horario");
        Id = getIntent().getStringExtra("Id");

        listViewComidas = (ListView) findViewById(R.id.listViewComidasPaciente);

        tvdia = (TextView) findViewById(R.id.tvDiaComidaPaciente);
        tvhorario = (TextView) findViewById(R.id.tvHorarioComidaPaciente);

        tvdia.setText(dia);
        tvhorario.setText(turno);
        getPlan();

        listViewComidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plan plan = listPlan.get(position);
                Intent intent = new Intent(PacienteDietaDeDiaTurno.this, InformacionPlatoPaciente.class);
                intent.putExtra("dia", dia);
                intent.putExtra("turno", turno);
                intent.putExtra("Id",Id);
                intent.putExtra("PlatoId",plan.getRecipeId().toString());
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
