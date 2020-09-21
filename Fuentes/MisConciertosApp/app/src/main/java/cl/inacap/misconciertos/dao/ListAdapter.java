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
import java.util.List;

import cl.inacap.misconciertos.R;
import cl.inacap.misconciertos.dto.Eventos;
import cl.inacap.misconciertos.dao.EventosDAO;

public class ListAdapter extends ArrayAdapter <Eventos> {

    private List<Eventos> lista;
    private Context mContext;
    private int resourceLayout;

    public ListAdapter(@NonNull Context context, int resource, List<Eventos> objects) {
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
}
