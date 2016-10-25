package com.nodry.nodry.Datos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres Barrado Martín on 10/24/16.
 */
public class GasolinerasDAO implements IGasolinerasDAO {

    private List<Gasolinera> listaGasolineras;

    public GasolinerasDAO() {
        listaGasolineras = new ArrayList<Gasolinera>();
    }

    /**
     * Metodo que retorna la lista de gasolineras leidas del JSON
     * @return Lista de gasolineras
     */
    @Override
    public List<Gasolinera> getListGasolineras(){
        try {
            RemoteFetch remoteFetch = new RemoteFetch();
            remoteFetch.getJSON();
            listaGasolineras = ParserJSON.readJsonStream(remoteFetch.getBufferedDataGasolineras());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return listaGasolineras;
        }
    }
}
