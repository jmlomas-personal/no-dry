package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.IGasolinerasDAO;

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
    public void getListGasolinerasTest(){
        gestionGasolinera.getGasolineras(IGasolinerasDAO.DEFAULT_CCAA, false);
        verify(gestionGasolinera).getGasolineras(IGasolinerasDAO.DEFAULT_CCAA, false);
    }

}
