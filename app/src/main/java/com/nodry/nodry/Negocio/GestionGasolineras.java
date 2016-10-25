package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.GasolinerasDAO;
import com.nodry.nodry.Datos.IGasolinerasDAO;

import java.util.List;

/**
 * Clase que realiza una serie de operaciones con los datos de las gasolineras
 * obtenidas de la capa de Datos
 * Created by Orlando on 10/24/16.
 */

public class GestionGasolineras implements IGestionGasolineras {

    IGasolinerasDAO gasolinerasDAO;

    public GestionGasolineras(){
        gasolinerasDAO = new GasolinerasDAO();
    }

    /**
     * Metodo que retorna la lista de gasolineras obtenidas de la capa de Datos
     * @return la lista de gasolineras obtenidas de la capa de Datos
     */
    @Override
    public List<Gasolinera> getGasolineras() {

        return gasolinerasDAO.getListGasolineras();

    }

}
