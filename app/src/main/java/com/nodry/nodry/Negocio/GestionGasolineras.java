package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.GasolinerasDAO;
import com.nodry.nodry.Datos.IGasolinerasDAO;

import java.util.List;

/**
 * Clase que realiza una serie de operaciones con los
 * datos de las gasolineras obtenidas de la capa de Datos.
 * @author Orlando Britto.
 * @version 1.0
 */
public class GestionGasolineras implements IGestionGasolineras {

    IGasolinerasDAO gasolinerasDAO;

    /**
     * Constructor
     */
    public GestionGasolineras(){
        gasolinerasDAO = new GasolinerasDAO();
    }

    @Override
    public List<Gasolinera> getGasolineras() {

        return gasolinerasDAO.getListGasolineras();

    }

}
