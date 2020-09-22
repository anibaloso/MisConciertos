package cl.inacap.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dao.EventosDAO;
import cl.inacap.misconciertos.dto.Eventos;

public class MainActivity extends AppCompatActivity {

    private EditText nombreArtista;
    private EditText valorEntrada;
    private Button guardar;
    private List<Eventos> arrayEventos = new ArrayList<>();
    private ListView mListView;
    EventosDAO mAdapter;
    TextView tvFecha;
    Calendar calendario;
    int day,month,year;
    Spinner spGenero;
    String[] items;
    Spinner spEvaluacion;
    String[] nota;
    private boolean primera=true;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreArtista=(EditText)findViewById(R.id.nombreArtista);
        valorEntrada=(EditText)findViewById(R.id.valorEntrada);
        guardar=(Button)findViewById(R.id.guardar);

        mListView = findViewById(R.id.conciertoLv);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eventos evento = new Eventos();
                List<String> errores = new ArrayList<>();
                mAdapter = new EventosDAO(MainActivity.this, R.layout.item_row, arrayEventos);

                //TODO validaciones de cada ingreso
                if(!nombreArtista.getText().toString().isEmpty()){
                    evento.setNombreArtista(nombreArtista.getText().toString());
                }else{
                    errores.add("Ingrese un Nombre de Artista o Grupo Musical");
                }
                try {
                    evento.setFechaEvento(EventosDAO.StringAfecha(tvFecha.getText().toString()));
                }catch(Exception e){
                    errores.add("Error al ingresar la Fecha");
                }
                if(!spGenero.getSelectedItem().toString().equals("Selecciona un Genero")){
                    evento.setGenero(spGenero.getSelectedItem().toString());
                }else{
                    errores.add("Seleccione un Genero Musical");
                }
                if(valorEntrada.getText().toString().isEmpty()){
                    errores.add("Ingrese el valor de la entrada");
                }else{
                    //TODO borrar despues 11.35

                    try{
                        if(Integer.parseInt(valorEntrada.getText().toString()) <= 2147483647){
                            if((Integer.parseInt(valorEntrada.getText().toString()) >= 0)){
                                evento.setPrecioEntrada(Integer.parseInt(valorEntrada.getText().toString()));
                            }else{
                                errores.add("El valor de la entrada debe ser igual o mayor a cero");
                            }
                        }
                    }catch(Exception e){
                        errores.add("Dudo haya una entrada taaaaaan cara, ni que fueras a comerte a Salma Hayek\n No sea longi");


                }}
                if(!spEvaluacion.getSelectedItem().toString().equals("Selecciona una Evaluación")){
                    evento.setCalificacion(Integer.parseInt(spEvaluacion.getSelectedItem().toString()));
                    if(evento.getCalificacion() >= 1 && evento.getCalificacion() <= 3){
                        evento.setImagen(R.mipmap.malo);
                    }
                    if(evento.getCalificacion() == 4 || evento.getCalificacion() == 5){
                        evento.setImagen(R.mipmap.neutro);
                    }
                    if(evento.getCalificacion() == 6 || evento.getCalificacion() == 7){
                        evento.setImagen(R.mipmap.bueno);
                    }
                }else{
                    errores.add("Seleccione una Calificacion");
                }

                //TODO si no hay errores al ingresar se agregara un concierto al array y se mostrara en el list view
                if(errores.isEmpty()){
                    arrayEventos.add(evento);
                    mListView.setAdapter(mAdapter);
                    Toast.makeText(getApplicationContext(),"Guardado con exito",Toast.LENGTH_SHORT).show();

                }else{
                    mostrarErrores(errores);
                }
                mAdapter.notifyDataSetChanged();

            }
        });


        tvFecha=(TextView) findViewById(R.id.etFecha);
        calendario=Calendar.getInstance();

        day=calendario.get(Calendar.DAY_OF_MONTH);
        month=calendario.get(Calendar.MONTH);
        year=calendario.get(Calendar.YEAR);

        month=month+1;

        tvFecha.setText(day+"/"+month+"/"+year);

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anio, int mes, int dia) {
                        mes=mes+1;
                        tvFecha.setText(dia+"/"+mes+"/"+anio);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

            spEvaluacion = (Spinner) findViewById(R.id.spEvaluacion);
            nota = getResources().getStringArray(R.array.evaluacion);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nota);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spEvaluacion.setAdapter(adapter2);
            spEvaluacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

        spGenero=(Spinner) findViewById(R.id.spGenero);
        items=getResources().getStringArray(R.array.generoMusical);
        //ahora poblamos
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenero.setAdapter(adapter);
        spGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(primera){
                    primera=false;
                }else{
                    Toast.makeText(getApplicationContext(), items[i],Toast.LENGTH_SHORT).show();
                }   //lo coloque en short por que era muy larga el tiempo de muestra
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void mostrarErrores (List<String> errores){
        String mensaje = "";
        for (String e: errores){
            mensaje+= "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder= new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create()
                .show();

    }

}