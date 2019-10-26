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

    private IPatient iPatient;
    private IState iState;

    private TextView tvNombre;
    private TextView tvConsulta;
    private TextView tvPeso;
    private TextView tvTalla;
    private TextView tvGlucosa;
    private Button btnModificar;
    private Button btnEnviarMensaje;

    private String Correo;
    private String Contraseña;
    private String nombre;
    private String Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_patient);

        Correo = getIntent().getStringExtra("Correo");
        Contraseña = getIntent().getStringExtra("Contraseña");
        nombre= getIntent().getStringExtra("Nombre");

        Id= getIntent().getStringExtra("Id");
        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        iState = ApiClient.getRetrofit().create(IState.class);

        tvNombre = (TextView) findViewById(R.id.tvNombrePatiente);
        tvNombre.setText(nombre);
        tvConsulta = (TextView) findViewById(R.id.tvUltimaConsulta);
        tvPeso = (TextView) findViewById(R.id.tvPeso);
        tvTalla = (TextView) findViewById(R.id.tvTalla);
        tvGlucosa = (TextView) findViewById(R.id.tvGlucosa);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEnviarMensaje = (Button) findViewById(R.id.btnEnviarMensaje);
        //getPatientes();
        getState(Id);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosPatient.this,DatosPatientModificar.class);
                intent.putExtra("Correo",Correo);
                intent.putExtra("Contraseña",Contraseña);
                intent.putExtra("Id",Id);
                intent.putExtra("Nombre",nombre);

                startActivity(intent);
            }
        });
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosPatient.this,EnviarMensaje.class);
                intent.putExtra("Correo",Correo);
                intent.putExtra("Contraseña",Contraseña);
                startActivity(intent);
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
                tvPeso.setText(state.getWeight().toString());
                tvTalla.setText(state.getHeight().toString());
                tvGlucosa.setText(state.getGlucose().toString());
            }

            @Override
            public void onFailure(Call<State> call, Throwable t) {

            }
        });
    }
}
