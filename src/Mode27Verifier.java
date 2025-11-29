import java.util.ArrayList;
import java.util.List;

public class Mode27Verifier implements Runnable {

    private int[][] sudoko;

    public Mode27Verifier(int[][] sudoko) {
        this.sudoko = sudoko;
    }

    @Override
    public void run() {

        CheckDuplicates check = new CheckDuplicates(sudoko);

        List<Pair<String, Integer>> locations = new ArrayList<>();
        List<Integer> duplicatedNumbers = new ArrayList<>();
        List<List<Integer>> indices = new ArrayList<>();

        Thread[] threads = new Thread[27];

        for (int i = 0; i < 9; i++) {
            threads[i] = new RowThread(i, check, locations, duplicatedNumbers, indices);
        }

        for (int i = 0; i < 9; i++) {
            threads[9 + i] = new ColumnThread(i, check, locations, duplicatedNumbers, indices);
        }

        for (int i = 0; i < 9; i++) {
            threads[18 + i] = new BoxThread(i, check, locations, duplicatedNumbers, indices);
        }

        for (Thread t : threads)
            t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {}
        }

        new PrintResult(locations, duplicatedNumbers, indices);
    }

    private static class RowThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<Pair<String, Integer>> locations;
        private List<Integer> duplicatedNumbers;
        private List<List<Integer>> indices;

        public RowThread(int index, CheckDuplicates check,
                         List<Pair<String, Integer>> locations,
                         List<Integer> duplicatedNumbers,
                         List<List<Integer>> indices) {
            this.index = index;
            this.check = check;
            this.locations = locations;
            this.duplicatedNumbers = duplicatedNumbers;
            this.indices = indices;
        }

        public void run() {
            ArrayList<Integer> row = check.getRow(index);

            if (!check.isValid(row)) {
                List<Integer> dups = check.getDuplicatedNumber(row);

                for (int dup : dups) {
                    locations.add(new Pair<>("ROW ", index + 1));
                    duplicatedNumbers.add(dup);
                    indices.add(check.getIndex(row, dup));
                }
            }
        }
    }

    private static class ColumnThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<Pair<String, Integer>> locations;
        private List<Integer> duplicatedNumbers;
        private List<List<Integer>> indices;

        public ColumnThread(int index, CheckDuplicates check,
                            List<Pair<String, Integer>> locations,
                            List<Integer> duplicatedNumbers,
                            List<List<Integer>> indices) {
            this.index = index;
            this.check = check;
            this.locations = locations;
            this.duplicatedNumbers = duplicatedNumbers;
            this.indices = indices;
        }

        public void run() {
            ArrayList<Integer> col = check.getColumn(index);

            if (!check.isValid(col)) {
                List<Integer> dups = check.getDuplicatedNumber(col);

                for (int dup : dups) {
                    locations.add(new Pair<>("Column ", index + 1));
                    duplicatedNumbers.add(dup);
                    indices.add(check.getIndex(col, dup));
                }
            }
        }
    }

    private static class BoxThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<Pair<String, Integer>> locations;
        private List<Integer> duplicatedNumbers;
        private List<List<Integer>> indices;

        public BoxThread(int index, CheckDuplicates check,
                         List<Pair<String, Integer>> locations,
                         List<Integer> duplicatedNumbers,
                         List<List<Integer>> indices) {
            this.index = index;
            this.check = check;
            this.locations = locations;
            this.duplicatedNumbers = duplicatedNumbers;
            this.indices = indices;
        }

        public void run() {
            ArrayList<Integer> box = check.getBox(index);

            if (!check.isValid(box)) {
                List<Integer> dups = check.getDuplicatedNumber(box);

                for (int dup : dups) {
                    locations.add(new Pair<>("Box ", index + 1));
                    duplicatedNumbers.add(dup);
                    indices.add(check.getIndex(box, dup));
                }
            }
        }
    }
}
