package com.nodry.nodry.Datos;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class RemoteFetchTest {

    @Test
    public void conexionCorrecta(){

        RemoteFetch r=new RemoteFetch();

        try {
            r.getJSON();
        }
        catch(Exception e)
        {
            fail();
        }

        assertNotNull(r.getBufferedDataGasolineras());
    }

}