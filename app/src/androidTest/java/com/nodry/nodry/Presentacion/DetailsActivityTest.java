package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.nodry.nodry.Datos.Gasolinera;
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

/**
 * Created by Manuel on 15/11/2016.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {

    List<Gasolinera> listaGasolineras;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<DetailsActivity> mActivityDetailsTestRule = new ActivityTestRule<>(DetailsActivity.class);

    @Test
    public void testIntent() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        listaGasolineras = new ArrayList<Gasolinera>();
        for(int i=0;i<2;i++){
            Gasolinera gas = new Gasolinera(i,"Santander","Cantabria","Calle Falsa 1 2 3",0.97,1.07,"Gasolinera "+i,1.06,1.15,"L-D: 08:00-21:00",43.395944,-4.155194);
            listaGasolineras.add(gas);
        }
        Intent intent = new Intent(targetContext, DetailsActivity.class);
        intent.putExtra("CCAA",06);
        intent.putExtra("IDEESS", listaGasolineras.get((int)1).getIDEESS());
        intent.putExtra("listaGasolineras",(Serializable)listaGasolineras);
        mActivityDetailsTestRule.launchActivity(intent);
    /* Your activity is initialized and ready to go. */
        onView(withId(R.id.imageView_icono)).check(matches(isDisplayed()));

    }

}