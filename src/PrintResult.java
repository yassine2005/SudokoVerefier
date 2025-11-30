import java.util.List;
import java.util.Map;


public class PrintResult {
//    public PrintResult(List<Pair<String,Integer>> locations, List<Integer> numberDuplicated, List<List<Integer>> results) {
//        //locations(type, location example row 1)
//        //result(number duplicated , its locations example #1 [1,2,3])
//        //the size of locations and result equal
//        //each member in the location must -> in the result
//
//        //check the size if empty -> valid
//        if(locations.isEmpty()){
//            System.out.println("VALID");
//            return;
//        }
//        System.out.println("INVALID");
//
//        int index = 0;
//        for (Pair<String,Integer> pair : locations) {
//            System.out.print(pair.getKey() + pair.getValue() + ", "
//            + "#" + numberDuplicated.get(index) + ", "+ "[");
//            List<Integer> locationsOfDuplicatedNumber = results.get(index++);
//            for (int j = 0; j < locationsOfDuplicatedNumber.size(); j++){
//                if(j == locationsOfDuplicatedNumber.size() - 1)
//                    System.out.println(locationsOfDuplicatedNumber.get(j) + "]");
//                else
//                    System.out.print(locationsOfDuplicatedNumber.get(j) + ", ");
//            }
//        }
//
//    }
        public PrintResult(List<FinalDuplicate> result){
        if(result.isEmpty()){
            System.out.println("VALID");
            return;
        }
        System.out.println("INVALID");
        for (FinalDuplicate duplicate : result){
            System.out.print(duplicate.getType() + " " + (duplicate.getIndex() + 1) + ", ");
            Map<Integer, List<Integer>> duplicates = duplicate.getDuplicates();
            for (Map.Entry<Integer, List<Integer>> element : duplicates.entrySet()){
                System.out.print("#" + element.getKey() + ", [");
                List<Integer> locations = element.getValue();
                for (int j = 0; j < locations.size(); j++){
                    if(j == locations.size() - 1){
                        System.out.print((locations.get(j)) + "]");
                        break;
                    }
                    else
                        System.out.print((locations.get(j)) + ", ");
                }
            }
            System.out.println("");
        }
    }

}
