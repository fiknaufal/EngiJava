import java.io.*;
import java.util.ArrayList;


public class SaveLoad {
    public SaveLoad(){}

    public static void Save(Map currentState, String fileToSave ) throws IOException {

        // try {
        PrintWriter pw = new PrintWriter(new FileWriter(fileToSave));
        // }
        // catch (IOException e) {
            // e.printStackTrace();
        // }

        //SAVE MAP
        Integer ncells = currentState.getMapMatrix().size();
        pw.write(ncells.toString()+"\n");
        System.out.println(ncells);
        System.out.println("Berhasil save!");
        while(true) {
            for (String cells : currentState.getMapMatrix()) {
                pw.write(cells+"\n");
            }
            break;
        }

        //SAVE PLAYER

        //Save Player Position
        Integer posX = currentState.getPlayer().getPlayerPos().getX();
        Integer posY = currentState.getPlayer().getPlayerPos().getY();
        pw.write(posX.toString()+" "+posY.toString()+"\n");


        //Total Jumlah Item di Inventory (Ga tahu ini perlu apa eggk di save)
        System.out.printf("Slot terisi di inventory player saat ini: %d\n", currentState.getPlayer().getInvCount());
        //Save Player Engimon
        Inventory<Engimon> inventoryE = currentState.getPlayer().getInventoryE();
        Integer engiCount = inventoryE.getSize();
        System.out.printf("Jumlah engimon player: %d\n", engiCount);
        pw.write(engiCount.toString()+"\n");

        while(true) {
            //TEST
            int count = 1;
            for(Engimon playerEngi : inventoryE.getArray()) {
                System.out.println("======================");
                //NamaE Engimon
                System.out.printf("Nama Engimon Player %d: ", count);
                System.out.println(playerEngi.getName());
                pw.write(playerEngi.getName()+"\n");

                int parentCount = 1;
                for(String parentName : playerEngi.getParentNames()) {
                    //Nama parent(s)
                    System.out.printf("Nama parent %d dari Engimon Player %d: ",parentCount, count);
                    System.out.println(parentName);
                    pw.write(parentName+"\n");
                    
                    parentCount++;
                }

                //Species Engimon
                System.out.printf("Species Engimon %d: %s\n", count, playerEngi.getSpecies());
                pw.write(playerEngi.getSpecies()+"\n");


                //Element Engimon
                System.out.printf("Element Engimon %d: %s dan %s\n",count,playerEngi.getElement1().toString(),playerEngi.getElement2().toString());
                pw.write(playerEngi.getElement1().toString()+" "+playerEngi.getElement2().toString()+"\n");

                //Sound Engimon
                System.out.printf("Sound Engimon %d: %s\n",count, playerEngi.getSound());
                pw.write(playerEngi.getSound()+"\n");

                //MaxExp Engimon
                Integer maxExp = playerEngi.getMaxExp();
                System.out.printf("MaxExp Engimon %d: %s\n",count, maxExp.toString());
                pw.write(maxExp.toString()+"\n");

                //Exp Engimon
                Integer exp = playerEngi.getExp();
                System.out.printf("Exp Engimon %d: %s\n",count, exp.toString());
                pw.write(exp.toString()+"\n");

                //Cumulative Exp Engimon
                Integer cumExp = playerEngi.getCml();
                System.out.printf("Cumulative Exp Engimon %d: %s\n",count, cumExp.toString());
                pw.write(cumExp.toString()+"\n");

                //Position Engimon
                Integer engiPosX = playerEngi.getEngimonPos().getX();
                Integer engiPosY = playerEngi.getEngimonPos().getX();
                System.out.printf("Posisi Engimon %d: %d %d\n",count,engiPosX,engiPosY);
                pw.write(engiPosX.toString()+" "+engiPosY.toString()+"\n");

                //Health Engimon
                Integer health = playerEngi.getHealth();
                System.out.printf("Health Engimon %d: %d\n", count, health);
                pw.write(health.toString()+"\n");


                //END OF PLAYER ENGIMON
                System.out.println("======================\n");

               
                count++;
            }
            break;
        }

        //Save Player SkillItem
        Inventory<SkillItem> inventoryS = currentState.getPlayer().getInventoryS();
        Integer skillItemCount = inventoryS.getSize();
        System.out.printf("Macam Skill Item Player: %d\n", skillItemCount);
        pw.write(skillItemCount.toString()+"\n");

        while(true) {
            //TEST
            int count = 1;
            for(SkillItem playerSkillItem : inventoryS.getArray()) {
                Integer jumlahSkillItem = playerSkillItem.getJumlah();
                System.out.printf("Player Skill Item: %s jumlah %d\n",playerSkillItem.getSkill().getSkillName(),jumlahSkillItem);
                pw.write(jumlahSkillItem.toString()+"\n");
                pw.write(playerSkillItem.getSkill().getSkillName()+"\n");
                System.out.printf("Detail Skill Item:\nNama Skill: %s\nBase Power: %d\nMastery Level: %d\n",playerSkillItem.getSkill().getSkillName(), playerSkillItem.getSkill().getBasePower(), playerSkillItem.getSkill().getMasteryLevel());
                Integer basePower = playerSkillItem.getSkill().getBasePower();
                Integer masteryLvl = playerSkillItem.getSkill().getMasteryLevel();
                pw.write(basePower.toString()+"\n");
                pw.write(masteryLvl.toString()+"\n");

                System.out.printf("Elements Skill: ");
                int numOfEl = playerSkillItem.getSkill().getElement().size();
                int elementCount = 1;
                for(Element element : playerSkillItem.getSkill().getElement()) {
                    if (elementCount < numOfEl) {
                        System.out.printf("%s ", element.toString());
                        pw.write(element.toString()+" ");
                    }
                    else{
                        System.out.printf("%s\n", element.toString());
                        pw.write(element.toString()+"\n");
                    }
                    elementCount++;
                }
                
            }
            break;
        }

        //Save Player MaxInventory Limit
        Integer playerMaxInv = currentState.getPlayer().getMaxInv();
        System.out.printf("Limit inventory player: %d\n", playerMaxInv);
        pw.write(playerMaxInv.toString()+"\n");

        //Save Player ID Active Engimon
        Integer idActiveEngimon = currentState.getPlayer().getIdActiveEngimon();
        System.out.printf("ID Active Engimon milik Player: %d\n", idActiveEngimon);
        pw.write(idActiveEngimon.toString()+"\n");

        //Save Wild Engimon on Map
        ArrayList<Engimon> wildEngis =  currentState.getWildEngi();
        Integer wildEngisCount = wildEngis.size();
        System.out.printf("Jumlah Wild Engimon: %d\n", wildEngisCount);

        while(true) {
            //TEST
            int count = 1;
            for(Engimon wildEngi : wildEngis) {
                System.out.println("======================");
                //Nama Engimon
                System.out.printf("Nama Wild Engimon Engimon %d: ", count);
                System.out.println(wildEngi.getName());
                pw.write(wildEngi.getName()+"\n");

                int parentCount = 1;
                for(String parentName : wildEngi.getParentNames()) {
                    //Nama parent(s)
                    System.out.printf("Nama parent %d dari Wild Engimon %d: ",parentCount, count);
                    System.out.println(parentName);
                    pw.write(parentName+"\n");
                    
                    parentCount++;
                }

                //Species Engimon
                System.out.printf("Species Wild Engimon %d: %s\n", count, wildEngi.getSpecies());
                pw.write(wildEngi.getSpecies()+"\n");


                //Element Engimon
                System.out.printf("Element Wild Engimon %d: %s dan %s\n",count,wildEngi.getElement1().toString(),wildEngi.getElement2().toString());
                pw.write(wildEngi.getElement1().toString()+" "+wildEngi.getElement2().toString()+"\n");

                //Sound Engimon
                System.out.printf("Sound  Wild Engimon %d: %s\n",count, wildEngi.getSound());
                pw.write(wildEngi.getSound()+"\n");

                //MaxExp Engimon
                Integer maxWildExp = wildEngi.getMaxExp();
                System.out.printf("MaxExp Wild Engimon %d: %s\n",count, maxWildExp.toString());
                pw.write(maxWildExp.toString()+"\n");

                //Exp Engimon
                Integer wildExp = wildEngi.getExp();
                System.out.printf("Exp Wild Engimon %d: %s\n",count, wildExp.toString());
                pw.write(wildExp.toString()+"\n");

                //Cumulative Exp Engimon
                Integer cumWildExp = wildEngi.getCml();
                System.out.printf("Cumulative Exp Wild Engimon %d: %s\n",count, cumWildExp.toString());
                pw.write(cumWildExp.toString()+"\n");

                //Position Engimon
                Integer engiPosX = wildEngi.getEngimonPos().getX();
                Integer engiPosY = wildEngi.getEngimonPos().getX();
                System.out.printf("Posisi Wild Engimon %d: %d %d\n",count,engiPosX,engiPosY);
                pw.write(engiPosX.toString()+" "+engiPosY.toString()+"\n");

                //Health Engimon
                Integer health = wildEngi.getHealth();
                System.out.printf("Health Wild Engimon %d: %d\n", count, health);
                pw.write(health.toString()+"\n");

                //Level Pembeda Tampilan Engimon
                Integer levelPembeda = currentState.getLevel();
                System.out.printf("Level pembeda tampilan Engimon: %d\n", levelPembeda);
                pw.write(levelPembeda.toString()+"\n");

                //Round Game
                Integer roundGame = currentState.getRound();
                System.out.printf("Round saat ini adalah: %d\n", roundGame);
                pw.write(roundGame.toString()+"\n");

                //Game State
                String stateGame = currentState.getGameState().toString();
                System.out.printf("Game State saat ini adalah: %s\n", stateGame);
                pw.write(stateGame);

                //END OF WILD ENGIMON
                System.out.println("======================\n");
                count++;
            }
            break;
        }


        //END OF SAVE
        pw.close();

    }

    public static Map Load (String fileToLoad) {
        //Belum diimplementasiin
        return new Map(5, "Map.txt", 5);
    }
}