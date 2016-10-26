package com.nodry.nodry.Presentacion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.R;

import java.util.ArrayList;

/**
 * Actividad principal de la aplicacion.
 * Muestra el listado de gasolineras.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements ILoadable {

    //TextView textView;
    ListView listView;
    ProgressDialog progress;

    //create our new array adapter
    ArrayAdapter<Gasolinera> adapter;

    GetGasolinerasTask getGasolinerasTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.por_defecto);
        setSupportActionBar(toolbar);

        //If que comprueba la conectividad a internet y llama al hilo GetGasolinerasTask para la obtención de la lista.
        listView = (ListView) findViewById(R.id.customListView);
        adapter = new GasolinerasArrayAdapter(this, 0, new ArrayList<Gasolinera>());
        listView.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        refresh();
    }

    private void refresh()
    {
        // Obtenemos el listado de Gasolineras
        getGasolinerasTask = new GetGasolinerasTask(adapter, this);
        getGasolinerasTask.execute();
    }

    @Override
    public void startLoading() {
        this.progress = ProgressDialog.show(this, "", "Cargando...");
    }

    @Override
    public void stopLoading() {
        this.progress.dismiss();
    }
}
