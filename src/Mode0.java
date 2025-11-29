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
        if (!check.isValid(row)) {
             duplicatedNumber.add(check.getDuplicatedNumber(row));
            locations.add(new Pair<>("ROW ", i + 1));
        }
    }

        for(int i=0;i<9;i++){
            ArrayList<Integer> col = check.getColumn(i);
            if (!check.isValid(col)) {
                duplicatedNumber.add(check.getDuplicatedNumber(col));
                locations.add(new Pair<>("Column ", i + 1));
    }}

        for(int i=0;i<9;i++){
            ArrayList<Integer> box = check.getBox(i);
            if (!check.isValid(box)) {
                duplicatedNumber.add(check.getDuplicatedNumber(box));
                locations.add(new Pair<>("Box", i + 1));
            }}

        for(int i=0;i<locations.size();i++){
        new PrintResult(Collections.singletonList(locations.get(i)),duplicatedNumber.get(i), Collections.singletonList(results.get(i)));

        }

}}