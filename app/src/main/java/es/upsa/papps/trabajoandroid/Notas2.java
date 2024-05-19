package es.upsa.papps.trabajoandroid;

public class Notas2 {
    private String titulo;
    private String contenido;

    public Notas2() {

    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContent(String contenido) {
        this.contenido = contenido;
    }

    public Notas2(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }
}
