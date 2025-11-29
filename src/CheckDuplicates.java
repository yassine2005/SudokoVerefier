import java.util.ArrayList;
import java.util.List;

public  class CheckDuplicates {

    private int[][] data;
    public CheckDuplicates(int[][] data) {
        this.data = data;
    }


    public ArrayList<Integer> getRow(int index) {
      ArrayList<Integer> row = new ArrayList<>();
      for(int i = 0; i < 9; i++){
          row.add(data[index][i]);
      }
       return row;
    }
   public ArrayList<Integer> getColumn(int index) {
      ArrayList<Integer> column = new ArrayList<>();
      for(int i = 0; i < 9; i++){
          column.add(data[i][index]);
      }
      return column;
   }

     public ArrayList<Integer> getBox(int index) {
        ArrayList<Integer> box = new ArrayList<>();
        int row = (index / 3)*3;
        int column = (index % 3)*3;

       for(int i = row; i < row+3; i++){
           for(int j = column; j < column+3; j++){
             box.add(data[i][j]);

           }
        }
        return box;

     }

     public boolean isValid(ArrayList<Integer> values) {
        int[]count = new int[10];
        for(int i = 0; i < values.size(); i++){
            int value = values.get(i);
            if(value<1||value>9){
                return false;
            }
            count[value]++;

            if (count[value] > 1)
                return false;
        }

         return true;
        }


     public List<Pair<String,Integer>> checkRows(){
         List<Pair<String,Integer>> rowDuplicates = new ArrayList<>();
       for(int i = 0; i < 9; i++){
           ArrayList<Integer> row = getRow(i);
           if (!isValid(row)){
               rowDuplicates.add(new Pair<>("row",i+1));
           }
       }
      return rowDuplicates;

     }
     public List<Pair<String,Integer>> checkColumns(){
         List<Pair<String,Integer>> columnDuplicates = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            ArrayList<Integer> column = getColumn(i);
            if (!isValid(column)){
                columnDuplicates.add(new Pair<>("COLUMN",i+1));
            }
        }
        return columnDuplicates;
     }
     public List<Pair<String,Integer>> checkBoxes(){
         List<Pair<String,Integer>> boxDuplicates = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            ArrayList<Integer> box = getBox(i);
            if (!isValid(box)){
                boxDuplicates.add(new Pair<>("BOX",i+1));
            }
        }
        return boxDuplicates;
     }
}
