import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;


public class SaveLoad {
    private static Engimon engiPlayer;
    private static Player player = new Player();
    private static ArrayList<Engimon> wildEngiList = new ArrayList<>();
    private static ArrayList<String> mapCells = new ArrayList<>();
    

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
                //Skill Engimon
                int countSkill = 1;
                Integer engimonSkillCount = playerEngi.getSkill().size();
                System.out.printf("Jumlah skill Engimon %d: %d\n",count, engimonSkillCount);
                pw.write(engimonSkillCount.toString()+"\n");
                for(Skill engiSkill : playerEngi.getSkill()){
                    Integer basePower = engiSkill.getBasePower();
                    Integer masteryLevel = engiSkill.getMasteryLevel();
                    System.out.printf("Nama skill %d dari Engimon %d: %s\n",countSkill,count,engiSkill.getSkillName());
                    System.out.printf("Base Power skill %d dari Engimon %d: %s\n",countSkill,count,engiSkill.getBasePower());
                    System.out.printf("Mastery skill %d dari Engimon %d: %s\n",countSkill,count,engiSkill.getMasteryLevel());
                    pw.write(engiSkill.getSkillName()+"\n");
                    pw.write(basePower+"\n");
                    pw.write(masteryLevel+"\n");

                    int countSkillElement = 1;
                    for (Element playerEngiSkillElement : engiSkill.getElement()) {
                        if (countSkillElement < engiSkill.getElement().size()) {
                            System.out.printf("%s ", playerEngiSkillElement);
                            pw.write(playerEngiSkillElement.toString()+" ");
                        } else {
                            System.out.printf("%s\n", playerEngiSkillElement);
                            pw.write(playerEngiSkillElement.toString()+"\n");
                        }
                        countSkillElement++;
                    }
                    countSkill++;
                }

                //Nama Engimon
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

                //Parent Species
                int parentSpeciesCount = 1;
                for(String parentSpecies : playerEngi.getParentSpecies()) {
                    //Nama parent(s)
                    System.out.printf("Species parent %d dari Engimon Player %d: ",parentSpeciesCount, count);
                    System.out.println(parentSpecies);
                    pw.write(parentSpecies+"\n");
                    
                    parentSpeciesCount++;
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
        pw.write(wildEngisCount.toString()+"\n");

        while(true) {
            //TEST
            int count = 1;
            for(Engimon wildEngi : wildEngis) {
                System.out.println("======================");
                //Skill Engimon
                int countSkill = 1;
                Integer engimonSkillCount = wildEngi.getSkill().size();
                System.out.printf("Jumlah skill Wild Engimon %d: %d\n",count, engimonSkillCount);
                pw.write(engimonSkillCount.toString()+"\n");
                for(Skill engiSkill : wildEngi.getSkill()){
                    Integer basePower = engiSkill.getBasePower();
                    Integer masteryLevel = engiSkill.getMasteryLevel();
                    System.out.printf("Nama skill %d dari Wild Engimon %d: %s\n",countSkill,count,engiSkill.getSkillName());
                    System.out.printf("Base Power skill %d dari Wild Engimon %d: %s\n",countSkill,count,engiSkill.getBasePower());
                    System.out.printf("Mastery skill %d dari Wild Engimon %d: %s\n",countSkill,count,engiSkill.getMasteryLevel());
                    pw.write(engiSkill.getSkillName()+"\n");
                    pw.write(basePower+"\n");
                    pw.write(masteryLevel+"\n");

                    int countSkillElement = 1;
                    for (Element wildEngiSkillElement : engiSkill.getElement()) {
                        if (countSkillElement < engiSkill.getElement().size()) {
                            System.out.printf("%s ", wildEngiSkillElement);
                            pw.write(wildEngiSkillElement.toString()+" ");
                        } else {
                            System.out.printf("%s\n", wildEngiSkillElement);
                            pw.write(wildEngiSkillElement.toString()+"\n");
                        }
                        countSkillElement++;
                    }
                    countSkill++;
                }
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

                //Parent Species
                int parentSpeciesCount = 1;
                for(String parentSpecies : wildEngi.getParentSpecies()) {
                    //Nama parent(s)
                    System.out.printf("Species parent %d dari Wild Engimon %d: ",parentSpeciesCount, count);
                    System.out.println(parentSpecies);
                    pw.write(parentSpecies+"\n");
                    
                    parentSpeciesCount++;
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

                //END OF WILD ENGIMON
                System.out.println("======================\n");
                count++;
            }
            break;
        }

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

        //END OF SAVE


        //CLOSING FILE
        pw.close();

    }

    public static Map Load (String fileToLoad) throws FileNotFoundException {
        
        //UNDER CONSTRUCTION
        System.out.printf("==============LOAD GAME==============\n");
        File myObj = new File(fileToLoad);
        Scanner myReader = new Scanner(myObj);

        //++++++++++ATTRIBUTE MAP++++++++++++
        //Read Number of Map Cells
        System.out.println("****start of map****");
        int ncells = Integer.parseInt(myReader.nextLine());
        System.out.printf("Banyaknya cells hasil load: %d\n", ncells);
        int countCells = 0;

        //Add cell(s)
        System.out.println("Bentuk map:");
        // ArrayList<String> mapMatrix = new ArrayList<>();
        while (countCells < ncells) {
            mapCells.add(myReader.nextLine());
            System.out.println(mapCells.get(countCells));
            countCells++;
        }
        System.out.println("****end of map****\n");

        //++++++++++++ATTRIBUTE PLAYER++++++++++
        System.out.println("****start of player****");
        Player player = new Player();
        //Read position
        String[] posPlayer = myReader.nextLine().split(" ");
        //Set player position
        player.setPlayerPos(Integer.parseInt(posPlayer[0]),Integer.parseInt(posPlayer[1]));
        System.out.printf("Posisi X Player: %s\nPosisi Y Player: %s\n",player.getPlayerPos().getX(),player.getPlayerPos().getY());
        //Read player number of engimon
        int numOfEngi = Integer.parseInt(myReader.nextLine());
        System.out.printf("Jumlah engimon milik player: %d\n",numOfEngi);
        int countEngiPlayer = 0;
        while (countEngiPlayer < numOfEngi) {
            int numOfEngiSkills = Integer.parseInt(myReader.nextLine());
            int countEngiSkills = 0;
            System.out.printf("Jumlah Skill Engimon %d: %d\n",countEngiPlayer+1,numOfEngiSkills);
            Vector<Skill> allEngiSkill = new Vector<Skill>(); // INI KEPAKE
            while(countEngiSkills < numOfEngiSkills) {
                String namaSkill = myReader.nextLine();
                int basePower = Integer.parseInt(myReader.nextLine());
                int masteryLevel = Integer.parseInt(myReader.nextLine());
                String[] listOfElements = myReader.nextLine().split(" ");
                System.out.printf("Banyak element skill: %d\n",listOfElements.length);
                Vector<Element> engiElements = new Vector<Element>();
                engiElements.add(Skill.stringToElement(listOfElements[0]));
                engiElements.add(Skill.stringToElement(listOfElements[1]));
                
                Skill sk = new Skill(namaSkill, basePower, masteryLevel, engiElements);
                allEngiSkill.add(sk);
                countEngiSkills++;
            }
            System.out.printf("Yang ini jumlah skill dari vector: %d\n",allEngiSkill.size());

            String engiName = myReader.nextLine();
            String parent1 = myReader.nextLine();
            String parent2 = myReader.nextLine();
            String spcParent1 = myReader.nextLine();
            String spcParent2 = myReader.nextLine();
            String species = myReader.nextLine();
            String[] listOfEngiElements = myReader.nextLine().split(" ");
            String sound = myReader.nextLine();
            int maxExp = Integer.parseInt(myReader.nextLine());
            int exp = Integer.parseInt(myReader.nextLine());
            int cumExp = Integer.parseInt(myReader.nextLine());
            String[] engiPos = myReader.nextLine().split(" ");
            int health = Integer.parseInt(myReader.nextLine());

            switch(species) {
                case "Firemon":
                    player.getInventoryE().add(new Firemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Watermon":
                    player.getInventoryE().add(new Watermon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Electricmon":
                    player.getInventoryE().add(new Electricmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Groundmon":
                    player.getInventoryE().add(new Groundmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Icemon":
                    player.getInventoryE().add(new Icemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "FireElectricmon":
                    player.getInventoryE().add(new FireElectricmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "WaterGroundmon":
                    player.getInventoryE().add(new WaterGroundmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "WaterIcemon":
                    player.getInventoryE().add(new WaterIcemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
            }
            countEngiPlayer++;
        }

        //Testing Engimon Player        
        for(Engimon engiPlayer : player.getInventoryE().getArray()) {
            engiPlayer.printData();
        }

        //Add Skill Item
        int kindOfSkillItem = Integer.parseInt(myReader.nextLine());
        int countSkillItem = 0;

        Vector<SkillItem> allSkillItem = new Vector<SkillItem>(); //Kayanya ga kepake
        while(countSkillItem < kindOfSkillItem) {
            int jumlahItem = Integer.parseInt(myReader.nextLine());
            String namaSkill = myReader.nextLine();
            int basePower = Integer.parseInt(myReader.nextLine());
            int masteryLevel = Integer.parseInt(myReader.nextLine());
            String[] listOfElements = myReader.nextLine().split(" ");
            System.out.printf("Banyak element skill: %d\n",listOfElements.length);
            Vector<Element> skillItemElements = new Vector<Element>();
            skillItemElements.add(Skill.stringToElement(listOfElements[0]));
            skillItemElements.add(Skill.stringToElement(listOfElements[1]));
                
            Skill sk = new Skill(namaSkill, basePower, masteryLevel, skillItemElements);
            allSkillItem.add(new SkillItem(sk, jumlahItem));

            player.getInventoryS().add(new SkillItem(sk, jumlahItem));
            countSkillItem++;
        }

        System.out.printf("Macam skill item di variable akhir: %d\n", player.getInventoryS().getSize());

        //Read wild engi
        //Ini max Inv, di skip aja
        myReader.nextLine();

        //Ini ID Active engimon
        int idActiveEngimon = Integer.parseInt(myReader.nextLine());
        player.setActiveEngi(idActiveEngimon);
        System.out.printf("ID Active Engimon: %d\n", idActiveEngimon);

        int numOfEngiLiar = Integer.parseInt(myReader.nextLine());
        System.out.printf("Jumlah engimon liar: %d\n",numOfEngiLiar);
        int countEngiLiar = 0;
        while (countEngiLiar < numOfEngiLiar) {
            int numOfEngiSkills = Integer.parseInt(myReader.nextLine());
            int countEngiSkills = 0;
            System.out.printf("Jumlah Skill Engimon %d: %d\n",countEngiLiar+1,numOfEngiSkills);
            Vector<Skill> allEngiSkill = new Vector<Skill>(); // INI KEPAKE
            while(countEngiSkills < numOfEngiSkills) {
                String namaSkill = myReader.nextLine();
                int basePower = Integer.parseInt(myReader.nextLine());
                int masteryLevel = Integer.parseInt(myReader.nextLine());
                String[] listOfElements = myReader.nextLine().split(" ");
                System.out.printf("Banyak element skill: %d\n",listOfElements.length);
                Vector<Element> engiElements = new Vector<Element>();
                engiElements.add(Skill.stringToElement(listOfElements[0]));
                engiElements.add(Skill.stringToElement(listOfElements[1]));
                
                Skill sk = new Skill(namaSkill, basePower, masteryLevel, engiElements);
                allEngiSkill.add(sk);
                countEngiSkills++;
            }
            System.out.printf("Yang ini jumlah skill dari vector: %d\n",allEngiSkill.size());

            String engiName = myReader.nextLine();
            String parent1 = myReader.nextLine();
            String parent2 = myReader.nextLine();
            String spcParent1 = myReader.nextLine();
            String spcParent2 = myReader.nextLine();
            String species = myReader.nextLine();
            String[] listOfEngiElements = myReader.nextLine().split(" ");
            String sound = myReader.nextLine();
            int maxExp = Integer.parseInt(myReader.nextLine());
            int exp = Integer.parseInt(myReader.nextLine());
            int cumExp = Integer.parseInt(myReader.nextLine());
            String[] engiPos = myReader.nextLine().split(" ");
            int health = Integer.parseInt(myReader.nextLine());

            switch(species) {
                case "Firemon":
                    wildEngiList.add(new Firemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Watermon":
                    wildEngiList.add(new Watermon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Electricmon":
                    wildEngiList.add(new Electricmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Groundmon":
                    wildEngiList.add(new Groundmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "Icemon":
                    wildEngiList.add(new Icemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "FireElectricmon":
                    wildEngiList.add(new FireElectricmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "WaterGroundmon":
                    wildEngiList.add(new WaterGroundmon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
                case "WaterIcemon":
                    wildEngiList.add(new WaterIcemon(engiName, parent1, parent2, spcParent1, spcParent2, exp, Integer.parseInt(engiPos[0]), Integer.parseInt(engiPos[1]), health, cumExp, maxExp, allEngiSkill));
                    break;
            }
            countEngiLiar++;
        }

        //Test engi liar


        for (Engimon engiLiar : wildEngiList) {
            engiLiar.printData();
        }

        int levelPembeda = Integer.parseInt(myReader.nextLine());
        int round = Integer.parseInt(myReader.nextLine());
        String gameState = myReader.nextLine();

        System.out.println("****end of player****\n");
        //Read from current to end
        // while (myReader.hasNextLine()) {
        //     String data = myReader.nextLine();
        //     System.out.println(data);
        // }
        //END OF LOAD
        myReader.close();
        System.out.printf("===========END OF LOAD GAME==========\n");


        //Ntar diganti sama constructor yang manggil data-data dari Load
        System.out.println("Cek!");
        return new Map(mapCells, player, wildEngiList, levelPembeda, round); //DUMMY DATA
    }
}