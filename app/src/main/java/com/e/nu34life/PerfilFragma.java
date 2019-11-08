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
import android.widget.Toast;

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
    private String email;
    private String contraseña;
    private String nombre;
    private String Id;

    private IPatient iPatient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getActivity().getIntent().getStringExtra("Correo");
        contraseña = getActivity().getIntent().getStringExtra("Contraseña");
        nombre = getActivity().getIntent().getStringExtra("Id");
        Id = getActivity().getIntent().getStringExtra("Nombre");
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
        getPatientes(email,contraseña);



        return vista;

    }

    private void getPatientes(String email, final String password){


        Call<Patient> call = iPatient.getPatientEP(email,password);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Patient listpatientes = response.body();
                if(listpatientes != null){
                    tvContraseña.setText(listpatientes.getPassword());
                    tvCorreo.setText(listpatientes.getEmail());
                    tvFecha.setText(listpatientes.getBirthdate());
                    tvNombre.setText(listpatientes.getName());
                    tvApellido.setText(listpatientes.getLastName());



                }

            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });

    }


}
