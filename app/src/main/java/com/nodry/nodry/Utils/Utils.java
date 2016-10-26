package com.nodry.nodry.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by att3mpt on 10/26/16.
 */

public class Utils {

    private static Map<String, String> CCAAbyID;
    private static Map<String, String> CCAAbyValue;
    private static List<String> CCAAList;

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

}
