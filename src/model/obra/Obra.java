package model.obra;

public class Obra {

    private static short proxNumb = 0;
    private short codigo;
    private String autor;
    private String titulo;
    private String genero;

    public Obra(String autor, String titulo, String genero) {
        proxNumb += 1;
        this.codigo = this.proxNumb;
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return this.genero;
    }

    public short getCodigo() {
        return codigo;
    }

}
