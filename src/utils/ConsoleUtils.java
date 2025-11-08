package utils;

/**
 * ConsoleUtils proporciona constantes y métodos para mostrar texto en la consola
 * con colores, estilos (negrita, subrayado) y fondos usando códigos ANSI.
 *
 * Java por sí mismo no tiene soporte “oficial” para colores en consola,
 * así que usamos códigos ANSI que la terminal interpreta.
 */
public class ConsoleUtils {

    // RESET → reinicia los estilos y colores al valor por defecto
    public static final String RESET = "\u001B[0m";

    // -------------------- COLORES DE TEXTO --------------------
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // -------------------- FONDOS --------------------
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";

    // -------------------- ESTILOS --------------------
    public static final String BOLD = "\u001B[1m";       // negrita
    public static final String UNDERLINE = "\u001B[4m";  // subrayado

    /**
     * Formatea un texto aplicando color, estilo y fondo.
     *
     * @param text Texto a mostrar
     * @param color Color de texto (ej: RED, GREEN)
     * @param style Estilo (ej: BOLD, UNDERLINE)
     * @param background Fondo (ej: BG_RED, BG_BLUE)
     * @return Texto listo para imprimir en consola con estilos aplicados
     *
     * Ejemplo de uso:
     * System.out.println(ConsoleUtils.format("✅ Éxito", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));
     */
    public static String format(String text, String color, String style, String background) {
        return style + color + background + text + RESET;
    }

    // -------------------- COMENTARIOS EXPLICATIVOS --------------------
    // Los códigos ANSI funcionan así:
    // \u001B → caracter de escape
    // [<código>m → indica el color o estilo
    //
    // COLORES BÁSICOS:
    // Negro 30, Rojo 31, Verde 32, Amarillo 33, Azul 34, Magenta 35, Cyan 36, Blanco 37
    //
    // FONDOS BÁSICOS:
    // Negro 40, Rojo 41, Verde 42, Amarillo 43, Azul 44, Magenta 45, Cyan 46, Blanco 47
    //
    // ESTILOS:
    // Normal 0, Negrita 1, Subrayado 4
    //
    // NOTA:
    // Siempre que termines un texto con RESET (\u001B[0m) aseguras que el
    // texto siguiente en la consola no herede los estilos anteriores.
}