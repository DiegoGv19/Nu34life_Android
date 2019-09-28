package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import Interface.INutritionist;
import Interface.IPatient;
import Model.ApiClient;
import Model.Nutritionist;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Afiliar extends AppCompatActivity {
    private String email;
    private IPatient iPatient;
    private INutritionist iNutritionist;
    private EditText etEmail;
    private Button btnAfiliar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afiliar);
        email = getIntent().getStringExtra("correo");
        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        iNutritionist = ApiClient.getRetrofit().create(INutritionist.class);
        etEmail = (EditText) findViewById(R.id.etEmailNutricionistaAfi);
        btnAfiliar = (Button) findViewById(R.id.btnAfiliarNutricionistaPatient);

        btnAfiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPatientes();
            }
        });

    }

    private void getPatientes(){

        final Patient patient = new Patient();
        final Nutritionist nutritionist = new Nutritionist();
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
                        patient.setId(pat.getId());
                        patient.setName(pat.getName());
                        patient.setLastName(pat.getLastName());
                        patient.setEmail(pat.getEmail());
                        patient.setPassword(pat.getPassword());
                        patient.setBirthdate(pat.getBirthdate());
                        Call<List<Nutritionist>> callp = iNutritionist.getNutricionista(etEmail.getText().toString());
                        callp.enqueue(new Callback<List<Nutritionist>>() {
                            @Override
                            public void onResponse(Call<List<Nutritionist>> call, Response<List<Nutritionist>> response) {
                                if(!response.isSuccessful()){
                                    return;
                                }

                                List<Nutritionist> listnutritionists = response.body();

                                if(listnutritionists != null){
                                    for (Nutritionist nut: listnutritionists){
                                        nutritionist.setId(nut.getId());
                                        nutritionist.setName(nut.getName());
                                        nutritionist.setEmail(nut.getEmail());
                                        nutritionist.setPassword(nut.getPassword());
                                        nutritionist.setLastName(nut.getLastName());
                                        nutritionist.setActivated(nut.getActivated());
                                        nutritionist.setValidated(nut.getValidated());
                                        nutritionist.setBirthdate(nut.getBirthdate());
                                        patient.setNutritionist(nutritionist);
                                        Call<Patient> callput = iPatient.putPatient(patient);
                                        callput.enqueue(new Callback<Patient>() {
                                            @Override
                                            public void onResponse(Call<Patient> call, Response<Patient> response) {

                                            }

                                            @Override
                                            public void onFailure(Call<Patient> call, Throwable t) {

                                            }
                                        });

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Nutritionist>> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
            }
        });

    }

}
