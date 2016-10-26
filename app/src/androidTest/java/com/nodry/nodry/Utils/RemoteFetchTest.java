package com.nodry.nodry.Utils;

import com.nodry.nodry.Datos.IGasolinerasDAO;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class RemoteFetchTest {

    RemoteFetch remoteFetch;

    @Before
    public void setUp() throws Exception {
        remoteFetch = new RemoteFetch(IGasolinerasDAO.DEFAULT_CCAA);
    }

    @Test
    public void conexionCorrecta(){

        try {
            remoteFetch.getJSON();
        }
        catch(Exception e)
        {
            fail();
        }

        assertNotNull(remoteFetch.getBufferedDataGasolineras());
    }
}