package com.nodry.nodry.Presentacion;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nodry.nodry.Comunes.Dominio.Gasolinera;
import com.nodry.nodry.Comunes.Presentacion.IUpdateable;
import com.nodry.nodry.R;

/**
 * Clase para la el renderizado en la visualizacion
 * en el listado de Gasolineras, pudiendo aplicar
 * a cada una plantilla prederterminada.
 * @author Code4Fun.org
 * @version 11/2016
 */
public class GasolinerasArrayAdapter extends ArrayAdapter<Gasolinera> implements IUpdateable
{
    // Contexto de la aplicacion
    private Context context;

    // Listado de las gasolineras
    private List<Gasolinera> listaGasolineras;

    // Constantes a utilizar
    private static final String DEFAULT_IMAGE   = "por_defecto";    // Imagen por defecto
    private static final String IMAGES_FOLDER   = "drawable";       // Directorio de las imagenes
    private static final String ERROR_TITLE     = "ERROR";          // Titulo para errores

    private static final String CONS_UNITS      = "€/L";            // Unidades para mostrar divisas
    private static final String DIR_SEPARATOR   = "/";              // Separador de directorios

    // Imagen por defecto de una gasolinera
    int imageDefaultID;

    /**
     * Constructor de la clase
     * @param context con el contexto de la aplicacion
     * @param resource heredado
     * @param objects con el listado de objetos a representar
     */
    public GasolinerasArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
        super(context, resource, objects);

        imageDefaultID = context.getResources().getIdentifier(DEFAULT_IMAGE, IMAGES_FOLDER, context.getPackageName());
        this.context = context;
        this.listaGasolineras = objects;
    }

    //called when rendering the list

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Gasolinera gasolinera = listaGasolineras.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gasolinera_layout, null);

        TextView rotulo =       (TextView) view.findViewById(R.id.rotulo);
        TextView direccion =    (TextView) view.findViewById(R.id.direccion);
        TextView diesel =       (TextView) view.findViewById(R.id.diesel);
        TextView gasolina =     (TextView) view.findViewById(R.id.gasolina);
        TextView dieselb =      (TextView) view.findViewById(R.id.dieselb);
        TextView gasolina98 =   (TextView) view.findViewById(R.id.gasolina98);
        ImageView image =       (ImageView) view.findViewById(R.id.image);

        //set address and description
        String rotuloTrim = "",
                completeRotulo = gasolinera.getRotulo();
        int rotuloLength = completeRotulo.length();

        if(rotuloLength >= 32){
            rotuloTrim = completeRotulo.substring(0, 28) + "...";
            rotulo.setText(rotuloTrim);
        }else{
            rotulo.setText(completeRotulo);
        }

        //display trimmed excerpt for description
        String direccionTrim = "", completeDireccion = gasolinera.getDireccion();//+",\n"+ gasolinera.getLocalidad();

        int descriptionLength = completeDireccion.length();
        if(descriptionLength >= 55){
            direccionTrim = completeDireccion.substring(0, 50) + "...";
            direccion.setText(direccionTrim);
        }else{
            direccion.setText(completeDireccion);
        }

        diesel.setText(String.valueOf(gasolinera.getGasoleo_a())        + CONS_UNITS);
        gasolina.setText(String.valueOf(gasolinera.getGasolina_95()     + CONS_UNITS));
        dieselb.setText(String.valueOf(gasolinera.getGasoleo_b())       + CONS_UNITS);
        gasolina98.setText(String.valueOf(gasolinera.getGasolina_98()   + CONS_UNITS));

        //get the image associated with this property
        Integer imageID = context.getResources().getIdentifier(IMAGES_FOLDER + DIR_SEPARATOR + gasolinera.getRotulo().trim().toLowerCase(), null, context.getPackageName());

        try {
            image.setImageResource((imageID == 0) ? imageDefaultID : imageID);
        }catch (Exception e){
            Log.d(ERROR_TITLE, e.toString());
        }

        return view;
    }

    @Override
    public void clear() {
        listaGasolineras = new ArrayList<Gasolinera>();
        notifyDataSetChanged();
    }

    @Override
    public void update(Collection<?> data) {
        listaGasolineras.clear();
        listaGasolineras.addAll((List<Gasolinera>)data);
        notifyDataSetChanged();
    }

    public List<Gasolinera> getListaGasolinerasMostradas(){
        return listaGasolineras;
    }
}
