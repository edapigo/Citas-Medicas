
package Modelo;



public class evento {
    private int id;
    private String tema_evento , descripcion,fecha,categoria,subcategoria;

    public evento() {
        
    }
    public evento(String tema_evento,String descripcion,String fecha ,String categoria,String subcategoria){
    this.tema_evento =tema_evento;
    this.descripcion = descripcion;
    this.fecha = fecha;
    this.categoria = categoria;
    this.subcategoria = subcategoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTema_evento() {
        return tema_evento;
    }

    public void setTema_evento(String tema_evento) {
        this.tema_evento = tema_evento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
    
}
