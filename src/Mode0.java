import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mode0 implements Checker {
    private int[][] data;

    public Mode0(int[][] board) {
        this.data = board;
    }

    @Override
    public void check() {
        CheckDuplicates check = new CheckDuplicates(data);
        List<FinalDuplicate> duplicates = new ArrayList<>();

        for (String type : new String[]{"ROW", "COLUMN", "BOX"}) {
            for (int i = 0; i < 9; i++) {
                ArrayList<Integer> unit;
                switch (type) {
                    case "ROW": unit = check.getRow(i); break;
                    case "COLUMN": unit = check.getColumn(i); break;
                    default: unit = check.getBox(i);
                }
                if (!check.isValid(unit)) {
                    HashMap<Integer, List<Integer>> map = new HashMap<>();
                    for (int dup : check.getDuplicatedNumber(unit)) {
                        if (!map.containsKey(dup)) {
                            map.put(dup, check.getIndex(unit, dup));
                        }
                    }
                    duplicates.add(new FinalDuplicate(type, i, map));
                }
            }
        }

        new PrintResult(duplicates);
    }
}
