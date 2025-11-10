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

    private static final String API_KEY = "your_api_key";

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Consulta la API para obtener la tasa de cambio completa entre dos monedas.
     *
     * @param base   Código de la moneda base (ej: "USD")
     * @param target Código de la moneda destino (ej: "ARS")
     * @return ExchangeRateResponse con todos los datos devueltos por la API
     */
    public ExchangeRateResponse getExchangeRate(String base, String target) {

        String urlStr = BASE_URL + API_KEY + "/pair/" + base + "/" + target;
        URI uri = URI.create(urlStr);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), ExchangeRateResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("| No se pudo obtener la conversión.\nError: " + e.getMessage());
        }
    }

    /**
     * Consulta la API para obtener la lista de códigos de monedas soportadas.
     *
     * @return SupportedCodesResponse con los códigos de monedas
     */
    public SupportedCodesResponse getSupportedCodes() {

        String urlStr = BASE_URL + API_KEY + "/codes";
        URI uri = URI.create(urlStr);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), SupportedCodesResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("| No se pudieron obtener los códigos de moneda.\nError: " + e.getMessage());
        }
    }
}