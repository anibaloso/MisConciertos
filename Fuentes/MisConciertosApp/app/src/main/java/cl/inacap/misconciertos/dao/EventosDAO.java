package cl.inacap.misconciertos.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cl.inacap.misconciertos.R;
import cl.inacap.misconciertos.dto.Eventos;

public class EventosDAO extends ArrayAdapter <Eventos> {

    private List<Eventos> lista;
    private Context mContext;
    private int resourceLayout;

    public EventosDAO(@NonNull Context context, int resource, List<Eventos> objects) {
        super(context, resource, objects);
        this.lista = objects;
        this.mContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);
        }
        DecimalFormat formatear = new DecimalFormat("###,###.##");

        Eventos eventos = lista.get(position);

        ImageView imagen = view.findViewById(R.id.imagenView);
        imagen.setImageResource(eventos.getImagen());

        TextView txtNombre = view.findViewById(R.id.txtNombre);
        txtNombre.setText("Nombre: " + eventos.getNombreArtista());

        TextView txtFecha = view.findViewById(R.id.txtFecha);
        txtFecha.setText("Fecha: " + EventosDAO.fechaAstring(eventos.getFechaEvento()));

        TextView txtGenero = view.findViewById(R.id.txtGenero);
        txtGenero.setText("Genero: " + eventos.getGenero());

        TextView txtValor = view.findViewById(R.id.txtPrecio);
        txtValor.setText("Precio: $" + formatear.format(eventos.getPrecioEntrada()));


        return view;

    }

    // TODO metodo para convertir las fechas de String a Date
    public static Date StringAfecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }

    // TODO metodo para convertir las fechas de Date a String
    public static String fechaAstring (Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formato.format(fecha);
        return fechaString;
    }
}
