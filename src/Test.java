import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileOutputStream fos = null;
        File file;

        boolean offSwitch=true;

        while (offSwitch){
            System.out.println("");
            System.out.println("""
                    Welcome to the game CANNON FODDER!
                    -----------------------------------------------------------------------------------------------
                    Press 1 to start the game
                    Press 2 to view high scores
                    Press 3 to terminate the game"""
            );
            System.out.println("Your choice?:");
            int usersMenuChoice = input.nextInt();
            switch (usersMenuChoice){
                case 1:
                    SecureRandom random = new SecureRandom();
                    int smallNumber = random.nextInt(1,5);
                    int mediumNumber = random.nextInt(3,7);
                    int largeNumber = random.nextInt(6,10);

                    Fighter myFighter = new Fighter(largeNumber,mediumNumber,smallNumber,true,((2*largeNumber)+(7*mediumNumber)+(smallNumber)), false);
                    Tank myTank = new Tank(smallNumber,largeNumber,mediumNumber,true,((2*smallNumber)+(7*largeNumber)+mediumNumber), false);
                    Healer myHealer = new Healer(mediumNumber,smallNumber,largeNumber,true,((2*mediumNumber)+largeNumber+(7*smallNumber)), false);
                    Armor lightArmor = new Armor("Light Armor",2,1);
                    ShortBlade shortBlade = new ShortBlade(5,1);
                    WoodenWand woodenWand = new WoodenWand(3,1);
                    RoundShield roundShield = new RoundShield();
                    WoodenShield woodenShield = new WoodenShield();
                    GoldenWand goldenWand = new GoldenWand();

                    myFighter.setActiveWeapon(shortBlade);
                    myFighter.setActiveArmor(lightArmor);
                    myTank.setActiveArmor(lightArmor);
                    myTank.setActiveWeapon(woodenShield);
                    myHealer.setActiveArmor(lightArmor);
                    myHealer.setActiveWeapon(woodenWand);
                    int score = 0;

                    System.out.println("Fighter created with S:" + myFighter.getStrength() + " V:" + myFighter.getVitality() + " I:" + myFighter.getIntelligence() + ".The HP is " + myFighter.getHP() + ".Fighter wields "+shortBlade.getName()+" with " + shortBlade.getValue() + " damages and " + shortBlade.getWeight() + " units of weight.");
                    System.out.println("Tank created with S:" + myTank.getStrength() + " V:" + myTank.getVitality() + " I:" + myTank.getIntelligence() + ".The HP is " + myTank.getHP() + ".Tank wields "+roundShield.getName()+" with " +roundShield.calculateDamage(myTank)+ " damages and " + roundShield.getWeight() + " unit of weight.");
                    System.out.println("Healer created with S:" + myHealer.getStrength() + " V:" + myHealer.getVitality() + " I:" + myHealer.getIntelligence() + ".The HP is " + myHealer.getHP() + ".Healer wields "+goldenWand.getName()+" with " + woodenWand.getValue() + " damages and " + woodenWand.getWeight() + " unit of weight.");
                    int healerMaxHP=myHealer.getHP();
                    int fighterMaxHP=myFighter.getHP();
                    int tankMaxHP=myTank.getHP();
                    myFighter.inventory.add(shortBlade);
                    myFighter.inventory.add(lightArmor);
                    myTank.inventory.add(lightArmor);
                    myTank.inventory.add(woodenShield);
                    myHealer.inventory.add(lightArmor);
                    myHealer.inventory.add(woodenWand);
                    for (int i = 0; ; i++) {
                        if (!myTank.isAlive && !myFighter.isAlive && !myHealer.isAlive) {
                            System.out.println("*******GAME OVER*******");
                            System.out.println("Your score is: " + score);
                            System.out.println("Please enter username:");
                            String userName = input.next();
                            String tempUser = userName+" - "+score;
                            try {
                                file = new File("D:/highscores.txt");
                                fos = new FileOutputStream(file);

                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                byte[] bytesArray = tempUser.getBytes();

                                fos.write(bytesArray);
                                fos.flush();
                                System.out.println("File Written Successfully");
                            }catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                            finally {
                                try {
                                    if (fos != null)
                                    {
                                        fos.close();
                                    }
                                }
                                catch (IOException ioe) {
                                    System.out.println("Error in closing the Stream");
                                }
                            }
                            break;
                        }
                        int enemyStunCounter = 0;
                        int deathEnemyCounter = 0;
                        int fighterInactivityCounter=0;
                        ArrayList<Enemy> enemyList = new ArrayList<>();
                        ArrayList<Item> droppedItemList = new ArrayList<>();

                        System.out.println("Level " + (i + 1));

                        int enemyGenerator = (int) Math.pow(2, i);
                        for (int k = 0; k < enemyGenerator; k++) {
                            int enemyStats = random.nextInt(1,5);
                            Enemy newEnemy = new Enemy(enemyStats,enemyStats,enemyStats,true,(10*enemyStats),false);
                            newEnemy.setEnemyName("Enemy" + (k + 1));
                            enemyList.add(newEnemy);
                        }
                        for (Enemy enemy : enemyList) {
                            int enemyWeapon = random.nextInt(10);
                            System.out.println(enemy.getEnemyName() + " created.");

                            if(enemyWeapon==0){
                                enemy.setActiveWeapon(woodenShield);
                                System.out.println("Enemy equipped Wooden Shield");
                            }else if(enemyWeapon==1){
                                enemy.setActiveWeapon(woodenWand);
                                System.out.println("Enemy equipped Wooden Wand");
                            }else{
                                enemy.setActiveWeapon(shortBlade);
                                System.out.println("Enemy equipped Short Blade");
                            }
                        }

                        int tempSize = enemyList.size();
                        boolean turnPass = true;



                        while ((myTank.isAlive || myFighter.isAlive || myHealer.isAlive) && deathEnemyCounter<tempSize ) {

                            if(turnPass) {

                                System.out.println("""
                                            Please choose the character which you want to play:
                                            ----------------------------------
                                            Press 1 to Fighter
                                            Press 2 to Tank
                                            Press 3 to Healer"""
                                );

                                int characterChoice = input.nextInt();

                                if (characterChoice == 1) {
                                    if(!myFighter.isAlive()){
                                        System.out.println("Fighter is dead. Please choose another character");
                                        continue;
                                    }
                                    if(myFighter.isStunned()){
                                        System.out.println("Fighter is inactive right now. Please choose another character");
                                        continue;
                                    }
                                    myFighter.characterMenu();
                                    int userMenuChoice = input.nextInt();
                                    switch (userMenuChoice){
                                        case 1:
                                            for (int j=0;j<enemyList.size();j++){
                                                System.out.println((j+1)+". "+enemyList.get(j).getEnemyName());
                                            }
                                            System.out.println("Choose the enemy you want to attack");
                                            int userEnemyChoice = (input.nextInt()-1);
                                            enemyList.get(userEnemyChoice).setHP(enemyList.get(userEnemyChoice).getHP()-myFighter.getActiveWeapon().calculateDamage(myFighter));
                                            System.out.println("Enemy take "+myFighter.getActiveWeapon().calculateDamage(myFighter)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getHP());
                                            if(enemyList.get(userEnemyChoice).getHP()<=0){
                                                System.out.println(enemyList.get(userEnemyChoice).getEnemyName()+" is dead");
                                                enemyList.get(userEnemyChoice).setAlive(false);
                                                enemyList.remove(userEnemyChoice);
                                                score +=1;
                                                deathEnemyCounter +=1;
                                                int itemType = random.nextInt(11);
                                                if(itemType==0){
                                                    Excalibur myExcalibur = new Excalibur((i+10),(i+3));
                                                    droppedItemList.add(myExcalibur);
                                                    System.out.println("Excalibur dropped");
                                                }else if(itemType==1){
                                                    Katana myKatana = new Katana((i+8),(i+2));
                                                    droppedItemList.add(myKatana);
                                                    System.out.println("Katana dropped");
                                                }else if(itemType==2){
                                                    ShortBlade myShortBlade = new ShortBlade((i+5),(i+1));
                                                    droppedItemList.add(myShortBlade);
                                                    System.out.println("Short blade dropped");
                                                }else if(itemType==3){
                                                    WoodenShield myWoodenShield = new WoodenShield((i+2),(i+1));
                                                    droppedItemList.add(myWoodenShield);
                                                    System.out.println("Wooden shield dropped");
                                                }else if(itemType==4){
                                                    RoundShield myRoundShield = new RoundShield((i+3),(i+2));
                                                    droppedItemList.add(myRoundShield);
                                                    System.out.println("Round shield dropped");
                                                }else if(itemType==5){
                                                    KiteShield myKiteShield = new KiteShield((i+5),(i+3));
                                                    droppedItemList.add(myKiteShield);
                                                    System.out.println("Kite shield dropped");
                                                }else if (itemType==6){
                                                    WoodenWand myWoodenWand = new WoodenWand((i+2),(i+1));
                                                    droppedItemList.add(myWoodenWand);
                                                    System.out.println("Wooden wand dropped");
                                                }else if (itemType==7){
                                                    GoldenWand myGoldenWand = new GoldenWand((i+5),(i+2));
                                                    droppedItemList.add(myGoldenWand);
                                                    System.out.println("Golden wand dropped");
                                                }else if (itemType==8){
                                                    Caduceus myCaduceus = new Caduceus((i+7),(i+3));
                                                    droppedItemList.add(myCaduceus);
                                                    System.out.println("Caduceus dropped");
                                                }else if (itemType==9){
                                                    Armor mySteelArmor = new Armor("Steel Armor",(i+3),(i+3));
                                                    droppedItemList.add(mySteelArmor);
                                                    System.out.println("Steel armor dropped");
                                                }else if (itemType==10){
                                                    Armor myLeatherArmor = new Armor("Leather Armor",(i+2),(i+1));
                                                    droppedItemList.add(myLeatherArmor);
                                                    System.out.println("Leather armor dropped");
                                                }else if (itemType==11){
                                                    Armor myVibraniumArmor = new Armor("Vibranium Armor",(i+5),(i+5));
                                                    droppedItemList.add(myVibraniumArmor);
                                                    System.out.println("Vibranium armor dropped");
                                                }



                                            }
                                            turnPass=false;
                                            break;

                                        case 2:
                                            if(!myHealer.isAlive()){
                                                System.out.println("You can not use this action because you are the only one alive");
                                                break;
                                            }
                                            shortBlade.specialAction(enemyList.get(0),myFighter);
                                            turnPass=false;
                                            break;

                                        case 3:
                                            myFighter.displayInventory();
                                            break;

                                        case 4:
                                            myFighter.selectItem();
                                            myFighter.equipItem(myFighter);
                                            break;

                                        case 5:
                                            myFighter.selectItem();
                                            myFighter.discardItem();
                                            break;

                                        case 6:
                                            myFighter.selectItem();
                                            myFighter.examineItem();
                                            break;

                                        case 7:
                                            System.out.println("Your damage: "+myFighter.getActiveWeapon().calculateDamage(myFighter));
                                            break;

                                        case 8:
                                            System.out.println("Your armor: "+myFighter.getActiveArmor().getValue());
                                            break;

                                        case 9:
                                            System.out.println("Your HP: "+myFighter.getHP());
                                            break;

                                    }
                                }else if(characterChoice==2){
                                    if(!myTank.isAlive()){
                                        System.out.println("Tank is dead. Please choose another character");
                                        continue;
                                    }
                                    myTank.characterMenu();
                                    int userMenuChoice = input.nextInt();
                                    switch (userMenuChoice){
                                        case 1:
                                            for (int j=0;j<enemyList.size();j++){
                                                System.out.println((j+1)+". "+enemyList.get(j).getEnemyName());
                                            }
                                            System.out.println("Choose the enemy you want to attack");
                                            int userEnemyChoice = (input.nextInt()-1);
                                            enemyList.get(userEnemyChoice).setHP(enemyList.get(userEnemyChoice).getHP()-myTank.getActiveWeapon().calculateDamage(myTank));
                                            System.out.println("Enemy take "+myTank.getActiveWeapon().calculateDamage(myTank)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getHP());
                                            if(enemyList.get(userEnemyChoice).getHP()<=0){
                                                System.out.println(enemyList.get(userEnemyChoice).getEnemyName()+" is dead");
                                                enemyList.get(userEnemyChoice).setAlive(false);
                                                enemyList.remove(userEnemyChoice);
                                                score +=1;
                                                deathEnemyCounter +=1;

                                            }
                                            turnPass=false;
                                            break;

                                        case 2:
                                            int stunChance = random.nextInt(enemyList.size());
                                            int stunPower = myTank.getActiveWeapon().stun(myTank);
                                            if(stunChance>stunPower){
                                                System.out.println("Enemy escape from stunning");
                                            }else {
                                                for(int n=0;n<enemyList.size();n++){
                                                    roundShield.specialAction(enemyList.get(n),myTank );
                                                }
                                                System.out.println("Enemy stunned for 2 turns");
                                            }
                                            turnPass=false;
                                            break;

                                        case 3:
                                            myTank.displayInventory();
                                            break;

                                        case 4:
                                            myTank.selectItem();
                                            myTank.equipItem(myTank);
                                            break;

                                        case 5:
                                            myTank.selectItem();
                                            myTank.discardItem();
                                            break;

                                        case 6:
                                            myTank.selectItem();
                                            myTank.examineItem();
                                            break;

                                        case 7:
                                            System.out.println("Your damage: "+myTank.getActiveWeapon().calculateDamage(myTank));
                                            break;

                                        case 8:
                                            System.out.println("Your armor: "+myTank.getActiveArmor().getValue());
                                            break;

                                        case 9:
                                            System.out.println("Your HP: "+myTank.getHP());
                                            break;

                                        default:
                                            System.out.println("Please enter a valid number");
                                            break;
                                    }

                                }else if(characterChoice==3){
                                    if(!myHealer.isAlive()){
                                        System.out.println("Healer is dead. Please choose another character");
                                        continue;
                                    }
                                    myHealer.characterMenu();

                                        int userMenuChoice = input.nextInt();

                                    switch (userMenuChoice){
                                        case 1:
                                            for (int j=0;j<enemyList.size();j++){
                                                System.out.println((j+1)+". "+enemyList.get(j).getEnemyName());
                                            }
                                            System.out.println("Choose the enemy you want to attack");
                                            int userEnemyChoice = (input.nextInt()-1);
                                            enemyList.get(userEnemyChoice).setHP(enemyList.get(userEnemyChoice).getHP()-myHealer.getActiveWeapon().calculateDamage(myHealer));
                                            System.out.println("Enemy take "+myHealer.getActiveWeapon().calculateDamage(myHealer)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getHP());
                                            if(enemyList.get(userEnemyChoice).getHP()<=0){
                                                System.out.println(enemyList.get(userEnemyChoice).getEnemyName()+" is dead");
                                                enemyList.get(userEnemyChoice).setAlive(false);
                                                enemyList.remove(userEnemyChoice);
                                                score +=1;
                                                deathEnemyCounter +=1;

                                            }
                                            turnPass=false;
                                            break;

                                        case 2:
                                            System.out.println("""
                                            Please choose the character to heal
                                            1.Fighter
                                            2.Tank
                                            3.Healer""");
                                            int healingCharacter = input.nextInt();
                                            if (healingCharacter==1 && !myFighter.isAlive()){
                                                System.out.println("Fighter is dead. Choose someone else");
                                                break;
                                            }else if (healingCharacter==1 && myFighter.isAlive()){
                                                goldenWand.specialAction(myFighter,myHealer);
                                                if(myFighter.getHP()>fighterMaxHP){
                                                    myFighter.setHP(fighterMaxHP);
                                                }
                                                turnPass=false;
                                            }else if (healingCharacter==2 && !myTank.isAlive()){
                                                System.out.println("Tank is dead. Choose someone else");
                                                break;
                                            }else if (healingCharacter==2 && myTank.isAlive()){
                                                goldenWand.specialAction(myTank,myHealer);
                                                if(myTank.getHP()>tankMaxHP){
                                                    myTank.setHP(tankMaxHP);
                                                }
                                                turnPass=false;
                                            }else if (healingCharacter==3 && myHealer.isAlive()){
                                                goldenWand.specialAction(myHealer,myHealer);
                                                if(myHealer.getHP()>healerMaxHP){
                                                    myHealer.setHP(healerMaxHP);
                                                }
                                                turnPass=false;
                                            }
                                            break;

                                        case 3:
                                            myHealer.displayInventory();
                                            break;

                                        case 4:
                                            myHealer.selectItem();
                                            myHealer.equipItem(myHealer);
                                            break;

                                        case 5:
                                            myHealer.selectItem();
                                            myHealer.discardItem();
                                            break;

                                        case 6:
                                            myHealer.selectItem();
                                            myHealer.examineItem();
                                            break;

                                        case 7:
                                            System.out.println("Your damage: "+myHealer.getActiveWeapon().calculateDamage(myHealer));
                                            break;

                                        case 8:
                                            System.out.println("Your armor: "+myHealer.getActiveArmor().getValue());
                                            break;

                                        case 9:
                                            System.out.println("Your HP: "+myHealer.getHP());
                                            break;
                                        default:
                                            System.out.println("Please enter a valid number");
                                            break;
                                    }
                                }else
                                    System.out.println("Please enter a valid number");

                            }else if(!turnPass){
                                SecureRandom rd = new SecureRandom();
                                int enemysTarget = rd.nextInt(2)+1;
                                if(enemyList.get(0).isAlive() && enemyList.get(0).isStunned() && enemyStunCounter<2){
                                    enemyStunCounter+=1;
                                    turnPass=true;
                                    continue;
                                }
                                if (enemyStunCounter>=2){
                                    enemyStunCounter=0;
                                    for(int n=0;n<enemyList.size();n++){
                                        enemyList.get(n).setStunned(false);
                                    }
                                }
                                if(myTank.isAlive()){
                                    myTank.setHP((myTank.getHP()+myTank.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                    System.out.println("The enemy attacked your Tank with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Tank: "+myTank.getHP());
                                    if(myTank.getHP()<=0){
                                        myTank.isAlive=false;
                                        System.out.println("Tank is dead");
                                    }
                                    turnPass=true;
                                }else if(!myTank.isAlive && myFighter.isAlive() && !myFighter.isStunned() && myHealer.isAlive()){
                                    if(enemysTarget==1){
                                        myFighter.setHP((myFighter.getHP()+myFighter.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                        System.out.println("The enemy attacked your Fighter with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Fighter: "+myFighter.getHP());
                                        if(myFighter.getHP()<=0){
                                            myFighter.isAlive=false;
                                            System.out.println("Fighter is dead");
                                        }
                                        turnPass=true;
                                    }else if(enemysTarget==2){
                                        myHealer.setHP((myHealer.getHP()+myHealer.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                        System.out.println("The enemy attacked your Healer with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Healer: "+myHealer.getHP());
                                        if(myHealer.getHP()<=0){
                                            myHealer.isAlive=false;
                                            System.out.println("Healer is dead");
                                        }
                                        turnPass=true;
                                    }
                                }else if(!myTank.isAlive && myFighter.isAlive() && myFighter.isStunned() && myHealer.isAlive()){
                                    myHealer.setHP((myHealer.getHP()+myHealer.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(myHealer));
                                    System.out.println("The enemy attacked your Healer with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Healer: "+myHealer.getHP());
                                    if(myHealer.getHP()<=0){
                                        myHealer.isAlive=false;
                                        System.out.println("Healer is dead");
                                    }
                                    if(fighterInactivityCounter>3){
                                            myFighter.setStunned(false);
                                            fighterInactivityCounter=0;
                                        }
                                        fighterInactivityCounter +=1;

                                    turnPass=true;
                                }else if((myFighter.isAlive() && !myHealer.isAlive) || (!myFighter.isAlive && myHealer.isAlive())){
                                    if(myFighter.isAlive){
                                        myFighter.setHP((myFighter.getHP()+myFighter.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(myFighter));
                                        System.out.println("The enemy attacked your Fighter with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Fighter: "+myFighter.getHP());
                                        if(myFighter.getHP()<=0){
                                            myFighter.isAlive=false;
                                            System.out.println("Fighter is dead. Game Over");
                                            break;
                                        }
                                        turnPass=true;
                                    }else if(myHealer.isAlive){
                                        myHealer.setHP((myHealer.getHP()+myHealer.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(myHealer));
                                        System.out.println("The enemy attacked your Healer with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Healer: "+myHealer.getHP());
                                        if(myHealer.getHP()<=0){
                                            myHealer.isAlive=false;
                                            System.out.println("Healer is dead.Game Over");
                                            break;
                                        }
                                        turnPass=true;
                                    }

                                }

                            }

                        }


                        while (deathEnemyCounter>=tempSize) {

                            System.out.println("""
                                    Player can now loot
                                    Please choose the character which you want to play:
                                    ----------------------------------
                                    Press 1 to Fighter
                                    Press 2 to Tank
                                    Press 3 to Healer"""
                            );
                            int characterChoice = input.nextInt();


                            if (characterChoice == 1) {
                                myFighter.endLevelMenu();
                                int userChoice = input.nextInt();
                                if (userChoice == 1) {
                                    for (int m = 0; m < droppedItemList.size(); m++) {
                                        System.out.println((m + 1) + ". " + droppedItemList.get(m).getName());
                                    }
                                } else if (userChoice == 2) {
                                    System.out.println("Please select an item according to its list position ");
                                    int pickedItem = input.nextInt() - 1;
                                    myFighter.inventory.add(droppedItemList.get(pickedItem));
                                    droppedItemList.remove(pickedItem);
                                } else if (userChoice == 3) {
                                    myFighter.displayInventory();
                                } else if (userChoice == 4) {
                                    myFighter.selectItem();
                                    myFighter.equipItem(myFighter);
                                } else if (userChoice == 5) {
                                    myFighter.selectItem();
                                    myFighter.discardItem();
                                } else if (userChoice == 6) {
                                    myFighter.selectItem();
                                    myFighter.examineItem();
                                } else if (userChoice == 7) {
                                    System.out.println("Your damage: " + myFighter.getActiveWeapon().calculateDamage(myFighter));
                                } else if (userChoice == 8) {
                                    System.out.println("Your armor: " + myFighter.getActiveArmor().getValue());
                                } else if (userChoice == 9) {
                                    System.out.println("Your HP: " + myFighter.getHP());
                                } else if (userChoice == 10){
                                    myFighter.setHP(myFighter.getHP() + (myFighter.getHP() / 3));
                                    if(myFighter.getHP()>fighterMaxHP){
                                        myFighter.setHP(fighterMaxHP);
                                        System.out.println("Fighter healed");
                                    }
                                    myTank.setHP(myTank.getHP() + (myTank.getHP() / 3));
                                    if(myTank.getHP()>tankMaxHP){
                                        myTank.setHP(tankMaxHP);
                                        System.out.println("Tank healed");
                                    }
                                    myHealer.setHP(myHealer.getHP() + (myHealer.getHP() / 3));
                                    if(myHealer.getHP()>healerMaxHP){
                                        myHealer.setHP(healerMaxHP);
                                        System.out.println("Healer healed");
                                    }
                                    break;
                                }

                            }else if(characterChoice==2){
                                myTank.endLevelMenu();
                                int userChoice = input.nextInt();
                                if (userChoice == 1) {
                                    for (int m = 0; m < droppedItemList.size(); m++) {
                                        System.out.println((m + 1) + ". " + droppedItemList.get(m).getName());
                                    }
                                } else if (userChoice == 2) {
                                    System.out.println("Please select an item according to its list position ");
                                    int pickedItem = input.nextInt() - 1;
                                    myTank.inventory.add(droppedItemList.get(pickedItem));
                                    droppedItemList.remove(pickedItem);
                                } else if (userChoice == 3) {
                                    myTank.displayInventory();
                                } else if (userChoice == 4) {
                                    myTank.selectItem();
                                    myTank.equipItem(myTank);
                                } else if (userChoice == 5) {
                                    myTank.selectItem();
                                    myTank.discardItem();
                                } else if (userChoice == 6) {
                                    myTank.selectItem();
                                    myTank.examineItem();
                                } else if (userChoice == 7) {
                                    System.out.println("Your damage: " + myTank.getActiveWeapon().calculateDamage(myTank));
                                } else if (userChoice == 8) {
                                    System.out.println("Your armor: " + myTank.getActiveArmor().getValue());
                                } else if (userChoice == 9) {
                                    System.out.println("Your HP: " + myTank.getHP());
                                } else if (userChoice == 10){
                                    myFighter.setHP(myFighter.getHP() + (myFighter.getHP() / 3));
                                    if(myFighter.getHP()>fighterMaxHP){
                                        myFighter.setHP(fighterMaxHP);
                                        System.out.println("Fighter healed");
                                    }
                                    myTank.setHP(myTank.getHP() + (myTank.getHP() / 3));
                                    if(myTank.getHP()>tankMaxHP){
                                        myTank.setHP(tankMaxHP);
                                        System.out.println("Tank healed");
                                    }
                                    myHealer.setHP(myHealer.getHP() + (myHealer.getHP() / 3));
                                    if(myHealer.getHP()>healerMaxHP){
                                        myHealer.setHP(healerMaxHP);
                                        System.out.println("Healer healed");
                                    }
                                    break;
                                }
                            }else if(characterChoice==3){
                                myHealer.endLevelMenu();
                                int userChoice = input.nextInt();
                                if (userChoice == 1) {
                                    for (int m = 0; m < droppedItemList.size(); m++) {
                                        System.out.println((m + 1) + ". " + droppedItemList.get(m).getName());
                                    }
                                } else if (userChoice == 2) {
                                    System.out.println("Please select an item according to its list position ");
                                    int pickedItem = input.nextInt() - 1;
                                    myHealer.inventory.add(droppedItemList.get(pickedItem));
                                    droppedItemList.remove(pickedItem);
                                } else if (userChoice == 3) {
                                    myHealer.displayInventory();
                                } else if (userChoice == 4) {
                                    myHealer.selectItem();
                                    myHealer.equipItem(myTank);
                                } else if (userChoice == 5) {
                                    myHealer.selectItem();
                                    myHealer.discardItem();
                                } else if (userChoice == 6) {
                                    myHealer.selectItem();
                                    myHealer.examineItem();
                                } else if (userChoice == 7) {
                                    System.out.println("Your damage: " + myHealer.getActiveWeapon().calculateDamage(myHealer));
                                } else if (userChoice == 8) {
                                    System.out.println("Your armor: " + myHealer.getActiveArmor().getValue());
                                } else if (userChoice == 9) {
                                    System.out.println("Your HP: " + myHealer.getHP());
                                } else if (userChoice == 10){
                                    myFighter.setHP(myFighter.getHP() + (myFighter.getHP() / 3));
                                    if(myFighter.getHP()>fighterMaxHP){
                                        myFighter.setHP(fighterMaxHP);
                                        System.out.println("Fighter healed");
                                    }
                                    myTank.setHP(myTank.getHP() + (myTank.getHP() / 3));
                                    if(myTank.getHP()>tankMaxHP){
                                        myTank.setHP(tankMaxHP);
                                        System.out.println("Tank healed");
                                    }
                                    myHealer.setHP(myHealer.getHP() + (myHealer.getHP() / 3));
                                    if(myHealer.getHP()>healerMaxHP){
                                        myHealer.setHP(healerMaxHP);
                                        System.out.println("Healer healed");
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    break;
                case 2:
                    try {
                        FileInputStream data = new FileInputStream("D:/highscores.txt");

                        System.out.println("Data in the file: ");


                        int i = data.read();

                        while(i != -1) {
                            System.out.print((char)i);


                            i = data.read();
                        }

                    }

                    catch(Exception e) {
                        e.getStackTrace();
                    }

                    break;
                case 3:
                    offSwitch = false;
                    break;

                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }

    }
}
