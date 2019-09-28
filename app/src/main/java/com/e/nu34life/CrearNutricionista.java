package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.List;

import Interface.INutritionist;
import Model.ApiClient;
import Model.Nutritionist;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearNutricionista extends AppCompatActivity {
    private static final String TAG = "CrearNutricionista";
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCumpleaños;
    private EditText etCorreo;
    private EditText etContraseña;
    private Button btnCrear;
    private Button btnCancelar;
    private INutritionist iNutritionist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nutricionista);
        iNutritionist = ApiClient.getRetrofit().create(INutritionist.class);
        etNombre= (EditText) findViewById(R.id.etNombre);
        etApellido= (EditText) findViewById(R.id.etApellido);
        etCumpleaños= (EditText) findViewById(R.id.etCumpleaños);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnCrear = (Button) findViewById(R.id.btnCrear);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombre.getText().toString()!=" "&&etApellido.getText().toString()!=" "&&etCumpleaños.getText().toString()!=" "&&etCorreo.getText().toString()!=" "&&etContraseña.getText().toString()!=" ") {
                    postNutricionista(etNombre.getText().toString(), etApellido.getText().toString(), etCumpleaños.getText().toString(), etCorreo.getText().toString(), etContraseña.getText().toString());
                    Intent intent = new Intent(CrearNutricionista.this, LoginNutricionista.class);
                    startActivity(intent);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearNutricionista.this,LoginNutricionista.class);
                startActivity(intent);
            }
        });

    }

    private void postNutricionista(String name, String lastName, String birthdate, String email, String password){
        Nutritionist nutritionist = new Nutritionist( name,  lastName,  birthdate,  email,  password,true,true);

        Call<Nutritionist> call = iNutritionist.postNutricionista(nutritionist);
        call.enqueue(new Callback<Nutritionist>() {
            @Override
            public void onResponse(Call<Nutritionist> call, Response<Nutritionist> response) {
                Log.e(TAG,"onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<Nutritionist> call, Throwable t) {

            }
        });

    }

}
