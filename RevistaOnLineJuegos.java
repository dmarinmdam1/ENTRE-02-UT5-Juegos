
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * La clase representa a una tienda on-line en la
 * que se publican los juegos que se van lanzando al mercado
 * 
 * Un objeto de esta clase guarda en un array los juegos 
 *
 * @author - Daniel Marín Martos
 */

public class RevistaOnLineJuegos 
{
    private String nombre;
    private Juego[] juegos;
    private int total;

    /**
     * Constructor  
     * Crea el array de juegos al tamaño máximo indicado por la constante
     * e inicializa el resto de atributos
     */
    
    public RevistaOnLineJuegos(String nombre, int n)
    {
        this.nombre = nombre.toUpperCase();
        
        juegos = new Juego[n];
        
        // total = 0; // -> innecesario ya que se inicializa automáticamente a 0
    }

    /**
     * Devuelve true si el array está completo, false en otro caso
     */
    
    public boolean estaCompleta()
    {
        return total == juegos.length;
    }
    
    /**
     * Efectúa una búsqueda en el array del juego cuyo titulo se
     * recibe como parámetro. Es ndiferente mayúsculas y minúsculas
     * Si existe el juego devuelve su posición, si no existe devuelve -1
     */
    
    public int existeJuego(String titulo)
    {
        for(int i = 0 ; i < total ; i++)
        {
            if(titulo.equalsIgnoreCase(juegos[i].getTitulo()))
            {
                return i;
            }
        }
        
        return -1;
    }
    
    /** ------- MÉTODO EXTRA AAA ------- **/

    /**
     * Calcula la posición para insertar un nuevo juego de forma ordenada
     */
    
    private int calcularPosicionDeInsercionOrdenada(String tituloJuego)
    {
        for(int i = 0 ; i < total ; i++)
        {
            if(juegos[i].getTitulo().compareTo(tituloJuego) > 0)
            {
                return i;
            }
        }
        
        return total;
    }
    
    /** ------- ---------------- ------- **/

    /**
     *    Añade un nuevo juego solo si el array no está completo y no existe otro juego
     *    ya con el mismo nombre.  Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El juego se añade de tal forma que queda insertado en orden alfabético de título
     *    (de menor a mayor)
     *     !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     */
    
    public void add(Juego juego)
    {
        if(estaCompleta())
        {
            System.out.println("No se puede añadir el juego \"" + juego.getTitulo() + "\" porque la revista está completa.");
        }
        else if(existeJuego(juego.getTitulo()) >= 0)
        {
            System.out.println("Ya está publicado el juego " + juego.getTitulo() + " en la revista on-line");
        }
        else
        {
            int posicionDeInsercion = calcularPosicionDeInsercionOrdenada(juego.getTitulo());

            for(int i = total ; i > posicionDeInsercion ; i--)
            {
                juegos[i] = juegos[i - 1];
            }
            
            juegos[posicionDeInsercion] = juego;
            
            total++;
        }
    }

    /**
     * Representación textual de la revista
     * Utiliza StringBuilder como clase de apoyo.
     * Se incluye el nombre de la  revista on-line.
     * (Ver resultados de ejecución)
     */
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nLos mejores juegos en nuestra revista ").append(nombre).append(" (").append(total).append(" juegos)\n");
        
        for(int i = 0 ; i < total ; i++)
        {
            sb.append("\n").append(juegos[i].toString()).append("\n--------------------");
        }
        
        return sb.toString();
    }

    /**
     *  Se puntúa el juego de título indicado con 
     *  la puntuación recibida como parámetro. 
     *  La puntuación es un valor entre 1 y 10 (asumimos esto como correcto)
     *  Si el juego no existe se muestra un mensaje en pantalla
     */
    
    public void puntuar(String titulo, int puntuacion)
    {        
        titulo = titulo.toUpperCase();
        
        int posicion = existeJuego(titulo);
        
        if(posicion == -1)
        {
            System.out.println("No existe el juego " + titulo + "\n");
        }
        else
        {
            juegos[posicion].puntuar(puntuacion);
        }
    }

    /**
     * Devuelve un array con los nombres de los juegos 
     * con una valoración media mayor a la indicada  
     * 
     * El array se devuelve todo en mayúsculas y ordenado ascendentemente
     */
    
    public String[] valoracionMayorQue(double valoracion)
    {
        String[] titulosValoracion = new String[total];
        
        int pos_titulosValoracion = 0;
        
        for(int i = 0 ; i < total ; i++)
        {
            if(juegos[i].getValoracionMedia() > valoracion)
            {
                titulosValoracion[pos_titulosValoracion] = juegos[i].getTitulo();
                pos_titulosValoracion++;
            }
        }
        
        return Arrays.copyOf(titulosValoracion, pos_titulosValoracion);
    }

    /**
     * Borrar los juegos del género indicado devolviendo
     * el nº de juegos borradas
     */
    
    public int borrarDeGenero(Genero genero)
    {
        int juegosBorrados = 0;
        
        for(int i = 0 ; i < total ; i++)
        {
            if(genero == juegos[i].getGenero())
            {
                for(int j = i ; j < total - 1 ; j++)
                {
                    juegos[j] = juegos[j + 1];
                }
                
                total--;
                               
                juegosBorrados++;
                
                i--;
            }
        }
        
        return juegosBorrados;
    }

    /**
     * Lee de un fichero de texto los datos de los juegos
     * con ayuda de un objeto de la  clase Scanner
     * y los guarda en el array. 
     */
    
    public void leerDeFichero()
    {
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File("juegos.txt"));

            while (sc.hasNextLine())
            {
                Juego juego = new Juego(sc.nextLine());
                this.add(juego);
            }

        }
        catch (IOException e)
        {
            System.out.println("Error al leer del fichero");
        }
        finally
        {
            sc.close();
        }
    }
}

