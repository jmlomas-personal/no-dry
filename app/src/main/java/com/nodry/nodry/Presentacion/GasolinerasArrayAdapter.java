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

import com.nodry.nodry.Datos.Gasolinera;
import com.nodry.nodry.R;

/**
 * Clase para la el renderizado en la visualizacion
 * en el listado de Gasolineras, pudiendo aplicar
 * a cada una plantilla prederterminada.
 * @author Alba Zubizarreta.
 * @version 1.0
 */
public class GasolinerasArrayAdapter extends ArrayAdapter<Gasolinera> implements IUpdateable
{
    // Contexto de la aplicacion
    private Context context;

    // Listado de las gasolineras
    private List<Gasolinera> listaGasolineras;

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

        imageDefaultID = context.getResources().getIdentifier("por_defecto", "drawable", context.getPackageName());
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

        TextView rotulo = (TextView) view.findViewById(R.id.rotulo);
        TextView direccion = (TextView) view.findViewById(R.id.direccion);
        TextView diesel = (TextView) view.findViewById(R.id.diesel);
        TextView gasolina = (TextView) view.findViewById(R.id.gasolina);
        TextView dieselb=(TextView) view.findViewById(R.id.dieselb);
        TextView gasolina98=(TextView) view.findViewById(R.id.gasolina98);
        ImageView image = (ImageView) view.findViewById(R.id.image);

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
        String direccionTrim = "",
                completeDireccion = gasolinera.getDireccion();//+",\n"+ gasolinera.getLocalidad();

        int descriptionLength = completeDireccion.length();
        if(descriptionLength >= 55){
            direccionTrim = completeDireccion.substring(0, 50) + "...";
            direccion.setText(direccionTrim);
        }else{
            direccion.setText(completeDireccion);
        }

        //set price and rental attributes
        //price.setText("$" + String.valueOf(gasolinera.getGasoleo_a()));
        diesel.setText(String.valueOf(gasolinera.getGasoleo_a()) + "€/L");
        gasolina.setText(String.valueOf(gasolinera.getGasolina_95() + "€/L"));
        dieselb.setText(String.valueOf(gasolinera.getGasoleo_b()) + "€/L");
        gasolina98.setText(String.valueOf(gasolinera.getGasolina_98() + "€/L"));
        //carspot.setText("Car: " + String.valueOf(property.getCarspots()));

        //get the image associated with this property
        Integer imageID = context.getResources().getIdentifier("drawable/" + gasolinera.getRotulo().trim().toLowerCase(), null, context.getPackageName());
        try {
            image.setImageResource((imageID == 0) ? imageDefaultID : imageID);
        }catch (Exception e){
            Log.d("Error", e.toString());
        }

        return view;
    }

    public List<Gasolinera> getGasolineras(){
        return listaGasolineras;
    }

    @Override
    public void clear() {
        listaGasolineras = new ArrayList<Gasolinera>();
        notifyDataSetChanged();
    }

    @Override
    public void update(Collection<?> c) {
        this.listaGasolineras.clear();
        this.listaGasolineras.addAll((List<Gasolinera>)c);
        this.notifyDataSetChanged();
    }
}//gasolinerasArrayAdapter
