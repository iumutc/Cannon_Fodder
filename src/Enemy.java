public class Enemy extends Character{



    private String enemyName;

    public Enemy(int s, int v, int i, boolean isAlive,int HP, boolean isStunned) {
        super(s, v, i, isAlive,HP, isStunned);
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


    public void setAlive(boolean alive){
        this.isAlive = alive;
    }
}
