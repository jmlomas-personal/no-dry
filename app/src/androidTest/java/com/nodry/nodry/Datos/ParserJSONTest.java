package com.nodry.nodry.Datos;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by att3mpt on 10/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class ParserJSONTest {

    RemoteFetch remoteFetch;

    @Before
    public void setUp() throws Exception {
        remoteFetch = new RemoteFetch();
        remoteFetch.getJSON();
    }

    @Test
    public void readJsonStreamStatus() {
        Boolean status = false;

        try {
            status = ParserJSON.readJsonStreamStatus(remoteFetch.getBufferedDataGasolineras());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!status) {
            fail();
        }
    }

}