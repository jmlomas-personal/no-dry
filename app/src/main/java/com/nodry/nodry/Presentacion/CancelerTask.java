package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

/**
 * Clase para la cancelacion de una tarea.
 * Monitoriza el tiempo de ejecucion de otra tarea.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class CancelerTask implements Runnable, INotificable {

    // Tarea a controlar
    private AsyncTask mainTask;

    // Contexto de la aplicacion
    private Context context;

    /**
     * Constructor de la clase
     * @param mainTask con la tarea a monitorizar
     * @param context con el contexto de la aplicacion
     */
    public CancelerTask(AsyncTask mainTask, Context context){
        this.mainTask = mainTask;
        this.context = context;
    }

    @Override
    public void run(){

        if(mainTask.getStatus() == AsyncTask.Status.RUNNING) {
            mainTask.cancel(true);
        }

        ((ILoadable)context).stopLoading();
        showMessage();
    }

    @Override
    public void showMessage() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Error");
        builder.setMessage("Se ha sobrepasado el tiempo de petición.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                //finish();
                //Lanzamos el NotDataFoundActivity
                Intent intent = new Intent(context,NotDataFoundActivity.class);
                context.startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
