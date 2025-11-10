package utils;

/**
 * MenuUtils es una clase de utilidades para mostrar el menú del programa
 * y mensajes de información de manera estilizada en consola.
 * Usa íconos, colores y líneas divisorias para mejorar la lectura.
 */
public class MenuUtils {

    public static final String ICONO_PC = "💻";
    public static final String ICONO_BANDERA_AR = "🇦🇷";
    public static final String ICONO_MATE = "🧉";
    public static final String ICONO_ESTADISTICA = "📊";
    public static final String ICONO_CODIGOS = "💱";
    public static final String ICONO_CONVERSION = "🧮";
    public static final String ICONO_SALIENDO = "👋";
    public static final String ICONO_ERROR = "❌";

    /**
     * Muestra el encabezado principal del programa con líneas divisorias
     * y llamada a la información del desarrollador.
     */
    public static void showMainMenu() {
        showDoubleLine();
        System.out.print("|");
        System.out.print(ConsoleUtils.format("                Conversor de Monedas Alura-ONE                  ", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));
        System.out.println("| ");
        showDoubleLine();
        showDeveloper();
    }

    public static void showDeveloper() {
        System.out.println("|         Developed " + ICONO_PC +
                " by a P4154N0 from " + ICONO_BANDERA_AR +
                " who takes " + ICONO_MATE + "         |");
        showDoubleLine();
    }

    /**
     * Muestra las opciones principales del menú, con colores y estilos en consola.
     * Se imprimen 4 opciones: conversiones rápidas, códigos de monedas, conversión personalizada y salir.
     */
    public static void showOptionsMainMenu() {

        System.out.print("| ");
        System.out.println(ConsoleUtils.format(
                ICONO_ESTADISTICA + " 1 - Muestra rápida de 6 conversiones",
                ConsoleUtils.CYAN, ConsoleUtils.BOLD, ""));

        System.out.print("| ");
        System.out.println(ConsoleUtils.format(
                ICONO_CODIGOS + " 2 - Ver Códigos de cambios disponibles",
                ConsoleUtils.YELLOW, ConsoleUtils.BOLD, ""));

        System.out.print("| ");
        System.out.println(ConsoleUtils.format(
                ICONO_CONVERSION + " 3 - Realizar cambio personalizado",
                ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));

        System.out.print("| ");
        System.out.println(ConsoleUtils.format(
                ICONO_SALIENDO + " 4 - Salir",
                ConsoleUtils.RED, ConsoleUtils.BOLD, ""));

        showDoubleLine();
    }

    /**
     * Muestra una línea doble para dividir secciones del menú.
     */
    public static void showDoubleLine() {
        System.out.println("|================================================================|");
    }

    /**
     * Muestra una línea simple para dividir secciones internas, menos destacadas.
     */
    public static void showSingleLine() {
        System.out.println("|----------------------------------------------------------------|");
    }
}