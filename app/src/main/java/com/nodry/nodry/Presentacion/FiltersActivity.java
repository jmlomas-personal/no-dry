package com.nodry.nodry.Presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.nodry.nodry.R;
import com.nodry.nodry.Utils.TipoGasolina;
import com.nodry.nodry.Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase de la actividad que muestra los
 * filtros de la apliacion.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class FiltersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Intent intent;
    String CCAA;
    String PRECIO;
    Double MAXVALUE;
    Spinner spinner;
    Spinner spinner2;
    TextView maximo;
    Button btnFiltrar;

    private static final ArrayList<String> tiposGasolina = new ArrayList<String>(Arrays.asList(
            TipoGasolina.SINPLOMO95.getTexto(),
            TipoGasolina.SINPLOMO98.getTexto(),
            TipoGasolina.DIESEL.getTexto(),
            TipoGasolina.DIESELPLUS.getTexto()
    ));

    // Mensajes de error
    private final static String MSG_FILTER_ERROR_CCAA       = "Seleccione una CCAA valida";
    private final static String MSG_FILTER_ERROR_COMBUS     = "Seleccione un valor máximo";
    private final static String MSG_FILTER_ERROR_NEG        = "El número introducido es negativo";
    private final static String MSG_FILTER_FILL_DATA        = "Introduzca un tipo de gasolina";

    private static final String EMPTY_SPINNER_TEXT          = "Seleccione...";

    // Campos para realizar llamadas a otras actividades
    private static final String EXTRA_CCAA                  = "CCAA";
    private static final String EXTRA_PRECIO                = "PRECIO";
    private static final String EXTRA_MAXVALUE              = "MAXVALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        btnFiltrar = (Button) findViewById(R.id.button_filtrar);
        btnFiltrar.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner_CCAA);
        spinner.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner_TiposGasolina);
        spinner2.setOnItemSelectedListener(this);

        maximo = (TextView) findViewById(R.id.editText_maximo);

        // Create an ArrayAdapter using a HashMap
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add(EMPTY_SPINNER_TEXT);
        adapter.addAll(Utils.getRestCCAAAAsList());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Recuperamos la CCAA
        intent = getIntent();
        CCAA = intent.getStringExtra(EXTRA_CCAA);
        PRECIO = intent.getStringExtra(EXTRA_PRECIO);
        MAXVALUE = intent.getDoubleExtra(EXTRA_MAXVALUE, 0.0);

        spinner.setSelection(adapter.getPosition(Utils.getRestCCAAAByID(CCAA)));

        //Añadido
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter2.add(EMPTY_SPINNER_TEXT);
        adapter2.addAll(tiposGasolina);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setSelection(adapter2.getPosition(PRECIO));

        if(PRECIO!=null && MAXVALUE>0) {
            maximo.setText(MAXVALUE.toString());
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.spinner_CCAA: {
                CCAA = Utils.getRestCCAAAByValue(parent.getItemAtPosition(pos).toString());
                break;
            }
            case R.id.spinner_TiposGasolina: {
                if(tiposGasolina.contains(parent.getItemAtPosition(pos).toString())){
                    PRECIO = parent.getItemAtPosition(pos).toString();
                }else{
                    PRECIO = null;
                }
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_filtrar: {

                if(!maximo.getText().toString().trim().equals("")) {
                    MAXVALUE = Double.parseDouble(maximo.getText().toString());
                }else{
                    MAXVALUE = null;
                }

                if(validateFilters()) {
                    openMainActivity();
                }

                break;
            }
        }
    }

    /**
     * Metodo que comprueba si los campos de filtro estan bien informados
     * @return verdadero si los campos han sido bien informados,
     * falso en cualquier otro caso.
     */
    private boolean validateFilters(){

        boolean bOk = true;
        String msg = "";

        if(CCAA == null){
            msg = MSG_FILTER_ERROR_CCAA;
            spinner.requestFocus();
            bOk = false;
        }

        if(PRECIO == null && MAXVALUE != null && MAXVALUE != 0.0){
            msg = MSG_FILTER_FILL_DATA;
            maximo.requestFocus();
            bOk = false;
        }

        if(PRECIO != null && (MAXVALUE == null || MAXVALUE == 0.0)){
            msg = MSG_FILTER_ERROR_COMBUS;
            maximo.requestFocus();
            bOk = false;
        }else if(PRECIO != null && MAXVALUE < 0.0){
            msg = MSG_FILTER_ERROR_NEG;
            maximo.requestFocus();
            bOk = false;
        }

        if(!bOk){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
        return bOk;
    }

    /**
     * Metodo que abre la pantalla principal
     */
    private void openMainActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra(EXTRA_CCAA, CCAA); //Optional parameters
        myIntent.putExtra(EXTRA_PRECIO, PRECIO);
        myIntent.putExtra(EXTRA_MAXVALUE, MAXVALUE);
        this.startActivity(myIntent);
    }
}
