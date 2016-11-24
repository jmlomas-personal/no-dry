package com.nodry.nodry.Comunes.Datos;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para tratar con los datos
 * de las Gasolineras de forma local.
 * @author Code4Fun.org
 * @version 11/2016
 */
public interface IGasolinerasLocalDAO {

    /**
     * Metodo para obtener el listado de gasolineras. No tenemos una capa de persistencia como tal,
     * con lo que se traen los datos de un fichero local donde se ha volcado la ultima peticion
     * satisfactoria.
     * Implementar en un futuro la capa de persistencia mediante ficheros planos es sencillo
     * siguiendo la arquitectura seleccionada.
     * @return listado de gasolineras obtenido de local
     * @throws FileNotFoundException si no se encuentra el fichero
     * @throws IOException si se produce alguna anomalia a la hora de tratar con el fichero
     */
    List<Gasolinera> getListGasolineras() throws FileNotFoundException, IOException;
}
