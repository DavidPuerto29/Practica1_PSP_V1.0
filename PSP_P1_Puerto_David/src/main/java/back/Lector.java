package back;

import java.io.*;
import java.util.HashMap;

/**
 * Clase en la que se almacenan los métodos de lectura en ficheros del programa,
 * al crear una variable de la clase se pedirá un archivo File con el path que leerán
 * los métodos en el que se gestionaran y leerán los datos.
 *
 * @author David Puerto Cuenca
 * @version 1.0
 */
public class Lector {
    private File f;

    public Lector(File f) {
        this.f = f;
    }

    /**
     * Este método se encarga de leer un fichero y guardar las ids con sus respectivas notas en un HashMap
     * para comenzar primero se comprueba si el archivo File existe en caso contrario lanza una excepción,
     * y después comprueba si la id solicitada por el usuario se encuentra, en caso de encontrarse
     * devuelve la nota.
     *
     * @param id Es la id en la que se va a almacenar esa nota, no puede haber id duplicadas.
     * @return hm.get(id); Devuelve la nota, en caso de no encontrarla devuelve null.
     *
     * @throws FileNotFoundException En caso de no encontrar el archivo.
     * @throws IOException Puede lanzar un error de entrada/salida.
     */
    public double leer(int id) throws IOException {
        HashMap<Integer, Double> hm = new HashMap<>();
        //Se comprueba que el archivo File existe, si no lanza una excepción.
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
                hm = leer();
        }catch (FileNotFoundException e){
            System.out.println("Error el archivo no ha sido encontrado.");
            System.exit(12);
        }
        return hm.get(id);
    }

    /**
     * Este método se encarga de leer un archivo File y de introducir las notas con sus respectivas ids
     * en un HashMap, para comenzar se comprueba que el fichero existe de lo contrario lanza una excepción,
     * después mediante un BufferedReader se lee el fichero y se guarda en la variable linea la linea de texto leida,
     * después ya que el fichero tiene un formato de: 1 4.85 se necesita poder acceder a las posiciones 1 y 3 de la linea
     * ya que el espacio no nos sirve para nada, se guarda en un Array de String y con el método .split()
     * se divide el string, se llama a la posición 0 y 1 del array y también se realiza el pertinente casting y una
     * vez hecho todo esto se guardan en el HashMap y al finalizar se devuelve el HashMap.
     *
     * @return hm HashMap con todos los datos de id y notas.
     * @throws FileNotFoundException En caso de no encontrar el archivo.
     * @throws IOException Puede lanzar un error de entrada/salida.
     */
    public HashMap<Integer, Double> leer() throws IOException{
        HashMap<Integer, Double> hm = new HashMap<>();
        //Se comprueba que el archivo File existe, si no lanza una excepción.
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea = null;
            String[] a;
            int key = 0;
            double nota = 0;
            while ((linea = br.readLine()) != null) {
                a = linea.split(" ");
                key = Integer.parseInt(a[0]);
                nota = Double.parseDouble(a[1]);
                    hm.put(key, nota);
            }
        }catch (FileNotFoundException e){
            System.out.println("Error el archivo no ha sido encontrado.");
                System.exit(12);
        }
        return hm;
    }


}
