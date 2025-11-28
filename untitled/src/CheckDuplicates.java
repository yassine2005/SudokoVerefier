import java.util.ArrayList;

public abstract class CheckDuplicates {

    protected int[][] data;
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

     public boolean hasDuplicates(ArrayList<Integer> values) {
        int[]count = new int[10];
        for(int i = 0; i < values.size(); i++){
            int value = values.get(i);
            if(value<1||value>9){
                return true;
            }
            count[value]++;

            if (count[value] > 1)
                return true;
        }

         return false;
        }


     public abstract void check();


}
