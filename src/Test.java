import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);



        boolean offSwitch=true;

        while (offSwitch){
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

                    Fighter myFighter = new Fighter(largeNumber,mediumNumber,smallNumber,true,((2*largeNumber)+(7*mediumNumber)+(smallNumber)));
                    Tank myTank = new Tank(smallNumber,largeNumber,mediumNumber,true,((2*smallNumber)+(7*largeNumber)+mediumNumber));
                    Healer myHealer = new Healer(mediumNumber,smallNumber,largeNumber,true,((2*mediumNumber)+largeNumber+(7*smallNumber)));
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

                    System.out.println("Fighter created with S:" + myFighter.getStrength() + " V:" + myFighter.getVitality() + " I:" + myFighter.getIntelligence() + ".The HP is " + myFighter.getFighterHP() + ".Fighter wields "+shortBlade.getName()+" with " + shortBlade.getValue() + " damages and " + shortBlade.getWeight() + " units of weight.");
                    System.out.println("Tank created with S:" + myTank.getStrength() + " V:" + myTank.getVitality() + " I:" + myTank.getIntelligence() + ".The HP is " + myTank.getTankHP() + ".Tank wields "+roundShield.getName()+" with " +roundShield.calculateDamage(myTank)+ " damages and " + roundShield.getWeight() + " unit of weight.");
                    System.out.println("Healer created with S:" + myHealer.getStrength() + " V:" + myHealer.getVitality() + " I:" + myHealer.getIntelligence() + ".The HP is " + myHealer.getHealerHP() + ".Healer wields "+goldenWand.getName()+" with " + woodenWand.getValue() + " damages and " + woodenWand.getWeight() + " unit of weight.");
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
                            break;
                        }
                        int deathEnemyCounter = 0;
                        ArrayList<Enemy> enemyList = new ArrayList<>();
                        ArrayList<Item> droppedItemList = new ArrayList<>();

                        System.out.println("Level " + (i + 1));

                        int enemyGenerator = (int) Math.pow(2, i);
                        for (int k = 0; k < enemyGenerator; k++) {
                            int enemyStats = random.nextInt(1,5);
                            Enemy newEnemy = new Enemy(enemyStats,enemyStats,enemyStats,true,(10*enemyStats));
                            newEnemy.setEnemyName("Enemy" + (k + 1));
                            enemyList.add(newEnemy);
                        }
                        for (Enemy enemy : enemyList) {
                            System.out.println(enemy.getEnemyName() + " created.");
                            enemy.setActiveWeapon(shortBlade);
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
                                    myFighter.characterMenu();
                                    int userMenuChoice = input.nextInt();
                                    switch (userMenuChoice){
                                        case 1:
                                            for (int j=0;j<enemyList.size();j++){
                                                System.out.println((j+1)+". "+enemyList.get(j).getEnemyName());
                                            }
                                            System.out.println("Choose the enemy you want to attack");
                                            int userEnemyChoice = (input.nextInt()-1);
                                            enemyList.get(userEnemyChoice).setEnemyHP(enemyList.get(userEnemyChoice).getEnemyHP()-myFighter.getActiveWeapon().calculateDamage(myFighter));
                                            System.out.println("Enemy take "+myFighter.getActiveWeapon().calculateDamage(myFighter)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getEnemyHP());
                                            if(enemyList.get(userEnemyChoice).getEnemyHP()<=0){
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
                                            System.out.println("Your HP: "+myFighter.getFighterHP());
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
                                            enemyList.get(userEnemyChoice).setEnemyHP(enemyList.get(userEnemyChoice).getEnemyHP()-myTank.getActiveWeapon().calculateDamage(myTank));
                                            System.out.println("Enemy take "+myTank.getActiveWeapon().calculateDamage(myTank)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getEnemyHP());
                                            if(enemyList.get(userEnemyChoice).getEnemyHP()<=0){
                                                System.out.println(enemyList.get(userEnemyChoice).getEnemyName()+" is dead");
                                                enemyList.get(userEnemyChoice).setAlive(false);
                                                enemyList.remove(userEnemyChoice);
                                                score +=1;
                                                deathEnemyCounter +=1;

                                            }
                                            turnPass=false;
                                            break;

                                        case 2:
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
                                            System.out.println("Your HP: "+myTank.getTankHP());
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
                                            enemyList.get(userEnemyChoice).setEnemyHP(enemyList.get(userEnemyChoice).getEnemyHP()-myHealer.getActiveWeapon().calculateDamage(myHealer));
                                            System.out.println("Enemy take "+myHealer.getActiveWeapon().calculateDamage(myHealer)+" damage. Remaining enemy HP is "+enemyList.get(userEnemyChoice).getEnemyHP());
                                            if(enemyList.get(userEnemyChoice).getEnemyHP()<=0){
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
                                                myFighter.setFighterHP(myFighter.getFighterHP()+myHealer.activeWeapon.heal(myHealer));
                                                System.out.println("Fighter healed "+myHealer.activeWeapon.heal(myHealer)+" HP");
                                                turnPass=false;
                                            }else if (healingCharacter==2 && !myTank.isAlive()){
                                                System.out.println("Tank is dead. Choose someone else");
                                                break;
                                            }else if (healingCharacter==2 && myTank.isAlive()){
                                                myTank.setTankHP(myTank.getTankHP()+myHealer.activeWeapon.heal(myHealer));
                                                System.out.println("Tank healed "+myHealer.activeWeapon.heal(myHealer)+" HP");
                                                turnPass=false;
                                            }else if (healingCharacter==3 && myHealer.isAlive()){
                                                myHealer.setHealerHP(myHealer.getHealerHP()+myHealer.activeWeapon.heal(myHealer));
                                                System.out.println("Healer healed "+myHealer.activeWeapon.heal(myHealer)+" HP");
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
                                            System.out.println("Your HP: "+myHealer.getHealerHP());
                                            break;
                                    }
                                }

                            }else if(!turnPass){
                                SecureRandom rd = new SecureRandom();
                                int enemysTarget = rd.nextInt(2)+1;
                                if(myTank.isAlive()){
                                    myTank.setTankHP((myTank.getTankHP()+myTank.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                    System.out.println("The enemy attacked your Tank with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Tank: "+myTank.getTankHP());
                                    if(myTank.getTankHP()<=0){
                                        myTank.isAlive=false;
                                        System.out.println("Tank is dead");
                                    }
                                    turnPass=true;
                                }else if(!myTank.isAlive && myFighter.isAlive() && myHealer.isAlive()){
                                    if(enemysTarget==1){
                                        myFighter.setFighterHP((myFighter.getFighterHP()+myFighter.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                        System.out.println("The enemy attacked your Fighter with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Fighter: "+myFighter.getFighterHP());
                                        if(myFighter.getFighterHP()<=0){
                                            myFighter.isAlive=false;
                                            System.out.println("Fighter is dead");
                                        }
                                        turnPass=true;
                                    }else if(enemysTarget==2){
                                        myHealer.setHealerHP((myHealer.getHealerHP()+myHealer.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0)));
                                        System.out.println("The enemy attacked your Healer with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Healer: "+myHealer.getHealerHP());
                                        if(myHealer.getHealerHP()<=0){
                                            myHealer.isAlive=false;
                                            System.out.println("Healer is dead");
                                        }
                                        turnPass=true;
                                    }
                                }else if((myFighter.isAlive() && !myHealer.isAlive) || (!myFighter.isAlive && myHealer.isAlive())){
                                    if(myFighter.isAlive){
                                        myFighter.setFighterHP((myFighter.getFighterHP()+myFighter.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(myFighter));
                                        System.out.println("The enemy attacked your Fighter with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Fighter: "+myFighter.getFighterHP());
                                        if(myFighter.getFighterHP()<=0){
                                            myFighter.isAlive=false;
                                            System.out.println("Fighter is dead. Game Over");
                                            break;
                                        }
                                        turnPass=true;
                                    }else if(myHealer.isAlive){
                                        myHealer.setHealerHP((myHealer.getHealerHP()+myHealer.getActiveArmor().getValue())-enemyList.get(0).getActiveWeapon().calculateDamage(myHealer));
                                        System.out.println("The enemy attacked your Healer with "+enemyList.get(0).getActiveWeapon().calculateDamage(enemyList.get(0))+" damage. Remaining HP of the Healer: "+myHealer.getHealerHP());
                                        if(myHealer.getHealerHP()<=0){
                                            myHealer.isAlive=false;
                                            System.out.println("Healer is dead.Game Over");
                                            break;
                                        }
                                        turnPass=true;
                                    }
                                }

                            }

                        }
                        myFighter.setFighterHP(myFighter.getFighterHP() + (myFighter.getFighterHP() / 2)); //HP can go beyond limit
                        myTank.setTankHP(myTank.getTankHP() + (myTank.getTankHP() / 2));
                        myHealer.setHealerHP(myHealer.getHealerHP() + (myHealer.getHealerHP() / 2));
                        while (deathEnemyCounter>=tempSize) {

                            System.out.println("""
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
                                    System.out.println("Your HP: " + myFighter.getFighterHP());
                                } else if (userChoice == 10)
                                    break;

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
                                    System.out.println("Your HP: " + myTank.getTankHP());
                                } else if (userChoice == 10)
                                    break;
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
                                    System.out.println("Your HP: " + myHealer.getHealerHP());
                                } else if (userChoice == 10)
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    offSwitch = false;
                    break;
            }
        }

    }
}
