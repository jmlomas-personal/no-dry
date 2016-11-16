package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.GasolinerasDAO;
import com.nodry.nodry.Datos.IGasolinerasDAO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que realiza una serie de operaciones con los
 * datos de las gasolineras obtenidas de la capa de Datos.
 * @author Orlando Britto.
 * @version 1.0
 */
public class GestionGasolineras implements IGestionGasolineras {

    IGasolinerasDAO gasolinerasDAO;

    /**
     * Constructor
     */
    public GestionGasolineras(){
        gasolinerasDAO = new GasolinerasDAO();
    }

    @Override
    public List<Gasolinera> getGasolineras(HashMap<String, String> filtros, boolean bForceLocal) {

        return gasolinerasDAO.getListGasolineras(filtros, bForceLocal);

    }

    @Override
    public Gasolinera getGasolinera(int IDEESS, List<Gasolinera> listaGasolineras) {
        Gasolinera gasolinera = null;

        for(Gasolinera g : listaGasolineras){
            if(g.getIDEESS()==IDEESS){
                gasolinera = g;
            }
        }

        return gasolinera;
    }

    public MasBaratas getMasBaratas(String localidad, List<Gasolinera> listaGasolineras){

        MasBaratas masBaratas = new MasBaratas();

        for(Gasolinera g: listaGasolineras){

            if(g.getLocalidad().trim().equals(localidad)) {

                // Comprobamos si es la mas barata de alguno de los cuatro carburantes
                if ((masBaratas.getMasBarata95() == null && g.getGasolina_95() > 0) ||
                        (g.getGasolina_95() > 0 && masBaratas.getMasBarata95().getGasolina_95() > g.getGasolina_95())) {
                    masBaratas.setMasBarata95(g);
                }

                if ((masBaratas.getMasBarata98() == null && g.getGasolina_98() > 0) ||
                        (g.getGasolina_98() > 0 && masBaratas.getMasBarata98().getGasolina_98() > g.getGasolina_98())) {
                    masBaratas.setMasBarata98(g);
                }

                if ((masBaratas.getMasBarataDiesel() == null && g.getGasoleo_a() > 0) ||
                        (g.getGasoleo_a() > 0 && masBaratas.getMasBarataDiesel().getGasoleo_a() > g.getGasoleo_a())) {
                    masBaratas.setMasBarataDiesel(g);
                }

                if ((masBaratas.getMasBarataDieselPlus() == null && g.getGasoleo_b() > 0) ||
                        (g.getGasoleo_b() > 0 && masBaratas.getMasBarataDieselPlus().getGasoleo_b() > g.getGasoleo_b())) {
                    masBaratas.setMasBarataDieselPlus(g);
                }
            }
        }

        return masBaratas;
    }

    public List<Gasolinera> ordenarPorPrecio(List<Gasolinera> listaGasolineras, TipoGasolina tipoGasolina){
        Collections.sort(listaGasolineras, new PrecioSort(tipoGasolina));
        return listaGasolineras;
    }

}
