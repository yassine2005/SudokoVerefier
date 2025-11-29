import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mode27Verifier {

    private int[][] sudoko;
    private FinalDuplicate[] allDuplicates;
    private DuplicateGetter getDuplicates;

    public Mode27Verifier(int[][] sudoko) {
        this.sudoko = sudoko;
        this.allDuplicates = new FinalDuplicate[27];
        this.getDuplicates = new DuplicateGetter(sudoko);
    }

    public FinalDuplicate[] runCheck() throws InterruptedException {
        Thread[] threads = new Thread[27];

        for (int i = 0; i < 9; i++) {
            threads[i] = new UnitThread(i, "ROW");
        }
        for (int i = 0; i < 9; i++) {
            threads[9 + i] = new UnitThread(i, "COLUMN");
        }
        for (int i = 0; i < 9; i++) {
            threads[18 + i] = new UnitThread(i, "BOX");
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return allDuplicates;
    }

    public void printResults() {
        System.out.println("ROWS:");
        printSection(0, 9);

        System.out.println("----------------------------------");

        System.out.println("COLUMNS:");
        printSection(9, 18);

        System.out.println("----------------------------------");

        System.out.println("BOXES:");
        printSection(18, 27);
    }

    private void printSection(int start, int end) {
        for (int i = start; i < end; i++) {

            FinalDuplicate fd = allDuplicates[i];

            if (fd == null) continue;

            for (Map.Entry<Integer, List<Integer>> entry : fd.getDuplicates().entrySet()) {

                int number = entry.getKey();
                List<Integer> positions = entry.getValue();

                System.out.print(fd.getType() + " " + (fd.getIndex() + 1) + ", ");
                System.out.print("#" + number + ", [");

                for (int j = 0; j < positions.size(); j++) {
                    System.out.print(positions.get(j) + 1);  // shift index to 1..9
                    if (j < positions.size() - 1) System.out.print(", ");
                }

                System.out.println("]");
            }
        }
    }

    private class UnitThread extends Thread {
        private int index;
        private String type;

        public UnitThread(int index, String type) {
            this.index = index;
            this.type = type;
        }

        public void run() {
            FinalDuplicate fd;

            if (type.equals("ROW")) {
                fd = getDuplicates.getRowDuplicate(index);
                allDuplicates[index] = fd;
            }
            else if (type.equals("COLUMN")) {
                fd = getDuplicates.getColumnDuplicate(index);
                allDuplicates[9 + index] = fd;
            }
            else {
                fd = getDuplicates.getBoxDuplicate(index);
                allDuplicates[18 + index] = fd;
            }
        }
    }
}
