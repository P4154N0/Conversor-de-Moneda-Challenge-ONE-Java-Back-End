import model.ExchangeRateResponse;
import utils.ConsoleUtils;
import utils.JsonFileWriter;
import utils.MenuUtils;
import model.SupportedCodesResponse;
import service.ExchangeRateService;
import utils.ExchangeRateUtils;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        // Scanner para leer la entrada del usuario
        Scanner sc = new Scanner(System.in);

        // Crear instancia del servicio de ExchangeRate (para consultar la API)
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        // Crear instancia del menú (para mostrar opciones y líneas divisorias)
        MenuUtils menu = new MenuUtils();

        boolean continuar = true; // Controla el bucle principal del programa

        while (continuar) {
            // Mostrar el menú principal
            menu.showMainMenu();

            // Mostrar las opciones del menú
            menu.showOptionsMainMenu();
            System.out.print("| Opcion: ");
            int option = sc.nextInt(); // Leer opción del usuario

            // Línea divisoria para separar secciones
            menu.showSingleLine();

            switch (option) {
                case 1:
                    // Caso 1: mostrar 6 conversiones rápidas
                    int numero = 1; // Contador de conversiones mostradas
                    System.out.println("| " + menu.ICONO_ESTADISTICA + " Mostrando 6 conversiones rápidas...");

                    try {
                        String base = "USD"; // Moneda base fija
                        String[] targets = {"ARS", "BRL", "CAD", "CHF", "CLP", "EUR"}; // Monedas destino

                        // Iterar sobre cada moneda destino y mostrar la conversión
                        for (String target : targets) {
                            menu.showSingleLine(); // Separador entre conversiones

                            // Mostrar número de conversión en color azul y negrita
                            System.out.print("| ");
                            System.out.println(ConsoleUtils.format("ℹ️ Conversión " + numero, ConsoleUtils.BLUE, ConsoleUtils.BOLD, ""));

                            // Llamar al método auxiliar para mostrar la conversión
                            mostrarConversion(exchangeRateService, base, target);

                            numero++;
                        }

                    } catch (RuntimeException e) {
                        // Manejo de errores si falla la consulta a la API
                        System.out.println("| " + menu.ICONO_ERROR + " Error al realizar las conversiones rápidas: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Caso 2: mostrar todos los códigos de monedas soportadas
                    try {
                        System.out.println("| " + menu.ICONO_CODIGOS + " Mostrando lista de códigos disponibles...");
                        menu.showSingleLine();

                        // Crear objeto del servicio (podría reutilizar exchangeRateService)
                        ExchangeRateService service = new ExchangeRateService();

                        // Llamar al método getSupportedCodes() para obtener todos los códigos de monedas
                        // Esto genera la URL, hace la solicitud HTTP GET y convierte el JSON en un objeto SupportedCodesResponse
                        SupportedCodesResponse response = service.getSupportedCodes();

                        // Mostrar la lista de códigos con banderas
                        ExchangeRateUtils.mostrarCodigosConBanderas(response);

                    } catch (RuntimeException e) {
                        // Mostrar error si no se pudo obtener la lista
                        System.out.println("| " + menu.ICONO_ERROR + "Error al obtener la lista de códigos de las monedas: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Caso 3: conversión personalizada con archivo JSON
                    System.out.println("| " + menu.ICONO_CONVERSION + " Realizando conversión personalizada...");
                    menu.showSingleLine();

                    // Pedir al usuario la moneda base
                    System.out.print("| Ingrese la moneda base (por ejemplo USD): ");
                    String base = sc.next().toUpperCase();

                    // Pedir al usuario la moneda destino
                    System.out.print("| Ingrese la moneda destino (por ejemplo ARS): ");
                    String target = sc.next().toUpperCase();

                    menu.showSingleLine();

                    try {
                        // Mostrar la conversión usando el método auxiliar
                        mostrarConversion(exchangeRateService, base, target);

                        // Crear objeto JsonFileWriter
                        JsonFileWriter jsonWriter = new JsonFileWriter();

                        // Obtener la respuesta completa de la API
                        ExchangeRateResponse response = exchangeRateService.getExchangeRate(base, target);

                        // Guardar la conversión en un archivo JSON dentro de la carpeta "consultas"
                        // El archivo llevará fecha y hora en el nombre
                        jsonWriter.saveJSON(response, "consultas");

                        menu.showSingleLine();

                        // Solicitar monto a convertir
                        System.out.print("| Ingrese un monto en " + base + " para convertir a " + target + ": ");
                        double monto = sc.nextDouble();

                        menu.showSingleLine();

                        // Calcular conversión
                        double convertido = monto * response.conversion_rate();

                        // Mostrar resultado de la conversión
                        System.out.println("| Resultado de la conversión:");
                        System.out.printf("| %.2f %s equivalen a %.2f %s%n", monto, base, convertido, target);

                        System.out.print("| ");
                        System.out.println(ConsoleUtils.format("✅ Conversión realizada con éxito", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));

                    } catch (Exception e) {
                        // Manejo de errores si falla la consulta a la API o al guardar el archivo
                        System.out.println("| " + menu.ICONO_ERROR + " Error al consultar la API o guardar el archivo: " + e.getMessage());
                    }

                    menu.showSingleLine();
                    break;

                case 4:
                    // Caso 4: salir del programa
                    System.out.println("| " + menu.ICONO_SALIENDO + " Saliendo del programa...");
                    menu.showSingleLine();
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Método auxiliar estático para mostrar una conversión.
     * Recibe:
     *  - service: el objeto de ExchangeRateService para consultar la API
     *  - base: código de la moneda base
     *  - target: código de la moneda destino
     *
     * No lanza excepciones al exterior; los errores se manejan dentro del try/catch del main
     */
    private static void mostrarConversion(ExchangeRateService service, String base, String target) {
        // Llamada al servicio para obtener la tasa de cambio
        ExchangeRateResponse response = service.getExchangeRate(base, target);

        // Mostrar la información de la conversión
        System.out.println("| Resultado de la consulta:");
        System.out.println("| Moneda base: " + response.base_code());
        System.out.println("| Moneda destino: " + response.target_code());
        System.out.println("| Tasa de conversión: " + response.conversion_rate());
        System.out.println("| ( 1 " + response.base_code() + " = " + response.conversion_rate() + " " + response.target_code() + " )");

        // Mensaje de éxito en verde y negrita
        System.out.print("| ");
        System.out.println(ConsoleUtils.format("✅ Conversión realizada con éxito", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));
    }
}