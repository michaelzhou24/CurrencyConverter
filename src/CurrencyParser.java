import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyParser {
    private String date;
    private long timeStamp;
    private Map<String, Double> currencies;

    public CurrencyParser() {
        currencies = new HashMap<String, Double>();
        timeStamp = 0;
        date = "";
        parse();
    }

    private void parse() {
        try {
            URL fromAPI = new URL("http://data.fixer.io/api/latest?access_key="+APIToken.API_KEY); // URL to Parse
            URLConnection yc = fromAPI.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                Object obj = new JSONParser().parse(inputLine);
                JSONObject jsonFile = (JSONObject) obj;

                date = (String) jsonFile.get("date");
                timeStamp = ((Number) jsonFile.get("timestamp")).longValue();
                JSONObject currencyList = (JSONObject) jsonFile.get("rates");

                for (Object o : currencyList.keySet()) {
                    String country = (String) o;
                    Number rate = (Number) currencyList.get(country);
                    currencies.put(country, rate.doubleValue());
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Failed to parse file!");
            e.printStackTrace();
        }
    }

    public double getRate(String toConvert, String base) {
        return currencies.get(base) / currencies.get(toConvert);
    }

    public String getDate() {
        return date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

}