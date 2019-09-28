package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adaptador.AdaptadorPatienet;
import Interface.INutritionist;
import Interface.IPatient;
import Model.ApiClient;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainNutricionita extends AppCompatActivity {
    private List<Patient> listaPacientes = new ArrayList<Patient>();
    private ListView lvPacientes;
    private String Correo;
    private IPatient iPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nutricionita);
        iPatient = ApiClient.getRetrofit().create(IPatient.class);
        lvPacientes = (ListView) findViewById(R.id.ListPatient);
        Correo = getIntent().getStringExtra("Correo");
        listaPacientes = getPatientes();
        final AdaptadorPatienet miadaptador = new AdaptadorPatienet(getApplicationContext(),listaPacientes);
        lvPacientes.setAdapter(miadaptador);


        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Patient enviar = listaPacientes.get(position);
                Intent intent = new Intent(MainNutricionita.this,DatosPatient.class);
                intent.putExtra("IdPatient",enviar.getId().toString());
                startActivity(intent);
            }
        });
    }


    private List<Patient> getPatientes(){
        final List<Patient> listapacientes = new ArrayList<Patient>();


        Call<List<Patient>> call = iPatient.getPatients();
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Patient> listpatientes = response.body();

                for (Patient pat: listpatientes) {

                    Patient patient = new Patient(pat.getId(),pat.getName(),pat.getLastName(),pat.getBirthdate(),pat.getEmail(),pat.getPassword(),pat.getActivated());
                    listapacientes.add(patient);
                }


            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {

            }
        });
        return listapacientes;
    }
}
