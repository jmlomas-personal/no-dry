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
 * Created by Juan Manuel Lomas on 04/10/2016.
 */

// Clase para trabajar con conexiones de red como tareas asincronas
public class GetGasolinerasTask extends AsyncTask<Void, List<Gasolinera>, List<Gasolinera>> implements INotificable {

    private Context context;
    private ArrayAdapter adapter;
    private Handler handler;
    private CancelerTask cancelerTask;

    private IGestionGasolineras gestionGasolineras;

    private static final int RESPONSE_DELAY = 60000;

    public GetGasolinerasTask (ArrayAdapter adapter, Context context){
        this.adapter = adapter;
        this.context = context;
        this.handler = new Handler();
        this.cancelerTask = new CancelerTask(this, this.context);

        if(!isNetworkAvailable(context)){
            showMessage();
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
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }

        gestionGasolineras = new GestionGasolineras();

        return gestionGasolineras.getGasolineras();
    }

    @Override
    protected void onPostExecute(List<Gasolinera> listaGasolineras) {
        ((IUpdateable)adapter).update(listaGasolineras);
        handler.removeCallbacks(cancelerTask);
        ((ILoadable)context).stopLoading();
    }

    @Override
    public void showMessage() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Error");
        builder.setMessage("No hay conexión a internet");
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

    //Metodo que comprueba si el dispositivo dispone de conexión a internet
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}// GetGasolineras