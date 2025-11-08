package model;

import java.util.List;

/**
 * SupportedCodesResponse representa la respuesta de la API cuando solicitamos
 * todos los códigos de monedas soportadas.
 *
 * Un "record" en Java genera automáticamente:
 *  - Constructor con todos los campos
 *  - Getters para cada campo (por ejemplo response.supported_codes())
 *  - equals, hashCode y toString
 */
public record SupportedCodesResponse(
        String result,                     // Resultado de la consulta (por ejemplo "success")
        String documentation,              // URL o referencia a la documentación de la API
        String terms_of_use,               // URL o texto con los términos de uso de la API
        List<List<String>> supported_codes // Lista de listas, cada sublista contiene:
        // [0] = código de moneda (por ejemplo "USD")
        // [1] = nombre de la moneda (por ejemplo "United States Dollar")
) {}