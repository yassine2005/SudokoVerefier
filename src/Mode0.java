import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mode0 implements Runnable {
    private int[][] data;

    public Mode0(int[][] board) {
        this.data = board;
    }

    @Override
    public void run() {

        CheckDuplicates check = new CheckDuplicates(data);

        List<Pair<String, Integer>> locations = new ArrayList<>();
       List<List<Integer>> results = new ArrayList<>();
        List<Integer> duplicatedNumber=new ArrayList<>();


        for(int i=0;i<9;i++){
        ArrayList<Integer> row = check.getRow(i);
        if (!check.isValid(row)){
            List<Integer> num = check.getDuplicatedNumber(row);
            for(int j=0;j<num.size();j++){
                int dup = num.get(j);
            locations.add(new Pair<>("ROW ", i + 1));
            results.add(check.getIndex(row,dup));
            duplicatedNumber.add(num.get(j));
            }
        }
    }

        for(int i=0;i<9;i++){
            ArrayList<Integer> col = check.getColumn(i);
            if (!check.isValid(col)){
                List<Integer> num = check.getDuplicatedNumber(col);
                for(int j=0;j<num.size();j++){
                    int dup = num.get(j);
                    locations.add(new Pair<>("Column ", i + 1));
                    results.add(check.getIndex(col,dup));
                    duplicatedNumber.add(num.get(j));
                }
            }
        }

        for(int i=0;i<9;i++){
            ArrayList<Integer> box = check.getBox(i);
            if (!check.isValid(box)){
                List<Integer> num = check.getDuplicatedNumber(box);
                for(int j=0;j<num.size();j++){
                    int dup = num.get(j);
                    locations.add(new Pair<>("Box", i + 1));
                    results.add(check.getIndex(box,dup));
                    duplicatedNumber.add(num.get(j));
                }
            }
        }

      new PrintResult(locations,duplicatedNumber,results);



}}