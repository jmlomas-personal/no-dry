package com.nodry.nodry.Datos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.nodry.nodry.Utils.DataFetch;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class GasolinerasDAOTest {

    GasolinerasDAO  g;
    List<Gasolinera> gasolineras;

    private static final String GALICIA_ID = "12";
    private static final List<String>  PROVINCIAS_GALICIA = new ArrayList<String>(Arrays.asList("CORUÑA (A)","LUGO","OURENSE","PONTEVEDRA"));

    @BeforeClass
    public static void setUpClass() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DataFetch.setContext(context);
    }

    @Before
    public void setUp() throws Exception {
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
    }

    @Test
    public void getListGasolineras() {
        try {
            gasolineras = g.getListGasolineras(IGasolinerasDAO.DEFAULT_CCAA, false);
            assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());
        } catch (IndexOutOfBoundsException e) {
            Log.d("El test no paso", e.toString());
            fail();
        }
    }

    @Test
    public void getListGasolinerasFiltroCCAA() {
        try {
            gasolineras = g.getListGasolineras(IGasolinerasDAO.DEFAULT_CCAA, false);
            assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());

            gasolineras = g.getListGasolineras(GALICIA_ID, false);
            assertTrue(
                    PROVINCIAS_GALICIA.contains(gasolineras.get(0).getProvincia())
            );
        } catch (IndexOutOfBoundsException e) {
            Log.d("El test no paso", e.toString());
            fail();
        }
    }

}