<<<<<<< HEAD
package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;

import java.util.List;

/**
 * Interfaz de la capa de negocio que opera con gasolineras
 * @author 0rlando Britto.
 * @version 1.0
 */
public interface IGestionGasolineras {

    /**
     * Metodo que retorna la lista de gasolineras
     * para una comunidad autonoma especifica.
     * @return la lista de gasolineras de esa CCAA
     */
    List<Gasolinera> getGasolineras(String CCAA, boolean bForceLocal);
    Gasolinera getGasolinera(int IDEESS, List<Gasolinera> listaGasolineras);

}
=======
package com.nodry.nodry.Negocio;

import com.nodry.nodry.Datos.Gasolinera;

import java.util.List;

/**
 * Interfaz de la capa de negocio que opera con gasolineras
 * @author 0rlando Britto.
 * @version 1.0
 */
public interface IGestionGasolineras {

    /**
     * Metodo que retorna la lista de gasolineras
     * para una comunidad autonoma especifica.
     * @return la lista de gasolineras de esa CCAA
     */
    List<Gasolinera> getGasolineras(String CCAA, boolean bForceLocal);
    Gasolinera getGasolinera(int IDEESS, List<Gasolinera> listaGasolineras);

}
>>>>>>> 1ba4ba6e710bd9da4db077ffeb0f54581a98419d
