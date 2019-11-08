package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adaptador.AdaptadorIngredientes;
import Adaptador.AdaptadorPasos;
import Interface.IIngredient;
import Interface.IPasos;
import Interface.IRecipe;
import Model.ApiClient;
import Model.Ingredient;
import Model.Recipe;
import Model.Steps;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasosPlatoPaciente extends AppCompatActivity {
    private String dia, turno;

    private String PlatoId;
    private ListView listaIngredientes;
    private IRecipe iRecipe;
    private IPasos iPasos;

    private TextView tvDia, tvTurno, tvPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasos_plato_paciente);


        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);
        iPasos = ApiClient.getRetrofit().create(IPasos.class);

        dia = getIntent().getStringExtra("dia");
        turno = getIntent().getStringExtra("turno");
        PlatoId = getIntent().getStringExtra("PlatoId");

        tvDia = (TextView) findViewById(R.id.tvDiaSeleccionadoPasos);
        tvTurno = (TextView) findViewById(R.id.tvTurnoSeleccionadoPasos);
        tvPlato = (TextView) findViewById(R.id.tvPlatoSeleccionadoPasos);


        listaIngredientes = (ListView) findViewById(R.id.lvListatPasos);

        tvDia.setText(dia);
        tvTurno.setText(turno);
        getRecipe();
        getSteps();
    }

    private void getRecipe() {
        Long id = Long.parseLong(PlatoId);
        Call<Recipe> call = iRecipe.getRecipeId(id);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                Recipe listrecipe = response.body();
                if (listrecipe != null) {
                    tvPlato.setText(listrecipe.getName());
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {

            }
        });

    }

    private void getSteps() {
        Long id = Long.parseLong(PlatoId);
        Call<List<Steps>> call = iPasos.getSteps(id);
        call.enqueue(new Callback<List<Steps>>() {
            @Override
            public void onResponse(Call<List<Steps>> call, Response<List<Steps>> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                List<Steps> listSteps = response.body();
                if (listSteps != null) {

                    final AdaptadorPasos miadaptador = new AdaptadorPasos(getApplicationContext(), listSteps);
                    listaIngredientes.setAdapter(miadaptador);

                }
            }

            @Override
            public void onFailure(Call<List<Steps>> call, Throwable t) {

            }
        });
    }

}
