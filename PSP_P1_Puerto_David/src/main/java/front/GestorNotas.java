package front;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase del paquete front se encarga de crear una interfaz ejecutable y más intuitiva para el usuario
 * para realizar las acciónes disponibles, se cuenta con un main en el que se incorpora un menu con un
 * switch case pudiendo realizar varias opciónes dependiendo de la función que el usuario elija
 * se accederán a unos métodos y a otros donde se recopilarán los parámetros de lanzamiento y después
 * con otro método se lanzarán obteniendo una respuesta de las clases del paquete back.
 *
 * @author David Puerto Cuenca
 * @version 1.0
 */
public class GestorNotas {
    public static void main(String[] args) {
        boolean bucle = true;
        Integer op ;
        System.out.println("Introduzca el nombre del fichero de notas a gestionar: ");
            String path = "./out/artifacts/PSP_P1_Puerto_David_jar/files/"+escribirString();
        while (bucle) {
            System.out.println("""
                         ╔══════════════════════|Gestor de notas|══════════════════════╗  \r
                    	 ║	        1 Escribir una nota	                               ║\r
                    	 ║	        2 Leer una nota                                    ║\r
                    	 ║	        3 Eliminar una nota				  	               ║\r
                    	 ║	        4 Generar 100 ids y notas aleatorias		       ║\r
                    	 ║	        5 Leer todas las notas 				  	           ║\r
                    	 ║	        6 Leer todas las notas y exportarlas  a un fichero ║\r
                    	 ║	        0 Para finalizar el programa.	 		           ║\r
                         ╚═════════════════════════════════════════════════════════════╝\r
                    """);
            op = escribirInteger();

            switch (op){
                case 0:
                    bucle = false;
                    break;

                case 1:
                    escribirNota(path);
                    break;

                case 2:
                    leerNota(path);
                    break;

                case 3:
                    eliminarNota(path);
                    break;

                case 4:
                    generarPrefab(path);
                    break;

                case 5:
                    leerTodasNotas(path);
                    break;

                case 6:
                    exportarNotas(path);
                    break;

                case null:
                    break;

                default:
                    System.out.println("Por favor, elija una opción del menu.");
                    break;

            }
        }
    }

    /**
     * Se encarga de escribir los argumentos de ejecución pidiéndole al usuario el id y la nota por entrada
     * y después mandándole el array de String con el proceso a ejecutarProceso donde ya se gestiona y se ejecuta
     * la acción.
     *
     * @param path El documento de texto en el que se van a realizar las acciones.
     */
    private static void escribirNota(String path){
        System.out.println("Introduzca el id:");
            String id = escribirString();
                System.out.println("Introduzca la nota:");
                    String nota = escribirString();
                        String[] proceso = {"java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","w",path,id,nota};
                            ejecutarProceso(proceso);
    }

    /**
     * Se encarga de escribir los argumentos de ejecución pidiéndole al usuario el id y después mandándole el array de String
     * con el proceso a ejecutarProceso donde ya se gestiona y se ejecuta la acción.
     *
     * @param path El documento de texto en el que se van a realizar las acciones.
     */
    private static void leerNota(String path){
        System.out.println("Introduzca el id:");
            String id = escribirString();
                String[] proceso = {"java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","r",path,id};
                    ejecutarProceso(proceso);
    }

    /**
     * Se encarga de escribir los argumentos de ejecución pidiéndole al usuario el id y después mandándole el array de String
     * con el proceso a ejecutarProceso donde ya se gestiona y se ejecuta la acción.
     *
     * @param path El documento de texto en el que se van a realizar las acciones.
     */
    private static void eliminarNota(String path){
        System.out.println("Introduzca el id:");
            String id = escribirString();
                String[] proceso = {"java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","d",path,id};
                    ejecutarProceso(proceso);
    }

    /**
     * Mediante el uso de la función random de java se generan 100 instancias del 1 al 100
     * id con sus respectivas notas.
     *
     * @param path El documento de texto en el que se van a realizar las acciones.
     */
    private static void generarPrefab(String path){
        Random prefab = new Random();
        String idS;
        String nota;
        double generarNota;
        for(int id = 1; id <= 100;++id ){
            idS = String.valueOf(id);
            generarNota = 10.00 * prefab.nextDouble();
            generarNota = Math.round(generarNota * 100.0) / 100.0;
            nota = String.valueOf(generarNota);
            String[] proceso = {"java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","w",path,idS,nota};
                ejecutarProceso(proceso);
        }
    }

    /**
     * Este método se encarga de leer todas las notas del programa al saber
     * que al introducir el comando 'r' y '-1' se leen todas las notas no se necesita
     * la introducción de datos del usuario.
     *
     * @param path El documento de texto en el que se van a realizar las acciones.
     */
    private static void leerTodasNotas(String path){
        String[] proceso = {"java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","r",path,"-1"};
            ejecutarProceso(proceso);
    }

    /**
     * Este método se encarga de declarar el Scanner y de devolver una introducción de
     * texto por parte del usuario asi se evita declarar el Scanner en todos los métodos...
     *
     * @return Devuelve el String introducido por el usuario.
     */
    private static String escribirString(){
        Scanner sc = new Scanner(System.in);
            return sc.nextLine();
    }

    /**
     * Este método se encarga de recibir parámetros de lanzamiento y ejecutarlos mediante
     * el uso de ProccesBuilder, también se encarga de mostrar al usuario el texto que
     * escribe el programa.
     *
     * @param proceso Los parámetros de ejecución que ejecuta.
     */
    private static void ejecutarProceso(String[] proceso){
        String linea;
        ProcessBuilder pb = new ProcessBuilder(proceso);
        Process p = null;
        try {
            p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    /**
     * Este método se encarga de recibir parámetros de lanzamiento y ejecutarlos mediante
     * el uso de ProcessBuilder, para comenzar se pide al usuario el path del documento
     * donde desea exportar los datos del otro fichero, después mediante el uso de un
     * PrintWritter se escribe a partir de la fila 4, ya que es donde comienzan las notas
     * del fichero, una vez escritas todas las notas se informa al usuario de que la exportación
     * ha sido correcta.
     *
     */
    private static void exportarNotas(String path){
        int cont = 0;
        boolean var = false;
        System.out.println("¿En que fichero desea guardar todas las notas?");
            String pathSave = "./out/artifacts/PSP_P1_Puerto_David_jar/files/"+escribirString()+".txt";

        try {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathSave)));
        ProcessBuilder pb = new ProcessBuilder("java","-jar","./out/artifacts/PSP_P1_Puerto_David_jar/PSP_P1_Puerto_David.jar","r",path,"-1");
            Process p = pb.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //Reutilización de variables
            while ((pathSave = br.readLine()) != null) {
                 if(cont == 4 || var){
                     pw.println(pathSave);
                        //var se declara true por lo que ne el resto del bucle
                        //entrara a la condición if.
                        var = true;
                 }
                 ++cont;
            }
            pw.close();
            br.close();
                System.out.println("Archivos exportados correctamente.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    /**
     *Este método se utiliza cuando al usuario se le pide una entrada de un dato entero
     * debido a que se puede producir una excepción, si el usuario introduce otro dato
     * se gestiona la excepción, se informa al usuario de que introduzca un dato correcto
     * y se hace return de null.
     *
     * @return En caso de que el dato no sea numérico se devolverá null.
     */
    private static Integer escribirInteger(){
        Scanner num = new Scanner(System.in);
        try{
            return num.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Introduzca un dato correcto.");
            return null;
        }
    }
}
