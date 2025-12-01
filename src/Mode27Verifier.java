import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mode27Verifier implements Checker {
    private int[][] sudoku;

    public Mode27Verifier(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public void check() {
        CheckDuplicates check = new CheckDuplicates(sudoku);
        List<FinalDuplicate> duplicates = new ArrayList<>();
        Thread[] threads = new Thread[27];

        for (int i = 0; i < 9; i++) threads[i] = new RowThread(i, check, duplicates);
        for (int i = 0; i < 9; i++) threads[9 + i] = new ColumnThread(i, check, duplicates);
        for (int i = 0; i < 9; i++) threads[18 + i] = new BoxThread(i, check, duplicates);

        for (Thread t : threads) t.start();
        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException ignored) {}
        }

        duplicates.sort((a, b) -> {
            int typeA = typeOrder(a.getType());
            int typeB = typeOrder(b.getType());
            if (typeA != typeB) return typeA - typeB;
            return a.getIndex() - b.getIndex();
        });

        new PrintResult(duplicates);
    }

    private int typeOrder(String type) {
        switch (type) {
            case "ROW": return 1;
            case "COLUMN": return 2;
            case "BOX": return 3;
        }
        return 99;
    }

    private static class RowThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<FinalDuplicate> duplicates;

        RowThread(int index, CheckDuplicates check, List<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> row = check.getRow(index);
            if (!check.isValid(row)) {
                HashMap<Integer, List<Integer>> map = new HashMap<>();
                for (int dup : check.getDuplicatedNumber(row)) {
                    if (!map.containsKey(dup)) {
                        map.put(dup, check.getIndex(row, dup));
                        synchronized (duplicates) {
                            duplicates.add(new FinalDuplicate("ROW", index, map));
                        }
                    }
                }
            }
        }
    }

    private static class ColumnThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<FinalDuplicate> duplicates;

        ColumnThread(int index, CheckDuplicates check, List<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> col = check.getColumn(index);
            if (!check.isValid(col)) {
                HashMap<Integer, List<Integer>> map = new HashMap<>();
                for (int dup : check.getDuplicatedNumber(col)) {
                    if (!map.containsKey(dup)) {
                        map.put(dup, check.getIndex(col, dup));
                        synchronized (duplicates) {
                            duplicates.add(new FinalDuplicate("COLUMN", index, map));
                        }
                    }
                }
            }
        }
    }

    private static class BoxThread extends Thread {
        private int index;
        private CheckDuplicates check;
        private List<FinalDuplicate> duplicates;

        BoxThread(int index, CheckDuplicates check, List<FinalDuplicate> duplicates) {
            this.index = index;
            this.check = check;
            this.duplicates = duplicates;
        }

        public void run() {
            ArrayList<Integer> box = check.getBox(index);
            if (!check.isValid(box)) {
                HashMap<Integer, List<Integer>> map = new HashMap<>();
                for (int dup : check.getDuplicatedNumber(box)) {
                    if (!map.containsKey(dup)) {
                        map.put(dup, check.getIndex(box, dup));
                        synchronized (duplicates) {
                            duplicates.add(new FinalDuplicate("BOX", index, map));
                        }
                    }
                }
            }
        }
    }
}
