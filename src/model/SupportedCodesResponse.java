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
        String result,
        String documentation,
        String terms_of_use,
        List<List<String>> supported_codes
) {}