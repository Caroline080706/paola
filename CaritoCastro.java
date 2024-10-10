
package caritocastro;


public class CaritoCastro {

    /**
     * @param args the command line arguments
     */
    
    }
    
   import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Pelicula {
    private String titulo;
    private int duracion;
    private int edadMinima;
    private String director;

    public Pelicula(String titulo, int duracion, int edadMinima, String director) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.edadMinima = edadMinima;
        this.director = director;
    }

    public int getEdadMinima() {
        return edadMinima;
    }
}

class Espectador {
    private String nombre;
    private int edad;
    private double dinero;

    public Espectador(String nombre, int edad, double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getDinero() {
        return dinero;
    }
}

class Asiento {
    private String identificador;
    private boolean ocupado;
    private Espectador espectador;

    public Asiento(String identificador) {
        this.identificador = identificador;
        this.ocupado = false;
        this.espectador = null;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void asignarEspectador(Espectador espectador) {
        this.ocupado = true;
        this.espectador = espectador;
    }

    @Override
    public String toString() {
        return "Asiento " + identificador + " " + (ocupado ? "Ocupado" : "Libre");
    }

    public String getIdentificador() {
        return identificador;
    }
}

class Cine {
    private Pelicula pelicula;
    private double precioEntrada;
    private Asiento[][] asientos;
    private String[][] identificadoresAsientos = {
        {"8A", "8B", "8C", "8D", "8E", "8F", "8G", "8H", "8I"},
        {"7A", "7B", "7C", "7D", "7E", "7F", "7G", "7H", "7I"},
        {"6A", "6B", "6C", "6D", "6E", "6F", "6G", "6H", "6I"},
        {"5A", "5B", "5C", "5D", "5E", "5F", "5G", "5H", "5I"},
        {"4A", "4B", "4C", "4D", "4E", "4F", "4G", "4H", "4I"},
        {"3A", "3B", "3C", "3D", "3E", "3F", "3G", "3H", "3I"},
        {"2A", "2B", "2C", "2D", "2E", "2F", "2G", "2H", "2I"},
        {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I"}
    };

    public Cine(Pelicula pelicula, double precioEntrada) {
        this.pelicula = pelicula;
        this.precioEntrada = precioEntrada;
        this.asientos = new Asiento[8][9];
        crearAsientos();
    }

    private void crearAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                asientos[i][j] = new Asiento(identificadoresAsientos[i][j]);
            }
        }
    }

    public void mostrarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                System.out.print(asientos[i][j].getIdentificador() + (asientos[i][j].estaOcupado() ? "(X)" : "(O)") + " ");
            }
            System.out.println();
        }
    }

    public boolean asignarAsiento(Espectador espectador) {
        if (espectador.getEdad() < pelicula.getEdadMinima()) {
            System.out.println(espectador.getNombre() + " no tiene la edad suficiente para ver la película.");
            return false;
        }
        if (espectador.getDinero() < precioEntrada) {
            System.out.println(espectador.getNombre() + " no tiene suficiente dinero para la entrada.");
            return false;
        }

        ArrayList<Asiento> asientosLibres = new ArrayList<>();
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                if (!asientos[i][j].estaOcupado()) {
                    asientosLibres.add(asientos[i][j]);
                }
            }
        }

        if (asientosLibres.isEmpty()) {
            System.out.println("No hay asientos disponibles.");
            return false;
        }

        Random rand = new Random();
        Asiento asientoElegido = asientosLibres.get(rand.nextInt(asientosLibres.size()));
        asientoElegido.asignarEspectador(espectador);
        System.out.println(espectador.getNombre() + " ha sido asignado al asiento " + asientoElegido.getIdentificador() + ".");
        return true;
    }
}

public class CineSimulacion {
    public static void main(String[] args) {
        Pelicula pelicula = new Pelicula("Matrix", 120, 16, "Wachowski");
        Cine cine = new Cine(pelicula, 10.0);

        ArrayList<Espectador> espectadores = new ArrayList<>();
        espectadores.add(new Espectador("Juan", 20, 15));
        espectadores.add(new Espectador("Ana", 16, 8));
        espectadores.add(new Espectador("Pedro", 17, 12));
        espectadores.add(new Espectador("Laura", 14, 20));
        espectadores.add(new Espectador("Miguel", 22, 30));

        Scanner scanner = new Scanner(System.in);
        boolean asignando = true;

        while (asignando && !espectadores.isEmpty()) {
            Espectador espectador = espectadores.remove(0);
            cine.asignarAsiento(espectador);
            cine.mostrarAsientos();

            System.out.print("¿Asignar otro asiento? (s/n): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("s")) {
                asignando = false;
            }
        }

        System.out.println("Fin de la simulación.");
        scanner.close();
    }
}

}
