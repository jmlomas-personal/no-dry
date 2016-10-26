package com.nodry.nodry.Datos;

import org.junit.Before;
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

    @Before
    public void setUp() throws Exception {
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
    }

    @Test
    public void getListGasolineras() {
        try {
            gasolineras = g.getListGasolineras(IGasolinerasDAO.DEFAULT_CCAA);
            assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getListGasolinerasFiltroCCAA() {
        try {
            gasolineras = g.getListGasolineras(IGasolinerasDAO.DEFAULT_CCAA);
            assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());

            gasolineras = g.getListGasolineras(GALICIA_ID);
            assertTrue(
                    PROVINCIAS_GALICIA.contains(gasolineras.get(0).getProvincia())
            );
        } catch (Exception e) {
            fail();
        }
    }

}