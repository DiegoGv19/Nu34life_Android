package com.e.nu34life;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import Interface.INutritionist;
import Interface.IState;
import Model.ApiClient;
import Model.Patient;
import Model.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPatient extends AppCompatActivity {

    /*Facebook*/
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    /*Otros*/
    private static final String TAG = "CrearNutricionista";
    private EditText etNombre;
    private EditText etApellido;
    private EditText etCumpleaños;
    private EditText etCorreo;
    private EditText etContraseña;
    private EditText etCelular;
    private EditText etIdNutricionista;

    private Button btnCrear;
    private Button btnCancelar;
    private INutritionist iNutritionist;
    private IState iState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_patient);

        /*Facebook*/
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        /*Otros*/
        iNutritionist = ApiClient.getRetrofit().create(INutritionist.class);
        iState = ApiClient.getRetrofit().create(IState.class);

        etNombre= (EditText) findViewById(R.id.etNombreC);
        etApellido= (EditText) findViewById(R.id.etApellidoC);
        etCumpleaños= (EditText) findViewById(R.id.etFechaC);
        etCorreo = (EditText) findViewById(R.id.etCorreoC);
        etContraseña = (EditText) findViewById(R.id.etContraseñaC);
        etCelular = (EditText) findViewById(R.id.etCelular);
        etIdNutricionista = (EditText) findViewById(R.id.etIdNutricionista);

        btnCrear = (Button) findViewById(R.id.btnCrearC);
        btnCancelar = (Button) findViewById(R.id.btnCancelarC);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    postPatiente(etNombre.getText().toString(), etApellido.getText().toString(), etCumpleaños.getText().toString(), etCorreo.getText().toString(), etContraseña.getText().toString(),etCelular.getText().toString(),etIdNutricionista.getText().toString());
                    Toast.makeText(CrearPatient.this,"Usuario Registrado",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrearPatient.this, LoginPatient.class);

                    startActivity(intent);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearPatient.this,LoginPatient.class);
                startActivity(intent);
            }
        });
    }
    private void postPatiente(String name, String lastName, String birthdate, String email, String password,String phone,String id){
        Patient patient = new Patient( name,  lastName,  birthdate,  email,  password, phone);
        Long IdNutricionista = Long.parseLong(id);
        Call<Patient> call = iNutritionist.postPatienteinNutricionista(patient,IdNutricionista);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Log.e(TAG,"onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if (currentAccessToken==null)
            {
                etNombre.setText("");
                etApellido.setText("");
                etCorreo.setText("");
                Toast.makeText(CrearPatient.this,"User Logged out",Toast.LENGTH_LONG).show();
            }
            else {
                loadUserProfile(currentAccessToken);
            }

        }
    };
    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    etNombre.setText(first_name);
                    etApellido.setText(last_name);
                    etCorreo.setText(email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus()
    {
        if (AccessToken.getCurrentAccessToken()!=null){
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    public boolean validar(){
        boolean retorno  = true;

        String nombre = etNombre.getText().toString();
        String apellido =  etApellido.getText().toString();
        String cumpleaños =  etCumpleaños.getText().toString();
        String correo =  etCorreo.getText().toString();
        String contraseña =  etContraseña.getText().toString();
        String phone = etCelular.getText().toString();
        String id = etIdNutricionista.getText().toString();

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

        if (id.isEmpty()){
            etIdNutricionista.setError("Ingresa Id");
            retorno = false;
        }

        return retorno;
    }
}
