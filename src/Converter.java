public class Converter {
    private String base;
    private String toConvert;
    private double amount;

    public Converter(String base, String toConvert, double amount) {
        this.base = base;
        this.toConvert = toConvert;
        this.amount = amount;
        convert();
    }

    public double convert() {
       CurrencyParser parser = new CurrencyParser();
       double rate = 0;
       try {
           rate = parser.getRate(base, toConvert);
       } catch (Exception e) {
           System.out.println("Unable to find a matching currency!");
           System.exit(0);
       }
       return amount*rate;
    }
}
