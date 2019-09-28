package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import Interface.IPatient;
import Interface.IRecipe;
import Model.ApiClient;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComidaAComer extends AppCompatActivity {

    private String dia,horario;
    private TextView tvdia, tvhorario,tvplato;
    private IRecipe iRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_acomer);
        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);

        dia = getIntent().getStringExtra("dia");

        horario = getIntent().getStringExtra("horario");

        tvdia = (TextView) findViewById(R.id.tvDiaComida);
        tvhorario = (TextView) findViewById(R.id.tvHorarioComida);
        tvplato = (TextView) findViewById(R.id.tvComidadelDia);
        tvdia.setText(dia);
        tvhorario.setText(horario);
        getRecipe(1l);
    }

    private void getRecipe(Long id){

        Call<Recipe> call = iRecipe.getRecipeId(id);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe listrecipe = response.body();
                tvplato.setText(listrecipe.getName());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {

            }
        });

    }
}
