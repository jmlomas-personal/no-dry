package com.nodry.nodry.Negocio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Orlando on 23/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    IGestionGasolineras gestionGasolinera;

    @Before
    public void setUp() throws Exception{
        gestionGasolinera = mock(IGestionGasolineras.class);
    }

    /**
     * Test que comprueba la correcta llamada al metodo getListaGasolineras()
     */
    @Test
    public void testExecutionGetLista(){
        gestionGasolinera.getGasolineras();
        verify(gestionGasolinera).getGasolineras();
    }

}