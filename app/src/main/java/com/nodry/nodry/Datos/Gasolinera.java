package com.nodry.nodry.Datos;

/**
 * Clase que representa una gasolinera. Cada una se rellenará con los datos procesados desde el JSON descargado del servidor
 * Created by Andrés Barrado Martín on 10/24/16
 */

public class Gasolinera implements Comparable<Gasolinera> {
    private int IDEESS;
    private String localidad;
    private String provincia;
    private String direccion;
    private double gasoleo_a;
    private double gasolina_95;
    private String rotulo;
    //TODO mejorable (se podría parametrizar para elegir la ordenación)
    private static final boolean orderByGasoleoA = true;


    public Gasolinera (int IDEESS, String localidad, String provincia, String direccion, double gasoleo_a, double gasolina_95, String rotulo){
        this.IDEESS = IDEESS;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.gasoleo_a = gasoleo_a;
        this.gasolina_95 = gasolina_95;
        this.rotulo = rotulo;
    }// Gasolinera

    public int getIDEESS() {
        return IDEESS;
    }

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

    @Override
    public int compareTo(Gasolinera another) {
        //Si el atributo orderByGasoleoA está a true se ordena por precio del diesel
        if(orderByGasoleoA){
            if(this.getGasoleo_a()>another.getGasoleo_a())
                return 1;
            else if(this.getGasoleo_a()<another.getGasoleo_a())
                return -1;
            else
                return 0;
         //En caso contrario se ordena por precio de la gasolina
        }else{
            if(this.getGasolina_95()>another.getGasolina_95())
                return 1;
            else if(this.getGasolina_95()<another.getGasolina_95())
                return -1;
            else
                return 0;
        }//if
    }//compareTo
}// Gasolinera

