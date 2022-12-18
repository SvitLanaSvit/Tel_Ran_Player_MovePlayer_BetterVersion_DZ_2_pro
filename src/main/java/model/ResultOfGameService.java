package model;

import java.util.ArrayList;
import java.util.List;

public class ResultOfGameService {
    public static void showTopPlayerIntoLeague(List<Player> players){
        System.out.println("--------------------TOP---------------------------");
        List<Player> bestTopThree = new ArrayList<>();
        bestTopThree.add(players.get(0));
        Game.printPlayers(bestTopThree);
        System.out.println("--------------------------------------------------");
    }
}
