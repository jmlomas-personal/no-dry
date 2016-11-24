package com.nodry.nodry.Presentacion;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Comunes.Presentacion.IFormateable;
import com.nodry.nodry.Comunes.Presentacion.ILoadable;
import com.nodry.nodry.Comunes.Presentacion.INotificable;
import com.nodry.nodry.Comunes.Presentacion.IUpdateable;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.R;
import com.nodry.nodry.Utils.TipoError;
import com.nodry.nodry.Utils.TipoGasolina;
import com.nodry.nodry.Utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Clase de la actividad principal de
 * la aplicacion.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class MainActivity extends AppCompatActivity implements ILoadable, INotificable, IFormateable, AdapterView.OnItemClickListener {

    private static List<Gasolinera> listaGasolineras;
    private static String CCAA;

    TextView textView;
    ListView listView;
    ProgressDialog progress;

    //create our new array adapter
    ArrayAdapter<Gasolinera> adapter;

    GetGasolinerasTask getGasolinerasTask;
    GestionGasolineras gestionGasolineras;
    Intent intent;
    String PRECIO;
    Double MAXVALUE;

    // Mensajes de la apliacion
    private static final String ERROR_TITLE                 = "Ooops! :(";
    private static final String ERROR_MSG_SERVICE_UPDATING  = "El servicio se esta actualizando y no devuelve datos.";
    private static final String ERROR_NO_DATA_FOUND         = "No se encontraron datos\nInténtelo de nuevo más tarde.";
    private static final String ERROR_MSG_FILE_PROCESSING   = "Se produjo un error al tratar los datos.";
    private static final String INFORMATION_MSG_LOCAL       = "El sistema esta mostrando datos almacenados en local.";
    private static final String INFORMATION_MSG_MINVALUE    = "No hay gasolineras con un coste por debajo del filtro";

    private static final String TITLE_ORDER_FILTER          = "Tipo de Carburante";
    private static final String MSG_LOADING                 = "Cargando...";
    private static final String OPTION_TEXT                 = "Continuar";
    private static final String DEFAULT_CCAA                = "06";

    // Campos para realizar llamadas a otras actividades
    private static final String EXTRA_CCAA                  = "CCAA";
    private static final String EXTRA_IDEESS                = "IDEESS";
    private static final String EXTRA_PRECIO                = "PRECIO";
    private static final String EXTRA_MAXVALUE              = "MAXVALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.setContext(this);
        gestionGasolineras = new GestionGasolineras();
        boolean bForce = true;
        String NEW_CCAA;

        //Editamos la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //If que comprueba la conectividad a internet y llama al hilo GetGasolinerasTask para la obtención de la lista.
        textView = (TextView) findViewById(R.id.textViewError);
        listView = (ListView) findViewById(R.id.customListView);
        adapter = new GasolinerasArrayAdapter(this, 0, new ArrayList<Gasolinera>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        // Cargamos la CCAA retornada por los filtros o en su defecto, por defecto
        intent = getIntent();

        PRECIO = intent.getStringExtra(EXTRA_PRECIO);
        MAXVALUE = intent.getDoubleExtra(EXTRA_MAXVALUE, 0.0);

        NEW_CCAA = intent.getStringExtra(EXTRA_CCAA);

        if(NEW_CCAA == null || NEW_CCAA.trim().equals("") ){
            NEW_CCAA = DEFAULT_CCAA;
        }

        if(CCAA!=null && !CCAA.trim().equals("")){
            if(CCAA.equals(NEW_CCAA)){
                bForce = false;
            }
        }

        MainActivity.setCCAA(NEW_CCAA);

        refreshData(bForce);
    }

    // Setters estaticos
    public static void setCCAA(String CCAA){
        MainActivity.CCAA = CCAA;
    }

    public static void setListaGasolineras(List<Gasolinera> lista){
        MainActivity.listaGasolineras = lista;
    }

    public static List<Gasolinera> getListaGasolineras(){
       return listaGasolineras;
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
        if(id == R.id.action_actualizar){
            PRECIO = null;
            refreshData(true);
        }
        if(id == R.id.action_ordenar){
            openOrderDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que abre la pantalla de fltros
     */
    private void openFiltrosActivity(){
        Intent myIntent = new Intent(this, FiltersActivity.class);
        myIntent.putExtra(EXTRA_CCAA,       CCAA); //Optional parameters
        myIntent.putExtra(EXTRA_PRECIO,     PRECIO); //Optional parameters
        myIntent.putExtra(EXTRA_MAXVALUE,   MAXVALUE); //Optional parameters
        this.startActivity(myIntent);
    }

    /**
     * Metodo que abre el dialogo de ordenacion
     */
    private void openOrderDialog(){

        final String tiposGasolina[] = new String[]{
                TipoGasolina.SINPLOMO95.getTexto(),
                TipoGasolina.SINPLOMO98.getTexto(),
                TipoGasolina.DIESEL.getTexto(),
                TipoGasolina.DIESELPLUS.getTexto()
        };

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(TITLE_ORDER_FILTER);

        b.setItems(tiposGasolina, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                PRECIO = tiposGasolina[which];
                MAXVALUE = null;

                formatData();
            }

        });

        b.show();
    }

    @Override
    public void startLoading() {
        textView.setVisibility(View.GONE);
        this.progress = ProgressDialog.show(this, "", MSG_LOADING);
    }

    @Override
    public void refreshData(boolean forceUpdate) {

        if(!forceUpdate &&
                listaGasolineras == null){
            forceUpdate = true;
        }

        if(forceUpdate) {
            startLoading();

            PRECIO = null;
            MAXVALUE = null;

            getGasolinerasTask = new GetGasolinerasTask(this, CCAA);
            getGasolinerasTask.execute();
        }else{
            formatData();
        }

    }

    @Override
    public void retrieveData(String errorMsg, TipoError tipoError, Collection<?> data) {

        String _msgError = errorMsg;
        TipoError _tipoError = tipoError;

        if(tipoError != TipoError.OK){
            showAlert(_msgError);
            _msgError = "";
            _tipoError = TipoError.OK;
        }

        if(data != null && !data.isEmpty()){
            MainActivity.setListaGasolineras((List<Gasolinera>) data);
        }else{
            if(data != null && data.isEmpty()){
                showAlert(ERROR_MSG_SERVICE_UPDATING);
            }

            try {
                MainActivity.setListaGasolineras(gestionGasolineras.obtenGasolinerasCache());
                _tipoError = TipoError.INFORMACION;
                _msgError = INFORMATION_MSG_LOCAL;
            } catch (FileNotFoundException e) {
                _tipoError = TipoError.ERROR;
                _msgError = ERROR_NO_DATA_FOUND;
            } catch (IOException e) {
                _tipoError = TipoError.ERROR;
                _msgError = ERROR_MSG_FILE_PROCESSING;
            }

            if(_tipoError == TipoError.ERROR){
                showMessage(ERROR_TITLE, _msgError);
            }else if(_tipoError == TipoError.INFORMACION){
                showAlert(_msgError);
            }

        }

        if(listaGasolineras != null &&
                !listaGasolineras.isEmpty()) {
            MainActivity.setCCAA(listaGasolineras.get(0).getIDCCAA());
            formatData();
        }

        stopLoading();
    }

    @Override
    public void formatData() {

        TipoGasolina tipoGasolina = null;
        List<Gasolinera> listaModificable = new ArrayList<Gasolinera>(listaGasolineras);

        if(PRECIO != null &&
                !PRECIO.trim().equals("")) {

            if(PRECIO.equals(TipoGasolina.SINPLOMO95.getTexto())){
                tipoGasolina = TipoGasolina.SINPLOMO95;
            }else if(PRECIO.equals(TipoGasolina.SINPLOMO98.getTexto())){
                tipoGasolina = TipoGasolina.SINPLOMO98;
            }else if(PRECIO.equals(TipoGasolina.DIESEL.getTexto())) {
                tipoGasolina = TipoGasolina.DIESEL;
            }else if(PRECIO.equals(TipoGasolina.DIESELPLUS.getTexto())){
                tipoGasolina = TipoGasolina.DIESELPLUS;
            };

            Utils.removeZeroValue(tipoGasolina, listaModificable);

            if (MAXVALUE != null && MAXVALUE > 0) {

                if (Utils.getMasBarata(tipoGasolina, listaModificable) > MAXVALUE) {
                    showMessage(ERROR_TITLE, INFORMATION_MSG_MINVALUE);
                }

                Utils.removeMaxValue(MAXVALUE, tipoGasolina, listaModificable);
            }

            ordenarPorPrecio(listaModificable, tipoGasolina);
        }

        ((IUpdateable)this.adapter).update(listaModificable);
    }

    @Override
    public void stopLoading() {
        this.progress.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent myIntent = new Intent(this, DetailsActivity.class);
        myIntent.putExtra(EXTRA_CCAA,   CCAA);
        myIntent.putExtra(EXTRA_IDEESS, ((GasolinerasArrayAdapter)adapter).getListaGasolinerasMostradas().get((int)l).getIDEESS());

        this.startActivity(myIntent);
    }

    @Override
    public void showMessage(String title, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(OPTION_TEXT, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();

                textView.setVisibility(View.VISIBLE);
                textView.setText(ERROR_NO_DATA_FOUND);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showAlert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void ordenarPorPrecio(List<Gasolinera> listaGasolineras, TipoGasolina tipoGasolina){
        Collections.sort(listaGasolineras, new PrecioSort(tipoGasolina));
    }
}
