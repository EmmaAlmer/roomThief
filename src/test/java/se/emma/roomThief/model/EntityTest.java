package se.emma.roomThief.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTest {

    @Test
    void testPunch(){

        Burglar burglar = new Burglar();
        Resident resident = new Resident();

        burglar.punch(resident);
        Assertions.assertEquals(8, resident.getHealth());

    }

    @Test
    void testTakeHit(){

        Burglar burglar = new Burglar();
        Resident resident = new Resident();

        burglar.takeHit(resident.getDamage());
        Assertions.assertEquals(9, burglar.getHealth());

    }

    @Test
    void testIsConsciousTrue(){

        Burglar burglar = new Burglar();
        Resident resident = new Resident();

        burglar.punch(resident);
        Assertions.assertTrue(resident.isConcious());
    }

    @Test
    void testIsConsciousFalse(){

        Burglar burglar = new Burglar();
        Resident resident = new Resident();

        burglar.punch(resident);
        burglar.punch(resident);
        burglar.punch(resident);
        Assertions.assertFalse(resident.isConcious());
    }
}
