package com.nodry.nodry.Presentacion;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Utils.TipoGasolina;

import java.util.Comparator;

/**
 * Clase comparador para poder ordenar
 * las gasolineras por precio.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class PrecioSort implements Comparator<Gasolinera> {

    // Con el tipo de la gasolina a comparar el precio
    TipoGasolina tipoGasolina;

    /**
     * Constructor de la clase
     * @param tipoGasolina con el tipo de gasolina a comparar el precio
     */
    public PrecioSort(TipoGasolina tipoGasolina){
        this.tipoGasolina = tipoGasolina;
    }

    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {

        int compare = 0;
        double value1 = 0.0, value2 = 0.0;

        switch (tipoGasolina) {

            case SINPLOMO95:
                value1 = g1.getGasolina_95();
                value2 = g2.getGasolina_95();
                break;

            case SINPLOMO98:
                value1 = g1.getGasolina_98();
                value2 = g2.getGasolina_98();
                break;

            case DIESEL:
                value1 = g1.getGasoleo_a();
                value2 = g2.getGasoleo_a();
                break;

            case DIESELPLUS:
                value1 = g1.getGasoleo_b();
                value2 = g2.getGasoleo_b();
                break;

        }

        if(value1 > value2) {
            compare = 1;
        }else if(value1 < value2) {
            compare = -1;
        }

        return compare;
    }

}
