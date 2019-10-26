package com.e.nu34life;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                if(validar()){
                    getNutricionista(etEmail.getText().toString(),etContraseña.getText().toString());

                }



                }


        });

    }

    private void getNutricionista(String email, String contraseña){

        Call<Nutritionist> call = iNutritionist.getNutricionista(email,contraseña);
        call.enqueue(new Callback<Nutritionist>() {
            @Override
            public void onResponse(Call<Nutritionist> call, Response<Nutritionist> response) {
                if(!response.isSuccessful()){

                    return;
                }

                Nutritionist listnutritionists = response.body();
                if(listnutritionists != null){
                    Intent intent = new Intent(LoginNutricionista.this,MainNutricionita.class);
                    intent.putExtra("Id",listnutritionists.getId().toString());
                    startActivity(intent);


                }
                else {
                    Toast.makeText(LoginNutricionista.this, "Este usuario no esta registrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Nutritionist> call, Throwable t) {

            }
        });
    }


    public boolean validar(){
        boolean retorno  = true;
        String email = etEmail.getText().toString();
        String contraseña = etContraseña.getText().toString();

        if (email.isEmpty()){
            etEmail.setError("Ingresa Email");
            retorno = false;
        }

        if (contraseña.isEmpty()){
            etContraseña.setError("Ingresa Contraeña");
            retorno = false;
        }

        return retorno;
    }
}
