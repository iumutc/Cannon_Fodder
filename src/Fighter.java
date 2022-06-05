public class Fighter extends Character {


    private int fighterHP;



    public int getFighterHP(){
        return fighterHP;
    }
    public void setFighterHP(int fighterHP) {
        this.fighterHP = fighterHP;
    }



    public Fighter(int s, int v, int i, boolean isAlive,int fighterHP) {
        super(s, v, i, isAlive);
        this.fighterHP=fighterHP;
    }


  //start of inventory related codes
    public void calculateInventorySpace(){
        super.calculateInventorySpace();
    }
    public void displayInventory(){
        super.displayInventory();
    }
    public void pickItem(){
        super.pickItem();
    }
    public void selectItem(){
        super.selectItem();
    }
    public void examineItem(){
        super.examineItem();
    }

    public void discardItem(){
        super.discardItem();
    }

}
