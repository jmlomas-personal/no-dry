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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Filtrar gasolineras por precio
 *
 * US175722_Prueba7
 *
 *  Implementado por Alba Zubizarreta
 *
 * o	PRUEBA 7 – Precio máximo negativo.
 1.	El usuario abre la aplicación.
 2.	La aplicación muestra un diálogo para indicar al usuario que se están cargando los datos.
 3.	La aplicación muestra la lista de gasolineras de la comunidad autónoma por defecto.
 4.	El usuario selecciona la opción de filtros del toolbar.
 5.	La aplicación muestra una ventana en la que aparecen opciones para filtrar por comunidad autónoma, para filtrar por un rango entre un mínimo fijo y un máximo que podrá establecer el usuario y para establecer el tipo de combustible.
 6.	El usuario introduce un valor negativo en el campo del precio máximo.
 7.	La aplicación informa al usuario de que el filtrado no se puede realizar porque el valor máximo es negativo.

 */

public class IntegrationTest_US175722_Prueba7 {

    @Rule
    public ActivityTestRule<FiltersActivity> mActivityFiltersTestRule = new ActivityTestRule<>(FiltersActivity.class, true, false);

    @Test
    public void integrationTest(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, FiltersActivity.class);
        mActivityFiltersTestRule.launchActivity(intent);

        //seleccionamos una comunidad autónoma del desplegable
        onView(withId(R.id.spinner_CCAA)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Asturias"))).perform(click());
        onView(withId(R.id.spinner_CCAA)).check(matches(withSpinnerText(containsString("Asturias"))));

        //seleccionamos un tipo de gasolina del desplegable
        onView(withId(R.id.spinner_TiposGasolina)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sin Plomo 95"))).perform(click());
        onView(withId(R.id.spinner_TiposGasolina)).check(matches(withSpinnerText(containsString("Sin Plomo 95"))));

        //Editamos el campo Máximo a un número negativo
        onView(withId(R.id.editText_maximo)).perform(typeText("-5"));

        //quitar teclado
        closeSoftKeyboard();

        //Pulsamos el botón Filtrar
        onView(withId(R.id.button_filtrar)).perform(click());

        //Comprobamos que se muestra el toast indicando que el numero introducido es negativo
        onView(withText("El número introducido es negativo")).inRoot(withDecorView(not(is(mActivityFiltersTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
