import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mode3Verifier implements Checker {
    private int[][] data;
    private CheckDuplicates checker;

    public Mode3Verifier(int[][] board) {
        this.data = board;
        this.checker = new CheckDuplicates(board);
    }

    @Override
    public void check() {
        List<FinalDuplicate> result = new ArrayList<>();

        WorkerThread rowThread = new WorkerThread("ROW", result);
        WorkerThread colThread = new WorkerThread("COLUMN", result);
        WorkerThread boxThread = new WorkerThread("BOX", result);

        rowThread.start();
        colThread.start();
        boxThread.start();

        try {
            rowThread.join();
            colThread.join();
            boxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new PrintResult(result);
    }

    private void unitWork(String type, List<FinalDuplicate> list) {
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> unit;
            switch (type) {
                case "ROW": unit = checker.getRow(i); break;
                case "COLUMN": unit = checker.getColumn(i); break;
                default: unit = checker.getBox(i);
            }
            if (!checker.isValid(unit)) {
                HashMap<Integer, List<Integer>> map = new HashMap<>();
                for (int dup : checker.getDuplicatedNumber(unit)) {
                    if (!map.containsKey(dup)) {
                        map.put(dup, checker.getIndex(unit, dup));
                    }
                }
                list.add(new FinalDuplicate(type, i, map));
            }
        }
    }

    private class WorkerThread extends Thread {
        private String type;
        private List<FinalDuplicate> sharedList;

        public WorkerThread(String type, List<FinalDuplicate> sharedList) {
            this.type = type;
            this.sharedList = sharedList;
        }

        @Override
        public void run() {
            List<FinalDuplicate> local = new ArrayList<>();
            unitWork(type, local);
            synchronized(sharedList) {
                sharedList.addAll(local);
            }
        }
    }
}
