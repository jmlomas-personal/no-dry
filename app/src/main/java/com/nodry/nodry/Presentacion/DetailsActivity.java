package com.nodry.nodry.Presentacion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.Datos.MasBaratas;
import com.nodry.nodry.Negocio.GestionGasolineras;
import com.nodry.nodry.R;
import com.nodry.nodry.Utils.Utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Actividad donde se ubican los filtros de
 * la aplicacion.
 * @author Juan Manuel Lomas Fernandez.
 * @version 1.0
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String CCAA;
    int IDEESS;

    List<Gasolinera> gasolineras;
    Gasolinera gasolinera;
    MasBaratas masBaratas;

    GestionGasolineras gestionGasolineras;
    DecimalFormat df = new DecimalFormat("0.000");

    ImageView imagen;
    TextView tvRotulo, tvLocalidad, tvProvincia, tvDireccion, tvSinPlomo95, tvSinPlomo98, tvDiesel, tvDieselPlus, tvHorario, tvEstado;
    Button bSinPlomo95, bSinPlomo98, bDiesel, bDieselPlus;
    ImageButton bMapa;

    private static final String CURRENCY = "€";

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
        CCAA = intent.getStringExtra("CCAA");
        IDEESS = intent.getIntExtra("IDEESS", 0);
        gasolineras = (List) getIntent().getSerializableExtra("listaGasolineras");

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

    private void initialize(){

        bSinPlomo95.setVisibility(View.GONE);
        bSinPlomo98.setVisibility(View.GONE);
        bDiesel.setVisibility(View.GONE);
        bDieselPlus.setVisibility(View.GONE);

    }

    private void loadData(){

        gestionGasolineras = new GestionGasolineras();
        gasolinera = gestionGasolineras.getGasolinera(IDEESS, gasolineras);
        masBaratas = gestionGasolineras.getMasBaratas(gasolinera.getLocalidad(), gasolineras);

    }

    private void paintData(){

        int imageDefaultID = this.getResources().getIdentifier("por_defecto", "drawable", this.getPackageName());
        Integer imageID = this.getResources().getIdentifier("drawable/" + gasolinera.getRotulo().trim().toLowerCase(), null, this.getPackageName());
        imagen.setImageResource((imageID == 0) ? imageDefaultID : imageID);

        tvRotulo.setText(gasolinera.getRotulo());
        tvLocalidad.setText(gasolinera.getLocalidad());
        tvProvincia.setText(gasolinera.getProvincia());
        tvDireccion.setText(gasolinera.getDireccion());

        tvSinPlomo95.setText( (gasolinera.getGasolina_95()>0)?""+gasolinera.getGasolina_95()+" €":"No Disponible" );
        tvSinPlomo98.setText( (gasolinera.getGasolina_98()>0)?""+gasolinera.getGasolina_98()+" €":"No Disponible" );
        tvDiesel.setText( (gasolinera.getGasoleo_a()>0)?""+gasolinera.getGasoleo_a()+" €":"No Disponible" );
        tvDieselPlus.setText( (gasolinera.getGasoleo_b()>0)?""+gasolinera.getGasoleo_b()+" €":"No Disponible" );

        tvHorario.setText(gasolinera.getHorario().replaceAll("; ","\n"));

        if(Utils.estaAbierto(gasolinera.getHorario())){
            tvEstado.setText("Abierto");
            tvEstado.setTextColor(Color.GREEN);
        }else{
            tvEstado.setText("Cerrado");
            tvEstado.setTextColor(Color.RED);
        }

        double aux = 0.0;

        if(gasolinera.getGasolina_95() > 0 &&
                masBaratas.getMasBarata95()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarata95().getIDEESS() &&
                gasolinera.getGasolina_95() != masBaratas.getMasBarata95().getGasolina_95()) {
            aux = gasolinera.getGasolina_95() - masBaratas.getMasBarata95().getGasolina_95();
            bSinPlomo95.setVisibility(View.VISIBLE);
            bSinPlomo95.setText("-"+df.format(aux)+" €");
        }

        if(gasolinera.getGasolina_98() > 0 &&
                masBaratas.getMasBarata98()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarata98().getIDEESS() &&
                gasolinera.getGasolina_98() != masBaratas.getMasBarata98().getGasolina_98()) {
            aux = gasolinera.getGasolina_98() - masBaratas.getMasBarata98().getGasolina_98();
            bSinPlomo98.setVisibility(View.VISIBLE);
            bSinPlomo98.setText("-"+df.format(aux)+" €");
        }

        if(gasolinera.getGasoleo_a() > 0
                && masBaratas.getMasBarataDiesel()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarataDiesel().getIDEESS() &&
                gasolinera.getGasoleo_a() != masBaratas.getMasBarataDiesel().getGasoleo_a()) {
            aux = gasolinera.getGasoleo_a() - masBaratas.getMasBarataDiesel().getGasoleo_a();
            bDiesel.setVisibility(View.VISIBLE);
            bDiesel.setText("-"+df.format(aux)+" €");
        }

        if(gasolinera.getGasoleo_b() > 0 &&
                masBaratas.getMasBarataDieselPlus()!=null &&
                gasolinera.getIDEESS() != masBaratas.getMasBarataDieselPlus().getIDEESS() &&
                gasolinera.getGasoleo_b() != masBaratas.getMasBarataDieselPlus().getGasoleo_b()) {
            aux = gasolinera.getGasoleo_b() - masBaratas.getMasBarataDieselPlus().getGasoleo_b();
            bDieselPlus.setVisibility(View.VISIBLE);
            bDieselPlus.setText("-"+df.format(aux)+" €");
        }

    }

    /**
     * Metodo que abre la aplicacion de google maps dadas dos coordenadas
     */
    private void refresh(){

        Intent myIntent = new Intent(this, DetailsActivity.class);
        myIntent.putExtra("CCAA", CCAA); //Optional parameters
        myIntent.putExtra("IDEESS", IDEESS); //Optional parameters
        myIntent.putExtra("listaGasolineras", (Serializable)gasolineras); //Optional parameters

        this.startActivity(myIntent);

    }
}
