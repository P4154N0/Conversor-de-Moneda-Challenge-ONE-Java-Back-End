package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ExchangeRateResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JsonFileWriter se encarga de guardar objetos ExchangeRateResponse
 * en archivos JSON, dentro de una carpeta específica, con un nombre que
 * incluye la fecha, hora y monedas involucradas.
 */
public class JsonFileWriter {

    /**
     * Guarda un objeto ExchangeRateResponse en un archivo JSON.
     *
     * @param exchangeRateResponse Objeto de la conversión (tasa de cambio)
     * @param folder Carpeta donde se guardará el archivo (por ejemplo "consultas")
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public void saveJSON(ExchangeRateResponse exchangeRateResponse, String folder) throws IOException {

        // 1️⃣ Crear la carpeta si no existe
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs(); // crea la carpeta incluyendo cualquier subcarpeta necesaria
        }

        // 2️⃣ Formato de fecha y hora para usar en el nombre del archivo
        //    Ej: 2025-11-08_14_35_20
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
        String timestamp = LocalDateTime.now().format(formatter);

        // 3️⃣ Construir el nombre completo del archivo
        //    Ej: "consultas/2025-11-08_14_35_20_USD_to_ARS.json"
        String fileName = folder + File.separator + timestamp + "_"
                + exchangeRateResponse.base_code() + "_to_"
                + exchangeRateResponse.target_code() + ".json";

        // 4️⃣ Crear un objeto Gson con formato bonito (Pretty Printing)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // 5️⃣ Escribir el JSON en el archivo
        //    try-with-resources asegura que FileWriter se cierre automáticamente
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            // Convertimos exchangeRateResponse a JSON y lo escribimos
            fileWriter.write(gson.toJson(exchangeRateResponse));
        }

        // ✅ Resultado: un archivo JSON dentro de la carpeta "folder",
        //    con nombre con fecha, hora y monedas, por ejemplo:
        //    consultas/2025-11-08_14_35_20_USD_to_ARS.json
    }
}