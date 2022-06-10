public class Enemy extends Character{


    private boolean isStunned;
    private String enemyName;

    public Enemy(int s, int v, int i, boolean isAlive,int HP) {
        super(s, v, i, isAlive,HP);

    }





    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }




    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getVitality() {
        return vitality;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    public void setStunStatus(){// will work on this later
        System.out.println("target successfully stunned");
        isStunned = true;
    }
    public void setAlive(boolean alive){
        this.isAlive = alive;
    }
}
