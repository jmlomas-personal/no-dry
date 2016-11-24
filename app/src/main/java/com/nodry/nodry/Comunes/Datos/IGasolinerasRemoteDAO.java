package com.nodry.nodry.Comunes.Datos;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz para tratar con los datos
 * de las Gasolineras de forma remota.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface IGasolinerasRemoteDAO {

    /**
     * Metodo para obtener el listado de gasolineras. No tenemos una capa de persistencia como tal,
     * con lo que se traen los datos al consumir un servicio REST externo.
     * Implementar en un futuro la capa de persistencia mediante ficheros planos es sencillo
     * siguiendo la arquitectura seleccionada.
     * @param CCAA con la comunidad autonoma de las gasolineras a retornar
     * @return listado de gasolineras obtenido de forma remota contra un servicio REST externo
     * @throws IOException si se produce alguna anomalia a la hora de tratar servicio que lee de remoto
     */
    List<Gasolinera> getListGasolineras(String CCAA) throws IOException;
}
