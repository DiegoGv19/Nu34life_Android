package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import Interface.IPatient;
import Model.ApiClient;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPatient extends AppCompatActivity {

    private Button btnCrear;
    private Button btnIniciar;
    private EditText etCorreo;
    private EditText etContraseña;
    private IPatient iPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);
        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        btnIniciar = (Button) findViewById(R.id.btnIniciar2);
        btnCrear = (Button) findViewById(R.id.btnCrear2);
        etContraseña = (EditText) findViewById(R.id.etContraseña2);
        etCorreo = (EditText) findViewById(R.id.etCorreo2);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPatient.this,CrearPatient.class);
                startActivity(intent);
            }
        });


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCorreo.getText().toString() != "" && etContraseña.getText().toString() != "" ){
                    getPatientes(etCorreo.getText().toString(),etContraseña.getText().toString());
                }
            }
        });


    }
    private void getPatientes(String email, final String contraseña){



        Call<List<Patient>> call = iPatient.getPatient(email);
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                List<Patient> listpatientes = response.body();
                if(listpatientes != null){
                    for (Patient pat: listpatientes){
                        if(pat.getPassword().equals(contraseña)){


                            Intent intent = new Intent(LoginPatient.this,MainPatient.class);
                            intent.putExtra("Correo",etCorreo.getText().toString());
                            startActivity(intent);
                        }                    }
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {

            }
        });

    }
}
