package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;

import java.util.List;

/**
 * Interfaz de la capa de negocio que opera con gasolineras
 * @author 0rlando Britto.
 * @version 1.0
 */
public interface IGestionGasolineras {

    /**
     * Metodo que retorna la lista de gasolineras obtenidas de la capa de Datos
     * @return la lista de gasolineras obtenidas de la capa de Datos
     */
    List<Gasolinera> getGasolineras();

}
