import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mode27Verifier implements Runnable {

    private int[][] sudoko;
    private ArrayList<FinalDuplicate> duplicates = new ArrayList<>();

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
            threads[i] = new RowThread(i, check, duplicates);
        }

        for (int i = 0; i < 9; i++) {
            threads[9 + i] = new ColumnThread(i, check, duplicates);
        }

        for (int i = 0; i < 9; i++) {
            threads[18 + i] = new BoxThread(i, check, duplicates);
        }

        for (Thread t : threads)
            t.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {}
        }

        new PrintResult(duplicates);
    }

    private static class RowThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<FinalDuplicate> duplicates;

        public RowThread(int index, CheckDuplicates check,
                         ArrayList<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> row = check.getRow(index);

            if (!check.isValid(row)) {
                List<Integer> dups = check.getDuplicatedNumber(row);

//                for (int dup : dups) {
//                    locations.add(new Pair<>("ROW ", index + 1));
//                    duplicatedNumbers.add(dup);
//                    indices.add(check.getIndex(row, dup));
//                }
                for(int i = 0; i < dups.size(); i++) {
                    int dup = dups.get(i);
                    HashMap<Integer,List<Integer>> map = new HashMap<>();
                    map.put(dup,check.getIndex(row,dup));
                    FinalDuplicate duplicate = new FinalDuplicate("ROW", index, map);
                    duplicates.add(duplicate);
                }
            }
        }
    }

    private static class ColumnThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private  List<FinalDuplicate> duplicates;

        public ColumnThread(int index, CheckDuplicates check,
                            ArrayList<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> col = check.getColumn(index);

            if (!check.isValid(col)) {
                List<Integer> dups = check.getDuplicatedNumber(col);

//                for (int dup : dups) {
//                    locations.add(new Pair<>("Column ", index + 1));
//                    duplicatedNumbers.add(dup);
//                    indices.add(check.getIndex(col, dup));
//                }
                for(int i = 0; i < dups.size(); i++) {
                    int dup = dups.get(i);
                    HashMap<Integer,List<Integer>> map = new HashMap<>();
                    map.put(dup,check.getIndex(col,dup));
                    FinalDuplicate duplicate = new FinalDuplicate("ROW", index, map);
                    duplicates.add(duplicate);
                }
            }
        }
    }

    private static class BoxThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<FinalDuplicate> duplicates;

        public BoxThread(int index, CheckDuplicates check,
                         ArrayList<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> box = check.getBox(index);

            if (!check.isValid(box)) {
                List<Integer> dups = check.getDuplicatedNumber(box);

//                for (int dup : dups) {
//                    locations.add(new Pair<>("Box ", index + 1));
//                    duplicatedNumbers.add(dup);
//                    indices.add(check.getIndex(box, dup));
//                }
                for(int i = 0; i < dups.size(); i++) {
                    int dup = dups.get(i);
                    HashMap<Integer,List<Integer>> map = new HashMap<>();
                    map.put(dup,check.getIndex(box,dup));
                    FinalDuplicate duplicate = new FinalDuplicate("BOX", index, map);
                    duplicates.add(duplicate);
                }
            }
        }
    }
}
