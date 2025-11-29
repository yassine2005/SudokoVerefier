import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicateGetter {

    private int[][] sudoko;
    private CheckDuplicates check;

    public DuplicateGetter(int[][] sudoko) {
        this.sudoko = sudoko;
        this.check = new CheckDuplicates(sudoko);
    }

    public HashMap<Integer, List<Integer>> findDuplicatesInUnit(ArrayList<Integer> values) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] count = new int[10];

        for (int i = 0; i < values.size(); i++) {
            int num = values.get(i);
            count[num]++;

            map.putIfAbsent(num, new ArrayList<>());
            map.get(num).add(i);
        }

        map.entrySet().removeIf(e -> e.getValue().size() < 2);

        return map;
    }

    public List<FinalDuplicate> getRowDuplicates() {
        List<FinalDuplicate> results = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            ArrayList<Integer> list = check.getRow(row);
            var duplicates = findDuplicatesInUnit(list);

            if (!duplicates.isEmpty()) {
                results.add(new FinalDuplicate("ROW", row, duplicates));
            }
        }
        return results;
    }

    public List<FinalDuplicate> getColumnDuplicates() {
        List<FinalDuplicate> results = new ArrayList<>();

        for (int col = 0; col < 9; col++) {
            ArrayList<Integer> list = check.getColumn(col);
            var duplicates = findDuplicatesInUnit(list);

            if (!duplicates.isEmpty()) {
                results.add(new FinalDuplicate("COLUMN", col, duplicates));
            }
        }
        return results;
    }

    public List<FinalDuplicate> getBoxDuplicates() {
        List<FinalDuplicate> results = new ArrayList<>();

        for (int box = 0; box < 9; box++) {
            ArrayList<Integer> list = check.getBox(box);
            var duplicates = findDuplicatesInUnit(list);

            if (!duplicates.isEmpty()) {
                results.add(new FinalDuplicate("BOX", box, duplicates));
            }
        }
        return results;
    }
}
