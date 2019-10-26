package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import Adaptador.AdaptadorPatienet;
import Interface.INutritionist;
import Interface.IPatient;
import Model.ApiClient;
import Model.Nutritionist;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainNutricionita extends AppCompatActivity {
    private List<Patient> listaPacientes;

    private ListView lvPacientes;
    private String Id;
    private Long IdN;
    private Button recordatorios;
    private INutritionist iNutritionist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nutricionita);
        iNutritionist = ApiClient.getRetrofit().create(INutritionist.class);
        lvPacientes = (ListView) findViewById(R.id.ListPatient);
        Id = getIntent().getStringExtra("Id");
        IdN = Long.parseLong(Id);
        recordatorios = (Button) findViewById(R.id.btnRecordatorios);

        //idNutricionista = getNutricionista(Correo,Contraseña);
        listaPacientes = getPatient(IdN);
        /*Patient patient = new Patient(100l,"Jose Luis","Cardenas","1987/05/14","jose@hotmail.com","****","987452657");
        listaPacientes.add(patient);
        */

        recordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainNutricionita.this,Recordatorios.class);
                startActivity(intent);
            }
        });

        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Patient enviar = listaPacientes.get(position);
                Intent intent = new Intent(MainNutricionita.this,DatosPatient.class);
                intent.putExtra("Correo",enviar.getEmail().toString());
                intent.putExtra("Contraseña",enviar.getPassword().toString());
                intent.putExtra("Nombre",enviar.getName().toString()+" "+enviar.getLastName().toString());
                intent.putExtra("Id",enviar.getId().toString());

                startActivity(intent);
            }
        });
    }
    private List<Patient> getPatient(Long id){
        final List<Patient> listapacientes = new ArrayList<Patient>();

        Call<List<Patient>> call = iNutritionist.getPatientDelNutri(id);
        call.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(!response.isSuccessful()){

                    return;
                }

                List<Patient> listpatientes = response.body();

                for (Patient pat: listpatientes) {

                    Patient patient = new Patient(pat.getId(),pat.getName(),pat.getLastName(),pat.getBirthdate(),pat.getEmail(),pat.getPassword(),pat.getPhone());
                    listapacientes.add(patient);
                }

                if (listaPacientes != null) {
                    final AdaptadorPatienet miadaptador = new AdaptadorPatienet(getApplicationContext(), listaPacientes);
                    lvPacientes.setAdapter(miadaptador);
                }
                else {
                    Toast.makeText(MainNutricionita.this,"No tienes Pacientes",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {

            }
        });
        return listapacientes;
    }


}
