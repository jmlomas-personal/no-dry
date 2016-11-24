package com.nodry.nodry.Datos;

import com.nodry.nodry.Comunes.Datos.IGasolinerasRemoteDAO;
import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.ParserJSON;
import com.nodry.nodry.Utils.RemoteFetch;

import java.io.IOException;
import java.util.List;

/**
 * Clase para tratar con los datos
 * de las Gasolineras de forma remota.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class GasolinerasRemoteDAO implements IGasolinerasRemoteDAO {

    @Override
    public List<Gasolinera> getListGasolineras(String CCAA) throws IOException {

        DataFetch dataFetch = new RemoteFetch(CCAA);
        dataFetch.getJSON();

        return ParserJSON.readJsonStream(dataFetch.getBufferedData());
    }

}
