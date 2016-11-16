package com.nodry.nodry.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by att3mpt on 10/26/16.
 */

public class Utils {

    private static Map<String, String> CCAAbyID;
    private static Map<String, String> CCAAbyValue;
    private static List<String> CCAAList;

    private final static String MSG_NO_GOOGLE_MAPS = "Google maps no instalado";
    private final static String EMPTY_DATA_RECEIVED = "\"ListaEESSPrecio\":[]";

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

    public static String[] tiposGasolina = {"Sin Plomo 95", "Sin Plomo 98", "Diesel", "Diesel Plus"};

    /**
     * Funcion que nos devuelve una tabla con la laista de
     * las comunidades autonomas para las llamadas al servicio
     * REST.
     * !!IMPORTANTE Hay que cambiarlo en un futuro por una consulta contra
     * el servicio REST:
     * https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/Listados/ComunidadesAutonomas/
     * @return tabla con el listado de comunidades
     */
    public static String getRestCCAAAByID(String ID){
        return CCAAbyID.get(ID);
    }

    public static String getRestCCAAAByValue(String Value){
        return CCAAbyValue.get(Value);
    }

    public static List<String> getRestCCAAAAsList(){
        return CCAAList;
    }

    public static BufferedInputStream writeToFile(BufferedInputStream in, String filename, Context context) {
        BufferedInputStream result = null;

        try {
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
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        return result;
    }

    public static BufferedInputStream readFromFile(String filename, Context context) {

        BufferedInputStream bufferedDataGasolineras = null;

        try {
            InputStream inputStream = context.openFileInput(filename);
            bufferedDataGasolineras = new BufferedInputStream(inputStream);
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }

        return bufferedDataGasolineras;
    }

    /**
     * Metodo que comprueba si el dispositivo dispone de conexión a internet
     * @param context con el contexto de la aplicacion
     * @return verdadero si la conexion esta activa, falso en cualquier otro caso
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void openGoogleMaps(Context context, double latitude, double longitude, String caption) {

        if(isGoogleMapsInstalled(context)) {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%f,%f(%s)", latitude, longitude, latitude, longitude, caption);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        }else{
            Toast.makeText(context, MSG_NO_GOOGLE_MAPS, Toast.LENGTH_LONG).show();
        }

    }

    public static boolean estaAbierto(String horario){
        boolean bAbierto = false;

        Pattern pI, pII, pIII, pIV;
        Matcher m;
        horario = horario.toUpperCase();

        List<String> horarios = Arrays.asList(horario.split("; ")), desglose = new ArrayList<String>();

        String sMatcherI = "([LMXJVSD]-[LMXJVSD]):\\s(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})";
        String sMatcherII = "([LMXJVSD]):\\s(\\d{2}):(\\d{2})-(\\d{2}):(\\d{2})";
        String sMatcherIII = "([LMXJVSD]-[LMXJVSD]):\\s(24H)";
        String sMatcherIV = "([LMXJVSD]):\\s(24H)";

        pI      = Pattern.compile(sMatcherI);
        pII     = Pattern.compile(sMatcherII);
        pIII    = Pattern.compile(sMatcherIII);
        pIV     = Pattern.compile(sMatcherIV);

        for(String hora : horarios){
            m = pI.matcher(hora);

            if(m.matches()){

                for(String s: days.subList(days.indexOf(""+m.group(1).charAt(0)), days.indexOf(""+m.group(1).charAt(2))+1)){
                    desglose.add(s + ";" + m.group(2)+ ";" + m.group(3)+ ";" + m.group(4)+ ";" + m.group(5));
                }

            }else{
                m = pII.matcher(hora);

                if(m.matches()){
                    desglose.add(m.group(1) + ";" + m.group(2)+ ";" + m.group(3)+ ";" + m.group(4)+ ";" + m.group(5));
                }else{
                    m = pIII.matcher(hora);

                    if(m.matches()) {

                        for (String s : days.subList(days.indexOf("" + m.group(1).charAt(0)), days.indexOf("" + m.group(1).charAt(2)) + 1)) {
                            desglose.add(s + ";00;00;00;00");
                        }

                    }else{
                        m = pIV.matcher(hora);

                        if(m.matches()) {
                            desglose.add(m.group(1) + ";00;00;00;00");
                        }
                    }
                }
            }

        }

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int horaIni, minutoIni, horaFin, minutoFin, diaSemana = (cal.get(Calendar.DAY_OF_WEEK)>1)?cal.get(Calendar.DAY_OF_WEEK)-2:6;
        List<String> dia;

        for(String d : desglose){
            dia = Arrays.asList(d.split(";"));

            if(dia.get(0).equals(days.get(diaSemana))){
                horaIni     = new Integer(dia.get(1));
                minutoIni   = new Integer(dia.get(2));
                horaFin     = new Integer(dia.get(3));
                minutoFin   = new Integer(dia.get(4));

                if(horaIni==0 && minutoIni==0 && horaFin==0 && minutoFin==0){
                    bAbierto = true;
                }else{
                    if(((cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE)) >= (horaIni * 60 + minutoIni)) &&
                                ((cal.get(Calendar.HOUR_OF_DAY)*60 + cal.get(Calendar.MINUTE)) <= (horaFin * 60 + minutoFin))){
                        bAbierto = true;
                    }
                }
            }
        }

        return bAbierto;
    }

    public static boolean isGoogleMapsInstalled(Context context)
    {
        boolean bExiste = false;

        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
            bExiste = true;
        }
        catch(PackageManager.NameNotFoundException e) {
            bExiste = false;
        }

        return bExiste;
    }
}

