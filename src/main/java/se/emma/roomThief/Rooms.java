package se.emma.roomThief;

import se.emma.roomThief.model.Burglar;
import se.emma.roomThief.model.Entity;
import se.emma.roomThief.model.Resident;

import java.util.Scanner;

public class Rooms {

    RoomEnum currentRoom = RoomEnum.LIVINGROOM;

    Scanner scanner;
    String userInput;
    boolean stekpanna = true;

    public Rooms(Scanner scanner){
        this.scanner = scanner;
    }

    //startrummet, vaknar upp och kan gå till alla rum
    public void livingroom(){

        //kollar om jag inte är i samma rum, metoden issameroom checkar det, annars säger den var vi är
        if (!isSameRoom(RoomEnum.LIVINGROOM)){

            currentRoom = RoomEnum.LIVINGROOM;
            System.out.println("du är nu i vardagsrummet");
        }
    }

    //här finns stekpannan, du kan välja om du vill ta den eller ej
    public void kitchen(Resident resident){

        //samma check och en check för om man kan byta från just det rummet
        if (!isSameRoom(RoomEnum.KITCHEN) && possibleToChangeRoom()){

            currentRoom = RoomEnum.KITCHEN;

            //om stekpannan fortfarande är i köket
            if (stekpanna){

                System.out.println("du är nu i köket, här hittar du en stekpanna, vill du ta upp " +
                        "stekpannan skriv \"ta upp\", annars tryck på enter");

                userInput = scanner.nextLine();

                //om spelaren tar upp stekpannan försvinner den och man har den i handen och styrkan ökar
                if (userInput.equals("ta upp")){

                    resident.addDamage(3);
                    stekpanna = false;
                    System.out.println("du har nu stekpannan i handen och din skada har dubblat i styrka");

                }
            //meddelande efter att man tagit upp pannnan och går till köket igen
            } else {
                System.out.println("du är nu i köket, du har stekpannan i handen");
            }
        }
    }

    //inget händer i detta rum bara en check
    public void bedroom(){

        if (!isSameRoom(RoomEnum.BEDROOM) && possibleToChangeRoom()){

            currentRoom = RoomEnum.BEDROOM;
            System.out.println("du är nu i sovrummet, du ser en stor mysig säng!");
        }
    }

    //här möter du inbrottstjuven, instansiera burglar och starta striden
    public void hallway(Entity burglar, Entity resident){

        if (!isSameRoom(RoomEnum.HALLWAY) && possibleToChangeRoom()){

            currentRoom = RoomEnum.HALLWAY;

            //om tjuven lever
            if (burglar.isConcious()) {
                System.out.println("du är nu i hallen, du hör en smäll och vänder dig om. Du ser en person som du " +
                        "misstänker är en inbrottstjuv! Vill du slåss mot tjuven?\n skriv \"ja\" för att slåss och " +
                        "\"nej\" för att fly");

                userInput = scanner.nextLine();

                //om användaren skriver ja börjar fighten
                if (userInput.equals("ja")) {
                    Fight.fight(burglar, resident);
                }
            //om tjuven dog
            } else {

                System.out.println("du är nu i hallen och ser den avsvimmade tjuven! RING POLISEN ");
            }
        }
    }

    //innan du slagit tjuven kan du ej göra något här
    public boolean office(Entity burglar){

        //kollar ifall man kan byta rum
        if (!isSameRoom(RoomEnum.OFFICE) && possibleToChangeRoom()){

            currentRoom = RoomEnum.OFFICE;

            //om tjuven är död kan man ringa polisen
            if (!burglar.isConcious()){

                System.out.println("du är nu i kontoret och kan i lugn och ro ringa polisen! \n" +
                        "skriv \"ring polisen\" om du vill ringa");

                userInput = scanner.nextLine();

                //om användaren skrev ring polisen
                if (userInput.equals("ring polisen")) {

                    System.out.println("polisen knackar på och meddelar att du vunnit spelet!");
                    //avslutar spelet
                    return false;
                }
            //om tjuven lever
            } else {
                System.out.println("du är nu i kontoret, du ser en telefon men du har för hög puls " +
                        "för att ringa polisen");
            }
        }

        //fortsätter spelet
        return true;
    }

    //metod som kollar om du redan är i samma rum
    public boolean isSameRoom(RoomEnum roomToCheck){

        if (currentRoom == roomToCheck){
            System.out.println("du är redan i det här rummet");
            return true;
        }
        return false;
    }

    //metod som kollar om du kan byta rum från ditt nuvarande rum
    public boolean possibleToChangeRoom(){

        //om rummet du är i inte är livingroom kan du ej gå dit
        if (currentRoom != RoomEnum.LIVINGROOM){
            System.out.println("du kan inte gå hit från detta rum");
            return false;
        }
        return true;
    }
}
