package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logic.CreatureManager;

public class Sword extends Weapon {
    private CreatureManager creatureManager;
    private float range;

    public Sword( float damage, float range, CreatureManager creatureManager) {
        super(damage);
        this.range = range;
        this.creatureManager = creatureManager;
    }

    @Override
    public void execute(float delta) {
        if (equippedOn != null) {
            GameObject enemy = creatureManager.getCreature(equippedOn.getX(), equippedOn.getY(), range, equippedOn.getType());
            if (enemy != null) {
                enemy.damage(damage);
            }
        }
    }
}

