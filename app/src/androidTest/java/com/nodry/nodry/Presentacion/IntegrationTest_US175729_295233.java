package com.nodry.nodry.Presentacion;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;




import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.GasolinerasDAO;
import com.nodry.nodry.Datos.IGasolinerasDAO;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.Presentacion.MainActivity;
import com.nodry.nodry.R;
import com.nodry.nodry.Utils.DataFetch;
import com.nodry.nodry.Utils.Utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.nodry.nodry.Utils.DataFetch.context;
import static org.hamcrest.Matchers.allOf;


@android.support.test.filters.LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest_US175729_295233 {


    private static final String TEMP_FILE_NAME = "temp.txt";
    private static final String TEST_LOCAL_FILE_NAME = "localFileText.txt";

    private static final String DEFAULT_CCAA = "06";

    private static final String TEST_ROTULO             = "CEPSA";
    private static final String TEST_DIRECCION          = "CARRETERA 6316 KM. 10,5";
    private static final String TEST_LOCALIDAD          = "NOVALES";
    private static final String TEST_PROVINCIA          = "CANTABRIA";
    private static final Double TEST_PRECIO_GASOLINA    = 0.500;
    private static final Double TEST_PRECIO_GASOLINA98  = 0.500;
    private static final Double TEST_PRECIO_GASOLEO     = 0.500;
    private static final Double TEST_PRECIO_GASOLEO_PLUS= 0.500;
    private static final Integer TEST_IDEESS            = 1039;

    private static final boolean FORCE_LOCAL_TRUE = true;
    private static final boolean FORCE_LOCAL_FALSE = false;
    List<Gasolinera> gasolineras;
    GasolinerasDAO  g;
    private static GestionGasolineras gestionGasolineras;
    private static BufferedInputStream bufferedDataGasolinerasTest;
    private static InputStream stream;
    private static HashMap<String, String> filtros;
    private static String jsonData =
            "{" +
                    "\"Fecha\": \"25/10/2016 20:40:39\"," +
                    "\"ListaEESSPrecio\":[" +
                    "{" +
                    "\"C.P.\": \"39195\","+
                    "\"Dirección\": \"CARRETERA ARGOÑOS SOMO KM. 28,7\","+
                    "\"Horario\": \"L-D: 07:00-22:00\","+
                    "\"Latitud\": \"43,469778\","+
                    "\"Localidad\": \"ARNUERO\","+
                    "\"Longitud (WGS84)\": \"-3,562250\","+
                    "\"Margen\": \"D\","+
                    "\"Municipio\": \"Arnuero\","+
                    "\"Precio Biodiesel\": null," +
                    "\"Precio Bioetanol\": null," +
                    "\"Precio Gas Natural Comprimido\": null," +
                    "\"Precio Gas Natural Licuado\": null," +
                    "\"Precio Gases licuados del petróleo\": null," +
                    "\"Precio Gasoleo A\": \"1,079\","+
                    "\"Precio Gasoleo B\": null," +
                    "\"Precio Gasolina 95 Protección\": \"1,199\","+
                    "\"Precio Gasolina  98\": null," +
                    "\"Precio Nuevo Gasoleo A\": \"1,129\","+
                    "\"Provincia\": \"CANTABRIA\","+
                    "\"Remisión\": \"OM\","+
                    "\"Rótulo\": \"CAMPSA\","+
                    "\"Tipo Venta\": \"P\","+
                    "\"% BioEtanol\": \"0,0\","+
                    "\"% Éster metílico\": \"0,0\","+
                    "\"IDEESS\": \"1080\","+
                    "\"IDMunicipio\": \"5749\","+
                    "\"IDProvincia\": \"39\","+
                    "\"IDCCAA\": \"06\"" +
                    "}," +
                    "{" +
                    "\"C.P.\": \"39840\","+
                    "\"Dirección\": \"CR N-629 79,7\","+
                    "\"Horario\": \"L-D: 08:00-22:00\","+
                    "\"Latitud\": \"43,341889\","+
                    "\"Localidad\": \"AMPUERO\","+
                    "\"Longitud (WGS84)\": \"-3,418167\","+
                    "\"Margen\": \"D\","+
                    "\"Municipio\": \"Ampuero\","+
                    "\"Precio Biodiesel\": null,\"Precio Bioetanol\": null," +
                    "\"Precio Gas Natural Comprimido\": null," +
                    "\"Precio Gas Natural Licuado\": null," +
                    "\"Precio Gases licuados del petróleo\": null," +
                    "\"Precio Gasoleo A\": \"1,079\","+
                    "\"Precio Gasoleo B\": null," +
                    "\"Precio Gasolina 95 Protección\": \"1,189\","+
                    "\"Precio Gasolina  98\": null," +
                    "\"Precio Nuevo Gasoleo A\": \"1,129\","+
                    "\"Provincia\": \"CANTABRIA\","+
                    "\"Remisión\": \"OM\","+
                    "\"Rótulo\": \"REPSOL\","+
                    "\"Tipo Venta\": \"P\","+
                    "\"% BioEtanol\": \"0,0\","+
                    "\"% Éster metílico\": \"0,0\","+
                    "\"IDEESS\": \"1048\","+
                    "\"IDMunicipio\": \"5745\","+
                    "\"IDProvincia\": \"39\","+
                    "\"IDCCAA\": \"06\"" +
                    "}," +
                    "{" +
                    "\"C.P.\": \"39450\","+
                    "\"Dirección\": \"CARRETERA N-611 KM. 163,2\","+
                    "\"Horario\": \"L-D: 06:00-22:00\","+
                    "\"Latitud\": \"43,181972\","+
                    "\"Localidad\": \"ARENAS DE IGUÑA\","+
                    "\"Longitud (WGS84)\": \"-4,052444\","+
                    "\"Margen\": \"I\","+
                    "\"Municipio\": \"Arenas de Iguña\","+
                    "\"Precio Biodiesel\": null," +
                    "\"Precio Bioetanol\": null," +
                    "\"Precio Gas Natural Comprimido\": null," +
                    "\"Precio Gas Natural Licuado\": null," +
                    "\"Precio Gases licuados del petróleo\": null," +
                    "\"Precio Gasoleo A\": \"1,099\","+
                    "\"Precio Gasoleo B\": null," +
                    "\"Precio Gasolina 95 Protección\": \"1,179\","+
                    "\"Precio Gasolina  98\": null," +
                    "\"Precio Nuevo Gasoleo A\": \"1,179\","+
                    "\"Provincia\": \"CANTABRIA\","+
                    "\"Remisión\": \"OM\","+
                    "\"Rótulo\": \"PETRONOR\","+
                    "\"Tipo Venta\": \"P\","+
                    "\"% BioEtanol\": \"0,0\","+
                    "\"% Éster metílico\": \"0,0\","+
                    "\"IDEESS\": \"1036\","+
                    "\"IDMunicipio\": \"5747\","+
                    "\"IDProvincia\": \"39\","+
                    "\"IDCCAA\": \"06\"" +
                    "}," +
                    "{" +
                    "\"C.P.\": \"39526\"," +
                    "\"Dirección\": \""+TEST_DIRECCION+"\"," +
                    "\"Horario\": \"L-D: 08:00-21:00\"," +
                    "\"Latitud\": \"43,395944\"," +
                    "\"Localidad\": \""+TEST_LOCALIDAD+"\"," +
                    "\"Longitud (WGS84)\": \"-4,155194\"," +
                    "\"Margen\": \"I\"," +
                    "\"Municipio\": \"Alfoz de Lloredo\"," +
                    "\"Precio Biodiesel\": null," +
                    "\"Precio Bioetanol\": null," +
                    "\"Precio Gas Natural Comprimido\": null," +
                    "\"Precio Gas Natural Licuado\": null," +
                    "\"Precio Gases licuados del petróleo\": null," +
                    "\"Precio Gasoleo A\": \""+TEST_PRECIO_GASOLEO+"\"," +
                    "\"Precio Gasoleo B\": \"0,765\"," +
                    "\"Precio Gasolina 95 Protección\": \""+TEST_PRECIO_GASOLINA+"\"," +
                    "\"Precio Gasolina  98\": \""+ TEST_PRECIO_GASOLINA98  +"\"," +
                    "\"Precio Nuevo Gasoleo A\": \""+ TEST_PRECIO_GASOLEO_PLUS +"\"," +
                    "\"Provincia\": \""+TEST_PROVINCIA+"\"," +
                    "\"Remisión\": \"dm\"," +
                    "\"Rótulo\": \""+TEST_ROTULO+"\"," +
                    "\"Tipo Venta\": \"P\"," +
                    "\"% BioEtanol\": \"0,0\"," +
                    "\"% Éster metílico\": \"0,0\"," +
                    "\"IDEESS\": \""+TEST_IDEESS+"\"," +
                    "\"IDMunicipio\": \"5744\"," +
                    "\"IDProvincia\": \"39\"," +
                    "\"IDCCAA\": \"06\"" +
                    "}" +
                    "]," +
                    "\"Nota\": \"Archivo de todos los productos en todas las estaciones de servicio. La actualización de precios se realiza cada media hora, con los precios en vigor en ese momento.\","+
                    "\"ResultadoConsulta\": \"OK\"" +
                    "}";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        g = new GasolinerasDAO();
        gasolineras = new ArrayList<Gasolinera>();
        filtros = new HashMap<String, String>();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        gestionGasolineras = new GestionGasolineras();
        context = InstrumentationRegistry.getTargetContext();
        DataFetch.setContext(context);
        stream = new ByteArrayInputStream(jsonData.getBytes("UTF-8"));
        bufferedDataGasolinerasTest = new BufferedInputStream(stream);
    }

    @Test
    public void Ordenar_11() {


        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_ordenar), withContentDescription("Ordenar"), isDisplayed()));
        actionMenuItemView.perform(click());




        onView(allOf(withId(android.R.id.text1), withText("Sin Plomo 95"),
                        childAtPosition(allOf(withId(R.id.select_dialog_listview),
                                withParent(withId(R.id.contentPanel))), 0),
                        isDisplayed())).perform(click());


        Utils.writeToFile(bufferedDataGasolinerasTest, TEMP_FILE_NAME, context);

        filtros.put("CCAA", IGasolinerasDAO.DEFAULT_CCAA);
        filtros.put("PRECIO", Utils.tiposGasolina[0]);
        gasolineras = gestionGasolineras.getGasolineras(filtros, FORCE_LOCAL_TRUE);

        Assert.assertTrue(gasolineras.get(0).getGasolina_95() < gasolineras.get(1).getGasolina_95());


    }

    private static Matcher<View> childAtPosition(
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
