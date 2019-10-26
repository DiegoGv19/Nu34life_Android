package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Interface.INutritionist;
import Model.ApiClient;
import Model.Nutritionist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearNutricionista extends AppCompatActivity {
    private static final String TAG = "CrearNutricionista";
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCumpleaños;
    private EditText etCorreo;
    private EditText etContraseña;
    private EditText etCelular;

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
        etCelular = (EditText) findViewById(R.id.etCelular);

        btnCrear = (Button) findViewById(R.id.btnCrear);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    postNutricionista(etNombre.getText().toString(), etApellido.getText().toString(), etCumpleaños.getText().toString(), etCorreo.getText().toString(), etContraseña.getText().toString(),etCelular.getText().toString());

                    Toast.makeText(CrearNutricionista.this,"Usuario Registrado",Toast.LENGTH_SHORT).show();

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

    private void postNutricionista(String name, String lastName, String birthdate, String email, String password, String celular){
        Nutritionist nutritionist = new Nutritionist( name,  lastName,  birthdate,  email,  password,celular);

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
    public boolean validar(){
        boolean retorno  = true;

        String nombre = etNombre.getText().toString();
        String apellido =  etApellido.getText().toString();
        String cumpleaños =  etCumpleaños.getText().toString();
        String correo =  etCorreo.getText().toString();
        String contraseña =  etContraseña.getText().toString();
        String phone = etCelular.getText().toString();
        if (nombre.isEmpty()){
            etNombre.setError("Ingresa Nombre");
            retorno = false;
        }

        if (contraseña.isEmpty()){
            etContraseña.setError("Ingresa Contraeña");
            retorno = false;
        }

        if (apellido.isEmpty()){
            etApellido.setError("Ingresa Apellido");
            retorno = false;
        }

        if (correo.isEmpty()){
            etCorreo.setError("Ingresa Correo");
            retorno = false;
        }

        if (cumpleaños.isEmpty()){
            etCumpleaños.setError("Ingresa Cumpleaños");
            retorno = false;
        }
        if (phone.isEmpty()){
            etCelular.setError("Ingresa Numero de Celular");
            retorno = false;
        }
        return retorno;
    }
}
