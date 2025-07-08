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

    private void mostrarMensajeProductoNoEncontrado(String nombreBuscado) {
        System.out.println("❌ No se encontró ningún producto con el nombre: \"" + nombreBuscado + "\"");
        System.out.println();
    }

    public List mostrarCoincidencias(String nombrebuscado){

        List <Producto> coincidencias = new ArrayList<>();
        boolean validacion = false;
        String nombre = nombrebuscado;
        for (Producto p : productos){
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                coincidencias.add(p);
            }
        }

        if (coincidencias.isEmpty()){
            mostrarMensajeProductoNoEncontrado(nombre);
        }

        System.out.println("🔍 Productos en inventario encontrados:");
        System.out.println();
        int cont = 1;
        for (Producto p : coincidencias){
            System.out.print(+cont+ ". ");
            p.mostrarProductos();
            System.out.println();
            System.out.println("--------------------------------------------------");
            cont++;
        }

        System.out.println("El numero de Productos con ese nombre es: " +coincidencias.size());
        System.out.println("---------------------------------------------------------");
        System.out.println();
        return coincidencias;
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
        System.out.println("📦 Inventario Actual:");
        if (productos.size()>0) {
            int cont = 1;
            for (Producto producto : productos) {
                System.out.println("------------------------------------------------");
                System.out.println();
                System.out.print(+cont+ ". ");
                producto.mostrarProductos();
                cont++;
            }
        }else {
            System.out.println();
            System.out.println("❌ No hay productos en el inventario");
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

    public void buscarProducto(){
        int i = 0;
        boolean encontrado = false;
        List <Producto> coincidencias = new ArrayList<>();
        boolean validacion = false;
        String nombre = "";
        System.out.println("Que producto quieres buscar? ");
        nombre = validarNombre();
        coincidencias = mostrarCoincidencias(nombre);
    }

    public void eliminarProductos() {
        List <Producto> coincidencias = new ArrayList<>();
        boolean validacion = false;
        String nombre = "";
        System.out.println("Que producto quieres eliminar? ");
        nombre = validarNombre();
            coincidencias = mostrarCoincidencias(nombre);

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