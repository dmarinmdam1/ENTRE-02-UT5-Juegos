
import java.util.Arrays;

/**
 * Punto de entrada a la aplicación
 * 
 * @author - Daniel Marín Martos
 */

public class AppRevistaJuegosOnLine 
{
    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Error en argumentos\nSintaxis: java AppRevistaJuegosOnLine <nombre> <n>");
        }
        else
        {
            /* ------- */
            
            RevistaOnLineJuegos revistaOnLineJuegos = new RevistaOnLineJuegos(args[0], Integer.parseInt(args[1]));
            
            revistaOnLineJuegos.leerDeFichero();
            
            System.out.println(revistaOnLineJuegos.toString());
            
            /* ------- */
            
            System.out.println("***************************************");
            
            System.out.println("Puntuando...");
            
            revistaOnLineJuegos.puntuar("Planet Zoo", 8);
            revistaOnLineJuegos.puntuar("Steep", 7);
            revistaOnLineJuegos.puntuar("Catastronauts", 9);
            revistaOnLineJuegos.puntuar("Wattam", 9);
            
            System.out.println("Después de puntuar la revista queda");
            System.out.println(revistaOnLineJuegos.toString());
            
            System.out.println("\n\n***************************************");
            
            System.out.println("Juegos con valoración media > 8.2");
            System.out.println(Arrays.toString(revistaOnLineJuegos.valoracionMayorQue(8.2)));
            
            System.out.println("***************************************");
            
            System.out.println("Borrando juegos de género ROL...");
            System.out.println("Borrados " + revistaOnLineJuegos.borrarDeGenero(Genero.ROL) + " juegos");
            
            System.out.println(revistaOnLineJuegos.toString());
            
            /* ------- */
        }
    }
}

