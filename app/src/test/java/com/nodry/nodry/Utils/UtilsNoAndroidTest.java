package com.nodry.nodry.Utils;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class UtilsNoAndroidTest {

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
    private static final Double FIELD_DIESEL          = 10.0;
    private static final Double FIELD_DIESELPLUS      = 1.06;
    private static final Double FIELD_LATITUD         = 43.395944;
    private static final Double FIELD_LONGITUD        = -4.155194;

    private static final Double MAX_VALUE             = 5.0;

    @Before
    public void setUp() throws Exception{

        listaGasolineras = new ArrayList<Gasolinera>();

        for(int i=0; i<10; i=i+2){
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
                    FIELD_CCAA),
            gas2 = new Gasolinera(
                    i+1,
                    FIELD_LOCALIDAD,
                    FIELD_PROVINCIA,
                    FIELD_DIRECCION,
                    FIELD_DIESEL - (i+2),
                    FIELD_SINPLOMO95,
                    FIELD_ROTULO + (i+1),
                    FIELD_DIESELPLUS,
                    FIELD_SINPLOMO98,
                    FIELD_HORARIO,
                    FIELD_LATITUD,
                    FIELD_LONGITUD,
                    FIELD_MUNICIPIO,
                    FIELD_CCAA);

            listaGasolineras.add(gas);
            listaGasolineras.add(gas2);
        }

    }

    @Test
    public void removeZeroValueTest(){
        Utils.removeZeroValue(TipoGasolina.DIESEL, listaGasolineras);

        Assert.assertTrue(listaGasolineras.size() == 9);
    }

    @Test
    public void removeMaxValueTest(){
        Utils.removeMaxValue(MAX_VALUE, TipoGasolina.DIESEL, listaGasolineras);

        for(Gasolinera g : listaGasolineras){
            if(g.getGasoleo_a() > MAX_VALUE){
                Assert.fail();
            }
        }
    }

    @Test
    public void getMasBarataTest(){
        Double precio = Utils.getMasBarata(TipoGasolina.DIESEL, listaGasolineras);

        Assert.assertTrue(precio == 0.0);
    }

    @Test
    public void getGasolinera(){
        Gasolinera g = Utils.getGasolinera(0, listaGasolineras);

        Assert.assertTrue(g.getRotulo().equals(FIELD_ROTULO + 0));
    }

}
