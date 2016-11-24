package com.nodry.nodry.Comunes.Presentacion;

import java.util.Collection;

/**
 * Interfaz que permite trabajar con
 * actualizaciones de colecciones.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface IUpdateable {

    /**
     * Metodo que permite actualizar los datos en funcion de una coleccion pasada por parametro
     * @param data con los datos a utilizar en la actualizacion
     */
    void update(Collection<?> data);
}
