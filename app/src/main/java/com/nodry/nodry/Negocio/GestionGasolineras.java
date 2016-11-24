package com.nodry.nodry.Negocio;

import com.nodry.nodry.Comunes.Negocio.IGestionGasolineras;
import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Datos.GasolinerasLocalDAO;
import com.nodry.nodry.Datos.GasolinerasRemoteDAO;
import com.nodry.nodry.Comunes.Datos.IGasolinerasLocalDAO;
import com.nodry.nodry.Comunes.Datos.IGasolinerasRemoteDAO;

import java.io.IOException;
import java.util.List;

/**
 * Clase para tratar con los datos
 * de Negocio sobre Gasolineras.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class GestionGasolineras implements IGestionGasolineras {

    // DAOs con los que trabajaremos
    IGasolinerasRemoteDAO gasolinerasRemoteDAO;
    IGasolinerasLocalDAO gasolinerasLocalDAO;

    /**
     * Constructor de la clase
     */
    public GestionGasolineras(){
        gasolinerasRemoteDAO = new GasolinerasRemoteDAO();
        gasolinerasLocalDAO = new GasolinerasLocalDAO();
    }

    @Override
    public List<Gasolinera> obtenGasolinerasServicioRest(String CCAA) throws IOException {
        return gasolinerasRemoteDAO.getListGasolineras(CCAA);
    }

    @Override
    public List<Gasolinera> obtenGasolinerasCache() throws IOException  {
        return gasolinerasLocalDAO.getListGasolineras();
    }
}
