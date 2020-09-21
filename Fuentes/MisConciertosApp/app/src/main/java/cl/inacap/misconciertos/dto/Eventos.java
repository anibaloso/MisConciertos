package cl.inacap.misconciertos.dto;

import java.util.Date;

public class Eventos {

    private String nombreArtista;
    private Date fechaEvento;
    private String genero;
    private int precioEntrada;
    private int calificacion;
    private int imagen;


    public Eventos(){

    }

    public Eventos(String nombreArtista, Date fechaEvento, String genero, int precioEntrada, int calificacion, int imagen) {
        this.nombreArtista = nombreArtista;
        this.fechaEvento = fechaEvento;
        this.genero = genero;
        this.precioEntrada = precioEntrada;
        this.calificacion = calificacion;
        this.imagen = imagen;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada=precioEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return fechaEvento+" "+nombreArtista+" "+precioEntrada+" ";
    }
}
