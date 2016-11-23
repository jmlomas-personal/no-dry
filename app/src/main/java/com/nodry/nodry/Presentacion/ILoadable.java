package com.nodry.nodry.Presentacion;

/**
 * Interfaz para implementar elementos que
 * presenten pantallas o procesos de carga.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public interface ILoadable {

    /**
     * Metodo para el comienzo de la carga
     */
    void startLoading();

    /**
     * Metodo para el fin de la carga.
     */
    void stopLoading();

}
