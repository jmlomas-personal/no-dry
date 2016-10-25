package com.nodry.nodry.Presentacion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unican.juanmanuellomas.nodry.Datos.Gasolinera;
import com.unican.juanmanuellomas.nodry.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadable {

    //TextView textView;
    ListView listView;
    ProgressDialog progress;

    //create our new array adapter
    ArrayAdapter<Gasolinera> adapter;

    //booleano para ver si ya ha empezado a cargar
    Boolean loading = false;

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
        //synchronized (this) {
        //if(!loading) {
        this.progress = ProgressDialog.show(this, "", "Cargando...");
        loading = true;
        //}
        //}
    }

    @Override
    public void stopLoading() {
        //synchronized (this) {
        //if(loading) {
        this.progress.dismiss();
        loading = false;
        //}
        //}
    }
}
