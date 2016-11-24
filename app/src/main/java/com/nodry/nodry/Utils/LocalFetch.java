package com.nodry.nodry.Utils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

/**
 * Clase para la obtencion de datos desde un fichero local de la aplicacion.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class LocalFetch extends DataFetch {

    /**
     * Constructor de la clase
     */
    public LocalFetch(){
        super();
    }

    @Override
    public void getJSON() throws FileNotFoundException {
        bufferedDataGasolineras = Utils.readFromFile(CACHE_FILE_NAME, this.context);
    }

    @Override
    public BufferedInputStream getBufferedData() {
        return bufferedDataGasolineras;
    }

}
