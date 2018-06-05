import java.util.Scanner;

public class CurrencyConverter {
    private String base;
    private String toConvert;
    private double amount;
    public CurrencyConverter() {
        base = "";
        toConvert = "";
        amount = 0;
    }

    private void run() {
        CurrencyParser parser = new CurrencyParser();
        System.out.println("Welcome to Currency Converter!");
        System.out.println("Currency data last updated: "+parser.getDate());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the base currency (or quit to exit): ");
            base = scanner.nextLine();
            if (base.equals("quit"))
                break;
            System.out.print("Enter the amount to convert: ");
            try {
                amount = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Not a valid number value!");
            }
            scanner.nextLine();
            System.out.print("Enter the currency to convert to: ");
            toConvert = scanner.nextLine();
            System.out.println();
            Converter converter = new Converter(base, toConvert, amount);
            double newAmount = Math.round(converter.convert() * 100.0) / 100.0;
            System.out.println("You get "+newAmount+" "+toConvert+".");
        }
    }

    public static void main(String[] args) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        currencyConverter.run();
    }
}
