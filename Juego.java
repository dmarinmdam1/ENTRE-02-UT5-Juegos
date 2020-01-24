
/**
 * Un objeto de esta clase guarda información relativa a un juego
 * 
 * @author - Daniel Marín Martos
 */

public class Juego
{
    private static final String SEPARADOR = ":";
    
    private String titulo;
    private Genero genero;
    private int year;
    private int[] valoraciones;

    /**
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en linea
     */
    
    public Juego(String linea)
    {
        String[] lineaSeparada = linea.split(SEPARADOR);
        
        for(int i = 0 ; i < lineaSeparada.length ; i++)
        {
            lineaSeparada[i] = lineaSeparada[i].trim();
        }
        
        lineaSeparada[1] = lineaSeparada[1].toUpperCase();

        titulo = lineaSeparada[0].toUpperCase();
        genero = Genero.valueOf(lineaSeparada[1]);
        year = Integer.parseInt(lineaSeparada[2]);

        valoraciones = new int[10];
        
        for(int i = 0 ; i < valoraciones.length ; i++)
        {
            valoraciones[i] = Integer.parseInt(lineaSeparada[i + 3]);
        }
    }

    /**
     * accesor título
     */
    
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * accesor género
     */
    
    public Genero getGenero()
    {
        return genero;
    }

    /**
     * accesor year
     */
    
    public int getYear()
    {
        return year;
    }

    /**
     * accesor valoraciones
     */
    
    public int[] getValoraciones()
    {
        return valoraciones;
    }

    /**
     * total votos emitidos
     */
    
    public int getVotos()
    {
        int votosTotales = 0;
        
        for(int i = 0 ; i < valoraciones.length ; i++)
        {
            votosTotales += valoraciones[i];
        }
        
        return votosTotales;
    }

    /**
     *  obtener valoración media
     */
    
    public double getValoracionMedia()
    {
        double sumaValoraciones = 0;
        
        for(int i = 0 ; i < valoraciones.length ; i++)
        {
            sumaValoraciones += (i + 1) * valoraciones[i];
        }
        
        return sumaValoraciones / getVotos();
    }

    /**
     *  Un usuario puntúa el juego con un valor entre 1 y 10 
     */
    
    public void puntuar(int puntuacion)
    {
        valoraciones[puntuacion-1]++;
    }

    /**
     * Representación textual del juego 
     * (Ver enunciado)
     */
    
    public String toString()
    {
        return titulo + "\nGénero: " + this.genero +
        "| Lanzamiento: " + this.year +
        "\nValoración (" + getVotos() + " votos): "
        + String.format("%.2f", this.getValoracionMedia());
    }

    public static void main(String[] args)
    {
        Juego juego1 = new Juego("Steep: deporte: 2016  : 0:0:0:0: 0: 0:0:0:12:  10");
        
        System.out.println(juego1.toString());

        Juego juego2 = new Juego("For the King: estrategia: 2018  : 0:0:0:7: 12: 0:33:13:2: 0");
        
        System.out.println(juego2.toString());
    }
}

