package com.nodry.nodry.Datos;

/**
 * Clase que representa una gasolinera.
 * Cada una se rellenara con los datos procesados desde
 * el JSON descargado del servidor.
 * @author Andres Barrado Martin.
 * @version 1.0
 */
public class Gasolinera {

    //Campos de la Gasolinera
    private int IDEESS;
    private String localidad;
    private String provincia;
    private String direccion;
    private double gasoleo_a;
    private double gasolina_95;
    private String rotulo;

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
    public Gasolinera (int IDEESS, String localidad, String provincia, String direccion, double gasoleo_a, double gasolina_95, String rotulo){
        this.IDEESS = IDEESS;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleo_a = gasoleo_a;
        this.gasolina_95 = gasolina_95;
        this.rotulo = rotulo;
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



}// Gasolinera

