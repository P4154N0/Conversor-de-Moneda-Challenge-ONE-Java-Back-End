package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ExchangeRateResponse;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
    public void saveJSON(ExchangeRateResponse exchangeRateResponse) throws IOException {

        // Esta línea se encarga de un objeto ExchangeRateResponse transformarlo en un objeto Json.

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter fileWriter = new FileWriter(exchangeRateResponse.base_code()+"_to_"+exchangeRateResponse.target_code()+".json");
        fileWriter.write(gson.toJson(exchangeRateResponse));
        fileWriter.close();

    }
}
