package BH_Intern_Task4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProductFilter {

    public static void main(String[] args) {
        // Defaults (can be overridden via command-line args)
        String inputFile  = "products.csv";
        String outputFile = "expensive_products.csv";
        double threshold  = 1000.0;

        if (args.length >= 1) inputFile = args[0];
        if (args.length >= 2) outputFile = args[1];
        if (args.length >= 3) {
            try {
                threshold = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid threshold provided. Using default: " + threshold);
            }
        }

        // Regex splits on commas that are NOT inside quotes:
        final String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*$)";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();
            if (header == null) {
                System.err.println("Input file is empty.");
                return;
            }

            // write header to output file (preserve header if present)
            bw.write(header);
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(splitRegex, 2); // at most 2 parts: name and price
                if (parts.length < 2) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                String name = unquote(parts[0].trim());
                String priceStr = unquote(parts[1].trim());

                // remove thousands separators if any (e.g., "1,299" => "1299")
                priceStr = priceStr.replaceAll(",", "");

                try {
                    double price = Double.parseDouble(priceStr);
                    if (price > threshold) {
                        // write the record exactly (preserve original formatting of name/price as simple CSV)
                        bw.write(escapeIfNeeded(name) + "," + price);
                        bw.newLine();
                    }
                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping line with invalid price: " + line);
                }
            }

            System.out.println("Filtering complete. Output written to: " + outputFile);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    // Remove surrounding double-quotes if present
    private static String unquote(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    // If value contains comma or quote, wrap in quotes and escape inner quotes
    private static String escapeIfNeeded(String s) {
        if (s == null) return "";
        boolean needQuotes = s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r");
        if (!needQuotes) return s;
        String escaped = s.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
