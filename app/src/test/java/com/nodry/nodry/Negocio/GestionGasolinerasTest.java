package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.IGasolinerasDAO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Orlando on 23/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestionGasolinerasTest {

    @Mock
    IGestionGasolineras gestionGasolinera;
    HashMap<String, String> filtros;

    @Before
    public void setUp() throws Exception{
        gestionGasolinera = mock(IGestionGasolineras.class);
        filtros = new HashMap<String, String>();
        filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
    }

    /**
     * Test que comprueba la correcta llamada al metodo getListaGasolineras()
     */
    @Test
    public void getListGasolinerasTest(){
        gestionGasolinera.getGasolineras(filtros, false);
        verify(gestionGasolinera).getGasolineras(filtros, false);
    }

}
