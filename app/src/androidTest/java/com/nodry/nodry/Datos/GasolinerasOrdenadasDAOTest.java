package com.nodry.nodry.Datos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.Negocio.TipoGasolina;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.LocalFetch;
import com.nodry.nodry.Utils.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class GasolinerasOrdenadasDAOTest {

    GasolinerasDAO  g;
    List<Gasolinera> gasolineras;

    private static final String TEMP_FILE_NAME = "temp.txt";
    private static final String TEST_LOCAL_FILE_NAME = "localFileText.txt";

    private static final String DEFAULT_CCAA = "06";

    private static final String TEST_ROTULO             = "CEPSA";
    private static final String TEST_DIRECCION          = "CARRETERA 6316 KM. 10,5";
    private static final String TEST_LOCALIDAD          = "NOVALES";
    private static final String TEST_PROVINCIA          = "CANTABRIA";
    private static final Double TEST_PRECIO_GASOLINA    = 0.500;
    private static final Double TEST_PRECIO_GASOLINA98  = 0.500;
    private static final Double TEST_PRECIO_GASOLEO     = 0.500;
    private static final Double TEST_PRECIO_GASOLEO_PLUS= 0.500;
    private static final Integer TEST_IDEESS            = 1039;

    private static final boolean FORCE_LOCAL_TRUE = true;
    private static final boolean FORCE_LOCAL_FALSE = false;

    private static final String GALICIA_ID = "12";
    private static final List<String>  PROVINCIAS_GALICIA = new ArrayList<String>(Arrays.asList("CORUÑA (A)","LUGO","OURENSE","PONTEVEDRA"));


    private static Context context;

    //private static RemoteFetch remoteFetch;
    private static HashMap<String, String> filtros;
    private static GestionGasolineras gestionGasolineras;
    private static BufferedInputStream bufferedDataGasolinerasTest;
    private static InputStream stream;
    private static String jsonData =
                "{" +
                "\"Fecha\": \"25/10/2016 20:40:39\"," +
                "\"ListaEESSPrecio\":[" +
                "{" +
                "\"C.P.\": \"39195\","+
                "\"Dirección\": \"CARRETERA ARGOÑOS SOMO KM. 28,7\","+
                "\"Horario\": \"L-D: 07:00-22:00\","+
                "\"Latitud\": \"43,469778\","+
                "\"Localidad\": \"ARNUERO\","+
                "\"Longitud (WGS84)\": \"-3,562250\","+
                "\"Margen\": \"D\","+
                "\"Municipio\": \"Arnuero\","+
                "\"Precio Biodiesel\": null," +
                "\"Precio Bioetanol\": null," +
                "\"Precio Gas Natural Comprimido\": null," +
                "\"Precio Gas Natural Licuado\": null," +
                "\"Precio Gases licuados del petróleo\": null," +
                "\"Precio Gasoleo A\": \"1,079\","+
                "\"Precio Gasoleo B\": null," +
                "\"Precio Gasolina 95 Protección\": \"1,199\","+
                "\"Precio Gasolina  98\": null," +
                "\"Precio Nuevo Gasoleo A\": \"1,129\","+
                "\"Provincia\": \"CANTABRIA\","+
                "\"Remisión\": \"OM\","+
                "\"Rótulo\": \"CAMPSA\","+
                "\"Tipo Venta\": \"P\","+
                "\"% BioEtanol\": \"0,0\","+
                "\"% Éster metílico\": \"0,0\","+
                "\"IDEESS\": \"1080\","+
                "\"IDMunicipio\": \"5749\","+
                "\"IDProvincia\": \"39\","+
                "\"IDCCAA\": \"06\"" +
                "}," +
                "{" +
                "\"C.P.\": \"39840\","+
                "\"Dirección\": \"CR N-629 79,7\","+
                "\"Horario\": \"L-D: 08:00-22:00\","+
                "\"Latitud\": \"43,341889\","+
                "\"Localidad\": \"AMPUERO\","+
                "\"Longitud (WGS84)\": \"-3,418167\","+
                "\"Margen\": \"D\","+
                "\"Municipio\": \"Ampuero\","+
                "\"Precio Biodiesel\": null,\"Precio Bioetanol\": null," +
                "\"Precio Gas Natural Comprimido\": null," +
                "\"Precio Gas Natural Licuado\": null," +
                "\"Precio Gases licuados del petróleo\": null," +
                "\"Precio Gasoleo A\": \"1,079\","+
                "\"Precio Gasoleo B\": null," +
                "\"Precio Gasolina 95 Protección\": \"1,189\","+
                "\"Precio Gasolina  98\": null," +
                "\"Precio Nuevo Gasoleo A\": \"1,129\","+
                "\"Provincia\": \"CANTABRIA\","+
                "\"Remisión\": \"OM\","+
                "\"Rótulo\": \"REPSOL\","+
                "\"Tipo Venta\": \"P\","+
                "\"% BioEtanol\": \"0,0\","+
                "\"% Éster metílico\": \"0,0\","+
                "\"IDEESS\": \"1048\","+
                "\"IDMunicipio\": \"5745\","+
                "\"IDProvincia\": \"39\","+
                "\"IDCCAA\": \"06\"" +
                "}," +
                "{" +
                "\"C.P.\": \"39450\","+
                "\"Dirección\": \"CARRETERA N-611 KM. 163,2\","+
                "\"Horario\": \"L-D: 06:00-22:00\","+
                "\"Latitud\": \"43,181972\","+
                "\"Localidad\": \"ARENAS DE IGUÑA\","+
                "\"Longitud (WGS84)\": \"-4,052444\","+
                "\"Margen\": \"I\","+
                "\"Municipio\": \"Arenas de Iguña\","+
                "\"Precio Biodiesel\": null," +
                "\"Precio Bioetanol\": null," +
                "\"Precio Gas Natural Comprimido\": null," +
                "\"Precio Gas Natural Licuado\": null," +
                "\"Precio Gases licuados del petróleo\": null," +
                "\"Precio Gasoleo A\": \"1,099\","+
                "\"Precio Gasoleo B\": null," +
                "\"Precio Gasolina 95 Protección\": \"1,179\","+
                "\"Precio Gasolina  98\": null," +
                "\"Precio Nuevo Gasoleo A\": \"1,179\","+
                "\"Provincia\": \"CANTABRIA\","+
                "\"Remisión\": \"OM\","+
                "\"Rótulo\": \"PETRONOR\","+
                "\"Tipo Venta\": \"P\","+
                "\"% BioEtanol\": \"0,0\","+
                "\"% Éster metílico\": \"0,0\","+
                "\"IDEESS\": \"1036\","+
                "\"IDMunicipio\": \"5747\","+
                "\"IDProvincia\": \"39\","+
                "\"IDCCAA\": \"06\"" +
                "}," +
                "{" +
                "\"C.P.\": \"39526\"," +
                "\"Dirección\": \""+TEST_DIRECCION+"\"," +
                "\"Horario\": \"L-D: 08:00-21:00\"," +
                "\"Latitud\": \"43,395944\"," +
                "\"Localidad\": \""+TEST_LOCALIDAD+"\"," +
                "\"Longitud (WGS84)\": \"-4,155194\"," +
                "\"Margen\": \"I\"," +
                "\"Municipio\": \"Alfoz de Lloredo\"," +
                "\"Precio Biodiesel\": null," +
                "\"Precio Bioetanol\": null," +
                "\"Precio Gas Natural Comprimido\": null," +
                "\"Precio Gas Natural Licuado\": null," +
                "\"Precio Gases licuados del petróleo\": null," +
                "\"Precio Gasoleo A\": \""+TEST_PRECIO_GASOLEO+"\"," +
                "\"Precio Gasoleo B\": \"0,765\"," +
                "\"Precio Gasolina 95 Protección\": \""+TEST_PRECIO_GASOLINA+"\"," +
                "\"Precio Gasolina  98\": \""+ TEST_PRECIO_GASOLINA98  +"\"," +
                "\"Precio Nuevo Gasoleo A\": \""+ TEST_PRECIO_GASOLEO_PLUS +"\"," +
                "\"Provincia\": \""+TEST_PROVINCIA+"\"," +
                "\"Remisión\": \"dm\"," +
                "\"Rótulo\": \""+TEST_ROTULO+"\"," +
                "\"Tipo Venta\": \"P\"," +
                "\"% BioEtanol\": \"0,0\"," +
                "\"% Éster metílico\": \"0,0\"," +
                "\"IDEESS\": \""+TEST_IDEESS+"\"," +
                "\"IDMunicipio\": \"5744\"," +
                "\"IDProvincia\": \"39\"," +
                "\"IDCCAA\": \"06\"" +
                "}" +
                "]," +
                "\"Nota\": \"Archivo de todos los productos en todas las estaciones de servicio. La actualización de precios se realiza cada media hora, con los precios en vigor en ese momento.\","+
                "\"ResultadoConsulta\": \"OK\"" +
                "}";

    @BeforeClass
    public static void setUpClass() throws Exception {
        gestionGasolineras = new GestionGasolineras();
        context = InstrumentationRegistry.getTargetContext();
        DataFetch.setContext(context);
        stream = new ByteArrayInputStream(jsonData.getBytes("UTF-8"));
        bufferedDataGasolinerasTest = new BufferedInputStream(stream);
    }

    @Before
    public void setUp() throws Exception {
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
        filtros = new HashMap<String, String>();
    }

    @Test
    public void getListaOrdenadaGasolina95Test(){

        try {
            Utils.writeToFile(bufferedDataGasolinerasTest, TEMP_FILE_NAME, context);

            filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
            filtros.put("PRECIO", Utils.tiposGasolina.get(0));
            gasolineras = gestionGasolineras.getGasolineras(filtros, FORCE_LOCAL_TRUE);

            Assert.assertTrue(gasolineras.get(0).getGasolina_95() == TEST_PRECIO_GASOLINA
                    && gasolineras.get(0).getDireccion().equals(TEST_DIRECCION)
                    && gasolineras.get(0).getIDEESS() == TEST_IDEESS
                    && gasolineras.get(0).getLocalidad().equals(TEST_LOCALIDAD)
                    && gasolineras.get(0).getProvincia().equals(TEST_PROVINCIA)
            );
        }catch(Exception e){
            Log.d("El test no paso", e.toString());
            Assert.fail();
        }
    }

    @Test
    public void getListaOrdenadaGasolina98Test(){

        try {
            Utils.writeToFile(bufferedDataGasolinerasTest, TEMP_FILE_NAME, context);

            filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
            filtros.put("PRECIO", Utils.tiposGasolina.get(1));
            gasolineras = gestionGasolineras.getGasolineras(filtros, FORCE_LOCAL_TRUE);

            Assert.assertTrue(gasolineras.get(0).getGasoleo_a() == TEST_PRECIO_GASOLINA98
                    && gasolineras.get(0).getDireccion().equals(TEST_DIRECCION)
                    && gasolineras.get(0).getIDEESS() == TEST_IDEESS
                    && gasolineras.get(0).getLocalidad().equals(TEST_LOCALIDAD)
                    && gasolineras.get(0).getProvincia().equals(TEST_PROVINCIA)
            );
        }catch(Exception e){
            Log.d("El test no paso", e.toString());
            Assert.fail();
        }
    }

    @Test
    public void getListaOrdenadaGasoleoTest(){

        try {
            Utils.writeToFile(bufferedDataGasolinerasTest, TEMP_FILE_NAME, context);

            filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
            filtros.put("PRECIO", Utils.tiposGasolina.get(2));
            gasolineras = gestionGasolineras.getGasolineras(filtros, FORCE_LOCAL_TRUE);

            Assert.assertTrue(gasolineras.get(0).getGasoleo_a() == TEST_PRECIO_GASOLEO
                    && gasolineras.get(0).getDireccion().equals(TEST_DIRECCION)
                    && gasolineras.get(0).getIDEESS() == TEST_IDEESS
                    && gasolineras.get(0).getLocalidad().equals(TEST_LOCALIDAD)
                    && gasolineras.get(0).getProvincia().equals(TEST_PROVINCIA)
            );
        }catch(Exception e){
            Log.d("El test no paso", e.toString());
            Assert.fail();
        }
    }

    @Test
    public void getListaOrdenadaGasoleoPlusTest(){
        try {
            Utils.writeToFile(bufferedDataGasolinerasTest, TEMP_FILE_NAME, context);

            filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
            filtros.put("PRECIO", Utils.tiposGasolina.get(3));
            gasolineras = gestionGasolineras.getGasolineras(filtros, FORCE_LOCAL_TRUE);

            Assert.assertTrue(gasolineras.get(0).getGasoleo_a() == TEST_PRECIO_GASOLEO_PLUS
                    && gasolineras.get(0).getDireccion().equals(TEST_DIRECCION)
                    && gasolineras.get(0).getIDEESS() == TEST_IDEESS
                    && gasolineras.get(0).getLocalidad().equals(TEST_LOCALIDAD)
                    && gasolineras.get(0).getProvincia().equals(TEST_PROVINCIA)
            );
        }catch(Exception e){
            Log.d("El test no paso", e.toString());
            Assert.fail();
        }
    }
}