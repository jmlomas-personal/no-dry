package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Comunes.Presentacion.IFormateable;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.Comunes.Negocio.IGestionGasolineras;
import com.nodry.nodry.Utils.TipoError;
import com.nodry.nodry.Utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.nodry.nodry.Utils.TipoError.ERROR;
import static com.nodry.nodry.Utils.TipoError.INFORMACION;
import static com.nodry.nodry.Utils.TipoError.OK;

/**
 * Clase para manejar tareas asincronas
 * encargadas de realizar peticiones en segundo
 * plano utilizando una conexion de datos.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class GetGasolinerasTask extends AsyncTask<Void, List<Gasolinera>, List<Gasolinera>>{

    // Con el contexto de la aplicacion
    private Context context;
    // Con la tarea de monitorizacion
    private CancelerTask cancelerTask;
    private Handler handler;

    // Mensajes para posibles errores
    private static final String ERROR_TITLE                 = "ERROR";
    private static final String ERROR_MSG_TIME_EXCEEDED     = "Se ha sobrepasado el tiempo de petición al servicio.";
    private static final String ERROR_MSG_FILE_PROCESSING   = "Se produjo un error al tratar los datos.";
    private static final String ERROR_MSG_FILE_NOT_FOUND    = "Error al intentar guardar en el fichero.";
    private static final String ERROR_NO_INTERNET_CONEXION  = "No hay conexión de datos.";

    private static final long RESPONSE_DELAY = 60000;   // Tiempo de respuesta permitido

    // Atributos de la clase
    private IGestionGasolineras gestionGasolineras;
    private String CCAA;
    private String errorMsg = "";
    private TipoError tipoError = OK;

    /**
     * Constructor de la clase
     * @param context con el contexto de la aplicacion
     * @param CCAA con la comunidad autonoma a utilizar para realizar la peticion de datos
     */
    public GetGasolinerasTask (Context context, String CCAA){
        this.CCAA = CCAA;
        this.context = context;
        this.handler = new Handler();
        this.cancelerTask = new CancelerTask(this);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        handler.postDelayed(cancelerTask, RESPONSE_DELAY);
    }

    @Override
    protected List<Gasolinera> doInBackground(Void... param) {
        List<Gasolinera> listaGasolineras = null;

        if(Utils.isNetworkAvailable(this.context)) {

            gestionGasolineras = new GestionGasolineras();

            try {
                listaGasolineras = gestionGasolineras.obtenGasolinerasServicioRest(CCAA);
            } catch (FileNotFoundException e) {
                tipoError = ERROR;
                errorMsg = ERROR_MSG_FILE_NOT_FOUND;
            } catch (IOException e) {
                tipoError = ERROR;
                errorMsg = ERROR_MSG_FILE_PROCESSING;
            }
        }else{
            tipoError = ERROR;
            errorMsg = ERROR_NO_INTERNET_CONEXION;
        }

        return listaGasolineras;
    }

    @Override
    protected void onPostExecute(List<Gasolinera> listaGasolineras) {

        handler.removeCallbacks(cancelerTask);
        ((IFormateable) context).retrieveData(errorMsg, tipoError, listaGasolineras);

    }

    @Override
    protected void onCancelled() {

        tipoError = INFORMACION;
        errorMsg = ERROR_MSG_TIME_EXCEEDED;
        ((IFormateable) context).retrieveData(errorMsg, tipoError, null);

    }

}