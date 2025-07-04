package inventario;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Area de declaracon de variables y constructores globales
        Scanner input = new Scanner(System.in); //se crea una varable de tipo scanner que ayuda en las entradas de teclado
        String salto; // se usa para limpiar el salto de linea
        int opcion;
        GestorInventario gestor = new GestorInventario(); //se crea el objeto gestor de la clase Gestornventario
        //************************************************************

        do { //Se crea el menu
            System.out.println("Hola y Bienvendo a tu Control de Inventario");
            System.out.println("===========================================");
            System.out.println("1. Ingresar Producto");
            System.out.println("2. Mostrar Inventario");
            System.out.println("3. Buscar Producto por Nombre");
            System.out.println("4. Eliminar Productos");

            System.out.println("0. Salir");
            opcion = input.nextInt(); // Se lee la opcion deseada
            input.nextLine();
            switch (opcion){
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
                    System.out.println("\uD83E\uDDD0 Que producto desea buscar?");
                    String nombre = input.nextLine().trim();
                    System.out.println();
                    gestor.buscarProducto(nombre);
                    break;
                case 4:
                    gestor.eliminarProductos();
                    break;
            }
        } while (opcion != 0);

    }
}