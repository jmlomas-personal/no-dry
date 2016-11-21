package com.nodry.nodry.Presentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.nodry.nodry.R;
import com.nodry.nodry.Utils.Utils;

/**
 * Actividad donde se ubican los filtros de
 * la aplicacion.
 * @author Juan Manuel Lomas Fernandez.
 * @version 1.0
 */
public class FiltersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Intent intent;
    String CCAA;
    double precioMaximo;
    String tipoCombustible;
    Spinner spinner;
    Spinner spinner2;
    Button btnFiltrar;

    // Mensajes de error
    private final static String MSG_FILTER_ERROR_CCAA = "Seleccione una CCAA valida";
    private final static String MSG_FILTER_ERROR_COMBUS="Seleccione un valor máximo";
    private final static String MSG_FILTER_ERROR_NEG="El número introducido es negativo";
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

        spinner2=(Spinner) findViewById(R.id.spinner_Filtros);
        spinner2.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using a HashMap
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("Seleccione...");
        adapter.addAll(Utils.getRestCCAAAAsList());
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Recuperamos la CCAA
        intent = getIntent();
        CCAA = intent.getStringExtra("CCAA");


        spinner.setSelection(adapter.getPosition(Utils.getRestCCAAAByID(CCAA)));

        //Añadido
        ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adapter1.add("Seleccione...");
        adapter1.addAll(Utils.getRestCombustibleList());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        CCAA = Utils.getRestCCAAAByValue(parent.getItemAtPosition(pos).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_filtrar: {
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
        if(tipoCombustible!=null && precioMaximo==0.0){
            msg=MSG_FILTER_ERROR_COMBUS;
            spinner.requestFocus();
            bOk=false;
        }
        if(tipoCombustible!=null && precioMaximo<0.0){
            msg=MSG_FILTER_ERROR_NEG;
            spinner.requestFocus();
            bOk=false;
        }

        if(!bOk){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

        return bOk;
    }


    /**
     * Metodo que abre la pantalla principal
     */
    private void openMainActivity(){
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("CCAA", CCAA); //Optional parameters
        this.startActivity(myIntent);
    }
}
