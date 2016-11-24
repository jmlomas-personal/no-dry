package com.nodry.nodry.Presentacion.IntegrationTest_US175729_OrdenarPrecio;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.nodry.nodry.Presentacion.MainActivity;
import com.nodry.nodry.R;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.Utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 *  Ordenar listado por precio
 *
 * US175729_Prueba2
 *
 *  Implementado por Orlando Britto
 *
 * o	PRUEBA 2 – Ordenar por precio.
 1.	El usuario abre la aplicación
 2.	La aplicación muestra un diálogo para indicar al usuario que se están cargando los datos.
 3.	La aplicación muestra la lista de gasolineras de la comunidad autónoma por defecto.
 4.	El usuario selecciona la opción de ordenar que aparece en el toolbar (flecha).
 5.	La aplicación mostrará una ventana preguntando al usuario el tipo de combustible por el que desea que se ordene el precio.
 6.	El usuario seleccionará un tipo de combustible.
 7.	El usuario seleccionará la opción de OK.
 8.	La aplicación mostrará la lista de gasolineras de la comunidad autónoma que posean la gasolina seleccionada por el usuario, ordenadas por precio de menor a mayor.

 */

@android.support.test.filters.LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest_US175729_Prueba2 {

    private static final String LBL_ORDENAR     = "Ordenar";
    private static final String LBL_SINPLOMO95  = "Sin Plomo 95";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Utils.setContext(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void Ordenar_11() {

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_ordenar), withContentDescription(LBL_ORDENAR), isDisplayed()));
        actionMenuItemView.perform(click());

        onView(allOf(withId(android.R.id.text1), withText(LBL_SINPLOMO95),
                childAtPosition(allOf(withId(R.id.select_dialog_listview),
                        withParent(withId(R.id.contentPanel))), 0),
                isDisplayed())).perform(click());

        // TO-DO recoger la primera gasolinera del listado y la segunda
        // Comprobar que la primera tiene un valor inferior al de la segunda

    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
