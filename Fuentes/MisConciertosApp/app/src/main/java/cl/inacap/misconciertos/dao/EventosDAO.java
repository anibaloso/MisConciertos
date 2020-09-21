package cl.inacap.misconciertos.dao;

import cl.inacap.misconciertos.dto.Eventos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventosDAO {

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

    public static String fechaAstring (Date fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formato.format(fecha);
        return fechaString;
    }

    





}
