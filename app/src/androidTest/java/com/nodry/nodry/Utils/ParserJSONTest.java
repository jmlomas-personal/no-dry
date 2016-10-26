package com.nodry.nodry.Utils;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.IGasolinerasDAO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class ParserJSONTest {

    private static RemoteFetch remoteFetch;
    private static InputStream stream;
    private static List<Gasolinera> listaGasolineras;
    private static String jsonData =
            "{" +
                "\"Fecha\": \"25/10/2016 20:40:39\"," +
                "\"ListaEESSPrecio\":[" +
                    "{" +
                        "\"C.P.\": \"39526\"," +
                        "\"Dirección\": \"CARRETERA 6316 KM. 10,5\"," +
                        "\"Horario\": \"L-D: 08:00-21:00\"," +
                        "\"Latitud\": \"43,395944\"," +
                        "\"Localidad\": \"NOVALES\"," +
                        "\"Longitud (WGS84)\": \"-4,155194\"," +
                        "\"Margen\": \"I\"," +
                        "\"Municipio\": \"Alfoz de Lloredo\"," +
                        "\"Precio Biodiesel\": null," +
                        "\"Precio Bioetanol\": null," +
                        "\"Precio Gas Natural Comprimido\": null," +
                        "\"Precio Gas Natural Licuado\": null," +
                        "\"Precio Gases licuados del petróleo\": null," +
                        "\"Precio Gasoleo A\": \"1,095\"," +
                        "\"Precio Gasoleo B\": \"0,765\"," +
                        "\"Precio Gasolina 95 Protección\": \"1,205\"," +
                        "\"Precio Gasolina  98\": \"1,310\"," +
                        "\"Precio Nuevo Gasoleo A\": \"1,155\"," +
                        "\"Provincia\": \"CANTABRIA\"," +
                        "\"Remisión\": \"dm\"," +
                        "\"Rótulo\": \"CEPSA\"," +
                        "\"Tipo Venta\": \"P\"," +
                        "\"% BioEtanol\": \"0,0\"," +
                        "\"% Éster metílico\": \"0,0\"," +
                        "\"IDEESS\": \"1039\"," +
                        "\"IDMunicipio\": \"5744\"," +
                        "\"IDProvincia\": \"39\"," +
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
                    "}" +
                "]," +
                "\"Nota\": \"Archivo de todos los productos en todas las estaciones de servicio. La actualización de precios se realiza cada media hora, con los precios en vigor en ese momento.\","+
                "\"ResultadoConsulta\": \"OK\"" +
            "}";

    @BeforeClass
    public static void setUpClass() throws Exception {
        remoteFetch = new RemoteFetch("");
        stream = new ByteArrayInputStream(jsonData.getBytes("UTF-8"));
        listaGasolineras = ParserJSON.readJsonStream(stream);
    }

    @Test
    public void readJsonStreamStatusTest() {
        Boolean status = false;

        try {
            remoteFetch.getJSON();
            status = ParserJSON.readJsonStreamStatus(remoteFetch.getBufferedDataGasolineras());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!status) {
            fail();
        }
    }

    @Test
    public void readJsonStreamRecordCountTest() {
        try {
            Assert.assertTrue(listaGasolineras.size() == 4);
        }catch(Exception e){
            Assert.fail();
        }
    }

    @Test
    public void readJsonStreamDataTest() {
        try {
            Gasolinera gasolinera = listaGasolineras.get(0);

            Assert.assertTrue(
                gasolinera.getRotulo().equals("CEPSA")
                    &&  gasolinera.getDireccion().equals("CARRETERA 6316 KM. 10,5")
                    &&  gasolinera.getGasoleo_a() == 1.095
                    &&  gasolinera.getGasolina_95() == 1.205
                    &&  gasolinera.getIDEESS() == 1039
                    &&  gasolinera.getLocalidad().equals("NOVALES")
                    &&  gasolinera.getProvincia().equals("CANTABRIA")
            );
        }catch(Exception e){
            Assert.fail();
        }
    }

}
