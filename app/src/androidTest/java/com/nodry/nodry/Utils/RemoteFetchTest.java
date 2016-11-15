package com.nodry.nodry.Utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.nodry.nodry.Datos.IGasolinerasDAO;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/25/16.
 */
public class RemoteFetchTest {

    RemoteFetch remoteFetch;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        DataFetch.setContext(context);
    }

    @Before
    public void setUp() throws Exception {
        remoteFetch = new RemoteFetch(IGasolinerasDAO.DEFAULT_CCAA);
    }

    @Test
    public void conexionCorrecta(){

        try {
            remoteFetch.getJSON();
        }
        catch(IOException e) {
            Log.d("El test no paso", e.toString());
            fail();
        }

        assertNotNull(remoteFetch.getBufferedData());
    }
}