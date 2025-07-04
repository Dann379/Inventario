package inventario;
import java.util.*;

public class GestorInventario {
    // Zona de declaracion de variables y constructores globales
    private ArrayList<Producto> productos = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    //**************************************************************

    public void crearProducto(){
        boolean nombrevalido = false;
        boolean preciovalido = false;
        boolean cantidadvalida = false;
        String nombre = "";
        double precio = 0;
        int cantidad = 0;

        while (!nombrevalido){
            System.out.println("Ingrese Producto: ");
            nombre = input.nextLine().trim();
            if (!nombre.isEmpty()){
            nombrevalido = true;
            } else {
                System.out.println("⚠️ Por favor Introdusca nombre valido");
            }
        }
        while (!preciovalido){
            System.out.println("Ingrese Precio: ");
            precio = input.nextDouble();
            input.nextLine();
            if (precio > 0){
                preciovalido = true;
            } else {
                System.out.println("⚠️ Por favor Ingrese precio mayor a 0");
            }
        }
        while (!cantidadvalida){
            System.out.println("Ingrese Cantidad: ");
            cantidad = input.nextInt();
            input.nextLine();
            if (cantidad > 0){
                cantidadvalida= true;
            } else {
                System.out.println("⚠️ Por favor Ingrese cantidad mayor a 0");
            }
        }
        productos.add(new Producto(nombre, precio, cantidad));
    }

    public void mostrarProductos(){
        System.out.println("📦 Productos en inventario:");
        if (productos.size()>0) {
            for (Producto producto : productos) {
                System.out.println("------------------------------------------------");
                producto.mostrarProductos();
            }
        }else {
            System.out.println();
            System.out.println("❌ No se encontraron productos");
        }
        System.out.println();
    }

    public void calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.getPrecio() * p.getCantidad();
        }
        System.out.println("El valor total del inventario es " + total);
        System.out.println();
    }

    private void mostrarMensajeProductoNoEncontrado(String nombreBuscado) {
        System.out.println();
        System.out.println("❌ No se encontró ningún producto con el nombre: \"" + nombreBuscado + "\"");
    }

    public void buscarProducto(String nombre){
        int coincidencia = 0;
        boolean encontrado = false;
        if (nombre.isEmpty()) {
            System.out.println("⚠️ Debe ingresar un nombre válido.");
            return;
        }
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                    p.mostrarProductos();
                    System.out.println("----------------------------------------------------");
                    encontrado = true;
                    coincidencia++;
            }
        }
        if (!encontrado){
            mostrarMensajeProductoNoEncontrado(nombre);
        }
        System.out.println("El numero de Productos con ese nombre es: " +coincidencia);
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    public void eliminarProductos() {
        List coincidencias = new ArrayList<>();
        System.out.println("Que producto quieres eliminar? ");
        String nombre = input.nextLine().trim();

        for (Producto p : productos){
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                coincidencias.add(p);
            }
        }

        if (coincidencias.isEmpty()){
            mostrarMensajeProductoNoEncontrado(nombre);
        }

        System.out.println("🔍 Productos a eliminar:");
        for (Producto p : productos){
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                p.mostrarProductos();
                System.out.println("--------------------------------------------------");
            }
        }
        }
    }

