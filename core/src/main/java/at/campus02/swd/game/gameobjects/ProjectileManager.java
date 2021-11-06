package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.logic.CreatureManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectileManager {
    private List<Projectile> projectiles;
    private CreatureManager creatureManager;


    public ProjectileManager() {
        projectiles = new ArrayList<>();
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public List<Projectile> list() { return projectiles; }

    public void act(float delta) {
        projectiles = projectiles.stream().filter(x -> x.getHealth() > 0).collect(Collectors.toList());

        projectiles.forEach(projectile -> {
            projectile.act(delta);
            checkCollision(projectile);
        });
    }

    private void checkCollision(Projectile projectile) {
        GameObject hitObject = creatureManager
                .getCreature(projectile.getX(), projectile.getY(), projectile.getHeight(), projectile.getType());
        if(hitObject != null) {
            if(hitObject.getType() == GameObjectType.PLAYER) {
                System.out.println("Player hit");
            }
            projectile.damage(1);
            hitObject.damage(projectile.getDamage());
        }
    }

    public void drawProjectiles(SpriteBatch batch) {
        projectiles.forEach(x -> x.draw(batch));
    }

    public void setCreatureManager(CreatureManager creatureManager) {
        this.creatureManager = creatureManager;
    }
}
