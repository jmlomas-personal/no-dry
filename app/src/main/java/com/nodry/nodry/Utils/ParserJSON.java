package com.nodry.nodry.Utils;

import android.util.JsonReader;
import android.util.JsonToken;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el parseado de datos de un fichero JSon
 * en un listado de objetos de tipo gasolinera.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class ParserJSON{

    private static final String CHARSET_UTF             = "UTF-8";
    private static final String OK_STATUS               = "OK";
    private static final String FIELD_STATUS            = "ResultadoConsulta";
    private static final String FIELD_ARRAY_GASOLINERAS = "ListaEESSPrecio";
    private static final String FIELD_ROTULO            = "Rótulo";
    private static final String FIELD_LOCALIDAD         = "Localidad";
    private static final String FIELD_MUNICIPIO         = "Municipio";
    private static final String FIELD_PROVINCIA         = "Provincia";
    private static final String FIELD_IDEESS            = "IDEESS";
    private static final String FIELD_IDCCAA            = "IDCCAA";
    private static final String FIELD_SINPLOMO95        = "Precio Gasolina 95 Protección";
    private static final String FIELD_DIESEL            = "Precio Gasoleo A";
    private static final String FIELD_DIESELPLUS        = "Precio Nuevo Gasoleo A";
    private static final String FIELD_SINPLOMO98        = "Precio Gasolina  98";
    private static final String FIELD_DIRECCION         = "Dirección";
    private static final String FIELD_LATITUD           = "Latitud";
    private static final String FIELD_LONGITUD          = "Longitud (WGS84)";
    private static final String FIELD_HORARIO           = "Horario";

    /**
     * Funcion que lee el JSon con formato de cadena de bytes
     * @param in Inputsream que contiene los datos del JSON
     * @return Retorno de una lista de gasolineras contenidas en el JSon ya formalizadas
     * @throws IOException
     */
    public static List<Gasolinera> readJsonStream (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, CHARSET_UTF));
        return readArrayGasolineras(reader);
    }

    /**
     * Funcion que pasado el JSon de Gasolineras, nos dice si se ha recibido
     * bien y completo utilizando el campo correspondiente.
     * @param in Inputsream que contiene los datos del JSON
     * @return Verdadero si se recibio el JSon correctamente o falso en cualquier otro caso.
     * @throws IOException
     */
    public static Boolean readJsonStreamStatus (InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, CHARSET_UTF));
        String status = "";

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if(name.equals(FIELD_STATUS)){
                status = reader.nextString();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();
        return status.equals(ParserJSON.OK_STATUS);
    }

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
            if(name.equals(FIELD_ARRAY_GASOLINERAS)){
                reader.beginArray();
                while (reader.hasNext()){
                    listGasolineras.add(readGasolinera(reader));
                }
                reader.endArray();
            }else{
                reader.skipValue();
            }
        }

        reader.endObject();
        reader.close();

        return listGasolineras;
    }

    /**
     * Funcion que pasado un lector de JSon, mapea una gasolinera
     * en sus correspondiente Objeto
     * @param reader con el elctor JSon
     * @return Objetos de tipo gasolinera ya mapeado
     * @throws IOException
     */
    public static Gasolinera readGasolinera (JsonReader reader) throws IOException {

        String rotulo="", localidad ="", provincia="", direccion="", horario="", municipio = "", idccaa = "";
        double gasoleoA = 0.0, sinplomo95 = 0.0, gasoleoB = 0.0, sinplomo98 = 0.0, latitud = 0.0, longitud = 0.0;
        int id = -1;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();

            if (name.equals(FIELD_ROTULO) && reader.peek() != JsonToken.NULL) {
                rotulo = reader.nextString();
            }else if (name.equals(FIELD_LOCALIDAD) && reader.peek() != JsonToken.NULL) {
                localidad = reader.nextString();
            }else if (name.equals(FIELD_MUNICIPIO) && reader.peek() != JsonToken.NULL) {
                municipio = reader.nextString();
            }else if(name.equals(FIELD_PROVINCIA) && reader.peek() != JsonToken.NULL){
                provincia = reader.nextString();
            }else if(name.equals(FIELD_IDEESS) && reader.peek() != JsonToken.NULL){
                id = reader.nextInt();
            }else if(name.equals(FIELD_IDCCAA) && reader.peek() != JsonToken.NULL){
                idccaa = reader.nextString();
            }else if(name.equals(FIELD_DIESEL) && reader.peek() != JsonToken.NULL) {
                gasoleoA = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals(FIELD_SINPLOMO95) && reader.peek() != JsonToken.NULL) {
                sinplomo95 = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals(FIELD_DIESELPLUS) && reader.peek() != JsonToken.NULL) {
                gasoleoB = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals(FIELD_SINPLOMO98) && reader.peek() != JsonToken.NULL) {
                sinplomo98 = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals(FIELD_DIRECCION) && reader.peek() != JsonToken.NULL){
                direccion = reader.nextString();
            }else if(name.equals(FIELD_LATITUD) && reader.peek() != JsonToken.NULL) {
                latitud = Double.parseDouble(reader.nextString().replace(",","."));
            }else if(name.equals(FIELD_LONGITUD) && reader.peek() != JsonToken.NULL) {
                longitud = Double.parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals(FIELD_HORARIO) && reader.peek() != JsonToken.NULL){
                horario = reader.nextString();
            }else{
                reader.skipValue();
            }

        }

        reader.endObject();
        return new Gasolinera(id,localidad,provincia,direccion,gasoleoA, sinplomo95, rotulo, gasoleoB, sinplomo98, horario, latitud, longitud, municipio, idccaa);
    }
}
