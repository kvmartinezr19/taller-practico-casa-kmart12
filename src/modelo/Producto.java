package modelo;

import java.util.Objects;

public class Producto {

    //Atributos
    private int idProducto;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int cantidadDisponible;

    //Creacion del contructor vacio
    public Producto() {
    }

    //Creación del constructor con parametros
    public Producto(int idProducto, String nombreProducto, String categoria, double precio, int cantidadDisponible) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    //Creacion metodos getter y setter

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return idProducto + "," + nombreProducto + "," + categoria + "," + precio + "," + cantidadDisponible;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return idProducto == producto.idProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
}
