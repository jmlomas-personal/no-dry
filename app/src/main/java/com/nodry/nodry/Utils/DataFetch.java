package com.nodry.nodry.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Clase para la obtencion de datos externos.
 * @author Code4Fun.org
 * @version 11/2016
 */
public abstract class DataFetch {

    public static final String CACHE_FILE_NAME = "cache";

    public BufferedInputStream bufferedDataGasolineras;

    /**
     * Constructor de la clase que inicializa el buffer de datos
     */
    public DataFetch(){
        bufferedDataGasolineras = null;
    }

    /**
     * Metodo que rellena el buffer de datos desde un origen externo
     * @throws IOException en caso de no poder obtener los datos
     */
    public abstract void getJSON() throws IOException;

    /**
     * Metodo para obtener los datos que se leyeron previamente
     * @return buffer con los datos obtenidos
     * @throws IOException en caso de no poder obtener los datos
     */
    public abstract BufferedInputStream getBufferedData() throws IOException;

}
