package com.nodry.nodry.Datos;

import java.util.List;

/**
 * Interfaz DAO para la futura implementacion de
 * las funciones CRUD en la capa de Datos.
 * @author Andres Barrado Martin.
 * @version 1.0
 */
public interface IGasolinerasDAO {

    /**
     * Funcion que devuelve el listado de las Gasolineras
     * @return Lista con las gasolineras
     */
    List<Gasolinera> getListGasolineras();

}
