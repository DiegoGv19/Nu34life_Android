package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Interface.INutrFact;
import Interface.IPlan;
import Interface.IRecipe;
import Model.ApiClient;
import Model.NutrFact;
import Model.Plan;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AignarComidaPaciente extends AppCompatActivity {
    private String dia, turno;

    private String PlatoId;
    private String Id;
    private IRecipe iRecipe;
    private INutrFact iNutrFact;
    private IPlan iPlan;

    private TextView tvDia, tvTurno, tvPlato;

    private Button btnAgregarNuevoPlan;
    private TextView TVCarbohydrates,TVEnergeticValue,TVProtein,TVSalt,TVSaturatedFats,TVSugars,TVTotalFat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aignar_comida_paciente);
        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);
        iNutrFact = ApiClient.getRetrofit().create(INutrFact.class);
        iPlan = ApiClient.getRetrofit().create(IPlan.class);

        dia = getIntent().getStringExtra("dia");
        turno = getIntent().getStringExtra("turno");
        PlatoId = getIntent().getStringExtra("PlatoId");
        Id = getIntent().getStringExtra("Id");

        tvDia = (TextView) findViewById(R.id.tvDiaSeleccionado);
        tvTurno = (TextView) findViewById(R.id.tvTurnoSeleccionado);
        tvPlato = (TextView) findViewById(R.id.tvPlatoSeleccionado);
        btnAgregarNuevoPlan = (Button) findViewById(R.id.btnAgregarNuevoPlan);
        TVCarbohydrates = (TextView) findViewById(R.id.TVCarbohydratesDato);
        TVEnergeticValue = (TextView) findViewById(R.id.TVEnergeticValueDato);
        TVProtein = (TextView) findViewById(R.id.TVProteinDato);
        TVSalt = (TextView) findViewById(R.id.TVSaltDato);
        TVSaturatedFats = (TextView) findViewById(R.id.TVSaturatedFatsDato);
        TVSugars = (TextView) findViewById(R.id.TVSugarsDato);
        TVTotalFat = (TextView) findViewById(R.id.TVTotalFatDatos);;



        tvDia.setText(dia);
        tvTurno.setText(turno);
        getRecipe();
        getNutriFact();

        btnAgregarNuevoPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPlan();
                Toast.makeText(AignarComidaPaciente.this,"La comida se agrego",Toast.LENGTH_SHORT).show();

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

    private void postPlan(){
        Long idplato = Long.parseLong(PlatoId);
        Long idpaciente = Long.parseLong(Id);

        Plan plan = new Plan( dia, idpaciente,  idplato,  turno);

        Call<Plan> call = iPlan.postPlan(plan);
        call.enqueue(new Callback<Plan>() {
            @Override
            public void onResponse(Call<Plan> call, Response<Plan> response) {

            }

            @Override
            public void onFailure(Call<Plan> call, Throwable t) {

            }
        });
    }
}