public class Sword extends Item implements SpecialAction{


    @Override
    public int calculateDamage(Character character) {
        return 0;
    }

    @Override
    public int heal(Character character) {
        return 0;
    }

    @Override
    public int inactivity(Character character) {
        return 0;
    }

    @Override
    public int stun(Character character) {
        return 0;
    }

    @Override
    public void specialAction(Character healer, Character fighter) {
        fighter.setStunned(true);

    }
}
