package model;

/**
 * ExchangeRateResponse es un record que representa la respuesta de la API de tasas de cambio.
 * Un "record" en Java automáticamente genera:
 *  - un constructor con todos los parámetros
 *  - getters para cada campo
 *  - equals, hashCode y toString
 */
public record ExchangeRateResponse(
        String result,                    // Resultado general de la consulta (por ejemplo "success")
        String documentation,             // URL o referencia a la documentación de la API
        String terms_of_use,              // URL o texto con los términos de uso de la API
        long time_last_update_unix,       // Última actualización en formato Unix timestamp
        String time_last_update_utc,      // Última actualización en formato UTC legible
        long time_next_update_unix,       // Próxima actualización en formato Unix timestamp
        String time_next_update_utc,      // Próxima actualización en formato UTC legible
        String base_code,                 // Código de la moneda base (por ejemplo "USD")
        String target_code,               // Código de la moneda destino (por ejemplo "ARS")
        double conversion_rate            // Tasa de conversión actual
) {
    /**
     * Método resumido que devuelve un String legible de la conversión.
     * Por ejemplo: "1 USD = 350.1234 ARS (actualizado: Fri, 08 Nov 2025 00:00:00 UTC)"
     *
     * @return String resumen de la conversión
     */
    public String resumen() {
        return String.format(
                "1 %s = %.4f %s (actualizado: %s)",
                base_code,          // moneda base
                conversion_rate,    // tasa de conversión
                target_code,        // moneda destino
                time_last_update_utc // fecha de última actualización legible
        );
    }
}