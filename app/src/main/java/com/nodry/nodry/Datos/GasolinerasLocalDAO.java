package com.nodry.nodry.Datos;

import com.nodry.nodry.Comunes.Datos.IGasolinerasLocalDAO;
import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.LocalFetch;
import com.nodry.nodry.Utils.ParserJSON;

import java.io.IOException;
import java.util.List;

/**
 * Clase para tratar con los datos
 * de las Gasolineras de forma local.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class GasolinerasLocalDAO implements IGasolinerasLocalDAO {

    @Override
    public List<Gasolinera> getListGasolineras() throws IOException {
        List<Gasolinera> listaGasolineras;

        DataFetch dataFetch = new LocalFetch();
        dataFetch.getJSON();

        return ParserJSON.readJsonStream(dataFetch.getBufferedData());
    }
}
