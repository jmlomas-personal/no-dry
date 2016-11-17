package com.nodry.nodry.Datos;

import java.util.HashMap;
import java.util.List;

/**
 * Interfaz DAO para la futura implementacion de
 * las funciones CRUD en la capa de Datos.
 * @author Andres Barrado Martin.
 * @version 1.0
 */
public interface IGasolinerasDAO {

    // En caso de que ninguna sea seleccionada mostramos las de Cantabria (06)
    String DEFAULT_CCAA = "06";

    /**
     * Funcion que devuelve el listado de las Gasolineras
     * para una determinada CCAA
     * @return Lista con las gasolineras de la CCAA
     */
    List<Gasolinera> getListGasolineras(String CCAA, boolean bForceLocal);
}
