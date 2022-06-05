public class Enemy extends Character{

    private int enemyHP;
    private boolean isStunned;
    private String enemyName;

    public Enemy(int s, int v, int i, boolean isAlive,int enemyHP) {
        super(s, v, i, isAlive);
        this.enemyHP=enemyHP;
    }

    public int getEnemyHP(){
        return enemyHP;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
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
