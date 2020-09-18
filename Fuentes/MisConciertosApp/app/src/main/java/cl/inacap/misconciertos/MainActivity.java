package cl.inacap.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.AndroidException;
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

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dto.Concierto;
import cl.inacap.misconciertos.dao.DaoConcierto;

public class MainActivity extends AppCompatActivity {

    private List<Concierto> conciertos=new ArrayList<>();

    private EditText nombreArtista;
    private EditText valorEntrada;
    private ListView conciertoLv;
    private Button guardar;
    private ArrayList<String> arrayList;
    TextView valorEm;
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
        conciertoLv=(ListView)findViewById(R.id.conciertoLv);
        arrayList=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,arrayList);
        conciertoLv.setAdapter(adapter);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Concierto concierto = new Concierto();
                List<String> errores = new ArrayList<>();
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

                if(!nombreArtista.getText().toString().isEmpty()){
                    concierto.setNombreArtista(nombreArtista.getText().toString());
                }else{
                    errores.add("Ingrese un Nombre de Artista o Grupo Musical");
                }
                try {
                    concierto.setFechaEvento(DaoConcierto.ParseFecha(tvFecha.getText().toString()));
                }catch(Exception e){
                    errores.add("Error al ingresar la Fecha");
                }
                if(!spGenero.getSelectedItem().toString().equals("Selecciona un Genero")){
                    concierto.setGenero(spGenero.getSelectedItem().toString());
                }else{
                    errores.add("Seleccione un Genero Musical");
                }
                if(!valorEntrada.getText().toString().isEmpty()){
                    concierto.setPrecioEntrada(Integer.parseInt(valorEntrada.getText().toString()));
                }else{
                    errores.add("Ingrese el valor de la entrada");
                }
                if(!spEvaluacion.getSelectedItem().toString().equals("Selecciona una Evaluación")){
                    concierto.setCalificacion(Integer.parseInt(spEvaluacion.getSelectedItem().toString()));
                }else{
                    errores.add("Seleccione una Calificacion");
                }

                if(errores.isEmpty()){
                    arrayList.add("Concierto de: " + concierto.getNombreArtista());
                    arrayList.add("Fecha: " + fecha.format(concierto.getFechaEvento()));
                    arrayList.add("Genero: " + concierto.getGenero());
                    arrayList.add("Valor de Entrada: $" + concierto.getPrecioEntrada());
                    arrayList.add("Calificacion: " + concierto.getCalificacion());
                    arrayList.add("------------------------------------------------");
                    Toast.makeText(getApplicationContext(),"Guardado con exito",Toast.LENGTH_SHORT).show();

                }else{
                    mostrarErrores(errores);
                }

                /*Toast.makeText(getApplicationContext(),"Guardado con exito",Toast.LENGTH_SHORT).show();
                arrayList.add("Fecha :"+tvFecha.getText().toString()+" -- Concierto de : \n"+ nombreArtista.getText().toString());
                arrayList.add("Valor de la entrada = "+valorEntrada.getText().toString());*/

                adapter.notifyDataSetChanged();
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
        /*
        this.nombreArtista=findViewById(R.id.nombreArtista);
        this.valorEntrada=findViewById(R.id.valorEntrada);
        this.conciertoLv=findViewById(R.id.conciertoLv);
        try {
        this.guardar=findViewById(R.id.guardar);
        this.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String nombre=null;
                int valor=0;
                int calificacion=0;
                calificacion=Integer.parseInt(spEvaluacion.toString());
                Time fecha;
                try {
                    nombre=nombreArtista.getText().toString();
                    valor=Integer.parseInt(valorEntrada.getText().toString());
                    if (valor<1){
                        throw new Exception();
                    }
                }catch(Exception e){
                    errores.add("El valor de la entrada es en pesos chilenos y debe ser mayor que 0");
                }
                try {
                    if (errores.isEmpty()){
                        Concierto con=new Concierto();
                        con.setPrecioEntrada(valor);
                        con.setNombreArtista(nombre);
                        con.setCalificacion(calificacion);
                        conciertos.add(con);
                    }else{
                        mostrarErrores(errores);
                    }
                }catch(Exception e){

                }

            }
        });
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"problemas boton",Toast.LENGTH_SHORT);
        }
        */
       /* try {*/
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
       /* }catch(Exception e){
            Toast.makeText(getApplicationContext(),"problema con el spiner evaluacion",Toast.LENGTH_SHORT);
        }

        try {   */


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
/*
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"problema con el spiner genero",Toast.LENGTH_SHORT);
        }
            /*
        //DecimalFormat formato=new DecimalFormat("###,###");
        //valorEntradas=(EditText) findViewById(R.id.valorEntrada);

*/

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