package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adaptador.AdaptadorIngredientes;
import Interface.IIngredient;
import Interface.IRecipe;
import Model.ApiClient;
import Model.Ingredient;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientesPlatoPaciente extends AppCompatActivity {
    private String dia, turno;

    private String PlatoId;
    private ListView listaIngredientes;
    private IRecipe iRecipe;
    private IIngredient iIngredient;

    private TextView tvDia, tvTurno, tvPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes_plato_paciente);

        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);
        iIngredient = ApiClient.getRetrofit().create(IIngredient.class);

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
        getIngredients();


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

    private void getIngredients() {
        Long id = Long.parseLong(PlatoId);
        Call<List<Ingredient>> call = iIngredient.getIgredients(id);
        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                List<Ingredient> listIngredient = response.body();
                if (listIngredient != null) {

                    final AdaptadorIngredientes miadaptador = new AdaptadorIngredientes(getApplicationContext(), listIngredient);
                    listaIngredientes.setAdapter(miadaptador);

                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {

            }
        });

    }

}
