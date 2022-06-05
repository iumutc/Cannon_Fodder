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
                    Shield myShield = new Shield();

                    myFighter.setActiveWeapon(shortBlade);
                    myFighter.setActiveArmor(lightArmor);
                    myTank.setActiveArmor(lightArmor);
                    myHealer.setActiveArmor(lightArmor);
                    myHealer.setActiveWeapon(woodenWand);
                    int score = 0;

                        System.out.println("Fighter created with S:" + myFighter.getStrength() + " V:" + myFighter.getVitality() + " I:" + myFighter.getIntelligence() + ".The HP is " + myFighter.getFighterHP() + ".Fighter wields short sword with " + shortBlade.getValue() + " damages and " + shortBlade.getWeight() + " units of weight.");
                        System.out.println("Tank created with S:" + myTank.getStrength() + " V:" + myTank.getVitality() + " I:" + myTank.getIntelligence() + ".The HP is " + myTank.getTankHP() + ".Tank wields RoundShield with " + myShield.calculateDamageofRoundShield(myTank) + " damages and " + myShield.weightofRoundShield + " unit of weight.");
                        System.out.println("Healer created with S:" + myHealer.getStrength() + " V:" + myHealer.getVitality() + " I:" + myHealer.getIntelligence() + ".The HP is " + myHealer.getHealerHP() + ".Healer wields GoldenWand with " + woodenWand.getValue() + " damages and " + woodenWand.getWeight() + " unit of weight.");
                        myFighter.inventory.add(shortBlade);
                        myFighter.inventory.add(lightArmor);
                        myTank.inventory.add(lightArmor);
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
