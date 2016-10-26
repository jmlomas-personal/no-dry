package com.nodry.nodry.Datos;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class GasolinerasDAOTest {

    GasolinerasDAO  g;
    List<Gasolinera> gasolineras;

    @Before
    public void setUp() throws Exception {
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
    }

    @Test
    public void getListGasolineras() {
        try {
            gasolineras = g.getListGasolineras();
            assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());
        } catch (Exception e) {
            fail();
        }
    }

}