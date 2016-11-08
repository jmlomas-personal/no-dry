package com.nodry.nodry.Utils;

import android.util.Log;

import com.nodry.nodry.Datos.Gasolinera;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Clase en la que se realizan la descarga de los
 * datos desde el servicio REST
 * @author Juan Manuel Lomas Fernandez
 * @version 1.0
 */
public class RemoteFetch extends DataFetch{
    //URL para obtener todas las gasolineras
    //private static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";

    //ID de la comunidad autonoma
    private static final String URL_GASOLINERAS_CCAA = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/";

    //ID de Santander: 5819
    //private static final String URL_GASOLINERAS_SANTANDER= "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";


    //Web de ayuda con todos los filtros posibles
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    private List<Gasolinera> gasolineraList;

    private String URL;


    /**
     * Constructor
     * @param CCAA con la comunidad autonoma por la que sacar el listado
     */
    public RemoteFetch(String CCAA){
        URL = URL_GASOLINERAS_CCAA + CCAA;
    }

    /**
     * Metodo que a través de una dirección URL obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     * @throws IOException
     */
    @Override
    public void getJSON() throws IOException {
        URL url= new URL(URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty("Accept", "application/json");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        bufferedDataGasolineras = new BufferedInputStream(urlConnection.getInputStream());
        //gasolineraList = ParserJSON.readJsonStream(new BufferedInputStream(urlConnection.getInputStream()));
    }//getJSON


    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     * @return
     */
    @Override
    public BufferedInputStream getBufferedData() {
        try {
            bufferedDataGasolineras = Utils.writeToFile(bufferedDataGasolineras, LocalFetch.TEMP_FILE_NAME, this.context);
        }
        catch(Exception e){
            Log.e("Exception", "File write failed: " + e.toString());
        }

        return bufferedDataGasolineras;
    }//getBufferedDataGasolineras

}//RemoteFetch

