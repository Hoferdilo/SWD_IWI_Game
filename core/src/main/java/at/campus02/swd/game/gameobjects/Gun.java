package at.campus02.swd.game.gameobjects;

public class Gun extends Weapon{

    private BulletFactory bulletFactory;
    private ProjectileManager projectileManager;
    private float cooldown = 0f;
    private final float BULLET_SIZE = 7f;
    private float bulletSpeed;
    private float firingRate;

    public Gun(ProjectileManager projectileManager, float speed, float firingRate) {
        super(10);
        this.projectileManager = projectileManager;
        this.bulletSpeed = speed;
        this.firingRate = firingRate;
    }

    //TODO: Cooldown and firing rate produce bugs implemented this way due to them not being called every frame
    //TODO: Weapons should be a gameObject in order to fix this issue
    @Override
    public void execute(float delta) {
        if(this.bulletFactory != null) {
            this.cooldown -= delta;
            if(this.cooldown < 0) {
                this.cooldown = 1/firingRate;
                Bullet bullet = bulletFactory.createBullet();
                bullet.setPosition(equippedOn.getX()+BULLET_SIZE, equippedOn.getY()+50);
                projectileManager.addProjectile(bullet);
            }
        }
    }

    @Override
    public void setEquippedOn(GameObject equippedOn) {
        if(equippedOn == null) {
            bulletFactory = null;
        }
        else {
            bulletFactory = new BulletFactory(10, bulletSpeed, 7, equippedOn.getType());
        }
        super.setEquippedOn(equippedOn);
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
}
