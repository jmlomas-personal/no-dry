package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.Negocio.IGestionGasolineras;

import java.util.List;

/**
 * Clase para la obtencion de datos de forma asincrona
 * en la aplicacion. Es necesaria ya que el hilo principal
 * no puede soportar cargas de mas de X segundos.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class GetGasolinerasTask extends AsyncTask<Void, List<Gasolinera>, List<Gasolinera>> implements INotificable {

    // Contexto de la actividad origen
    private Context context;

    // String con la comunidad de las gasolineras a obtener
    private String CCAA;

    // Adapter que se encargara de presentar los datos una vez obtenidos
    private ArrayAdapter adapter;

    // Manejador para la supervision de tiempos
    private Handler handler;

    // Tarea que se encarga de que no se sobrepase el tiempo de peticion especificado
    private CancelerTask cancelerTask;

    // Mensajes de error
    private static final String MSG_NO_CONEXION = "No hay conexion a internet";
    private static final String MSG_NO_DATA = "No hay datos que mostrar,\nintentelo mas tarde de nuevo";

    // Tiempo de peticion especificado de un minuto
    private static final long RESPONSE_DELAY = 60000;

    // Tiempo para que se vea el dialogo de carga
    private static final long TEST_DELAY = 1000;

    private IGestionGasolineras gestionGasolineras;

    /**
     * Constructor
     * @param adapter con el adaptador que visualizara los datos
     * @param context con la actividad origen de la llamada
     */
    public GetGasolinerasTask (ArrayAdapter adapter, String CCAA, Context context){
        this.adapter = adapter;
        this.CCAA = CCAA;
        this.context = context;
        this.handler = new Handler();
        this.cancelerTask = new CancelerTask(this, this.context);

        // Si la conexion no funciona mostramos el mensaje y acabamos
        if(!isNetworkAvailable(context)){
            showMessage(ERROR_MSG ,MSG_NO_CONEXION);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((ILoadable)context).startLoading();
        handler.postDelayed(cancelerTask, RESPONSE_DELAY);
    }

    @Override
    protected List<Gasolinera> doInBackground(Void... param) {
        // Para forzar a ver si funciona el dialogo de carga
        try {
            Thread.sleep(TEST_DELAY);
        } catch (InterruptedException e) {
        }

        gestionGasolineras = new GestionGasolineras();

        return gestionGasolineras.getGasolineras(CCAA);
    }

    @Override
    protected void onPostExecute(List<Gasolinera> listaGasolineras) {

        handler.removeCallbacks(cancelerTask);

        if(listaGasolineras == null || listaGasolineras.size() == 0){
            showMessage(ERROR_MSG ,MSG_NO_DATA);
        }else {
            ((IUpdateable) adapter).update(listaGasolineras);
        }

        ((ILoadable) context).stopLoading();
    }

    @Override
    public void showMessage(String title, String msg) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                //finish(); //Lanzamos el NotDataFoundActivity
                Intent intent = new Intent(context,NotDataFoundActivity.class);
                context.startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Metodo que comprueba si el dispositivo dispone de conexión a internet
     * @param context con el contexto de la aplicacion
     * @return verdadero si la conexion esta activa, falso en cualquier otro caso
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}// GetGasolineras