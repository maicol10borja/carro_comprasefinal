package models;

public class Productos {
    private Long idProducto;
    private String nombre;
    private Categoria categoria;
    private double precio;

    public Productos() {
    }

    public Productos(Long idProducto, String nombre, String categoria, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        Categoria categoria1=new Categoria();
        categoria1.setNombre(categoria);
        this.categoria = categoria1;
        this.precio = precio;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
