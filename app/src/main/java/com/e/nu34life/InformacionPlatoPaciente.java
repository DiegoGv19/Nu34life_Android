package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import Interface.INutrFact;
import Interface.IRecipe;
import Model.ApiClient;
import Model.NutrFact;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionPlatoPaciente extends AppCompatActivity {
    private String dia, turno;

    private String PlatoId;

    private IRecipe iRecipe;
    private INutrFact iNutrFact;

    private TextView tvDia, tvTurno, tvPlato;
    private Button btnIngredientesPlato,btnPasosPlato;

    private TextView TVCarbohydrates,TVEnergeticValue,TVProtein,TVSalt,TVSaturatedFats,TVSugars,TVTotalFat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_plato_paciente);

        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);
        iNutrFact = ApiClient.getRetrofit().create(INutrFact.class);

        dia = getIntent().getStringExtra("dia");
        turno = getIntent().getStringExtra("turno");
        PlatoId = getIntent().getStringExtra("PlatoId");

        tvDia = (TextView) findViewById(R.id.tvDiaSeleccionadoPasos);
        tvTurno = (TextView) findViewById(R.id.tvTurnoSeleccionadoPasos);
        tvPlato = (TextView) findViewById(R.id.tvPlatoSeleccionadoPasos);
        btnIngredientesPlato = (Button) findViewById(R.id.btnIngredientesPlato);
        btnPasosPlato = (Button) findViewById(R.id.btnPasosPlato);
        TVCarbohydrates = (TextView) findViewById(R.id.TVCarbohydratesDatoPlato);
        TVEnergeticValue = (TextView) findViewById(R.id.TVEnergeticValueDatoPlato);
        TVProtein = (TextView) findViewById(R.id.TVProteinDatoPlato);
        TVSalt = (TextView) findViewById(R.id.TVSaltDatoPlato);
        TVSaturatedFats = (TextView) findViewById(R.id.TVSaturatedFatsDatoPlato);
        TVSugars = (TextView) findViewById(R.id.TVSugarsDatoPlato);
        TVTotalFat = (TextView) findViewById(R.id.TVTotalFatDatosPlato);;



        tvDia.setText(dia);
        tvTurno.setText(turno);
        getRecipe();
        getNutriFact();

        btnIngredientesPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformacionPlatoPaciente.this,IngredientesPlatoPaciente.class);
                intent.putExtra("dia",dia);
                intent.putExtra("turno",turno);
                intent.putExtra("PlatoId",PlatoId);
                startActivity(intent);

            }
        });

        btnPasosPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformacionPlatoPaciente.this,PasosPlatoPaciente.class);
                intent.putExtra("dia",dia);
                intent.putExtra("turno",turno);
                intent.putExtra("PlatoId",PlatoId);
                startActivity(intent);
            }
        });
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
    private void getNutriFact() {
        final List<String> datosApi = null;
        Long id = Long.parseLong(PlatoId);
        Call<NutrFact> call = iNutrFact.getNutrFact(id);
        call.enqueue(new Callback<NutrFact>() {
            @Override
            public void onResponse(Call<NutrFact> call, Response<NutrFact> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                NutrFact nutrFact = response.body();
                if (nutrFact != null) {


                    TVCarbohydrates.setText(nutrFact.getCarbohydrates().toString());
                    TVEnergeticValue.setText(nutrFact.getEnergeticValue().toString());
                    TVProtein.setText(nutrFact.getProtein().toString());
                    TVSalt.setText(nutrFact.getSalt().toString());
                    TVSaturatedFats.setText(nutrFact.getSaturatedFats().toString());
                    TVSugars.setText(nutrFact.getSugars().toString());
                    TVTotalFat.setText(nutrFact.getTotalFat().toString());


                }


            }

            @Override
            public void onFailure(Call<NutrFact> call, Throwable t) {

            }
        });


    }

}
