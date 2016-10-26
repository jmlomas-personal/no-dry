package com.nodry.nodry.Presentacion;

/**
 * Interfaz para implementar elementos que
 * presenten notificaciones o mensajes en pantalla.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public interface INotificable {

    String ERROR_MSG = "ATENCION";

    /**
     * Metodo que muestra el mensaje correspondiente
     * @param msg con el mensaje a mostrar
     */
    void showMessage(String title, String msg);
}
