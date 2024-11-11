package se.emma.roomThief;
import se.emma.roomThief.model.Burglar;
import se.emma.roomThief.model.Entity;
import se.emma.roomThief.model.Resident;

import java.util.Scanner;

public class Game {

    public void introGame() {

        Scanner scanner = new Scanner(System.in);
        Rooms rooms = new Rooms(scanner);
        boolean gameLoop = true;
        Resident resident = new Resident();
        Burglar burglar = new Burglar();

        System.out.println("välkommen till inbrottsspelet!");
        System.out.println("du är nu i ett hus med 5 olika rum! Dörrarna till de olika rummen " +
                "är kopplade på olika sätt,\ntesta trycka på siffrorna mellan 1 - 5 för att gå " +
                "runt och utforska. Tryck 6 för att avsluta spelet. Du är nu i vardagsrummet");

        //här börjar spelloopen
        while (gameLoop && resident.isConcious()) {

            String userInput = scanner.nextLine();

            switch (userInput) {

                case "1" -> rooms.livingroom();
                case "2" -> rooms.kitchen(resident);
                case "3" -> rooms.bedroom();
                case "4" -> rooms.hallway(burglar, resident);
                case "5" -> gameLoop = rooms.office(burglar);
                case "6" -> gameLoop = false;
            }
        }
    }
}
