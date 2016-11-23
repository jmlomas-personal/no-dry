package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.nodry.nodry.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.*;

/**
 * US-175722
 * TASK 295206
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest_US175722_295206{

    @Rule
    public ActivityTestRule<FiltersActivity> mActivityFiltersTestRule = new ActivityTestRule<>(FiltersActivity.class, true, false);

    @Test
    public void integrationTest(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, FiltersActivity.class);
        mActivityFiltersTestRule.launchActivity(intent);
        //El título filtros existe y coincide
        onView(withId(R.id.lbl_Filtros)).check(matches(withText("Filtros")));
        //El botón filtrar existe y su texto coincide
        onView(withId(R.id.button_filtrar)).check(matches(isDisplayed()));
        onView(withId(R.id.button_filtrar)).check(matches(withText("FILTRAR")));
        //La etiqueta Comunidad Autónoma es correcta y el spinner existe
        onView(withId(R.id.lbl_filter_CCAA)).check(matches(withText("Comunidad Autónoma")));
        onView(withId(R.id.spinner_CCAA)).check(matches(isDisplayed()));
        //La etiqueta tipo de combustible y el desplegable correspondiente existe
        onView(withId(R.id.textViewFiltros)).check(matches(withText("Tipo de combustible")));
        onView(withId(R.id.spinner_TiposGasolina)).check(matches(isDisplayed()));
        //La etiqueta Máximo y el campo de escritura existen correctamente
        onView(withId(R.id.infoMaximo)).check(matches(withText("Máximo:")));
        onView(withId(R.id.editText_maximo)).check(matches(isDisplayed()));

        //Editamos el campo Máximo:
        onView(withId(R.id.editText_maximo)).perform(typeText("1"));
        closeSoftKeyboard();

        //Pulsamos el botón Filtrar
        onView(withId(R.id.button_filtrar)).perform(click());

        //Comprobamos que se muestra el toast indicando que falta un campo por introducir
        onView(withText("Introduzca un tipo de gasolina")).inRoot(withDecorView(not(is(mActivityFiltersTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}