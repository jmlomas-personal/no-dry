package com.nodry.nodry.Presentacion;

import android.os.AsyncTask;

/**
 * Clase para manejar tareas asincronas
 * encargadas de eliminar otras tareas que
 * excedieron su tiempo.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class CancelerTask implements Runnable {

    // La tarea a ser monitorizada
    private AsyncTask mainTask;

    /**
     * Constructor de la clase
     * @param mainTask con la tarea a monitorizar
     */
    public CancelerTask(AsyncTask mainTask){
        this.mainTask = mainTask;
    }

    @Override
    public void run(){
        if(mainTask.getStatus() == AsyncTask.Status.RUNNING) {
            mainTask.cancel(true);
        }
    }

}
