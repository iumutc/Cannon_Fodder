import java.security.SecureRandom;

public class Shield extends Item implements SpecialAction{


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
    public void specialAction(Character target, Character tank) {
        target.setStunned(true);

    }
}
