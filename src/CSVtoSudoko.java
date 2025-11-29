import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVtoSudoko {

    public static int[][] readSudoko(String filename) {

        List<String> lines = null;
        List<int[]> allrows = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("ERROR: Cannot read file: " + filename);
            return null;
        }


        try {
            for (String line : lines) {

                if (line.trim().isEmpty()) continue;

                String[] numbers = line.trim().split("[,\\s]+");

                if (numbers.length != 9) {
                    System.out.println("ERROR: Line does not contain 9 numbers → " + line);
                    return null;
                }

                int[] row = new int[9];

                for (int i = 0; i < 9; i++) {
                    try {
                        row[i] = Integer.parseInt(numbers[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Invalid number → " + numbers[i]);
                        return null;
                    }
                }

                allrows.add(row);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Failed to parse CSV file.");
            return null;
        }


        if (allrows.size() != 9) {
            System.out.println("ERROR: File must contain exactly 9 rows, but found: " + allrows.size());
            return null;
        }


        int[][] sudoko = new int[9][9];

        for (int r = 0; r < 9; r++) {
            sudoko[r] = allrows.get(r);
        }

        return sudoko;
    }
}
