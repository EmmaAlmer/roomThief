package se.emma.roomThief.model;

public abstract class Entity {

    //vilken roll karaktären har
    private String role;

    //hälsa hos karaktären
    private int health;

    //skada karaktären GER entiteten, protected för att jag vill komma åt i ärvande klasser
    private int damage;

    public Entity (String role, int health, int damage){

        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole(){
        return role;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    //utför attack på en annan entitet
    public void punch(Entity toPunch){

        toPunch.takeHit(damage);
    }

    //tar hälsan och tar bort med damage
    public void takeHit(int damage){

        this.health -= damage;
    }

    //när boende hittar stekpannan ökas damage med 3, setDamage metoden
    public void addDamage(int damage){

        this.damage += damage;
    }

    //true om hälsa över 0
    public boolean isConcious(){

        if (this.health > 0){
            return true;
        }
        return false;
    }
}
