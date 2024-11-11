package se.emma.roomThief;

import se.emma.roomThief.model.Burglar;
import se.emma.roomThief.model.Entity;
import se.emma.roomThief.model.Resident;

public class Fight {

    public static void fight(Entity burglar, Entity resident){

        System.out.println("nu börjar slagsmålet!");

        //så länge som båda lever fortsätter fighten
        while (resident.isConcious() && burglar.isConcious()){

            burglar.punch(resident);
            System.out.println("du blev slagen och tog " + burglar.getDamage() + " skada");

            if (resident.isConcious()) {
                resident.punch(burglar);
                System.out.println("du slog tjuven och gav " + resident.getDamage() + " skada");
            }
        }
        if (burglar.isConcious()){

            System.out.println("du förlorade slagsmålet och förlorade spelet, wompwomp");

        } else {

            System.out.println("du vann slagsmålet och kan nu ringa polisen!");
        }
    }
}
