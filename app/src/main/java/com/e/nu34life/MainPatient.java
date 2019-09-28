package com.e.nu34life;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

public class MainPatient extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient2);
        email = getIntent().getStringExtra("Correo");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

    }



    private boolean loadFragment(Fragment fragment){
        if (fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.navigation_salud:
                fragment = new SaludFragma();
                break;
            case R.id.navigation_perfil:
                fragment = new PerfilFragma();
                break;
            case R.id.navigation_comida:
                fragment = new ComidasFragment();
                break;
        }

        return loadFragment(fragment);
    }


}
