package com.nodry.nodry.Comunes.Negocio;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz para tratar con los datos
 * de Negocio sobre Gasolineras.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface IGestionGasolineras {

    /**
     * Devuelve un listado de gasolineras segun una comunidad autonoma dada
     * @param CCAA con la comunidad autonoma de las gasolineras a retornar
     * @return listado de gasolineras
     * @throws IOException si se produce alguna anomalia a la hora de obtener el listado
     */
    List<Gasolinera> obtenGasolinerasServicioRest(String CCAA) throws IOException;

    /**
     * Devuelve un listado de gasolineras correspondiente a la ultima peticion satisfactoria
     * realizada
     * @return listado de gasolineras
     * @throws IOException si se produce alguna anomalia a la hora de obtener el listado
     */
    List<Gasolinera> obtenGasolinerasCache() throws IOException ;

}

