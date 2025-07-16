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
                System.out.println("⚠️ Por favor Introduzca nombre valido");
            }
        }
        return nombre;
    }

    private void mostrarMensajeProductoNoEncontrado(String nombreBuscado) {
        System.out.println("❌ No se encontró ningún producto con el nombre: \"" + nombreBuscado + "\"");
        System.out.println();
    }

    public List<Producto> buscarCoincidencias(String nombrebuscado) {
        List<Producto> coincidencias = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombrebuscado.toLowerCase())) {
                coincidencias.add(p);
            }
        }
        return coincidencias; // Siempre regresa la lista (vacía o con datos)
    }

    private List<Producto> solicitarCoincidencias() {
        while (true) {
            String nombre = validarNombre();
            List<Producto> coincidencias = buscarCoincidencias(nombre); // ya optimizado
            if (coincidencias.isEmpty()) {
                System.out.println("❌ No se encontraron productos con ese nombre..");
                System.out.println();
                return coincidencias;
            } else {
                return coincidencias;
            }
        }
    }

    public void mostrarCoincidencias(List<Producto> coincidencias) {
        if (coincidencias.isEmpty()) {
            System.out.println("❌ No se encontraron productos.");
        } else {
            int i = 1;
            for (Producto p : coincidencias) {
                System.out.println("------------------------------------------------");
                System.out.println(i + ". Producto: " + p.getNombre() + " | Precio: "+p.getPrecio()+ " | Cantidad en Stock: "+p.getCantidad());
                i++;
            }
        }
        System.out.println();
        System.out.println("------------------------------------------------");
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
                System.out.print(+cont+ ". ");
                producto.mostrarProductos();
                cont++;
            }
        }else {
            System.out.println();
            System.out.println("❌ No hay productos en el inventario");
        }
        System.out.println("------------------------------------------------");
        System.out.println();
    }

    public void calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.getPrecio() * p.getCantidad();
        }
        System.out.println("El valor total del inventario es $" + total);
        System.out.println();
    }

    public List buscarProducto(){
        while (true) {
            String nombre = validarNombre();
            List<Producto> coincidencias = buscarCoincidencias(nombre); // ya optimizado
            if (!coincidencias.isEmpty()) {
                mostrarCoincidencias(coincidencias);
                return coincidencias;
            } else {
                System.out.println("❌ No se encontraron productos con ese nombre. Intente nuevamente.");
            }
        }
    }

    private Producto seleccionarProducto(List<Producto> coincidencias) {
        mostrarCoincidencias(coincidencias);
        boolean validacion = false;
        int index = 0;
            System.out.println("Selecciona el numero del Producto");
            index = input.nextInt();
            input.nextLine();

            if (index > 0 || index <= coincidencias.size()) {
                return coincidencias.get(index - 1);
            } else {
                System.out.println("Seleccione un numero de la lista");
                return null;
            }
    }

    private void eliminarSeleccion(Producto producto) {
        productos.remove(producto);
        System.out.println("✅ Producto eliminado correctamente.");
        System.out.println();
    }

    public void eliminarProducto() {
        List<Producto> coincidencias = solicitarCoincidencias();
        if (!coincidencias.isEmpty()){
            Producto seleccion = seleccionarProducto(coincidencias);
            System.out.println("Confirmar Eliminacion? (S/N)");
            String confirmacion = input.nextLine().trim().toLowerCase();

            if (confirmacion.equalsIgnoreCase("s")) {
                eliminarSeleccion(seleccion);
            } else {
                System.out.println("❌ Operación cancelada.");
            }
        }
    }

    /*public void editarProducto(){
        List coincidencias = new ArrayList<>();
        boolean validacion = false;
        System.out.println("-------------Editar Producto------------");
            String nombre = validarNombre();
            coincidencias = listarCoincidencias(nombre);


    }*/
}