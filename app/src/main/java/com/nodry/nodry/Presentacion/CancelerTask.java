package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

/**
 * Created by Manu on 23/10/2016.
 */

public class CancelerTask implements Runnable, INotificable {

    private AsyncTask mainTask;
    private Context context;

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
