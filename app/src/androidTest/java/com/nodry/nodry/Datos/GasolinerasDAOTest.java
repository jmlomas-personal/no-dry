package com.nodry.nodry.Datos;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class GasolinerasDAOTest {

    GasolinerasDAO g;
    List<Gasolinera> gasolineras;

    @Before
    public void setUp() throws Exception{
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
    }

    @Test
    public void compruebaProvincia(){

        try{
            gasolineras = g.getListGasolineras();
        }
        catch(Exception e){
            fail();
        }

        assertEquals("CANTABRIA", gasolineras.get(0).getProvincia());
    }
}