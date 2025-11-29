import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVtoSudoko {
    public static int[][] readBoard(String filename)
    {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            List<int[]> allrows = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }


    }
}
