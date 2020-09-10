package cl.inacap.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etFecha;
    Calendar myCalendar=Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            actualizarInput();
        }

        private void actualizarInput() {
            String formatoFecha="MM/dd/YY";
            SimpleDateFormat sdf= new SimpleDateFormat(formatoFecha, Locale.US);
            etFecha.setText(sdf.format(myCalendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}