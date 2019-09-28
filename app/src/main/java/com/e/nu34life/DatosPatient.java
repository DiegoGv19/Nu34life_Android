package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import Interface.IPatient;
import Interface.IState;
import Model.ApiClient;
import Model.Patient;
import Model.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPatient extends AppCompatActivity {

    private String IdPatient;
    private IPatient iPatient;
    private IState iState;

    private TextView tvNombre;
    private TextView tvConsulta;
    private TextView tvPeso;
    private TextView tvTalla;
    private TextView tvGlucosa;
    private Button btnModificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_patient);

        IdPatient = getIntent().getStringExtra("IdPatient");

        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        iState = ApiClient.getRetrofit().create(IState.class);

        tvNombre = (TextView) findViewById(R.id.tvNombrePatiente);
        tvConsulta = (TextView) findViewById(R.id.tvUltimaConsulta);
        tvPeso = (TextView) findViewById(R.id.tvPeso);
        tvTalla = (TextView) findViewById(R.id.tvTalla);
        tvGlucosa = (TextView) findViewById(R.id.tvGlucosa);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        getPatientes();
        getState();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosPatient.this,DatosPatientModificar.class);
                intent.putExtra("IdUsuario",IdPatient);
                startActivity(intent);
            }
        });

    }

    private void getPatientes(){
        Long id = Long.parseLong(IdPatient);
        Call<Patient> call = iPatient.getPatientId(id);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Patient patienteaux = response.body();
                tvNombre.setText(patienteaux.getName() + "  " + patienteaux.getLastName());


            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });
    }

    private void getState(){

        Call<State> call = iState.getStateId(1l);
        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                if(!response.isSuccessful()){
                    return;
                }
                State state = response.body();
                tvPeso.setText(state.getWeight().toString());
                tvTalla.setText(state.getHeight().toString());
                tvGlucosa.setText(state.getGlucose().toString());
                tvConsulta.setText(state.getGeneratedDate());
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
}
