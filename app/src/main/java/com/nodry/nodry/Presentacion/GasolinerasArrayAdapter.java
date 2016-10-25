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

import com.unican.juanmanuellomas.nodry.Datos.Gasolinera;
import com.unican.juanmanuellomas.nodry.R;

/**
 * Created by Juan Manuel Lomas on 04/10/2016.
 */
public class GasolinerasArrayAdapter extends ArrayAdapter<Gasolinera> implements IUpdateable
{
        private Context context;
        private List<Gasolinera> listaGasolineras;
        int imageDefaultID;

        //constructor, call on creation
    public GasolinerasArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
        super(context, resource, objects);

        imageDefaultID = context.getResources().getIdentifier("por_defecto", "drawable", context.getPackageName());
        this.context = context;
        this.listaGasolineras = objects;
    }

    //called when rendering the list
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
        ImageView image = (ImageView) view.findViewById(R.id.image);

        //set address and description
        String completeRotulo = gasolinera.getRotulo();
        rotulo.setText(completeRotulo);

        //display trimmed excerpt for description
        String direccionTrim = "",
                completeDireccion = gasolinera.getDireccion();//+",\n"+ gasolinera.getLocalidad();

        int descriptionLength = completeDireccion.length();
        if(descriptionLength >= 34){
            direccionTrim = completeDireccion.substring(0, 30) + "...";
            direccion.setText(direccionTrim);
        }else{
            direccion.setText(completeDireccion);
        }

        //set price and rental attributes
        //price.setText("$" + String.valueOf(gasolinera.getGasoleo_a()));
        diesel.setText(String.valueOf(gasolinera.getGasoleo_a()) + "€/L");
        gasolina.setText(String.valueOf(gasolinera.getGasolina_95() + "€/L"));
        //carspot.setText("Car: " + String.valueOf(property.getCarspots()));

        //get the image associated with this property
        Integer imageID = context.getResources().getIdentifier("drawable/" + gasolinera.getRotulo().trim().toLowerCase(), null, context.getPackageName());
        try {
            image. setImageResource((imageID == 0) ? imageDefaultID : imageID);
        }catch (Exception e){
            Log.d("LLEGO",e.toString());
        }finally {

        }

        return view;
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
