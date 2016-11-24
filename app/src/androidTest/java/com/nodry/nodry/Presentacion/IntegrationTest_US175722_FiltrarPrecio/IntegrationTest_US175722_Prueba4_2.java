package com.nodry.nodry.Presentacion.IntegrationTest_US175722_FiltrarPrecio;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.nodry.nodry.Presentacion.FiltersActivity;
import com.nodry.nodry.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * /**
 *  Filtrar gasolineras por precio
 *
 * US175722_Prueba4_2
 *
 * Implementado por Juan Manuel Lomas
 *
 * o	PRUEBA 4 – Campos vacíos en el filtrado.
 1.	El usuario abre la aplicación.
 2.	La aplicación muestra un diálogo para indicar al usuario que se están cargando los datos.
 3.	La aplicación muestra la lista de gasolineras de la comunidad autónoma por defecto.
 4.	El usuario selecciona la opción de filtros del toolbar.
 5.	La aplicación muestra una ventana en la que aparecen opciones para filtrar por comunidad autónoma, para filtrar por un rango entre un mínimo fijo y un máximo que podrá establecer el usuario y para establecer el tipo de combustible.
 6.	El usuario introducirá un valor máximo de precio o el tipo de combustible por el que desea filtrar (solamente uno).
 7.	El usuario pulsará el botón de filtrar.
 8.	La aplicación mostrará un mensaje indicando que hay campos en el filtrado sin rellenar y son necesarios para la realización del filtrado.

 */


public class IntegrationTest_US175722_Prueba4_2 {

    private final static String MSG_FILTER_ERROR_COMBUS   = "Seleccione un valor máximo";

    private final static String VALUE_SPINNER_TIPOGAS   = "Sin Plomo 95";
    private final static String VALUE_MAX               = "1";

    @Rule
    public ActivityTestRule<FiltersActivity> mActivityFiltersTestRule = new ActivityTestRule<>(FiltersActivity.class, true, false);

    @Test
    public void integrationTest() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, FiltersActivity.class);
        mActivityFiltersTestRule.launchActivity(intent);

        //seleccionamos un tipo de gasolina del desplegable
        onView(withId(R.id.spinner_TiposGasolina)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(VALUE_SPINNER_TIPOGAS))).perform(click());
        onView(withId(R.id.spinner_TiposGasolina)).check(matches(withSpinnerText(containsString(VALUE_SPINNER_TIPOGAS))));

        //quitar teclado
        closeSoftKeyboard();

        //Pulsamos el botón Filtrar
        onView(withId(R.id.button_filtrar)).perform(click());

        //Comprobamos que se muestra el toast indicando que se ha de introducir un valor máximo
        onView(withText(MSG_FILTER_ERROR_COMBUS)).inRoot(withDecorView(not(is(mActivityFiltersTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}