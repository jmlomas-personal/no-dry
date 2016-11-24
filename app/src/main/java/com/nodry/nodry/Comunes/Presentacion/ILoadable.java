package com.nodry.nodry.Comunes.Presentacion;

/**
 * Interfaz que permite trabajar con
 * cargas de proceso.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface ILoadable {

    /**
     * Metodo para comenzar el proceso de carga
     */
    void startLoading();

    /**
     * Metodo para finalizar el proceso de carga
     */
    void stopLoading();
}
