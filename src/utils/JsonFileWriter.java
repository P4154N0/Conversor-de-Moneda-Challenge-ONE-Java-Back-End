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

        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
        String timestamp = LocalDateTime.now().format(formatter);

        String fileName = folder + File.separator + timestamp + "_"
                + exchangeRateResponse.base_code() + "_to_"
                + exchangeRateResponse.target_code() + ".json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(gson.toJson(exchangeRateResponse));
        }
    }
}