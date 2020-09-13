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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dto.Concierto;
import cl.inacap.misconciertos.dao.DaoConcierto;

public class MainActivity extends AppCompatActivity {

    //private List<String> entradas=new ArrayList<>();

    TextView valorEm;
    TextView tvFecha;
    Calendar calendario;
    int day,month,year;
    Spinner spGenero;
    String[] items;
    Spinner spEvaluacion;
    String[] nota;
    private boolean primera=true;
    EditText valorEntradas;

    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spEvaluacion=(Spinner) findViewById(R.id.spEvaluacion);
        nota=getResources().getStringArray(R.array.evaluacion);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,nota);
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


        DecimalFormat formato=new DecimalFormat("###,###");
        valorEntradas=(EditText) findViewById(R.id.valorEntrada);


        tvFecha=(TextView) findViewById(R.id.etFecha);
        calendario=Calendar.getInstance();

        day=calendario.get(Calendar.DAY_OF_MONTH);
        month=calendario.get(Calendar.MONTH);
        year=calendario.get(Calendar.YEAR);

        month=month+1;

        tvFecha.setText(day+" / "+month+" / "+year);

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int anio, int mes, int dia) {
                        mes=mes+1;
                        tvFecha.setText(dia+" / "+mes+" / "+anio);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }


    public void Guardar(View view) {
        Concierto concierto = new Concierto();


    }

    public void mostrarErrores (List<String> errores){
        String mensaje = "";
        for (String e: errores){
            mensaje+= "-" + e + "\n";
        }
        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(MainActivity.this);
        alerBuilder.setTitle("Errores")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .create()
                .show();
    }
}