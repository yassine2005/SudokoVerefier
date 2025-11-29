import java.util.List;

public class PrintResult {
    public PrintResult(List<Pair<String,Integer>> locations, List<Integer> numberDuplicated, List<List<Integer>> results) {
        //locations(type, location example row 1)
        //result(number duplicated , its locations example #1 [1,2,3])
        //the size of locations and result equal
        //each member in the location must -> in the result

        //check the size if empty -> valid
        if(locations.isEmpty()){
            System.out.println("VALID");
            return;
        }
        System.out.println("INVALID");

        int index = 0;
        for (Pair<String,Integer> pair : locations) {
            System.out.print(pair.getKey() + pair.getValue() + ", "
            + "#" + numberDuplicated.get(index) + ", "+ "[");
            List<Integer> locationsOfDuplicatedNumber = results.get(index++);
            for (int j = 0; j < locationsOfDuplicatedNumber.size(); j++){
                if(j == locationsOfDuplicatedNumber.size() - 1)
                    System.out.println(locationsOfDuplicatedNumber.get(j) + "]");
                else
                    System.out.print(locationsOfDuplicatedNumber.get(j) + ", ");
            }
        }

    }
}
