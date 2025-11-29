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
            threads[i] = new creatingThreads(i, "ROW");
        }


        for (int i = 0; i < 9; i++) {
            threads[9 + i] = new creatingThreads(i, "COLUMN");
        }


        for (int i = 0; i < 9; i++) {
            threads[18 + i] = new creatingThreads(i, "BOX");
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        return allDuplicates;
    }

    private class creatingThreads extends Thread {
        private int index;
        private String type;

        public creatingThreads(int index, String type) {
            this.index = index;
            this.type = type;
        }

        public void run() {
            FinalDuplicate d = null;

            if (type.equals("ROW")) {
                d = getDuplicates.getRowDuplicate(index);
                allDuplicates[index] = d;
            }
            else if (type.equals("COLUMN")) {
                d = getDuplicates.getColumnDuplicate(index);
                allDuplicates[9 + index] = d;
            }
            else {
                d = getDuplicates.getBoxDuplicate(index);
                allDuplicates[18 + index] = d;
            }
        }
    }
}
