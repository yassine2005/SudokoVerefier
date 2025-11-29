import java.util.HashMap;
import java.util.List;

public class FinalDuplicate {
    private String type;
    private int index;
    private HashMap<Integer, List<Integer>> duplicates;

    public FinalDuplicate(String type, int index, HashMap<Integer, List<Integer>> duplicates) {
        this.type = type;
        this.index = index;
        this.duplicates = duplicates;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public HashMap<Integer, List<Integer>> getDuplicates() {
        return duplicates;
    }
}
