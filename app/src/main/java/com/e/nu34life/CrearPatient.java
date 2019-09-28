package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Interface.IPatient;
import Model.ApiClient;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPatient extends AppCompatActivity {

    private static final String TAG = "CrearNutricionista";
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCumpleaños;
    private EditText etCorreo;
    private EditText etContraseña;
    private Button btnCrear;
    private Button btnCancelar;
    private IPatient iPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_patient);

        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        etNombre= (EditText) findViewById(R.id.etNombreC);
        etApellido= (EditText) findViewById(R.id.etApellidoC);
        etCumpleaños= (EditText) findViewById(R.id.etFechaC);
        etCorreo = (EditText) findViewById(R.id.etCorreoC);
        etContraseña = (EditText) findViewById(R.id.etContraseñaC);
        btnCrear = (Button) findViewById(R.id.btnCrearC);
        btnCancelar = (Button) findViewById(R.id.btnCancelarC);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombre.toString()!=" "&& etApellido.getText().toString()!=" "&&etCumpleaños.getText().toString()!=" "&&etCorreo.getText().toString()!=" "&&etContraseña.getText().toString()!=" ") {
                    postPatiente(etNombre.getText().toString(), etApellido.getText().toString(), etCumpleaños.getText().toString(), etCorreo.getText().toString(), etContraseña.getText().toString());
                    Intent intent = new Intent(CrearPatient.this, LoginPatient.class);
                    startActivity(intent);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearPatient.this,LoginPatient.class);
                startActivity(intent);
            }
        });
    }
    private void postPatiente(String name, String lastName, String birthdate, String email, String password){
        Patient patient = new Patient( name,  lastName,  birthdate,  email,  password,true);

        Call<Patient> call = iPatient.postPatient(patient);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Log.e(TAG,"onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });
    }
}
