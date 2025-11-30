public class Test {
    public static void main(String[] args) {
        int[][] board = {
                {5,3,4,6,8,8,9,2,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {1,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
//        if(args.length < 2)
//            return;
//        String fileName = args[0];
//        int mode = Integer.parseInt(args[1]);
//        int [][] board = CSVtoSudoko.readSudoko(fileName);
        //new CreateThreads(27, board);
        //Mode3Verifier mode3 = new Mode3Verifier(board);
//        Mode27Verifier mode27 = new Mode27Verifier(board);
//        Thread thread27 = new Thread(mode27);
//        thread27.start();
        Mode0 mode0 = new Mode0(board);
        Thread thread0 = new Thread(mode0);
        thread0.start();

    }


}