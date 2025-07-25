package inventario;

public class Producto {
    //Zona para definir variables
    private String nombre;
    private double precio;
    private int cantidad;
    //************************************************
    public Producto(String nombre, double precio, int cantidad){
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
    }

    public void mostrarProductos(){
        System.out.println("Producto: " +nombre+ " | Precio: $" +precio+ " | Cantidad en Stock: " +cantidad);
    }

    public String getNombre() {

        return nombre;
    }

    public double getPrecio() {

        return precio;
    }

    public int getCantidad() {

        return cantidad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
