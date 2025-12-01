public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <path/to/csv> <mode>");
            System.out.println("mode: 0, 3, 27");
            return;
        }

        String path = args[0];
        String mode = args[1];

        int[][] board = CSVtoSudoko.readSudoko(path);
        if (board == null) return;

        FactoryDesign factory = new FactoryDesign();
        Checker checker = factory.createChecker(mode, board);
        if (checker == null) {
            System.out.println("ERROR: invalid mode -> " + mode);
            return;
        }

        checker.check();
    }
}
