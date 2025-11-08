package model;

public class Menu {

    public static final String ICONO_PC = "💻";
    public static final String ICONO_BANDERA_AR = "🇦🇷";
    public static final String ICONO_MATE = "🧉";
    public static final String ICONO_ESTADISTICA = "📊";
    public static final String ICONO_CODIGOS = "💱";
    public static final String ICONO_CONVERSION = "🧮";
    public static final String ICONO_SALIENDO = "👋";
    public static final String ICONO_ERROR = "❌";

    public static void showMainMenu() {
        showDoubleLine();
        System.out.println("|                Conversor de Monedas Alura-ONE                  |");
        showDoubleLine();
        showDeveloper();
    }

    public static void showDeveloper() {
        System.out.println("|         Developed " + ICONO_PC +
                " by a P4154N0 from " + ICONO_BANDERA_AR +
                " who takes " + ICONO_MATE + "         |");
        showDoubleLine();
    }

    public static void showOptionsMainMenu() {
        String menuOptions = String.format("""
                | 1 - %s Muestra rápida de 6 conversiones.
                | 2 - %s Ver Códigos de cambios disponibles.
                | 3 - %s Realizar cambio personalizado.
                | 4 - %s Salir.""",
                ICONO_ESTADISTICA,
                ICONO_CODIGOS,
                ICONO_CONVERSION,
                ICONO_SALIENDO
        );

        System.out.println(menuOptions);
        showDoubleLine();
    }

    public static void showDoubleLine() {
        System.out.println("|================================================================|");

    }
    public static void showSingleLine() {
        System.out.println("|----------------------------------------------------------------|");
    }
}
