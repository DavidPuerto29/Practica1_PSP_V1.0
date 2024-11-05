package back;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Clase encargada de gestiónar los métodos de lector y escritor y de crear una interfaz
 * rudimentaria a base de argumentos de terminal.
 *
 * @author David Puerto Cuenca
 * @version 1.0
 */
public class Gestor {
    private static HashMap<Integer, Double> hm = new HashMap<>();
    /**
     * Primero planteo un método que seria una parte de una solución que intente crear para que
     * cuando se elimine una nota del fichero no haya que leer todo el fichero otra vez
     * y luego sobreescribirlo, sino que desde la posición en la que sabemos que la nota
     * ha sido eliminada se siga desde ahí ejemplo: si la nota es 1 no se ahorrara nada
     * de memoria, pero si la nota eliminada es 100 sobreescribirá el fichero desde la
     * posición 100 ahorrándose 100 procesos.
     *
     * Este método consta de un HashMap y otro auxiliar, primero se lee la id que desea eliminar el usuario.
     * y después se recorre un bucle for hasta llegar a la id deseada, una vez alli se entra al segundo bucle
     * for en el que se guardaran en el HashMap auxiliar las ids desde la eliminada, para completar este método
     * faltaría un sistema en el que se pudiera sobreescribir un fichero desde x linea.
     *
     * @param hm HashMap donde se almacenan los datos de las notas.
     * @param hmm HashMap auxiliar donde se almacenaran los datos desde la id eliminada.
     * @param tam Con valor del tamaño del HashMap cada vez que recorre una nota se le descuenta
     * una unidad, esto sirve para saber en todo momento donde en que posición del fichero se encuentra
     * para saber cuantas veces recorrer el bucle for.
     * @cont es una variable usada para que cuando se cumpla la primera condición del bucle se recorra
     * hasta que finalice de introducir los datos en el HashMap y no haciendo tan solo una instancia.
     * @param id Es el ejemplo de la id que introduciría el usuario para eliminar.
     */
        /*
        ----------------------------------------------------------------------------------------------------------------
        HashMap<Integer, Double> hm = new HashMap<>();
        HashMap<Integer, Double> hmm = new HashMap<>();
        hm.put(1, 5.4);
        hm.put(2, 1.4);
        hm.put(3, 7.4); //Variable a eliminar.
        hm.put(4, 7.4);
        hm.put(5, 8.4);
        hm.put(6, 3.4);
        hm.put(7, 4.4);

        Integer id = 3;
        int var = id;
        int tam = hm.size();
        boolean cont = false;

        for (int id2 : hm.keySet()) {
            tam--;
            if (var == id2 || cont == true) {
                cont = true;
                    Double b = hm.get(id2);
                        for (int i = 0; i <= tam; ++i) {
                            hmm.put(id2, b);
                        }
            }
        }
        //Al final del programa se eliminan las variables para después escribir
        el segundo array desde la posición que tenía en el fichero la variable eliminada
        hm.remove(id);
        hmm.remove(id);
        //pw
        System.out.println(hmm);
    --------------------------------------------------------------------------------------------------------------------
    */

    /**
     * El método main integra un menu rudimentario a base de comandos de terminal, en este se pueden realizar varias
     * operaciónes con el fichero seleccionado: (w) Escribe una nota en el fichero, (r) Lee una nota de fichero o
     * si se introduce de id '-1' lee todas las notas almacenadas en el documento, por último (d) elimina una nota
     * del fichero también siempre que se inicie el programa se realizara una lectura del fichero y se guardara en el
     * HashMap para poder tratar los datos correctamente.
     *
     * Una variable muy importante es comprobador, que se encarga de comprobar si la id que pide el usuario se encuentra
     * en el HashMap o no, ya que de no encontrarse el método devolvería null y saltarían varias excepciónes gracias
     * a esta variable podemos informar al usuario dependiendo de la acción a realizar si la variable no existe,
     * ya está utilizada etc...
     *
     * @param args Son los datos que el programa utiliza para realizar las acciones, el primer argumento
     * selecciona la operación a realizar, el segundo argumento introduce el nombre del fichero a utilizar,
     * el tercer argumento introduce la id tratada y en caso de realizar una escritura se pide un cuarto dato
     * que sería la nota a escribir.
     */
    public static void main(String[] args) {
        String op = args[0];
        //Verifica si el número de argumentos requeridos es correcto y si no informa al usuario.
        if(args.length < 3 || args[0].equals("r") || args[0].equals("d")){
            System.out.println("""
                             ╔═══════════════════════════════════════════════════════════════════════════╗  \r  
                    	     ║  Error los datos introducidos no son correctos, datos requeridos:[w/r/d], ║
                             ║ [Nombre del fichero], [id] y en caso de querer escribir también [nota]    ║\r
                             ╚═══════════════════════════════════════════════════════════════════════════╝\r
                    """);
            System.exit(10);
        }

        //Importante si ejecutas por terminal habría que añadir ./files/Nombre de documento de texto.
        Lector l = new Lector(new File(args[1]+".txt"));
        Escritor e = new Escritor(new File( args[1]+".txt"));
        Integer id = Integer.parseInt(args[2]);
        Double comprobador = null;

        try {
            //Cargamos datos por cada instancia del programa
            hm = l.leer();
        }catch (FileNotFoundException ex){
            System.out.println("El fichero seleccionado no existe.");
                System.exit(12);
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida.");
        }

        switch (op) {
            case "w":
                //Verifica si el número de argumentos requeridos es correcto y si no informa al usuario.
                if(args.length != 4){
                    System.out.println("""
                             ╔═══════════════════════════════════════════════════════════════════════════╗  \r
                    	     ║  Error los datos introducidos no son correctos, datos requeridos:[w/r/d], ║
                             ║ [Nombre del fichero], [id] y en caso de querer escribir también [nota]    ║\r
                             ╚═══════════════════════════════════════════════════════════════════════════╝\r
                    """);
                    System.exit(10);
                }
                comprobador = hm.get(id);
                if(comprobador == null) {
                    double nota = Double.parseDouble(args[3]);
                    try {
                        e.escribe(id, nota);
                    } catch (IOException ex) {
                        System.out.println("Error de entrada/salida.");
                    }
                    System.out.println("Guardado el id "+id+" con nota "+nota+" en "+args[1]);
                }else{
                    System.out.println("El id "+id+" ya existe.");
                }
                break;

            case "r":
                comprobador = hm.get(id);
                if(comprobador != null) {
                    //Ya que comprobador contiene la nota requerida reutilizamos la variable para mostrársela al usuario.
                    System.out.println("La nota del id " + id + " es " + comprobador);
                } else if(id == -1) {
                    System.out.println("""
                         ╔════════════════════╗  \r
                    	 ║  Todas las notas   ║\r
                         ╚════════════════════╝\r
                    """);
                        for(int id2 : hm.keySet()){
                            System.out.println(id2+" "+hm.get(id2));
                        }
                }else{
                    System.out.println("El id "+id+" no existe.");
                }
                break;

            case "d":
                comprobador = hm.get(id);
                if(comprobador != null) {
                    hm.remove(id);
                    try {
                        e.escribe(hm);
                    } catch (IOException ex) {
                        System.out.println("Error de entrada/salida.");
                    }
                    //Ya que comprobador contiene la nota requerida reutilizamos la variable para mostrársela al usuario.
                    System.out.println("Eliminado el id "+id+" con nota "+comprobador);
                }else{
                    System.out.println("El id no existe.");
                }
                break;

            default:
                System.out.println("Error opción incorrecta.");
                    System.exit(11);
                break;
            }

    }
}
