package back;

import java.io.*;
import java.util.HashMap;

/**
 * Clase en la que se almacenan los métodos de escritura en ficheros del programa,
 * al crear una variable de la clase se pedirá un archivo File con el path que leerán
 * los métodos en el que se gestionaran y escribirá los datos.
 *
 * @author David Puerto Cuenca
 * @version 1.0
 */
public class Escritor {
    private File f;

    public Escritor(File f) {
        this.f = f;
    }

    /**
     * Este método se encarga de escribir en el ficheró de texto un id con su respectiva nota,
     * para comenzar se comprueba que se posean permisos de escritura de lo contrario se lanzara
     * una excepción y después se escribirá en el fichero concatenando los datos.
     *
     * Ejemplo de formato:
     * 1 2.4
     * 2 3.4
     * etc...
     *
     * @param id Es la id en la que se va a almacenar esa nota, no puede haber id duplicadas.
     * @param nota Es la nota a la que se relaciona el id puede tener decimales.
     * @throws IOException Puede lanzar un error de entrada/salida.
     */
    public void escribe(int id, double nota) throws IOException {
        //Se comprueba que se tienen permisos de escritura, si no lanza una excepción.
        if(f.canWrite()) {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
                pw.println(id + " " + nota);
                    pw.close();
        }else {
            throw new IOException("Error de entrada/salida.");
        }
    }

    /**
     * Este método se encarga de escribir en el fichero de texto todos los id y notas que contiene el HashMap,
     * este método si sobreescribe el fichero, también se comprueba que se posean permisos de escritura de lo
     * contrario se lanzara una excepción.
     *
     * @param hm HashMap con los datos de key(id) y value (nota).
     * @throws IOException Puede lanzar un error de entrada/salida.
     */
    public void escribe(HashMap<Integer,Double> hm) throws IOException {
        //Se comprueba que se tienen permisos de escritura, si no lanza una excepción.
        if (f.canWrite()) {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
                for (int id : hm.keySet()) {
                    pw.println(id + " " + hm.get(id));
                }
            pw.close();
        } else {
            throw new IOException("Error de entrada/salida.");
        }
    }

}
