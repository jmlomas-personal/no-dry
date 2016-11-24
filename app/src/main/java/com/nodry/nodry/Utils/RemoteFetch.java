package com.nodry.nodry.Utils;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Clase para la obtencion de datos desde un servicio REST.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class RemoteFetch extends DataFetch{
    //Web de ayuda con todos los filtros posibles
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    //URL para obtener todas las gasolineras
    //private static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";

    //ID de la comunidad autonoma
    private static final String URL_GASOLINERAS_CCAA    = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/";
    private static final String PROPERTY_KEY            = "Accept";
    private static final String PROPERTY_VALUE          = "application/json";

    private List<Gasolinera> gasolineraList;
    private String URL;

    /**
     * Constructor de la clase
     * @param CCAA con la comunidad autonoma a utilizar como filtro en la URL del servicio
     */
    public RemoteFetch(String CCAA){
        super();
        URL = URL_GASOLINERAS_CCAA + CCAA;
    }

    @Override
    public void getJSON() throws IOException {

        URL url = new URL(URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty(PROPERTY_KEY, PROPERTY_VALUE);
        bufferedDataGasolineras = new BufferedInputStream(urlConnection.getInputStream());

    }

    @Override
    public BufferedInputStream getBufferedData() throws IOException{

        if(bufferedDataGasolineras!=null) {
            bufferedDataGasolineras = Utils.writeToFile(bufferedDataGasolineras, CACHE_FILE_NAME, this.context);
        }

        return bufferedDataGasolineras;
    }

}
