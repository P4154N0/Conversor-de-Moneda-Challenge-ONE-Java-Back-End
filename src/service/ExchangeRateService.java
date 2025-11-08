package service;

import com.google.gson.Gson;
import model.ExchangeRateResponse;
import model.SupportedCodesResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * ExchangeRateService es la clase encargada de comunicarse con la API de ExchangeRate.
 * Permite:
 * 1. Obtener la tasa de cambio entre dos monedas.
 * 2. Obtener la lista de códigos de monedas soportadas.
 */
public class ExchangeRateService {

    // 🔑 Clave de la API (cada usuario debería usar su propia API Key)
    private static final String API_KEY = "00afafd404fdfa4f00ed432b";

    // URL base de la API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Consulta la API para obtener la tasa de cambio completa entre dos monedas.
     *
     * @param base   Código de la moneda base (ej: "USD")
     * @param target Código de la moneda destino (ej: "ARS")
     * @return ExchangeRateResponse con todos los datos devueltos por la API
     */
    public ExchangeRateResponse getExchangeRate(String base, String target) {

        // Construye la URL final, por ejemplo:
        // https://v6.exchangerate-api.com/v6/API_KEY/pair/USD/ARS
        String urlStr = BASE_URL + API_KEY + "/pair/" + base + "/" + target;
        URI uri = URI.create(urlStr);

        // Crear un cliente HTTP para enviar la solicitud
        HttpClient client = HttpClient.newHttpClient();

        // Crear una solicitud GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        // Intentar enviar la solicitud y convertir la respuesta JSON a un objeto Java
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertir el JSON a ExchangeRateResponse usando Gson
            return new Gson().fromJson(response.body(), ExchangeRateResponse.class);

        } catch (Exception e) {
            // Si algo falla, lanzar RuntimeException con mensaje de error
            throw new RuntimeException("| No se pudo obtener la conversión.\nError: " + e.getMessage());
        }
    }

    /**
     * Consulta la API para obtener la lista de códigos de monedas soportadas.
     *
     * @return SupportedCodesResponse con los códigos de monedas
     */
    public SupportedCodesResponse getSupportedCodes() {

        // Construye la URL para obtener los códigos:
        // https://v6.exchangerate-api.com/v6/API_KEY/codes
        String urlStr = BASE_URL + API_KEY + "/codes";
        URI uri = URI.create(urlStr);

        // Crear cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear solicitud GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        // Enviar solicitud y convertir respuesta JSON a objeto Java
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), SupportedCodesResponse.class);

        } catch (Exception e) {
            // Si falla, lanzar excepción con mensaje
            throw new RuntimeException("| No se pudieron obtener los códigos de moneda.\nError: " + e.getMessage());
        }
    }
}