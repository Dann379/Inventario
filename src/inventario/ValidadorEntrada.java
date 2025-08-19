package inventario;
import java.util.*;

public class ValidadorEntrada {

    private ArrayList<Producto> productos = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static String validarNombre() {
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

    public static double validarPrecio() {
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

    public static int validarCantidad() {
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
}
