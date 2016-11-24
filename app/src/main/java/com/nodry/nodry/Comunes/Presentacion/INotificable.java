package com.nodry.nodry.Comunes.Presentacion;

/**
 * Interfaz que permite trabajar con
 * notificaciones de mensajes.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface INotificable {

    /**
     * Metodo que permite lanzar un mensaje detallado, con titulo y cuerpo.
     * @param title con el titulo
     * @param msg con el mensaje a mostrar
     */
    void showMessage(String title, String msg);

    /**
     * Metodo para lanzar mensajes cortos en forma de alerta, donde solo importa el mensaje
     * @param msg con el mensaje a mostrar
     */
    void showAlert(String msg);
}
