public class SaveLoad {
    public SaveLoad(){}

    public static void Save(Map currentState, String fileToSave ) {

        PrintWriter pw = new PrintWriter(new FileWriter(fileToSave));

        //Save current map
        int ncells = currentState.getMapMatrix().size();
        pw.write(ncells);
        while(true) {
            for (String cells : currentState.getMapMatrix()) {
                pw.write(cells);
            break;
            }
        }

        //Save current player
        pw.write(currentState.getPlayer().getPlayerPos().getX())
        pw.write(currentState.getPlayer().getPlayerPos().getY())

        int nitems = currentState.getPlayer().getInvCount()

        while(true) {

        }

    }

    public static Map Load (String fileToLoad) {
        
    }
}