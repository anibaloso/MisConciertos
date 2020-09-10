package cl.inacap.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertos.dto.valorEntradas;

public class MainActivity extends AppCompatActivity {

    private List<cl.inacap.misconciertos.dto.valorEntradas> entradas=new ArrayList<>();

    TextView tvFecha;
    Calendar calendario;
    int day,month,year;
    Spinner spGenero;
    String[] items;
    Spinner spEvaluacion;
    String[] nota;
    private boolean primera=true;
    TextView valorEntradas;

    private Button guardar;



    public TextView getValorEntradas() {
        return valorEntradas;
    }

    public void setValorEntradas(TextView valorEntradas) {
        this.valorEntradas = valorEntradas;
    }

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
                if(primera){
                    primera=false;
                }else{
                    Toast.makeText(getApplicationContext(), items[i],Toast.LENGTH_LONG).show();
                }
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
                    Toast.makeText(getApplicationContext(), items[i],Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        DecimalFormat formato=new DecimalFormat("###,###");
        valorEntradas=(TextView) findViewById(R.id.valorEntrada);
        valorEntradas.setText("$"+formato.format(valorEntradas));


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


}