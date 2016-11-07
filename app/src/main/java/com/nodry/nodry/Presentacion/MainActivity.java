package com.nodry.nodry.Presentacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.IGasolinerasDAO;
import com.nodry.nodry.R;

import java.util.ArrayList;

/**
 * Actividad principal de la aplicacion.
 * Muestra el listado de gasolineras.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements ILoadable {

    TextView textView;
    ListView listView;
    ProgressDialog progress;

    //create our new array adapter
    ArrayAdapter<Gasolinera> adapter;

    GetGasolinerasTask getGasolinerasTask;
    Intent intent;
    String CCAA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Editamos la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //If que comprueba la conectividad a internet y llama al hilo GetGasolinerasTask para la obtención de la lista.
        listView = (ListView) findViewById(R.id.customListView);
        adapter = new GasolinerasArrayAdapter(this, 0, new ArrayList<Gasolinera>());
        listView.setAdapter(adapter);

        // Cargamos la CCAA retornada por los filtros o en su defecto, por defecto
        intent = getIntent();
        CCAA = intent.getStringExtra("CCAA");

        if(CCAA == null || CCAA.trim().equals("")){
            CCAA = IGasolinerasDAO.DEFAULT_CCAA;
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        refresh();
    }
    @Override
    public void conexionIncorrecta(String mensaje){
        textView=(TextView) findViewById(R.id.textViewError);
        textView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        textView.setText(mensaje);
    }
    /**
     * Metodo para actualizar el listado de gasolineras
     */
    private void refresh()
    {
        if(listView.getVisibility()==View.GONE){
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        // Obtenemos el listado de Gasolineras
        getGasolinerasTask = new GetGasolinerasTask(adapter, CCAA, this);
        getGasolinerasTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filtros) {
            openFiltrosActivity();
        }
        if(id== R.id.action_actualizar){
            refresh();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que abre la pantalla de fltros
     */
    private void openFiltrosActivity(){
        Intent myIntent = new Intent(this, FiltersActivity.class);
        myIntent.putExtra("CCAA", CCAA); //Optional parameters
        this.startActivity(myIntent);
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
