package com.nodry.nodry.Negocio;

import com.nodry.nodry.Comunes.Negocio.IGestionGasolineras;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Orlando on 23/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestionGasolinerasTest {

    private static final String CCAA = "06";

    @Mock
    IGestionGasolineras gestionGasolinera;

    @Before
    public void setUp() throws Exception{
        gestionGasolinera = mock(IGestionGasolineras.class);
    }

    @Test
    public void obtenGasolinerasServicioRestTest(){
        try {
            gestionGasolinera.obtenGasolinerasServicioRest(CCAA);
            verify(gestionGasolinera).obtenGasolinerasServicioRest(CCAA);
        }catch(Exception E){
            Assert.fail();
        }
    }

    @Test
    public void obtenGasolinerasCacheTest(){
        try {
            gestionGasolinera.obtenGasolinerasCache();
            verify(gestionGasolinera).obtenGasolinerasCache();
        }catch(Exception E){
            Assert.fail();
        }
    }

}
