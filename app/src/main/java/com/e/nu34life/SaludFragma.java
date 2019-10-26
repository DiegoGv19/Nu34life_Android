package com.e.nu34life;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import Interface.IPatient;
import Interface.IState;
import Model.ApiClient;
import Model.Patient;
import Model.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaludFragma extends Fragment {

    private TextView tvPeso;
    private TextView tvTalla;
    private TextView tvGlucosa;
    private TextView tvNombre;
    private TextView tvFecha;
    private View vista;
    private IState iState;
    private String email;
    private IPatient iPatient;

    private String nombre;
    private String Id;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getActivity().getIntent().getStringExtra("Correo");
        nombre = getActivity().getIntent().getStringExtra("Nombre");
        Id = getActivity().getIntent().getStringExtra("Id");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         vista = inflater.inflate(R.layout.fragment_salud,null);
         iState = ApiClient.getRetrofit().create(IState.class);
         iPatient = ApiClient.getRetrofit().create(IPatient.class);
         tvPeso = vista.findViewById(R.id.tvPatientePeso);
         tvTalla = vista.findViewById(R.id.tvPatientTalla);
         tvGlucosa = vista.findViewById(R.id.tvPatientGlucosa);
         tvNombre = vista.findViewById(R.id.tvPatienteNombreS);
         tvFecha = vista.findViewById(R.id.tvPatientFechaS);

        tvNombre.setText(nombre);
        getState(Id);
        return vista;
    }


    private void getState(String id){
        Long idPat = Long.parseLong(id);
        Call<State> call = iState.getStateId(idPat);
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
