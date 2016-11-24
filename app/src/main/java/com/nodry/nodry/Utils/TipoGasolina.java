package com.nodry.nodry.Utils;

/**
 * Enumerado con los tipos de carburantes
 * con los que trabaja la aplicacion.
 * @author Code4Fun.org
 * @version 11/2016
 */
public enum TipoGasolina {

    SINPLOMO95("Sin Plomo 95"),
    SINPLOMO98("Sin Plomo 98"),
    DIESEL("Diesel"),
    DIESELPLUS("Diesel Plus");

    // Atributos
    private String texto;

    /**
     * Constructor
     * @param texto con el texto a asignar a ese objeto del enumerado
     */
    TipoGasolina(String texto) {
        this.texto = texto;
    }

    // Getters y Setters
    public String getTexto() {
        return texto;
    }

}
