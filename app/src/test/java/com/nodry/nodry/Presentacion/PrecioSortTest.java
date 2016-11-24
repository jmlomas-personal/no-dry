package com.nodry.nodry.Presentacion;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Utils.TipoGasolina;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnit4.class)
public class PrecioSortTest {

    private List<Gasolinera> listaGasolineras;

    private static final String FIELD_CCAA            = "CCAA";
    private static final String FIELD_ROTULO          = "Gasolinera ";
    private static final String FIELD_LOCALIDAD       = "Santander";
    private static final String FIELD_MUNICIPIO       = "Santander";
    private static final String FIELD_PROVINCIA       = "Cantabria";
    private static final String FIELD_DIRECCION       = "Calle Falsa 1 2 3";
    private static final String FIELD_HORARIO         = "L-D: 08:00-21:00";
    private static final Double FIELD_SINPLOMO95      = 1.07;
    private static final Double FIELD_SINPLOMO98      = 1.15;
    private static final Double FIELD_DIESEL          = 5.0;
    private static final Double FIELD_DIESELPLUS      = 1.06;
    private static final Double FIELD_LATITUD         = 43.395944;
    private static final Double FIELD_LONGITUD        = -4.155194;

    @Before
    public void setUp() throws Exception{

        listaGasolineras = new ArrayList<Gasolinera>();

        for(int i=0; i<3; i++){
            Gasolinera gas = new Gasolinera(
                    i,
                    FIELD_LOCALIDAD,
                    FIELD_PROVINCIA,
                    FIELD_DIRECCION,
                    FIELD_DIESEL - i,
                    FIELD_SINPLOMO95,
                    FIELD_ROTULO + i,
                    FIELD_DIESELPLUS,
                    FIELD_SINPLOMO98,
                    FIELD_HORARIO,
                    FIELD_LATITUD,
                    FIELD_LONGITUD,
                    FIELD_MUNICIPIO,
                    FIELD_CCAA);

            listaGasolineras.add(gas);
        }

    }

    @Test
    public void compareTest(){
        try {
            Gasolinera primeraOld = listaGasolineras.get(0),
                ultimaOld = listaGasolineras.get(listaGasolineras.size()-1);
            Collections.sort(listaGasolineras,  new PrecioSort(TipoGasolina.DIESEL));

            if(listaGasolineras.get(0).getGasoleo_a() != ultimaOld.getGasoleo_a() ||
                    listaGasolineras.get(listaGasolineras.size()-1).getGasoleo_a() != primeraOld.getGasoleo_a()){
                Assert.fail();
            }

        }catch(Exception E){
            Assert.fail();
        }
    }

}
