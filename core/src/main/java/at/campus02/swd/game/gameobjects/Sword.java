package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logic.EnemyManager;

public class Sword extends Weapon {
    private GameObject equippedOn;
    private EnemyManager enemyManager;

    public Sword(float range, float damage, EnemyManager enemyManager) {
        super(range, damage);
        this.enemyManager = enemyManager;
        this.equippedOn = null;
    }

    @Override
    public void execute() {
        if(equippedOn != null) {
            GameObject enemy = enemyManager.getEnemy(equippedOn.getX(), equippedOn.getY(), range);
            if(enemy != null) {
                enemy.damage(damage);
                if(enemy.getHealth() <= 0) {
                    enemyManager.remove(enemy);
                }
            }
        }
    }

    public GameObject getEquippedOn() {
        return equippedOn;
    }

    public void setEquippedOn(GameObject equippedOn) {
        this.equippedOn = equippedOn;
    }
}
