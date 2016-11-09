package com.nodry.nodry.Utils;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.nodry.nodry.Datos.Gasolinera;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los metodos necesarios para formalizar los datos
 * del JSON que devuelve el servicio REST con las estaciones de servicios.
 * @author Juan Manuel Lomas Fernandez
 * @version 1.0
 */
public class ParserJSON{

    public static final String OK_STATUS = "OK";

    /**
     * Funcion que lee el JSon con formato de cadena de bytes
     * @param in Inputsream que contiene los datos del JSON
     * @return Retorno de una lista de gasolineras contenidas en el JSon ya formalizadas
     * @throws IOException
     */
    public static List<Gasolinera> readJsonStream (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readArrayGasolineras(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Funcion que pasado el JSon de Gasolineras, nos dice si se ha recibido
     * bien y completo utilizando el campo correspondiente.
     * @param in Inputsream que contiene los datos del JSON
     * @return Verdadero si se recibio el JSon correctamente o falso en cualquier otro caso.
     * @throws IOException
     */
    public static Boolean readJsonStreamStatus (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        String status = "";

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals("ResultadoConsulta")){
                status = reader.nextString();
            }else{
                reader.skipValue();
            }

            Log.d("ENTRA", "Nombre del elemento: "+name);
        }//while
        reader.endObject();
        return status.equals(ParserJSON.OK_STATUS);
    }//readStatus

    /**
     * Funcion que pasado un lector de JSon, mapea las gasolineras
     * en sus correspondientes Objetos
     * @param reader con el elctor JSon
     * @return Listado de objetos de tipo gasolinera ya mapeados
     * @throws IOException
     */
    public static List readArrayGasolineras (JsonReader reader) throws IOException {
        List<Gasolinera> listGasolineras = new ArrayList<Gasolinera>();

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            Log.d("ENTRA", "Nombre del elemento: "+name);
            if(name.equals("ListaEESSPrecio")){
                reader.beginArray();
                while (reader.hasNext()){
                    listGasolineras.add(readGasolinera(reader));
                }//while
                reader.endArray();
            }else{
                reader.skipValue();
                //if
            }
        }//while
        reader.endObject();
        return listGasolineras;
    }//readArrayGasolineras

    /**
     * Funcion que pasado un lector de JSon, mapea una gasolinera
     * en sus correspondiente Objeto
     * @param reader con el elctor JSon
     * @return Objetos de tipo gasolinera ya mapeado
     * @throws IOException
     */
    public static Gasolinera readGasolinera (JsonReader reader) throws IOException {
        reader.beginObject();
        boolean add = false;
        String rotulo="", localidad ="", provincia="", direccion="", horario="";
        int id = -1;
        double gasoleoA = 0.0, sinplomo95 = 0.0, gasoleoB = 0.0, sinplomo98 = 0.0, latitud = 0.0, longitud = 0.0;

        while(reader.hasNext()){
            String name = reader.nextName();

            if (name.equals("Rótulo") && reader.peek() != JsonToken.NULL) {
                rotulo = reader.nextString();
            }else if (name.equals("Localidad") && reader.peek() != JsonToken.NULL) {
                localidad = reader.nextString();
            }else if(name.equals("Provincia") && reader.peek() != JsonToken.NULL){
                provincia = reader.nextString();
            }else if(name.equals("IDEESS") && reader.peek() != JsonToken.NULL){
                id = reader.nextInt();
            }else if(name.equals("Precio Gasoleo A") && reader.peek() != JsonToken.NULL) {
                gasoleoA = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals("Precio Gasolina 95 Protección") && reader.peek() != JsonToken.NULL) {
                sinplomo95 = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasoleo B") && reader.peek() != JsonToken.NULL) {
                gasoleoB = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals("Precio Gasolina 98") && reader.peek() != JsonToken.NULL) {
                sinplomo98 = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Dirección") && reader.peek() != JsonToken.NULL){
                direccion = reader.nextString();
            }else if(name.equals("Latitud") && reader.peek() != JsonToken.NULL) {
                latitud = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals("Longitud (WGS84)") && reader.peek() != JsonToken.NULL) {
                longitud = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Horario") && reader.peek() != JsonToken.NULL){
                horario = reader.nextString();
            }else{
                reader.skipValue();
            }//if

        }// while
        reader.endObject();
        return new Gasolinera(id,localidad,provincia,direccion,gasoleoA, sinplomo95, rotulo, gasoleoB, sinplomo98, horario, latitud, longitud);
    }// readGasolinera
}//ParserJSON
