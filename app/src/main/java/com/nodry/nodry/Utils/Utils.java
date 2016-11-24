package com.nodry.nodry.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Toast;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase con funciones auxiliares para
 * la aplicacion.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class Utils {

    private static Map<String, String> CCAAbyID;
    private static Map<String, String> CCAAbyValue;

    private static List<String> CCAAList;

    private final static String LOCAL_TIMEZONE          = "Europe/Madrid";
    private final static int TIME_BASE                  = 60;
    private final static String EMPTY_DATA_RECEIVED     = "\"ListaEESSPrecio\":[]";
    private final static String MSG_NO_GOOGLE_MAPS      = "Google maps no instalado";
    private final static String GMAPS_PACKAGE_NAME      = "com.google.android.apps.maps";
    private final static String GMAPS_URI_FORMAT        = "geo:%f,%f?q=%f,%f(%s)";

    private final static String MATCHER_DAY_RANGE       = "([LMXJVSD]-[LMXJVSD]):\\s(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})";
    private final static String MATCHER_DAY_RANGE_24H   = "([LMXJVSD]-[LMXJVSD]):\\s(24H)";
    private final static String MATCHER_SINGLE_DAY      = "([LMXJVSD]):\\s(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})";
    private final static String MATCHER_SINGLE_DAY_24H  = "([LMXJVSD]):\\s(24H)";
    private final static String MATCHER_SPLITTER        = "; ";
    private final static String CUSTOM_SPLITTER         = ";";
    private final static String ALTERNATIVE_24H_FORMAT  = ";00;00;00;00";

    private final static List<String> days = new ArrayList<String>(
            Arrays.asList("L", "M", "X", "J", "V", "S", "D")
    );

    // Cargamos al inicio las distintas colecciones para utilizarlas una vez arrancada la app
    static{

        CCAAbyID = new HashMap<String, String>();
        CCAAbyID.put("01", "Andalucia");
        CCAAbyID.put("02", "Aragón");
        CCAAbyID.put("03", "Asturias");
        CCAAbyID.put("04", "Baleares");
        CCAAbyID.put("05", "Canarias");
        CCAAbyID.put("06", "Cantabria");
        CCAAbyID.put("07", "Castilla la Mancha");
        CCAAbyID.put("08", "Castilla y León");
        CCAAbyID.put("09", "Cataluña");
        CCAAbyID.put("18", "Ceuta");
        CCAAbyID.put("10", "Comunidad Valenciana");
        CCAAbyID.put("11", "Extremadura");
        CCAAbyID.put("12", "Galicia");
        CCAAbyID.put("13", "Madrid");
        CCAAbyID.put("19", "Melilla");
        CCAAbyID.put("14", "Murcia");
        CCAAbyID.put("15", "Navarra");
        CCAAbyID.put("16", "País Vasco");
        CCAAbyID.put("17", "Rioja (La)");

        CCAAbyValue = new HashMap<String, String>();
        CCAAbyValue.put("Andalucia", "01");
        CCAAbyValue.put("Aragón", "02");
        CCAAbyValue.put("Asturias", "03");
        CCAAbyValue.put("Baleares", "04");
        CCAAbyValue.put("Canarias", "05");
        CCAAbyValue.put("Cantabria", "06");
        CCAAbyValue.put("Castilla la Mancha", "07");
        CCAAbyValue.put("Castilla y León", "08");
        CCAAbyValue.put("Cataluña", "09");
        CCAAbyValue.put("Ceuta", "18");
        CCAAbyValue.put("Comunidad Valenciana", "10");
        CCAAbyValue.put("Extremadura", "11");
        CCAAbyValue.put("Galicia", "12");
        CCAAbyValue.put("Madrid", "13");
        CCAAbyValue.put("Melilla", "19");
        CCAAbyValue.put("Murcia", "14");
        CCAAbyValue.put("Navarra", "15");
        CCAAbyValue.put("País Vasco", "16");
        CCAAbyValue.put("Rioja (La)", "17");

        CCAAList = new ArrayList<String>();
        CCAAList.add("Andalucia");
        CCAAList.add("Aragón");
        CCAAList.add("Asturias");
        CCAAList.add("Baleares");
        CCAAList.add("Canarias");
        CCAAList.add("Cantabria");
        CCAAList.add("Castilla la Mancha");
        CCAAList.add("Castilla y León");
        CCAAList.add("Cataluña");
        CCAAList.add("Ceuta");
        CCAAList.add("Comunidad Valenciana");
        CCAAList.add("Extremadura");
        CCAAList.add("Galicia");
        CCAAList.add("Madrid");
        CCAAList.add("Melilla");
        CCAAList.add("Murcia");
        CCAAList.add("Navarra");
        CCAAList.add("País Vasco");
        CCAAList.add("Rioja (La)");

    }

    /**
     * Funcion que nos devuelve el nombre de una comunidad autonoma por su id
     * @param ID con el id de la comunidad autonoma
     * @return el nombre de la comunidad autonoma
     */
    public static String getRestCCAAAByID(String ID){
        return CCAAbyID.get(ID);
    }

    /**
     * Funcion que nos devuelve el identificador de una comunidad autonoma por su nombre
     * @param Value con el nombre de la comunidad autonoma
     * @return el id de la comunidad autonoma
     */
    public static String getRestCCAAAByValue(String Value){
        return CCAAbyValue.get(Value);
    }

    /**
     * Funcion que nos devuelve un listado con todas las comunidades autonomas con las que
     * trabaja la aplicacion.
     * @return listado con las comunidades autonomas
     */
    public static List<String> getRestCCAAAAsList(){
        return CCAAList;
    }

    /**
     * Metodo que permite escribir en el directorio de la aplicacion un fichero con un buffer de datos
     * @param in con el buffer de los datos
     * @param filename con el nombre del fichero a crear
     * @param context con el contecto de la aplicacion
     * @return buffer con los datos utilizados para crear el fichero
     * @throws IOException en caso de no haberse podido escribir el fichero
     */
    public static BufferedInputStream writeToFile(BufferedInputStream in, String filename, Context context) throws IOException {
        BufferedInputStream result = null;


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inAux;

        byte[] contents = new byte[1024];

        int bytesRead = 0;
        String strFileContents = "";

        while ((bytesRead = in.read(contents)) != -1) {
            baos.write(contents, 0, bytesRead);
            strFileContents += new String(contents, 0, bytesRead);
        }
        baos.flush();

        if (strFileContents.indexOf(EMPTY_DATA_RECEIVED)== -1){

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(strFileContents);
            outputStreamWriter.close();

            inAux = new ByteArrayInputStream(baos.toByteArray());
            result = new BufferedInputStream(inAux);
        }

        return result;
    }

    /**
     * Metodo que permite leer un fichero del directorio de la aplicacion
     * @param filename con el nombre del fichero
     * @param context con el contexto de la aplicacion
     * @return buffer con los datos utilizados para crear el fichero
     * @throws FileNotFoundException en caso de no haber encontrado del fichero
     */
    public static BufferedInputStream readFromFile(String filename, Context context)  throws FileNotFoundException {

        BufferedInputStream bufferedDataGasolineras = null;
        InputStream inputStream = context.openFileInput(filename);
        bufferedDataGasolineras = new BufferedInputStream(inputStream);

        return bufferedDataGasolineras;
    }

    /**
     * Metodo que nos dice si la conexion de datos del movil esta disponible o no
     * @param context con el contexto de la aplicacion
     * @return verdadero si esta disponible, falso en cualquier otro caso
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Metodo que permite abrir una ventana nueva con la aplicacion de Google Maps, indicando las
     * coordenadas a senyalar en el mapa
     * @param context con el contexto de la aplicacion
     * @param latitude con la latitud
     * @param longitude con la longitud
     * @param caption con la etiqueta a mostrar sobre al ubicacion
     */
    public static void openGoogleMaps(Context context, double latitude, double longitude, String caption) {

        if(isGoogleMapsInstalled(context)) {
            String uri = String.format(Locale.ENGLISH, GMAPS_URI_FORMAT, latitude, longitude, latitude, longitude, caption);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        }else{
            Toast.makeText(context, MSG_NO_GOOGLE_MAPS, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Metodo que permite saber, pasado un horario en un formato concreto, si esta abierta una gasolinera
     * o no
     * @param horario con el formato original
     * @return verdadero si se pudo comprobar que estaba abierta, falso en cualquier otro caso
     */
    public static boolean estaAbierto(String horario){
        boolean abierto = false;

        Pattern pI, pII, pIII, pIV;
        Matcher m;
        horario = horario.toUpperCase();

        List<String> horarios = Arrays.asList(horario.split(MATCHER_SPLITTER)), desglose = new ArrayList<String>();

        String sMatcherI    = MATCHER_DAY_RANGE;
        String sMatcherII   = MATCHER_SINGLE_DAY;
        String sMatcherIII  = MATCHER_DAY_RANGE_24H;
        String sMatcherIV   = MATCHER_SINGLE_DAY_24H;

        pI      = Pattern.compile(sMatcherI);
        pII     = Pattern.compile(sMatcherII);
        pIII    = Pattern.compile(sMatcherIII);
        pIV     = Pattern.compile(sMatcherIV);

        for(String hora : horarios){
            m = pI.matcher(hora);

            if(m.matches()){

                for(String s: days.subList(days.indexOf(""+m.group(1).charAt(0)), days.indexOf(""+m.group(1).charAt(2))+1)){
                    desglose.add(s + CUSTOM_SPLITTER + m.group(2)+ CUSTOM_SPLITTER + m.group(3)+ CUSTOM_SPLITTER + m.group(4)+ CUSTOM_SPLITTER + m.group(5));
                }

            }else{
                m = pII.matcher(hora);

                if(m.matches()){
                    desglose.add(m.group(1) + CUSTOM_SPLITTER + m.group(2)+ CUSTOM_SPLITTER + m.group(3)+ CUSTOM_SPLITTER + m.group(4)+ CUSTOM_SPLITTER + m.group(5));
                }else{
                    m = pIII.matcher(hora);

                    if(m.matches()) {

                        for (String s : days.subList(days.indexOf("" + m.group(1).charAt(0)), days.indexOf("" + m.group(1).charAt(2)) + 1)) {
                            desglose.add(s + ALTERNATIVE_24H_FORMAT);
                        }

                    }else{
                        m = pIV.matcher(hora);

                        if(m.matches()) {
                            desglose.add(m.group(1) + ALTERNATIVE_24H_FORMAT);
                        }
                    }
                }
            }

        }

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(LOCAL_TIMEZONE));
        int horaIni, minutoIni, horaFin, minutoFin, diaSemana = (cal.get(Calendar.DAY_OF_WEEK)>1)?cal.get(Calendar.DAY_OF_WEEK)-2:6;
        List<String> dia;

        for(String d : desglose){
            dia = Arrays.asList(d.split(CUSTOM_SPLITTER));

            if(dia.get(0).equals(days.get(diaSemana))){
                horaIni     = new Integer(dia.get(1));
                minutoIni   = new Integer(dia.get(2));
                horaFin     = new Integer(dia.get(3));
                minutoFin   = new Integer(dia.get(4));

                if(horaIni==0 && minutoIni==0 && horaFin==0 && minutoFin==0){
                    abierto = true;
                }else{
                    if(((cal.get(Calendar.HOUR_OF_DAY) * TIME_BASE + cal.get(Calendar.MINUTE)) >= (horaIni * TIME_BASE + minutoIni)) &&
                                ((cal.get(Calendar.HOUR_OF_DAY) * TIME_BASE + cal.get(Calendar.MINUTE)) <= (horaFin * TIME_BASE + minutoFin))){
                        abierto = true;
                    }
                }
            }
        }

        return abierto;
    }

    /**
     * Metodo que comprueba si Google Maps esta instalado en el dispositivo
     * @param context con el contexto de la aplicacion
     * @return verdadero si esta instalado, falso en cualquier otro caso
     */
    public static boolean isGoogleMapsInstalled(Context context)
    {
        boolean instalada;

        try {
            context.getPackageManager().getApplicationInfo(GMAPS_PACKAGE_NAME, 0 );
            instalada = true;
        }
        catch(PackageManager.NameNotFoundException e) {
            instalada = false;
        }

        return instalada;
    }

    /**
     * Metodo que permite eleminar todas las gasolineras que tengan informado a 0 el precio de un
     * tipo de gasolina
     * @param tipoGasolina con el tipo de gasolina a comprobar el precio
     * @param listaGasolineras con el listado de gasolineras
     */
    public static  void removeZeroValue(TipoGasolina tipoGasolina, List<Gasolinera> listaGasolineras){
        boolean bdel = false;
        List<Gasolinera> removeList = new ArrayList<Gasolinera>();

        for(Gasolinera g : listaGasolineras){

            bdel = false;

            switch(tipoGasolina){
                case SINPLOMO95:
                    bdel = (g.getGasolina_95() == 0);
                    break;
                case SINPLOMO98:
                    bdel = (g.getGasolina_98() == 0);
                    break;
                case DIESEL:
                    bdel = (g.getGasoleo_a() == 0);
                    break;
                case DIESELPLUS:
                    bdel = (g.getGasoleo_b() == 0);
                    break;
            }

            if(bdel){
                removeList.add(g);
            }

        }

        listaGasolineras.removeAll(removeList);

    }

    /**
     * Metodo que permite eleminar todas las gasolineras que tengan informado el precio de un
     * tipo de gasolina por encima de un valor dado.
     * @param maxValue con el valor maximo a comprobar el precio
     * @param tipoGasolina con el tipo de gasolina a comprobar el precio
     * @param listaGasolineras con el listado de gasolineras
     */
    public static void removeMaxValue(Double maxValue, TipoGasolina tipoGasolina, List<Gasolinera> listaGasolineras){

        boolean bdel = false;
        List<Gasolinera> removeList = new ArrayList<Gasolinera>();

        for(Gasolinera g : listaGasolineras){

            bdel = false;

            switch(tipoGasolina){
                case SINPLOMO95:
                    bdel = (g.getGasolina_95() > maxValue);
                    break;
                case SINPLOMO98:
                    bdel = (g.getGasolina_98()  > maxValue);
                    break;
                case DIESEL:
                    bdel = (g.getGasoleo_a()  > maxValue);
                    break;
                case DIESELPLUS:
                    bdel = (g.getGasoleo_b()  > maxValue);
                    break;
            }

            if(bdel){
                removeList.add(g);
            }

        }

        listaGasolineras.removeAll(removeList);

    }

    /**
     * Metodo que devuelve el precio mas barato para un tipo de gasolina de entre un listado de
     * gasolineras
     * @param tipoGasolina con el tipo de gasolina a comprobar el precio
     * @param listaGasolineras con el listado de gasolineras
     * @return precio mas barato
     */
    public static  Double getMasBarata (TipoGasolina tipoGasolina, List<Gasolinera> listaGasolineras){

        Double minimo = 0.0;

        for(Gasolinera g : listaGasolineras){

            switch(tipoGasolina){
                case SINPLOMO95:
                    if(minimo == 0 || minimo > g.getGasolina_95()){
                        minimo = g.getGasolina_95();
                    }
                    break;
                case SINPLOMO98:
                    if(minimo == 0 || minimo > g.getGasolina_98()){
                        minimo = g.getGasolina_98();
                    }
                    break;
                case DIESEL:
                    if(minimo == 0 || minimo > g.getGasoleo_a()){
                        minimo = g.getGasoleo_a();
                    }
                    break;
                case DIESELPLUS:
                    if(minimo == 0 || minimo > g.getGasoleo_b()){
                        minimo = g.getGasoleo_b();
                    }
                    break;
            }

        }

        return minimo;
    }

    /**
     * Funcion que pasado un listado de gasolineras devuelve las mas baratas por tipo de combustible
     * para un municipio.
     * @param municipio con el municipio a comparar el precio
     * @param listaGasolineras con el listado de gasolineras
     * @return las gasolineras mas baratas por tipo de carburante
     */
    public static MasBaratas getMasBaratas(String municipio, List<Gasolinera> listaGasolineras){

        MasBaratas masBaratas = new MasBaratas();

        for(Gasolinera g: listaGasolineras){

            if(g.getMunicipio().trim().equals(municipio)) {

                // Comprobamos si es la mas barata de alguno de los cuatro carburantes
                if ((masBaratas.getMasBarata95() == null && g.getGasolina_95() > 0) ||
                        (g.getGasolina_95() > 0 && masBaratas.getMasBarata95().getGasolina_95() > g.getGasolina_95())) {
                    masBaratas.setMasBarata95(g);
                }

                if ((masBaratas.getMasBarata98() == null && g.getGasolina_98() > 0) ||
                        (g.getGasolina_98() > 0 && masBaratas.getMasBarata98().getGasolina_98() > g.getGasolina_98())) {
                    masBaratas.setMasBarata98(g);
                }

                if ((masBaratas.getMasBarataDiesel() == null && g.getGasoleo_a() > 0) ||
                        (g.getGasoleo_a() > 0 && masBaratas.getMasBarataDiesel().getGasoleo_a() > g.getGasoleo_a())) {
                    masBaratas.setMasBarataDiesel(g);
                }

                if ((masBaratas.getMasBarataDieselPlus() == null && g.getGasoleo_b() > 0) ||
                        (g.getGasoleo_b() > 0 && masBaratas.getMasBarataDieselPlus().getGasoleo_b() > g.getGasoleo_b())) {
                    masBaratas.setMasBarataDieselPlus(g);
                }
            }
        }

        return masBaratas;
    }

    /**
     * Metodo que dado un listado de gasolineras devuelve una por su identificador de estacion
     * @param IDEESS con el identificador de estacion
     * @param listaGasolineras con el listado de gasolineras a utiliar
     * @return gasolinera con el id aportado si se encontro, nulo en cualquier otro caso
     */
    public static Gasolinera getGasolinera(int IDEESS, List<Gasolinera> listaGasolineras) {
        Gasolinera gasolinera = null;

        for(Gasolinera g : listaGasolineras){
            if(g.getIDEESS()==IDEESS){
                gasolinera = g;
            }
        }

        return gasolinera;
    }

}

