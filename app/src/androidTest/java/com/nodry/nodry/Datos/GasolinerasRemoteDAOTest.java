package com.nodry.nodry.Datos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.nodry.nodry.Comunes.Datos.IGasolinerasRemoteDAO;
import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.Utils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GasolinerasRemoteDAOTest {

    IGasolinerasRemoteDAO gasolinerasDAO;
    private static List<Gasolinera> listaGasolineras;

    private static final String CCAA                    = "06";
    private static final String CCAA_NAME               = "CANTABRIA";
    private static final String TITLE_ERROR             = "ERROR";

    private static Context context;

    @BeforeClass
    public static void setUpClass() throws Exception {

        context = InstrumentationRegistry.getTargetContext();
        Utils.setContext(context);

    }

    @Before
    public void setUp() throws Exception {
        gasolinerasDAO = new GasolinerasRemoteDAO();
        listaGasolineras = new ArrayList<Gasolinera>();
    }

    @Test
    public void getListGasolinerasTest() {
        try {

            listaGasolineras = gasolinerasDAO.getListGasolineras(CCAA);
            assertEquals(CCAA_NAME, listaGasolineras.get(0).getProvincia());

        } catch (IndexOutOfBoundsException e) {
            Log.d(TITLE_ERROR, e.toString());
            fail();
        } catch (IOException e) {
            Log.d(TITLE_ERROR, e.toString());
            fail();
        }
    }


}