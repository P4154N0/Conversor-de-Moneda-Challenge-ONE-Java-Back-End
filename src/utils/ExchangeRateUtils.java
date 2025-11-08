package utils;

import model.SupportedCodesResponse;
import java.util.List;
import java.util.Map;

/**
 * ExchangeRateUtils contiene utilidades relacionadas con las tasas de cambio,
 * en particular para mostrar códigos de monedas junto con su respectiva bandera.
 */
public class ExchangeRateUtils {

    /**
     * Mapa de códigos de moneda a sus banderas emoji correspondientes.
     * - Map.ofEntries nos permite crear un mapa inmutable de manera clara.
     * - Cada entrada es un par <CÓDIGO_MONEDA, BANDERA_EMOJI>.
     * - Ejemplo: "USD" → "🇺🇸", "ARS" → "🇦🇷"
     */
    public static final Map<String, String> FLAGS = Map.ofEntries(
            Map.entry("AED", "🇦🇪"),
            Map.entry("AFN", "🇦🇫"),
            Map.entry("ALL", "🇦🇱"),
            Map.entry("AMD", "🇦🇲"),
            Map.entry("ANG", "🇳🇱"),
            Map.entry("AOA", "🇦🇴"),
            Map.entry("ARS", "🇦🇷"),
            Map.entry("AUD", "🇦🇺"),
            Map.entry("AWG", "🇦🇼"),
            Map.entry("AZN", "🇦🇿"),
            Map.entry("BAM", "🇧🇦"),
            Map.entry("BBD", "🇧🇧"),
            Map.entry("BDT", "🇧🇩"),
            Map.entry("BGN", "🇧🇬"),
            Map.entry("BHD", "🇧🇭"),
            Map.entry("BIF", "🇧🇮"),
            Map.entry("BMD", "🇧🇲"),
            Map.entry("BND", "🇧🇳"),
            Map.entry("BOB", "🇧🇴"),
            Map.entry("BRL", "🇧🇷"),
            Map.entry("BSD", "🇧🇸"),
            Map.entry("BTN", "🇧🇹"),
            Map.entry("BWP", "🇧🇼"),
            Map.entry("BYN", "🇧🇾"),
            Map.entry("BZD", "🇧🇿"),
            Map.entry("CAD", "🇨🇦"),
            Map.entry("CDF", "🇨🇩"),
            Map.entry("CHF", "🇨🇭"),
            Map.entry("CLF", "🇨🇱"),
            Map.entry("CLP", "🇨🇱"),
            Map.entry("CNH", "🇨🇳"),
            Map.entry("CNY", "🇨🇳"),
            Map.entry("COP", "🇨🇴"),
            Map.entry("CRC", "🇨🇷"),
            Map.entry("CUP", "🇨🇺"),
            Map.entry("CVE", "🇨🇻"),
            Map.entry("CZK", "🇨🇿"),
            Map.entry("DJF", "🇩🇯"),
            Map.entry("DKK", "🇩🇰"),
            Map.entry("DOP", "🇩🇴"),
            Map.entry("DZD", "🇩🇿"),
            Map.entry("EGP", "🇪🇬"),
            Map.entry("ERN", "🇪🇷"),
            Map.entry("ETB", "🇪🇹"),
            Map.entry("EUR", "🇪🇺"),
            Map.entry("FJD", "🇫🇯"),
            Map.entry("FKP", "🇫🇰"),
            Map.entry("FOK", "🇫🇴"),
            Map.entry("GBP", "🇬🇧"),
            Map.entry("GEL", "🇬🇪"),
            Map.entry("GGP", "🇬🇬"),
            Map.entry("GHS", "🇬🇭"),
            Map.entry("GIP", "🇬🇮"),
            Map.entry("GMD", "🇬🇲"),
            Map.entry("GNF", "🇬🇳"),
            Map.entry("GTQ", "🇬🇹"),
            Map.entry("GYD", "🇬🇾"),
            Map.entry("HKD", "🇭🇰"),
            Map.entry("HNL", "🇭🇳"),
            Map.entry("HRK", "🇭🇷"),
            Map.entry("HTG", "🇭🇹"),
            Map.entry("HUF", "🇭🇺"),
            Map.entry("IDR", "🇮🇩"),
            Map.entry("ILS", "🇮🇱"),
            Map.entry("IMP", "🇮🇲"),
            Map.entry("INR", "🇮🇳"),
            Map.entry("IQD", "🇮🇶"),
            Map.entry("IRR", "🇮🇷"),
            Map.entry("ISK", "🇮🇸"),
            Map.entry("JEP", "🇯🇪"),
            Map.entry("JMD", "🇯🇲"),
            Map.entry("JOD", "🇯🇴"),
            Map.entry("JPY", "🇯🇵"),
            Map.entry("KES", "🇰🇪"),
            Map.entry("KGS", "🇰🇬"),
            Map.entry("KHR", "🇰🇭"),
            Map.entry("KID", "🇰🇮"),
            Map.entry("KMF", "🇰🇲"),
            Map.entry("KRW", "🇰🇷"),
            Map.entry("KWD", "🇰🇼"),
            Map.entry("KYD", "🇰🇾"),
            Map.entry("KZT", "🇰🇿"),
            Map.entry("LAK", "🇱🇦"),
            Map.entry("LBP", "🇱🇧"),
            Map.entry("LKR", "🇱🇰"),
            Map.entry("LRD", "🇱🇷"),
            Map.entry("LSL", "🇱🇸"),
            Map.entry("LYD", "🇱🇾"),
            Map.entry("MAD", "🇲🇦"),
            Map.entry("MDL", "🇲🇩"),
            Map.entry("MGA", "🇲🇬"),
            Map.entry("MKD", "🇲🇰"),
            Map.entry("MMK", "🇲🇲"),
            Map.entry("MNT", "🇲🇳"),
            Map.entry("MOP", "🇲🇴"),
            Map.entry("MRU", "🇲🇷"),
            Map.entry("MUR", "🇲🇺"),
            Map.entry("MVR", "🇲🇻"),
            Map.entry("MWK", "🇲🇼"),
            Map.entry("MXN", "🇲🇽"),
            Map.entry("MYR", "🇲🇾"),
            Map.entry("MZN", "🇲🇿"),
            Map.entry("NAD", "🇳🇦"),
            Map.entry("NGN", "🇳🇬"),
            Map.entry("NIO", "🇳🇮"),
            Map.entry("NOK", "🇳🇴"),
            Map.entry("NPR", "🇳🇵"),
            Map.entry("NZD", "🇳🇿"),
            Map.entry("OMR", "🇴🇲"),
            Map.entry("PAB", "🇵🇦"),
            Map.entry("PEN", "🇵🇪"),
            Map.entry("PGK", "🇵🇬"),
            Map.entry("PHP", "🇵🇭"),
            Map.entry("PKR", "🇵🇰"),
            Map.entry("PLN", "🇵🇱"),
            Map.entry("PYG", "🇵🇾"),
            Map.entry("QAR", "🇶🇦"),
            Map.entry("RON", "🇷🇴"),
            Map.entry("RSD", "🇷🇸"),
            Map.entry("RUB", "🇷🇺"),
            Map.entry("RWF", "🇷🇼"),
            Map.entry("SAR", "🇸🇦"),
            Map.entry("SBD", "🇸🇧"),
            Map.entry("SCR", "🇸🇨"),
            Map.entry("SDG", "🇸🇩"),
            Map.entry("SEK", "🇸🇪"),
            Map.entry("SGD", "🇸🇬"),
            Map.entry("SHP", "🇸🇭"),
            Map.entry("SLE", "🇸🇱"),
            Map.entry("SLL", "🇸🇱"),
            Map.entry("SOS", "🇸🇴"),
            Map.entry("SRD", "🇸🇷"),
            Map.entry("SSP", "🇸🇸"),
            Map.entry("STN", "🇸🇹"),
            Map.entry("SYP", "🇸🇾"),
            Map.entry("SZL", "🇸🇿"),
            Map.entry("THB", "🇹🇭"),
            Map.entry("TJS", "🇹🇯"),
            Map.entry("TMT", "🇹🇲"),
            Map.entry("TND", "🇹🇳"),
            Map.entry("TOP", "🇹🇴"),
            Map.entry("TRY", "🇹🇷"),
            Map.entry("TTD", "🇹🇹"),
            Map.entry("TVD", "🇹🇻"),
            Map.entry("TWD", "🇹🇼"),
            Map.entry("TZS", "🇹🇿"),
            Map.entry("UAH", "🇺🇦"),
            Map.entry("UGX", "🇺🇬"),
            Map.entry("USD", "🇺🇸"),
            Map.entry("UYU", "🇺🇾"),
            Map.entry("UZS", "🇺🇿"),
            Map.entry("VES", "🇻🇪"),
            Map.entry("VND", "🇻🇳"),
            Map.entry("VUV", "🇻🇺"),
            Map.entry("WST", "🇼🇸"),
            Map.entry("XAF", "🇨🇫"),
            Map.entry("XCD", "🇦🇬"),
            Map.entry("XOF", "🇧🇯"),
            Map.entry("XPF", "🇵🇫"),
            Map.entry("YER", "🇾🇪"),
            Map.entry("ZAR", "🇿🇦"),
            Map.entry("ZMW", "🇿🇲"),
            Map.entry("ZWL", "🇿🇼")
    );

    /**
     * Muestra en consola los códigos de moneda soportados junto con sus banderas.
     * @param response Objeto con la lista de códigos soportados, obtenido de la API
     */
    public static void mostrarCodigosConBanderas(SupportedCodesResponse response) {

        // Mensaje informativo inicial
        System.out.print("| ");
        System.out.println(ConsoleUtils.format("ℹ️ Obteniendo códigos de monedas...", ConsoleUtils.BLUE, ConsoleUtils.BOLD, ""));
        System.out.println("| Códigos soportados:");
        System.out.println("|----------------------------------------------------------------|");
        System.out.println("| Flag CODE - Country");

        // Obtener la lista de códigos de monedas de la respuesta
        List<List<String>> codes = response.supported_codes();

        // Recorrer cada par <código, nombre de moneda>
        for (List<String> codePair : codes) {
            String code = codePair.get(0);   // código de la moneda
            String name = codePair.get(1);   // nombre del país/moneda
            String bandera = FLAGS.getOrDefault(code, "🏳️");
            // Si no tenemos bandera para esa moneda, mostramos una bandera blanca como placeholder

            // Imprimir cada moneda con su bandera y nombre
            System.out.printf("| %s %s - %s%n", bandera, code, name);
        }
    }
}