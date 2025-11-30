import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;


public class Mode0 implements Runnable {
    private int[][] data;
    private List<FinalDuplicate> duplicates = new ArrayList<>();
    private Thread thread0;
    public Mode0(int[][] board) {
        this.data = board;
        thread0=new Thread(this);
        thread0.start();

    }

    @Override
    public void run() {

        CheckDuplicates check = new CheckDuplicates(data);

        for(int i=0;i<9;i++){
            ArrayList<Integer> row = check.getRow(i);
            if (!check.isValid(row)){
                List<Integer> num = check.getDuplicatedNumber(row);
                HashMap<Integer,List<Integer>> map = new HashMap<>();
                for(int j=0;j<num.size();j++){
                    int dup = num.get(j);
    //            locations.add(new Pair<>("ROW ", i + 1));
    //            results.add(check.getIndex(row,dup));
    //            duplicatedNumber.add(num.get(j));
                    map.put(dup,check.getIndex(row,dup));
                }
                    FinalDuplicate duplicate = new FinalDuplicate("ROW", i, map);
                    duplicates.add(duplicate);
        }
    }

        for(int i=0;i<9;i++){
            ArrayList<Integer> col = check.getColumn(i);
            if (!check.isValid(col)){
                List<Integer> num = check.getDuplicatedNumber(col);
                HashMap<Integer,List<Integer>> map = new HashMap<>();
                for(int j=0;j<num.size();j++){
                    int dup = num.get(j);
//                    locations.add(new Pair<>("Column ", i + 1));
//                    results.add(check.getIndex(col,dup));
//                    duplicatedNumber.add(num.get(j));
                    map.put(dup,check.getIndex(col,dup));
                    FinalDuplicate duplicate = new FinalDuplicate("Column", i, map);
                    duplicates.add(duplicate);
                }
            }
        }

        for(int i=0;i<9;i++){
            ArrayList<Integer> box = check.getBox(i);
            if (!check.isValid(box)){
                List<Integer> num = check.getDuplicatedNumber(box);
                HashMap<Integer,List<Integer>> map = new HashMap<>();
                for(int j=0;j<num.size();j++){
                    int dup = num.get(j);
//                    locations.add(new Pair<>("Box", i + 1));
//                    results.add(check.getIndex(box,dup));
//                    duplicatedNumber.add(num.get(j));
                    map.put(dup,check.getIndex(box,dup));
                    FinalDuplicate duplicate = new FinalDuplicate("Box", i, map);
                    duplicates.add(duplicate);
                }
            }
        }

        new PrintResult(duplicates);

    }
}