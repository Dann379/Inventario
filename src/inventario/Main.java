package inventario;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Area de declaracon de variables y constructores globales
        Scanner input = new Scanner(System.in); //se crea una varable de tipo scanner que ayuda en las entradas de teclado
        int opcion = -1;
        GestorInventario gestor = new GestorInventario(); //se crea el objeto gestor de la clase Gestornventario
        //************************************************************

        do { //Se crea el menu
            try {
                System.out.println("Hola y Bienvendo a tu Control de Inventario");
                System.out.println("===========================================");
                System.out.println("1. Ingresar Producto");
                System.out.println("2. Mostrar Inventario");
                System.out.println("3. Buscar Producto por Nombre");
                System.out.println("4. Eliminar Productos");
                System.out.println("5. Editar Producto (Edición Avanzada)");
                System.out.println("6. Editar Producto Exacto");
                System.out.println("7. Guardar Inventario en Archivo");

                System.out.println("0. Salir");
                opcion = input.nextInt(); // Se lee la opcion deseada
                input.nextLine();
                switch (opcion) {
                    case 0:
                        break;
                    case 1:
                        System.out.println();
                        gestor.crearProducto();
                        break;
                    case 2:
                        System.out.println();
                        gestor.mostrarProductos();
                        gestor.calcularTotal();
                        break;
                    case 3:
                        System.out.println();
                        gestor.buscarProducto();
                        break;
                    case 4:
                        gestor.eliminarProducto();
                        break;
                    case 5:
                        gestor.editarProducto();
                        System.out.println();
                        break;
                    case 6:
                        gestor.editarProductoexacto();
                        System.out.println();
                        break;
                    case 7:
                        gestor.guardarInventarioEnArchivo();
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println();
                System.out.println("❌ Entrada inválida. Ingresa un número válido.");
                input.nextLine(); // limpiar el buffer
                System.out.println();
            }
        } while (opcion != 0);
    }
}