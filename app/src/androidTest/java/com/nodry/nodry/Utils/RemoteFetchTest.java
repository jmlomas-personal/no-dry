package com.nodry.nodry.Utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RemoteFetchTest {

    private static final String CCAA            = "06";
    private static final String TITLE_ERROR     = "ERROR";

    RemoteFetch remoteFetch;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Utils.setContext(context);
    }

    @Before
    public void setUp() throws Exception {
        remoteFetch = new RemoteFetch(CCAA);
    }

    @Test
    public void conexionCorrecta(){

        try {
            remoteFetch.getJSON();
            assertNotNull(remoteFetch.getBufferedData());
        }
        catch(IOException e) {
            Log.d(TITLE_ERROR, e.toString());
            fail();
        }
    }
}