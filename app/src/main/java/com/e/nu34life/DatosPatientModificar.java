package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DatosPatientModificar extends AppCompatActivity {
    private String IdPatient;
    private IPatient iPatient;
    private IState iState;
    private TextView tvNombre;
    private TextView tvFechaActual;
    private EditText etPeso;
    private EditText etTalla;
    private EditText etGlucosa;
    private Button btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_patient_modificar);
        IdPatient = getIntent().getStringExtra("IdUsuario");

        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        iState = ApiClient.getRetrofit().create(IState.class);

        tvNombre = (TextView) findViewById(R.id.tvNombrePatienteM);
        tvFechaActual = (TextView) findViewById(R.id.tvUltimaConsultaM);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etTalla = (EditText) findViewById(R.id.etTalla);
        etGlucosa = (EditText) findViewById(R.id.etGlucosa);
        btnModificar = (Button) findViewById(R.id.btnGuardarCambios);

        getPatientes();
        getState();
        FechaActual();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putState();
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
                etPeso.setText(state.getWeight().toString());
                etTalla.setText(state.getHeight().toString());
                etGlucosa.setText(state.getGlucose().toString());

            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }

    private void FechaActual(){


        Date date = new Date();
        SimpleDateFormat fechaC = new SimpleDateFormat("d,MMMM 'del', yyyy");
        String sFecha = fechaC.format(date);
        tvFechaActual.setText(sFecha);
    }

    private void putState(){

        Call<State> call = iState.getStateId(1l);
        call.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Integer peso = Integer.parseInt(etTalla.getText().toString());
                Integer talla = Integer.parseInt(etPeso.getText().toString());
                Integer Glucosa = Integer.parseInt(etGlucosa.getText().toString());
                Date date = new Date();
                SimpleDateFormat fechaC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                String sFecha = fechaC.format(date);
                State state = response.body();
                state.setHeight(talla);
                state.setWeight(peso);
                state.setGlucose(Glucosa);
                state.setGeneratedDate(sFecha);

                Call<State> callput = iState.putState(state);
                callput.enqueue(new Callback<State>() {
                    @Override
                    public void onResponse(Call<State> call, Response<State> response) {

                    }

                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });

    }
}
