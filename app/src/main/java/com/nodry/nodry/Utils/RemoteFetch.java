package com.nodry.nodry.Utils;

import android.content.Context;
import android.util.Log;

import com.nodry.nodry.Datos.Gasolinera;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Clase en la que se realizan la descarga de los
 * datos desde el servicio REST
 * @author Juan Manuel Lomas Fernandez
 * @version 1.0
 */
public class RemoteFetch {
    //URL para obtener todas las gasolineras
    //private static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";

    //ID de la comunidad autonoma
    private static final String URL_GASOLINERAS_CCAA = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/";

    //ID de Santander: 5819
    //private static final String URL_GASOLINERAS_SANTANDER= "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";


    //Web de ayuda con todos los filtros posibles
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    private List<Gasolinera> gasolineraList;

    private BufferedInputStream bufferedDataGasolineras;
    private String URL;
    public static Context context;

    /**
     * Constructor
     * @param CCAA con la comunidad autonoma por la que sacar el listado
     */
    public RemoteFetch(String CCAA){
        URL = URL_GASOLINERAS_CCAA + CCAA;
    }
    public static void setContext(Context context){
        RemoteFetch.context = context;
    }

    /**
     * Metodo que a través de una dirección URL obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     * @throws IOException
     */
    public void getJSON() throws IOException {
        URL url= new URL(URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty("Accept", "application/json");
        //BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        bufferedDataGasolineras = new BufferedInputStream(urlConnection.getInputStream());
        //gasolineraList = ParserJSON.readJsonStream(new BufferedInputStream(urlConnection.getInputStream()));
    }//getJSON

    private void writeToFile() {

        try {

            byte[] contents = new byte[1024];

            int bytesRead = 0;
            String strFileContents = "";
            while((bytesRead = bufferedDataGasolineras.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(RemoteFetch.context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(strFileContents);
            outputStreamWriter.close();

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");
            bufferedDataGasolineras = new BufferedInputStream(inputStream);
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }
    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     * @return
     */
    public BufferedInputStream getBufferedDataGasolineras() {
        try {
            writeToFile();
        }
        catch(Exception e){
            Log.e("Exception", "File write failed: " + e.toString());
        }
        try {
            readFromFile();
        }
        catch(Exception e){
            System.out.println("Fallo");
        }
        return bufferedDataGasolineras;
    }//getBufferedDataGasolineras

}//RemoteFetch

