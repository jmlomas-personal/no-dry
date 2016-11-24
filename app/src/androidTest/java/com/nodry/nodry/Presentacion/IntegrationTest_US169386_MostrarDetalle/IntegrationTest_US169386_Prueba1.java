package com.nodry.nodry.Presentacion.IntegrationTest_US169386_MostrarDetalle;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Presentacion.DetailsActivity;
import com.nodry.nodry.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Mostrar detalle de gasolineras
 * US169386_Prueba1
 *
 * Implementado por Juan Manuel Lomas
 *
 * o	PRUEBA 1 – Comprobación de ítems.
 1.	El usuario abre la aplicación
 2.	La aplicación muestra un diálogo para indicar al usuario que se están cargando los datos.
 3.	La aplicación muestra la lista de gasolineras de la comunidad autónoma por defecto.
 4.	El usuario selecciona una gasolinera para abrir el detalle.
 4.1. La aplicación mostrará los datos asociados a la gasolinera tales como icono, nombre, dirección, combustibles disponibles (95, 98, diésel y diésel +), provincia, municipio, horario de apertura, y una opción para ubicar la gasolinera mediante google maps.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest_US169386_Prueba1 {

    List<Gasolinera> listaGasolineras;

    private static final String EXTRA_CCAA            = "CCAA";
    private static final String EXTRA_IDEESS          = "IDEESS";
    private static final String EXTRA_LIST            = "LIST";
    private static final String CCAA                  = "06";

    private static final String LBL_LOCALIDAD         = "Localidad:";
    private static final String LBL_PROVINCIA         = "Provincia:";
    private static final String LBL_DIRECCION         = "Dirección:";
    private static final String LBL_SINPLOMO95        = "Sin Plomo 95:";
    private static final String LBL_SINPLOMO98        = "Sin Plomo 98:";
    private static final String LBL_DIESEL            = "Diesel:";
    private static final String LBL_DIESELPLUS        = "Diesel Plus:";

    private static final String FIELD_ROTULO          = "Gasolinera ";
    private static final String FIELD_LOCALIDAD       = "Santander";
    private static final String FIELD_MUNICIPIO       = "Santander";
    private static final String FIELD_PROVINCIA       = "Cantabria";
    private static final String FIELD_DIRECCION       = "Calle Falsa 1 2 3";
    private static final String FIELD_HORARIO         = "L-D: 08:00-21:00";
    private static final Double FIELD_SINPLOMO95      = 1.07;
    private static final Double FIELD_SINPLOMO98      = 1.15;
    private static final Double FIELD_DIESEL          = 0.97;
    private static final Double FIELD_DIESELPLUS      = 1.06;
    private static final Double FIELD_LATITUD         = 43.395944;
    private static final Double FIELD_LONGITUD        = -4.155194;

    @Rule
    public ActivityTestRule<DetailsActivity> mActivityDetailsTestRule = new ActivityTestRule<>(DetailsActivity.class, true, false);

    @Test
    public void testIntent() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        listaGasolineras = new ArrayList<Gasolinera>();

        for(int i=0; i<3; i++){
            Gasolinera gas = new Gasolinera(
                    i,
                    FIELD_LOCALIDAD,
                    FIELD_PROVINCIA,
                    FIELD_DIRECCION,
                    FIELD_DIESEL + i,
                    FIELD_SINPLOMO95,
                    FIELD_ROTULO + i,
                    FIELD_DIESELPLUS + i,
                    FIELD_SINPLOMO98,
                    FIELD_HORARIO,
                    FIELD_LATITUD,
                    FIELD_LONGITUD,
                    FIELD_MUNICIPIO,
                    CCAA);
            listaGasolineras.add(gas);
        }

        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putExtra(EXTRA_CCAA,     CCAA);
        intent.putExtra(EXTRA_IDEESS,   listaGasolineras.get(1).getIDEESS());
        intent.putExtra(EXTRA_LIST,     (Serializable)listaGasolineras);
        mActivityDetailsTestRule.launchActivity(intent);

    // Pruebas realizadas sobre el Activity

        //Icono Existe
        onView(withId(R.id.imageView_icono)).check(matches(isDisplayed()));
        //Rotulo existe y su nombre coincide
        onView(withId(R.id.textView_Rotulo)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_Rotulo)).check(matches(withText(listaGasolineras.get(1).getRotulo())));
        //Localidad existe y su etiqueta coincide
        onView(withId(R.id.textView_Localidad)).check(matches(withText(listaGasolineras.get(1).getLocalidad())));
        onView(withId(R.id.lbl_Localidad)).check(matches(withText(LBL_LOCALIDAD)));
        //Provincia existe y su etiqueta coincide
        onView(withId(R.id.textView_Provincia)).check(matches(withText(listaGasolineras.get(1).getProvincia())));
        onView(withId(R.id.lbl_Provincia)).check(matches(withText(LBL_PROVINCIA)));
        //Dirección existe y su etiqueta coincide
        onView(withId(R.id.lbl_Direccion)).check(matches(withText(LBL_DIRECCION)));
        onView(withId(R.id.textView_Direccion)).check(matches(isDisplayed()));
        //Los campos de los diferentes combustibles existen
        onView(withId(R.id.textView_Gasolina95)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_Gasolina98)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_Diesel)).check(matches(isDisplayed()));
        onView(withId(R.id.textView_DieselPlus)).check(matches(isDisplayed()));
        //Las etiquetas de los diferentes combustibles coinciden
        onView(withId(R.id.lbl_Gasolina95)).check(matches(withText(LBL_SINPLOMO95)));
        onView(withId(R.id.lbl_Gasolina98)).check(matches(withText(LBL_SINPLOMO98)));
        onView(withId(R.id.lbl_Diesel)).check(matches(withText(LBL_DIESEL)));
        onView(withId(R.id.lbl_DieselPlus)).check(matches(withText(LBL_DIESELPLUS)));
        //Los botones aparecen para las gasolineras más baratas
        onView(withId(R.id.button_Diesel)).check(matches(isDisplayed()));
        onView(withId(R.id.button_DieselPlus)).check(matches(isDisplayed()));
        //El horario se muestra correctamente
        onView(withId(R.id.textView_Horario)).check(matches(withText(listaGasolineras.get(1).getHorario())));

    }

}