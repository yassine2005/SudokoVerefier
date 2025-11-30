import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mode3Verifier {
    private int[][] data;
    private CheckDuplicates checker;
    private List<FinalDuplicate> result = new ArrayList<>();


    public Mode3Verifier(int[][] board) {
        this.data = board;
        checker = new CheckDuplicates(board);

        WorkerThread rowThread = new WorkerThread("ROW");
        WorkerThread colThread = new WorkerThread("COLUMN");
        WorkerThread boxThread = new WorkerThread("BOX");

        rowThread.start();
        colThread.start();
        boxThread.start();

        //this to make the main thread stop and call print class after excution of threds
        try {
            rowThread.join();
            colThread.join();
            boxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result.addAll(rowThread.getDuplicates());
        result.addAll(colThread.getDuplicates());
        result.addAll(boxThread.getDuplicates());
        PrintResult printResult = new PrintResult(result);

    }

    private class WorkerThread extends Thread {
        private String type;
        //private List<FinalDuplicate> duplicates;
        private List<FinalDuplicate> rowDuplicates = new ArrayList<>();
        private List<FinalDuplicate> colDuplicates = new ArrayList<>();
        private List<FinalDuplicate> boxDuplicates = new ArrayList<>();

        private WorkerThread(String type){
            this.type = type;
            //duplicates = new ArrayList<>();
        }

        @Override
        public void run() {
            if (type.equals("ROW")){
//                for (int i = 0; i < 9; i++) {
//                    ArrayList<Integer> row = checker.getRow(i);
//                    if(!checker.isValid(row)){
//                        HashMap<Integer,List<Integer>> map = new HashMap<>();
//                        List<Integer> duplicatedNumbers = checker.getDuplicatedNumber(row);
//                        for (int dup : duplicatedNumbers) { //get indexs for each duplicated number
//                            List<Integer> indices = checker.getIndex(row, dup);
//                            map.put(dup, indices);
//                        }
//                        FinalDuplicate fd = new FinalDuplicate("ROW", i, map);
//                        rowDuplicates.add(fd);
//                    }
//                }
                unitWork("ROW");
            }
            else if (type.equals("COLUMN")){
//                for (int i = 0; i < 9; i++) {
//                    ArrayList<Integer> col = checker.getColumn(i);
//                    if(!checker.isValid(col)){
//                        HashMap<Integer,List<Integer>> map = new HashMap<>();
//                        List<Integer> duplicatedNumbers = checker.getDuplicatedNumber(col);
//                        for (int dup : duplicatedNumbers) { //get indexs for each duplicated number
//                            List<Integer> indices = checker.getIndex(col, dup);
//                            map.put(dup, indices);
//                        }
//                        FinalDuplicate fd = new FinalDuplicate("COLUMN", i, map);
//                        colDuplicates.add(fd);
//                    }
//                }
                unitWork("COLUMN");
            }
            else{
//                for (int i = 0; i < 9; i++) {
//                    ArrayList<Integer> box = checker.getBox(i);
//                    if(!checker.isValid(box)){
//                        HashMap<Integer,List<Integer>> map = new HashMap<>();
//                        List<Integer> duplicatedNumbers = checker.getDuplicatedNumber(box);
//                        for (int dup : duplicatedNumbers) { //get indexs for each duplicated number
//                            List<Integer> indices = checker.getIndex(box, dup);
//                            map.put(dup, indices);
//                        }
//                        FinalDuplicate fd = new FinalDuplicate("BOX", i, map);
//                        boxDuplicates.add(fd);
//                    }
                unitWork("BOX");
            }
            //result.addAll(duplicates);
        }
        private void unitWork(String type){
            ArrayList<Integer> unit = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                switch (type) {
                    case "ROW":{
                        unit = checker.getRow(i);
                        break;
                    }
                    case "COLUMN":{
                        unit = checker.getColumn(i);
                        break;
                    }
                    case  "BOX":
                        unit = checker.getBox(i);

                }
                if(!checker.isValid(unit)){
                    HashMap<Integer,List<Integer>> map = new HashMap<>();
                    List<Integer> duplicatedNumbers = checker.getDuplicatedNumber(unit);
                    for (int dup : duplicatedNumbers) { //get indexs for each duplicated number
                        List<Integer> indices = checker.getIndex(unit, dup);
                        map.put(dup, indices);
                    }
                    FinalDuplicate fd = new FinalDuplicate(null, -1,null);
                    switch (type) {
                        case "ROW":{
                            fd = new FinalDuplicate("ROW", i, map);
                            rowDuplicates.add(fd);
                            break;
                        }
                        case "COLUMN":{
                            fd = new FinalDuplicate("COLUMN", i, map);
                            colDuplicates.add(fd);
                            break;
                        }
                        case  "BOX":
                            fd = new FinalDuplicate("BOX", i, map);
                            boxDuplicates.add(fd);
                    }
                }
            }

        }
        public  List<FinalDuplicate> getDuplicates() {
            if(type.equals("ROW"))
                return rowDuplicates;
            else if(type.equals("COLUMN"))
                return colDuplicates;
            else
                return boxDuplicates;

        }
    }
}
