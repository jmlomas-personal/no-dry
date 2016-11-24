package com.nodry.nodry.Presentacion.IntegrationTest_US175722_FiltrarPrecio;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.nodry.nodry.Presentacion.FiltersActivity;
import com.nodry.nodry.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 *  Filtrar gasolineras por precio
 *
 * US175722_Prueba4
 *
 * Implementado por Manuel Álvarez
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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest_US175722_Prueba4 {

    private final static String MSG_FILTER_FILL_DATA    = "Introduzca un tipo de gasolina";

    private final static String LBL_BUTTON_FILTROS      = "Filtros";
    private final static String LBL_BUTTON_FILTER       = "FILTRAR";
    private final static String LBL_CCAA                = "Comunidad Autónoma";
    private final static String LBL_TIPO_COMBUSTIBLE    = "Tipo de combustible";
    private final static String LBL_MAX                 = "Máximo:";

    private final static String VALUE_MAX               = "1";

    @Rule
    public ActivityTestRule<FiltersActivity> mActivityFiltersTestRule = new ActivityTestRule<>(FiltersActivity.class, true, false);

    @Test
    public void integrationTest(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, FiltersActivity.class);
        mActivityFiltersTestRule.launchActivity(intent);
        //El título filtros existe y coincide
        onView(withId(R.id.lbl_Filtros)).check(matches(withText(LBL_BUTTON_FILTROS)));
        //El botón filtrar existe y su texto coincide
        onView(withId(R.id.button_filtrar)).check(matches(isDisplayed()));
        onView(withId(R.id.button_filtrar)).check(matches(withText(LBL_BUTTON_FILTER)));
        //La etiqueta Comunidad Autónoma es correcta y el spinner existe
        onView(withId(R.id.lbl_filter_CCAA)).check(matches(withText(LBL_CCAA)));
        onView(withId(R.id.spinner_CCAA)).check(matches(isDisplayed()));
        //La etiqueta tipo de combustible y el desplegable correspondiente existe
        onView(withId(R.id.textViewFiltros)).check(matches(withText(LBL_TIPO_COMBUSTIBLE)));
        onView(withId(R.id.spinner_TiposGasolina)).check(matches(isDisplayed()));
        //La etiqueta Máximo y el campo de escritura existen correctamente
        onView(withId(R.id.infoMaximo)).check(matches(withText(LBL_MAX)));
        onView(withId(R.id.editText_maximo)).check(matches(isDisplayed()));

        //Editamos el campo Máximo:
        onView(withId(R.id.editText_maximo)).perform(typeText(VALUE_MAX));
        closeSoftKeyboard();

        //Pulsamos el botón Filtrar
        onView(withId(R.id.button_filtrar)).perform(click());

        //Comprobamos que se muestra el toast indicando que falta un campo por introducir
        onView(withText(MSG_FILTER_FILL_DATA)).inRoot(withDecorView(not(is(mActivityFiltersTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}