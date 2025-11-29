import java.util.List;
import java.util.Map;

public class FinalDuplicate {

    private String type;
    private int index;
    private Map<Integer, List<Integer>> duplicates;

    public FinalDuplicate(String type, int index, Map<Integer, List<Integer>> duplicates) {
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

    public Map<Integer, List<Integer>> getDuplicates() {
        return duplicates;
    }
}
