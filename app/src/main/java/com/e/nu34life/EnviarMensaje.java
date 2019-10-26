package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import Interface.IPatient;
import Model.ApiClient;
import Model.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviarMensaje extends AppCompatActivity {

    private Button enviar;
    private EditText mensaje;
    private TextView numero;
    private String Correo;
    private String Contraseña;
    private IPatient iPatient;
    private TextView tvFechaActual;
    private TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensaje);
        iPatient = ApiClient.getRetrofit().create(IPatient.class);

        Correo = getIntent().getStringExtra("Correo");
        Contraseña = getIntent().getStringExtra("Contraseña");
        nombre = (TextView) findViewById(R.id.tvNombreReceptor);
        mensaje = (EditText) findViewById(R.id.etMensajeEnviar);
        numero = (TextView) findViewById(R.id.tvNumerodeCelular);
        enviar = (Button) findViewById(R.id.btnEnviar);
        tvFechaActual = (TextView) findViewById(R.id.tvFechadeEnvio);
        getPatientes(Correo,Contraseña);
        FechaActual();
        if(ActivityCompat.checkSelfPermission(EnviarMensaje.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EnviarMensaje.this,Manifest.permission. SEND_SMS)!= PackageManager.PERMISSION_GRANTED){ActivityCompat.requestPermissions(EnviarMensaje.this,new String[]
                {
                        Manifest.permission.SEND_SMS,
                },1000);
        }else{

        };
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje(mensaje.getText().toString(),numero.getText().toString());

            }
        });

    }

    private void enviarMensaje(String mensaje, String numero){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(),"Mensaje enviado",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Mensaje no enviado, Dato incorrectos",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
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

                    numero.setText(listpatientes.getPhone());
                    nombre.setText(listpatientes.getName()+" "+listpatientes.getLastName());



                }

            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });

    }

    private void FechaActual(){


        Date date = new Date();
        SimpleDateFormat fechaC = new SimpleDateFormat("d,MMMM 'del', yyyy");
        String sFecha = fechaC.format(date);
        tvFechaActual.setText(sFecha);
    }
}
