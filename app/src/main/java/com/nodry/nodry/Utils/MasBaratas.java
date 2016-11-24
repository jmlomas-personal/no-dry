package com.nodry.nodry.Utils;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

/**
 * Clase para manejar las gasolineras
 * mas baratas para cada tipo de gasolina.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class MasBaratas {

    // Atributos
    Gasolinera masBarata95;
    Gasolinera masBarata98;
    Gasolinera masBarataDiesel;
    Gasolinera masBarataDieselPlus;

    // Getters y Setters
    public Gasolinera getMasBarata95() {
        return masBarata95;
    }

    public void setMasBarata95(Gasolinera masBarata95) {
        this.masBarata95 = masBarata95;
    }

    public Gasolinera getMasBarata98() {
        return masBarata98;
    }

    public void setMasBarata98(Gasolinera masBarata98) {
        this.masBarata98 = masBarata98;
    }

    public Gasolinera getMasBarataDiesel() {
        return masBarataDiesel;
    }

    public void setMasBarataDiesel(Gasolinera masBarataDiesel) {
        this.masBarataDiesel = masBarataDiesel;
    }

    public Gasolinera getMasBarataDieselPlus() {
        return masBarataDieselPlus;
    }

    public void setMasBarataDieselPlus(Gasolinera masBarataDieselPlus) {
        this.masBarataDieselPlus = masBarataDieselPlus;
    }

}
