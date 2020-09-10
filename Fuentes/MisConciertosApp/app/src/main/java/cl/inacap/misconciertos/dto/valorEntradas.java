package cl.inacap.misconciertos.dto;

import android.icu.text.DecimalFormat;
import android.icu.text.UFormat;

import androidx.annotation.NonNull;

public class valorEntradas {
    int valores;
    DecimalFormat formato=new DecimalFormat("###.###");

    public int getValores() {
        return valores;
    }

    public void setValores(int valores) {
        this.valores = valores;
    }

    @Override
    public String toString() {
        return "$"+ formato.format(valores);
    }
}
