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

        Scanner sc = new Scanner(System.in);

        ExchangeRateService exchangeRateService = new ExchangeRateService();

        MenuUtils menu = new MenuUtils();

        boolean continuar = true;

        while (continuar) {

            menu.showMainMenu();

            menu.showOptionsMainMenu();
            System.out.print("| Opcion: ");
            int option = sc.nextInt();

            menu.showSingleLine();

            switch (option) {
                case 1:
                    int numero = 1;
                    System.out.println("| " + menu.ICONO_ESTADISTICA + " Mostrando 6 conversiones rápidas...");

                    try {
                        String base = "USD";
                        String[] targets = {"ARS", "BRL", "CAD", "CHF", "CLP", "EUR"};

                        for (String target : targets) {
                            menu.showSingleLine();

                            System.out.print("| ");
                            System.out.println(ConsoleUtils.format("ℹ️ Conversión " + numero, ConsoleUtils.BLUE, ConsoleUtils.BOLD, ""));
                            mostrarConversion(exchangeRateService, base, target);
                            numero++;
                        }

                    } catch (RuntimeException e) {
                        System.out.println("| " + menu.ICONO_ERROR + " Error al realizar las conversiones rápidas: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("| " + menu.ICONO_CODIGOS + " Mostrando lista de códigos disponibles...");
                        menu.showSingleLine();

                        ExchangeRateService service = new ExchangeRateService();
                        SupportedCodesResponse response = service.getSupportedCodes();
                        ExchangeRateUtils.mostrarCodigosConBanderas(response);

                    } catch (RuntimeException e) {
                        System.out.println("| " + menu.ICONO_ERROR + "Error al obtener la lista de códigos de las monedas: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("| " + menu.ICONO_CONVERSION + " Realizando conversión personalizada...");
                    menu.showSingleLine();

                    System.out.print("| Ingrese la moneda base (por ejemplo USD): ");
                    String base = sc.next().toUpperCase();

                    System.out.print("| Ingrese la moneda destino (por ejemplo ARS): ");
                    String target = sc.next().toUpperCase();

                    menu.showSingleLine();

                    try {
                        mostrarConversion(exchangeRateService, base, target);

                        JsonFileWriter jsonWriter = new JsonFileWriter();
                        ExchangeRateResponse response = exchangeRateService.getExchangeRate(base, target);
                        jsonWriter.saveJSON(response, "consultas");

                        menu.showSingleLine();

                        System.out.print("| Ingrese un monto en " + base + " para convertir a " + target + ": ");
                        double monto = sc.nextDouble();

                        menu.showSingleLine();

                        double convertido = monto * response.conversion_rate();

                        System.out.println("| Resultado de la conversión:");
                        System.out.printf("| %.2f %s equivalen a %.2f %s%n", monto, base, convertido, target);

                        System.out.print("| ");
                        System.out.println(ConsoleUtils.format("✅ Conversión realizada con éxito", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));

                    } catch (Exception e) {
                        System.out.println("| " + menu.ICONO_ERROR + " Error al consultar la API o guardar el archivo: " + e.getMessage());
                    }

                    menu.showSingleLine();
                    break;

                case 4:
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
        ExchangeRateResponse response = service.getExchangeRate(base, target);

        System.out.println("| Resultado de la consulta:");
        System.out.println("| Moneda base: " + response.base_code());
        System.out.println("| Moneda destino: " + response.target_code());
        System.out.println("| Tasa de conversión: " + response.conversion_rate());
        System.out.println("| ( 1 " + response.base_code() + " = " + response.conversion_rate() + " " + response.target_code() + " )");

        System.out.print("| ");
        System.out.println(ConsoleUtils.format("✅ Conversión realizada con éxito", ConsoleUtils.GREEN, ConsoleUtils.BOLD, ""));
    }
}