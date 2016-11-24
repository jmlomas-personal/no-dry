package com.nodry.nodry.Comunes.Presentacion;

import com.nodry.nodry.Utils.TipoError;

import java.util.Collection;

/**
 * Interfaz que permite trabajar con
 * obtencion y manipulacion de datos.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface IFormateable {

    /**
     * Metodo para actualizar los datos
     * @param forceUpdate verdadero para forzar una actualizacion, falso en cualquier otro caso
     */
    void refreshData(boolean forceUpdate);

    /**
     * Metodo para recibir datos como una coleccion. Permite especificar si se produjeron errores
     * a la hora de obtener los datos.
     * @param errorMsg con el mensaje de error retornado
     * @param tipoError con el tipo de error retornado
     * @param data con los datos retornados
     */
    void retrieveData(String errorMsg, TipoError tipoError, Collection<?> data);

    /**
     * Metodo que permitira formatear los datos de forma autonoma
     */
    void formatData();
}
