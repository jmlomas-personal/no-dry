package com.nodry.nodry.Presentacion;

import java.util.Collection;

/**
 * Interfaz para implementar elementos que
 * presenten capacidad para actualizar algun atributo
 * con una coleccion dadda.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public interface IUpdateable {

    /**
     * Metodo que actualiza los datos.
     * @param c con la coleccion que serrvira la actualizacion
     */
    public void update(Collection<?> c);
}
