import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;


public class Mode0 implements Checker {
    private int[][] data;
    private List<FinalDuplicate> duplicates = new ArrayList<>();
    private CheckDuplicates check;

    public Mode0(int[][] board) {
        this.data = board;
        run();
    }

    @Override
    public void run() {
        unitWork("ROW");
        unitWork("COLUMN");
        unitWork("BOX");

        //CheckDuplicates check = new CheckDuplicates(data);

//        for(int i=0;i<9;i++){
//            ArrayList<Integer> row = check.getRow(i);
//            if (!check.isValid(row)){
//                List<Integer> num = check.getDuplicatedNumber(row);
//                HashMap<Integer,List<Integer>> map = new HashMap<>();
//                for(int j=0;j<num.size();j++){
//                    int dup = num.get(j);
//    //            locations.add(new Pair<>("ROW ", i + 1));
//    //            results.add(check.getIndex(row,dup));
//    //            duplicatedNumber.add(num.get(j));
//                    map.put(dup,check.getIndex(row,dup));
//                }
//                    FinalDuplicate duplicate = new FinalDuplicate("ROW", i, map);
//                    duplicates.add(duplicate);
//        }
//    }


//        for(int i=0;i<9;i++){
//            ArrayList<Integer> col = check.getColumn(i);
//            if (!check.isValid(col)){
//                List<Integer> num = check.getDuplicatedNumber(col);
//                HashMap<Integer,List<Integer>> map = new HashMap<>();
//                for(int j=0;j<num.size();j++){
//                    int dup = num.get(j);
////                    locations.add(new Pair<>("Column ", i + 1));
////                    results.add(check.getIndex(col,dup));
////                    duplicatedNumber.add(num.get(j));
//                    map.put(dup,check.getIndex(col,dup));
//                    FinalDuplicate duplicate = new FinalDuplicate("ROW", i, map);
//                    duplicates.add(duplicate);
//                }
//            }
//        }

//        for(int i=0;i<9;i++){
//            ArrayList<Integer> box = check.getBox(i);
//            if (!check.isValid(box)){
//                List<Integer> num = check.getDuplicatedNumber(box);
//                HashMap<Integer,List<Integer>> map = new HashMap<>();
//                for(int j=0;j<num.size();j++){
//                    int dup = num.get(j);
////                    locations.add(new Pair<>("Box", i + 1));
////                    results.add(check.getIndex(box,dup));
////                    duplicatedNumber.add(num.get(j));
//                    map.put(dup,check.getIndex(box,dup));
//                    FinalDuplicate duplicate = new FinalDuplicate("ROW", i, map);
//                    duplicates.add(duplicate);
//                }
//            }
//        }
        new PrintResult(duplicates);


}
    private void unitWork(String type){
        check = new CheckDuplicates(data);
        ArrayList<Integer> unit = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            switch (type) {
                case "ROW":{
                    unit = check.getRow(i);
                    break;
                }
                case "COLUMN":{
                    unit = check.getColumn(i);
                    break;
                }
                case  "BOX":
                    unit = check.getBox(i);

            }
            if(!check.isValid(unit)){
                HashMap<Integer,List<Integer>> map = new HashMap<>();
                List<Integer> duplicatedNumbers = check.getDuplicatedNumber(unit);
                for (int dup : duplicatedNumbers) {
                    List<Integer> indices = check.getIndex(unit, dup);
                    map.put(dup, indices);
                }
                FinalDuplicate fd = new FinalDuplicate(null, -1,null);
                switch (type) {
                    case "ROW":{
                        fd = new FinalDuplicate("ROW", i, map);
                        break;
                    }
                    case "COLUMN":{
                        fd = new FinalDuplicate("COLUMN", i, map);
                        break;
                    }
                    case  "BOX":
                        fd = new FinalDuplicate("BOX", i, map);
                }
                duplicates.add(fd);
            }
        }

    }


}