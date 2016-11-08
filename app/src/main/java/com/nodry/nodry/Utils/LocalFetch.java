package com.nodry.nodry.Utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by att3mpt on 11/8/16.
 */

public class LocalFetch extends DataFetch {

    public static final String TEMP_FILE_NAME = "temp.txt";

    @Override
    public void getJSON() throws IOException {
        bufferedDataGasolineras = Utils.readFromFile(TEMP_FILE_NAME, this.context);
    }//getJSON


    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     * @return
     */
    @Override
    public BufferedInputStream getBufferedData() {
        return bufferedDataGasolineras;
    }//getBufferedDataGasolineras

}
