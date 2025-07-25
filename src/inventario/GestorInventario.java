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
        int index;

        while (!validacion) {
            try {
                System.out.println("Selecciona el número del producto:");
                System.out.println("0 para cancelar");
                index = input.nextInt();
                input.nextLine(); // limpiar el salto de línea

                if (index > 0 && index <= coincidencias.size()) {
                    validacion = true;
                    return coincidencias.get(index - 1);
                } else {
                    System.out.println("❌ Operacion cancelada.");
                    return null;
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Ingresa un número válido.");
                input.nextLine(); // limpiar el buffer
            }
        }

        // Nunca llega aquí porque el ciclo solo termina con return
        return null;
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

    private void editarNombre(Producto producto){
        System.out.println("*******Editar Producto******");
        String nombreNuevo = validarNombre();
        producto.setNombre(nombreNuevo);
        System.out.println();
        System.out.println("✅ El nombre se actualizo correctamente.");

    }

    private void editarPrecio(Producto producto){
        double precioNuevo = validarPrecio();
        producto.setPrecio(precioNuevo);
        System.out.println();
        System.out.println("✅ El precio se actualizo correctamente.");
    }

    private void editarCantidad(Producto producto){
        int cantidadNuevo = validarCantidad();
        producto.setCantidad(cantidadNuevo);
        System.out.println("✅ La cantidad se actualizo correctamente.");
    }

    private double validarPrecio() {
        double nuevoprecio=0;
        boolean preciovalido = false;
        String cantidad = null;

        while (!preciovalido) {
            System.out.println("Ingrese el nuevo precio del producto: ");
            cantidad = input.nextLine().trim();

            if (!cantidad.isEmpty()) {
                try{
                    nuevoprecio = Double.parseDouble(cantidad);
                    if (nuevoprecio>0){
                        preciovalido = true;
                    }else {
                        System.out.println("❌ El precio debe ser mayor a cero.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("❌ Entrada inválida. Ingresa solo números (usa punto si es decimal).");
                }
            } else {
                System.out.println("⚠️ Por favor no dejar vacio el campo");
            }
        } return nuevoprecio;
    }

    private int validarCantidad() {
        int nuevacantidad=0;
        boolean cantidadvalido = false;
        String cantidad = null;

        while (!cantidadvalido) {
            System.out.println("Ingrese la nueva cantidad de inventario: ");
            cantidad = input.nextLine().trim();

            if (!cantidad.isEmpty()) {
                try{
                    nuevacantidad = Integer.parseInt(cantidad);
                    if (nuevacantidad>0){
                        cantidadvalido = true;
                    }else {
                        System.out.println("❌ El precio debe ser mayor a cero.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("❌ Entrada inválida. Ingresa solo números.");
                }
            } else {
                System.out.println("⚠️ Por favor no dejar vacio el campo");
            }
        } return nuevacantidad;
    }

    public void editarProducto(){
        int opcion = -1;
        List<Producto> coincidencias = solicitarCoincidencias();
        if (!coincidencias.isEmpty()) {
            Producto seleccion = seleccionarProducto(coincidencias);
            if (seleccion == null) {
                return;
            } else {

                do { //Se crea el menu
                    try {
                        System.out.println("Que atributo quieres editar?");
                        System.out.println("===========================================");
                        System.out.println("1. Nombre");
                        System.out.println("2. Precio");
                        System.out.println("3. Cantidad");

                        System.out.println("0. Salir");
                        opcion = input.nextInt(); // Se lee la opcion deseada
                        input.nextLine();
                        switch (opcion) {
                            case 0:
                                break;
                            case 1:
                                System.out.println();
                                editarNombre(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                            case 2:
                                System.out.println();
                                editarPrecio(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                            case 3:
                                System.out.println();
                                editarCantidad(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("❌ Entrada inválida. Ingresa un número válido.");
                        input.nextLine(); // limpiar el buffer
                        System.out.println();
                    }
                } while (opcion != 0);
            }
        }
    }

    public void editarProductoexacto(){
        String nombre = validarNombre();
        List<Producto> coincidencias = new ArrayList<>();

        for (Producto p : productos){
            if (p.getNombre().equalsIgnoreCase(nombre)){
                coincidencias.add(p);
            }
        }
        if (coincidencias.size()<=1) {
           // mostrarCoincidencias(coincidencias);
            int opcion = -1;
            if (!coincidencias.isEmpty()) {
                Producto seleccion = seleccionarProducto(coincidencias);
                do { //Se crea el menu
                    try {
                        System.out.println("Que atributo quieres editar?");
                        System.out.println("===========================================");
                        System.out.println("1. Nombre");
                        System.out.println("2. Precio");
                        System.out.println("3. Cantidad");

                        System.out.println("0. Salir");
                        opcion = input.nextInt(); // Se lee la opcion deseada
                        input.nextLine();
                        switch (opcion) {
                            case 0:
                                break;
                            case 1:
                                System.out.println();
                                editarNombre(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                            case 2:
                                System.out.println();
                                editarPrecio(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                            case 3:
                                System.out.println();
                                editarCantidad(seleccion);
                                seleccion.mostrarProductos();
                                System.out.println();
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("❌ Entrada inválida. Ingresa un número válido.");
                        input.nextLine(); // limpiar el buffer
                        System.out.println();
                    }
                } while (opcion != 0);
            } else {
                System.out.println("❌ No se encontró ningún producto con ese nombre exacto.");
                System.out.println("-------- Pruebe con edicion avanzada.----------");
            }
        } else {
            System.out.println("Hay mas de un articulo con el mismo nombre favor de usar Edicion avanzada");
        }
    }
}