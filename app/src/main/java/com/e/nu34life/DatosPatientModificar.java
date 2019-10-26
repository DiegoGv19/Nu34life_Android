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

    private String Correo;
    private String Contraseña;
    private String nombre;
    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_patient_modificar);
        Correo = getIntent().getStringExtra("Correo");
        Contraseña = getIntent().getStringExtra("Contraseña");
        nombre= getIntent().getStringExtra("Nombre");

        Id= getIntent().getStringExtra("Id");


        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        iState = ApiClient.getRetrofit().create(IState.class);

        tvNombre = (TextView) findViewById(R.id.tvNombrePatienteM);
        tvFechaActual = (TextView) findViewById(R.id.tvUltimaConsultaM);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etTalla = (EditText) findViewById(R.id.etTalla);
        etGlucosa = (EditText) findViewById(R.id.etGlucosa);
        btnModificar = (Button) findViewById(R.id.btnGuardarCambios);
        tvNombre.setText(nombre);

        getState(Id);
        FechaActual();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putState(Correo,Contraseña);
            }
        });

    }


    private void getState(String id){
        Long idpat;
        idpat = Long.parseLong(id);
        Call<State> call = iState.getStateId(idpat);
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

    private void putState(String Correo, String Contraseña){


        Integer peso = Integer.parseInt(etPeso.getText().toString());
        Integer talla = Integer.parseInt(etTalla.getText().toString());
        Integer Glucosa = Integer.parseInt(etGlucosa.getText().toString());

        State state = new State("Cambio",talla,peso,Glucosa);

        Call<State> callput = iState.putState(state, Correo, Contraseña);
        callput.enqueue(new Callback<State>() {
            @Override
            public void onResponse(Call<State> call, Response<State> response) {
                    }
                    @Override
                    public void onFailure(Call<State> call, Throwable t) {

                    }
                });



    }
}
