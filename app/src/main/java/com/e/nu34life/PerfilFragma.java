package com.e.nu34life;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import Interface.IPatient;
import Model.ApiClient;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragma extends Fragment {

    private View vista;
    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvCorreo;
    private TextView tvContraseña;
    private TextView tvFecha;
    private Button btnAfiliar;
    private String email;

    private IPatient iPatient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getActivity().getIntent().getStringExtra("Correo");
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        vista = inflater.inflate(R.layout.fragment_perfil,null);
                //email
        iPatient = ApiClient.getRetrofit().create(IPatient.class);


        tvNombre = (TextView) vista.findViewById(R.id.tvPatientNombre);
        tvApellido= (TextView) vista.findViewById(R.id.tvPatientApellido);
        tvCorreo= (TextView) vista.findViewById(R.id.tvPatientCorreo);
        tvContraseña= (TextView) vista.findViewById(R.id.tvPatientContraseña);
        tvFecha= (TextView) vista.findViewById(R.id.tvPatienteFecha);
        btnAfiliar = (Button) vista.findViewById(R.id.btnAfiliar);
        getPatientes();

        btnAfiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Afiliar.class);
                intent.putExtra("correo",email);
                startActivity(intent);
            }
        });

        return vista;

    }

    private void getPatientes(){


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
                        tvContraseña.setText(pat.getPassword());
                        tvCorreo.setText(pat.getEmail());
                        tvFecha.setText(pat.getBirthdate());
                        tvNombre.setText(pat.getName());
                        tvApellido.setText(pat.getLastName());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {

            }
        });

    }


}
