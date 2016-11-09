package com.nodry.nodry.Datos;

import java.io.Serializable;

/**
 * Clase que representa una gasolinera.
 * Cada una se rellenara con los datos procesados desde
 * el JSON descargado del servidor.
 * @author Andres Barrado Martin.
 * @version 1.0
 */
public class Gasolinera implements Serializable {

    //Campos de la Gasolinera
    private int IDEESS;
    private String localidad;
    private String provincia;
    private String direccion;
    private double gasoleo_a;
    private double gasoleo_b;
    private double gasolina_95;
    private double gasolina_98;
    private String rotulo;
    private String horario;
    private double latitud;
    private double longitud;

    // Preparamos un ejemplo de ordenacion que utilizaremos mas daelante
    private static final boolean orderByGasoleoA = true;

    /**
     * Constructor de la clase
     *
     * @param IDEESS con el IDEES de la gasolinera
     * @param localidad con la localidad de la gasolinera
     * @param provincia con la provincia de la gasolinera
     * @param direccion con la direccion de la gasolinera
     * @param gasoleo_a con el rpecio del gasoleo_a de la gasolinera
     * @param gasolina_95 con el rpecio de la gasolina_95 de la gasolinera
     * @param rotulo con el rotulo de la gasolineras
     */
    public Gasolinera (int IDEESS, String localidad, String provincia, String direccion, double gasoleo_a, double gasolina_95, String rotulo, double gasoleo_b, double gasolina_98, String horario, double latitud, double longitud){
        this.IDEESS = IDEESS;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleo_a = gasoleo_a;
        this.gasolina_95 = gasolina_95;
        this.rotulo = rotulo;
        this.gasoleo_b = gasoleo_b;
        this.gasolina_98 = gasolina_98;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }// Gasolinera

    /**
     * Retorna el valor del atributo IDEES
     * @return IDEESS
     */
    public int getIDEESS() {
        return IDEESS;
    }

    /**
     * Modifica el valor del atributo IDEESS por el pasado por parametro
     */
    public void setIDEESS(int IDEESS) {
        this.IDEESS = IDEESS;
    }

    /**
     * Retorna el valor del atributo localidad
     * @return localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Modifica el valor del atributo localidad por el pasado por parametro
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Retorna el valor del atributo provincia
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Modifica el valor del atributo provincia por el pasado por parametro
     */
    public void setProvincia(String provincia) { this.provincia = provincia;  }

    /**
     * Retorna el valor del atributo direccion
     * @return direccion
     */
    public String getDireccion() { return direccion; }

    /**
     * Modifica el valor del atributo direccion por el pasado por parametro
     */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /**
     * Retorna el valor del atributo gasoleo_a
     * @return gasoleo_a
     */
    public double getGasoleo_a() {
        return gasoleo_a;
    }

    /**
     * Modifica el valor del atributo gasoleo_a por el pasado por parametro
     */
    public void setGasoleo_a(double gasoleo_a) { this.gasoleo_a = gasoleo_a; }

    /**
     * Retorna el valor del atributo rotulo
     * @return rotulo
     */
    public String getRotulo() {
        return rotulo;
    }

    /**
     * Modifica el valor del atributo rotulo por el pasado por parametro
     */
    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    /**
     * Retorna el valor del atributo gasolina_95
     * @return gasolina_95
     */
    public double getGasolina_95() {
        return gasolina_95;
    }

    /**
     * Modifica el valor del atributo gasoleo_95 por el pasado por parametro
     */
    public void setGasolina_95(double gasolina_95) {
        this.gasolina_95 = gasolina_95;
    }

    /**
     * Retorna el valor del atributo gasoleo_b
     * @return gasoleo_b
     */
    public double getGasoleo_b() {
        return gasoleo_b;
    }

    /**
     * Modifica el valor del atributo gasoleo_b por el pasado por parametro
     */
    public void setGasoleo_b(double gasoleo_b) { this.gasoleo_b = gasoleo_b; }

    /**
     * Retorna el valor del atributo gasolina_98
     * @return gasolina_98
     */
    public double getGasolina_98() {
        return gasolina_98;
    }

    /**
     * Modifica el valor del atributo gasoleo_98 por el pasado por parametro
     */
    public void setGasolina_98(double gasolina_98) {
        this.gasolina_98 = gasolina_98;
    }

    /**
     * Retorna el valor del atributo horario
     * @return horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Modifica el valor del atributo horario por el pasado por parametro
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Retorna el valor del atributo latitud
     * @return latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Modifica el valor del atributo latitud por el pasado por parametro
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Retorna el valor del atributo longitud
     * @return longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Modifica el valor del atributo longitud por el pasado por parametro
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}// Gasolinera

