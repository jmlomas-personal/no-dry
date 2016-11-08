package com.nodry.nodry.Utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;


public abstract class DataFetch {

    public BufferedInputStream bufferedDataGasolineras;
    public static Context context;

    /**
     * Metodo que a través de una dirección URL o un fichero obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     * @throws IOException
     */
    public abstract void getJSON() throws IOException;

    public abstract BufferedInputStream getBufferedData();

    public static void setContext(Context context){
        RemoteFetch.context = context;
    }

}
