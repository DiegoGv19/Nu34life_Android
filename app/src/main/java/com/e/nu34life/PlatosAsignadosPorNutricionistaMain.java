package com.e.nu34life;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adaptador.AdaptadorComidas;
import Interface.IRecipe;
import Model.ApiClient;
import Model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatosAsignadosPorNutricionistaMain extends AppCompatActivity {
    private List<Recipe> listRecipe;

    private String dia,turno;
    private String Contraseña;
    private String Correo;
    private ListView listViewComidas;
    private TextView tvdia, tvhorario;
    private IRecipe iRecipe;

    private Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos_asignados_por_nutricionista_main);

        iRecipe = ApiClient.getRetrofit().create(IRecipe.class);

        dia = getIntent().getStringExtra("dia");
        Correo = getIntent().getStringExtra("Correo");
        Contraseña = getIntent().getStringExtra("Contraseña");
        turno = getIntent().getStringExtra("horario");

        listViewComidas = (ListView) findViewById(R.id.listViewComidas);

        tvdia = (TextView) findViewById(R.id.tvDiaComida);
        tvhorario = (TextView) findViewById(R.id.tvHorarioComida);

        btnAgregar = (Button) findViewById(R.id.btnAgregarPlatoNuevo);

        tvdia.setText(dia);
        tvhorario.setText(turno);
        getRecipe();

        listViewComidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = listRecipe.get(position);
                Intent intent = new Intent(PlatosAsignadosPorNutricionistaMain.this, InformacionPlatoSeleccionadoNutricionista.class);
                intent.putExtra("dia", dia);
                intent.putExtra("turno", turno);
                intent.putExtra("Correo",Correo);
                intent.putExtra("Contraseña",Contraseña);
                intent.putExtra("PlatoId",recipe.getId().toString());
                startActivity(intent);

            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatosAsignadosPorNutricionistaMain.this, ComidaAComer.class);
                intent.putExtra("dia", dia);
                intent.putExtra("turno", turno);
                intent.putExtra("Correo",Correo);
                intent.putExtra("Contraseña",Contraseña);
                startActivity(intent);
            }
        });
    }

    private void getRecipe(){

        Call<List<Recipe>> call = iRecipe.getRecipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(!response.isSuccessful()){

                    return;
                }

                List<Recipe> listrecipe = response.body();
                listRecipe = listrecipe;
                if (listrecipe != null) {
                    final AdaptadorComidas miadaptador = new AdaptadorComidas(getApplicationContext(), listrecipe);
                    listViewComidas.setAdapter(miadaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

    }
}
