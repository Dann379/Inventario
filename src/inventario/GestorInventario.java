package inventario;
import java.util.*;

public class GestorInventario {
    // Zona de declaracion de variables y constructores globales
    private ArrayList<Producto> productos = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    //**************************************************************

    public String validarNombre() {
        boolean nombrevalido = false;
        String nombre = null;
        while (!nombrevalido) {
            System.out.println("Ingrese nombre de Producto: ");
            nombre = input.nextLine().trim();
            if (!nombre.isEmpty()) {
                nombrevalido = true;
            } else {
                System.out.println("⚠️ Por favor Introdusca nombre valido");
            }
        }
        return nombre;
    }
    public void crearProducto(){
        boolean preciovalido = false;
        boolean cantidadvalida = false;
        double precio = 0;
        int cantidad = 0;
        System.out.println("ingresar nuevo producto");
        String nombre = validarNombre();
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
        System.out.println("❌ No se encontró ningún producto con el nombre: \"" + nombreBuscado + "\"");
        System.out.println();
    }

    public void buscarProducto(){
        int coincidencia = 0;
        boolean encontrado = false;
        /*if (nombre.isEmpty()) {
            System.out.println("⚠️ Debe ingresar un nombre válido.");
            return;
        }*/
        System.out.println("Buscar producto en inventario");
        String nombre = validarNombre();
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
        List <Producto> coincidencias = new ArrayList<>();
        boolean validacion = false;
        String nombre = "";
        /*while (!validacion){
            System.out.println("Que producto quieres eliminar? ");
            nombre = input.nextLine().trim();
            if (nombre.isEmpty()){
                System.out.println("El campo no puede estar vacio, favor de ingresar un nombre de producto valido");
            } else {
                validacion = true;
            }
        }*/
        System.out.println("Que producto quieres eliminar? ");
        nombre = validarNombre();
        for (Producto p : productos){
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                coincidencias.add(p);
            }
        }

        if (coincidencias.isEmpty()){
            mostrarMensajeProductoNoEncontrado(nombre);
            return;
        }

        System.out.println("🔍 Productos a eliminar:");
            for (Producto p : coincidencias){
                p.mostrarProductos();
                System.out.println("--------------------------------------------------");
            }
        System.out.println("Deseas eliminarlos? (S/N)");
            String confirmacion = input.nextLine().trim().toLowerCase();

            if (confirmacion.equalsIgnoreCase("s")) {
                String finalNombre = nombre;
                productos.removeIf(producto -> producto.getNombre().toLowerCase().contains(finalNombre.toLowerCase()));
                System.out.println("✅ Productos eliminados.");
            } else {
                System.out.println("❌ Operación cancelada.");
            }
    }
}