package com.nodry.nodry.Datos;

import android.util.Log;

import com.nodry.nodry.Utils.ParserJSON;
import com.nodry.nodry.Utils.RemoteFetch;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa las funciones CRUD necesarias
 * para trabajar con las gasolineras.
 * En este caso se vuelcan los datos desde el servicio web
 * parseando el JSON devuelto por este.
 * @author Andres Barrado Martin.
 * @version 1.0
 */
public class GasolinerasDAO implements IGasolinerasDAO {

    // Listado con las gasolineras
    private List<Gasolinera> listaGasolineras;

    /**
     * Constructor
     */
    public GasolinerasDAO() {
        listaGasolineras = new ArrayList<Gasolinera>();
    }

    @Override
    public List<Gasolinera> getListGasolineras(String CCAA){

        try {
            RemoteFetch remoteFetch = new RemoteFetch(CCAA);
            remoteFetch.getJSON();
            listaGasolineras = ParserJSON.readJsonStream(remoteFetch.getBufferedDataGasolineras());
        }catch(Exception e) {
            Log.d("Error", e.toString());
        }

        return listaGasolineras;
    }

}
