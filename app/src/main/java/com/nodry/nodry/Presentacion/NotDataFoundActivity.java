package com.nodry.nodry.Presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nodry.nodry.R;

/**
 * Actividad que muestra un error a la hora de obtener los datos.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class NotDataFoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_data_found);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.por_defecto);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

}