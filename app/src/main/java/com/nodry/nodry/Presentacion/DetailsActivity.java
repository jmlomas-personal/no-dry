package com.nodry.nodry.Presentacion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.R;
import com.nodry.nodry.Utils.MasBaratas;
import com.nodry.nodry.Utils.Utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Clase de la actividad que muestra el
 * detalle de una gasolinera.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String CCAA;
    int IDEESS;

    List<Gasolinera> listGasolineras;
    Gasolinera gasolinera;
    MasBaratas masBaratas;

    GestionGasolineras gestionGasolineras;
    DecimalFormat df = new DecimalFormat("0.000");

    ImageView imagen;
    TextView tvRotulo, tvLocalidad, tvMunicipio, tvProvincia, tvDireccion, tvSinPlomo95, tvSinPlomo98, tvDiesel, tvDieselPlus, tvHorario, tvEstado;
    Button bSinPlomo95, bSinPlomo98, bDiesel, bDieselPlus;
    ImageButton bMapa;

    private static final String TEXT_CURRENCY           = " €";
    private static final String TEXT_SUBS               = "-";
    private static final String TEXT_NOT_AVAILABLE      = "No Disponible";
    private static final String TEXT_OPEN               = "Abierto";
    private static final String TEXT_CLOSED             = "Cerrado";

    private static final String DEFAULT_IMAGE       = "por_defecto";
    private static final String IMAGES_FOLDER       = "drawable";
    private static final String TEXT_TO_REPLACE     = "; ";
    private static final String TEXT_REPLACEMENT    = "\n";

    // Campos para realizar llamadas a otras actividades
    private static final String EXTRA_CCAA          = "CCAA";
    private static final String EXTRA_IDEESS        = "IDEESS";
    private static final String EXTRA_LIST          = "LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Recuperamos los controles visuales
        imagen          = (ImageView) findViewById(R.id.imageView_icono);

        tvRotulo        = (TextView) findViewById(R.id.textView_Rotulo);
        tvLocalidad     = (TextView) findViewById(R.id.textView_Localidad);
        tvMunicipio     = (TextView) findViewById(R.id.textView_Municipio);
        tvProvincia     = (TextView) findViewById(R.id.textView_Provincia);
        tvDireccion     = (TextView) findViewById(R.id.textView_Direccion);
        tvSinPlomo95    = (TextView) findViewById(R.id.textView_Gasolina95);
        tvSinPlomo98    = (TextView) findViewById(R.id.textView_Gasolina98);
        tvDiesel        = (TextView) findViewById(R.id.textView_Diesel);
        tvDieselPlus    = (TextView) findViewById(R.id.textView_DieselPlus);
        tvHorario       = (TextView) findViewById(R.id.textView_Horario);
        tvEstado        = (TextView) findViewById(R.id.textView_Estado);

        bSinPlomo95 = (Button) findViewById(R.id.button_Gasolina95);
        bSinPlomo95.setOnClickListener(this);
        bSinPlomo98 = (Button) findViewById(R.id.button_Gasolina98);
        bSinPlomo98.setOnClickListener(this);
        bDiesel     = (Button) findViewById(R.id.button_Diesel);
        bDiesel.setOnClickListener(this);
        bDieselPlus = (Button) findViewById(R.id.button_DieselPlus);
        bDieselPlus.setOnClickListener(this);

        bMapa = (ImageButton) findViewById(R.id.imageButton_Mapa);
        bMapa.setOnClickListener(this);

        // Recuperamos la CCAA y el ID de la gasolinera
        intent = getIntent();
        CCAA = intent.getStringExtra(EXTRA_CCAA);
        IDEESS = intent.getIntExtra(EXTRA_IDEESS, 0);
        listGasolineras = (List) getIntent().getSerializableExtra(EXTRA_LIST);

        // Inicializamos y cargamos
        initialize();
        loadData();
        paintData();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_Gasolina95: {
                this.IDEESS = masBaratas.getMasBarata95().getIDEESS();
                break;
            }
            case R.id.button_Gasolina98: {
                this.IDEESS = masBaratas.getMasBarata98().getIDEESS();
                break;
            }
            case R.id.button_Diesel: {
                this.IDEESS = masBaratas.getMasBarataDiesel().getIDEESS();
                break;
            }
            case R.id.button_DieselPlus: {
                this.IDEESS = masBaratas.getMasBarataDieselPlus().getIDEESS();
                break;
            }
            case R.id.imageButton_Mapa: {
                Utils.openGoogleMaps(this, gasolinera.getLatitud(), gasolinera.getLongitud(), gasolinera.getRotulo());
                break;
            }
        }

        if(view.getId() != R.id.imageButton_Mapa) {
            refresh();
        }
    }

    /**
     * Metodo que inicializa la actividad
     */
    private void initialize(){

        bSinPlomo95.setVisibility(View.GONE);
        bSinPlomo98.setVisibility(View.GONE);
        bDiesel.setVisibility(View.GONE);
        bDieselPlus.setVisibility(View.GONE);

    }

    /**
     * Metodo que carga los necesarios para la actividad
     */
    private void loadData(){

        gasolinera = Utils.getGasolinera(IDEESS, listGasolineras);
        masBaratas = Utils.getMasBaratas(gasolinera.getMunicipio(), listGasolineras);

    }

    /**
     * Metodo que pinta los datos en la apariencia de la actividad
     */
    private void paintData(){

        int imageDefaultID = this.getResources().getIdentifier(DEFAULT_IMAGE, IMAGES_FOLDER, this.getPackageName());
        Integer imageID = this.getResources().getIdentifier(IMAGES_FOLDER + "/" + gasolinera.getRotulo().trim().toLowerCase(), null, this.getPackageName());
        imagen.setImageResource((imageID == 0) ? imageDefaultID : imageID);

        tvRotulo.setText(gasolinera.getRotulo());
        tvLocalidad.setText(gasolinera.getLocalidad());
        tvMunicipio.setText(gasolinera.getMunicipio());
        tvProvincia.setText(gasolinera.getProvincia());
        tvDireccion.setText(gasolinera.getDireccion());

        tvSinPlomo95.setText(   (gasolinera.getGasolina_95() > 0)?  gasolinera.getGasolina_95()+ TEXT_CURRENCY: TEXT_NOT_AVAILABLE );
        tvSinPlomo98.setText(   (gasolinera.getGasolina_98() > 0)?  gasolinera.getGasolina_98()+ TEXT_CURRENCY: TEXT_NOT_AVAILABLE );
        tvDiesel.setText(       (gasolinera.getGasoleo_a() > 0)?    gasolinera.getGasoleo_a()+   TEXT_CURRENCY: TEXT_NOT_AVAILABLE );
        tvDieselPlus.setText(   (gasolinera.getGasoleo_b() > 0)?    gasolinera.getGasoleo_b()+   TEXT_CURRENCY: TEXT_NOT_AVAILABLE );

        tvHorario.setText(gasolinera.getHorario().replaceAll(TEXT_TO_REPLACE, TEXT_REPLACEMENT));

        if(Utils.estaAbierto(gasolinera.getHorario())){
            tvEstado.setText(TEXT_OPEN);
            tvEstado.setTextColor(Color.GREEN);
        }else{
            tvEstado.setText(TEXT_CLOSED);
            tvEstado.setTextColor(Color.RED);
        }

        double aux = 0.0;

        if(gasolinera.getGasolina_95() > 0 &&
                masBaratas.getMasBarata95()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarata95().getIDEESS() &&
                gasolinera.getGasolina_95() != masBaratas.getMasBarata95().getGasolina_95()) {
            aux = gasolinera.getGasolina_95() - masBaratas.getMasBarata95().getGasolina_95();
            bSinPlomo95.setVisibility(View.VISIBLE);
            bSinPlomo95.setText(TEXT_SUBS + df.format(aux) + TEXT_CURRENCY);
        }

        if(gasolinera.getGasolina_98() > 0 &&
                masBaratas.getMasBarata98()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarata98().getIDEESS() &&
                gasolinera.getGasolina_98() != masBaratas.getMasBarata98().getGasolina_98()) {
            aux = gasolinera.getGasolina_98() - masBaratas.getMasBarata98().getGasolina_98();
            bSinPlomo98.setVisibility(View.VISIBLE);
            bSinPlomo98.setText(TEXT_SUBS + df.format(aux) + TEXT_CURRENCY);
        }

        if(gasolinera.getGasoleo_a() > 0
                && masBaratas.getMasBarataDiesel()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarataDiesel().getIDEESS() &&
                gasolinera.getGasoleo_a() != masBaratas.getMasBarataDiesel().getGasoleo_a()) {
            aux = gasolinera.getGasoleo_a() - masBaratas.getMasBarataDiesel().getGasoleo_a();
            bDiesel.setVisibility(View.VISIBLE);
            bDiesel.setText(TEXT_SUBS + df.format(aux) + TEXT_CURRENCY);
        }

        if(gasolinera.getGasoleo_b() > 0 &&
                masBaratas.getMasBarataDieselPlus()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarataDieselPlus().getIDEESS() &&
                gasolinera.getGasoleo_b() != masBaratas.getMasBarataDieselPlus().getGasoleo_b()) {
            aux = gasolinera.getGasoleo_b() - masBaratas.getMasBarataDieselPlus().getGasoleo_b();
            bDieselPlus.setVisibility(View.VISIBLE);
            bDieselPlus.setText(TEXT_SUBS + df.format(aux) + TEXT_CURRENCY);
        }

    }

    /**
     * Metodo que actualiza la actividad para una nueva gasolinera
     */
    private void refresh(){

        Intent myIntent = new Intent(this, DetailsActivity.class);
        myIntent.putExtra(EXTRA_CCAA, CCAA);
        myIntent.putExtra(EXTRA_IDEESS, IDEESS);
        myIntent.putExtra(EXTRA_LIST, (Serializable)listGasolineras);

        this.startActivity(myIntent);
    }
}

