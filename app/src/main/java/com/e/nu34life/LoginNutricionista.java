package com.e.nu34life;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import Interface.INutritionist;
import Model.ApiClient;
import Model.Nutritionist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginNutricionista extends AppCompatActivity {
    private EditText etEmail;
    private EditText etContraseña;
    private Button btnIniciar;
    private Button btnCrear;
    private INutritionist iNutritionist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_nutricionista);
        iNutritionist = ApiClient.getRetrofit().create(INutritionist.class);

        etEmail = (EditText) findViewById(R.id.etCorreo);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnCrear = (Button) findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginNutricionista.this,CrearNutricionista.class);

                startActivity(intent);
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString() != "" && etContraseña.getText().toString() != "" ){
                    getNutricionista(etEmail.getText().toString(),etContraseña.getText().toString());
                }



                }


        });

    }

    private void getNutricionista(String email, final String contraseña){

        Call<List<Nutritionist>> call = iNutritionist.getNutricionista(email);
        call.enqueue(new Callback<List<Nutritionist>>() {
            @Override
            public void onResponse(Call<List<Nutritionist>> call, Response<List<Nutritionist>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                List<Nutritionist> listnutritionists = response.body();
                if(listnutritionists != null){
                    for (Nutritionist nut: listnutritionists){
                        if(nut.getPassword().equals(contraseña)){
                            Intent intent = new Intent(LoginNutricionista.this,MainNutricionita.class);
                            intent.putExtra("Correo",etEmail.getText().toString());
                            startActivity(intent);
                        }                    }
                }
            }

            @Override
            public void onFailure(Call<List<Model.Nutritionist>> call, Throwable t) {

            }

        });

    }
}
