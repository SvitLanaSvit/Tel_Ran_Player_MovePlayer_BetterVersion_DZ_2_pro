package start;

import enumLeague.League;
import generator.Generator;
import model.Game;
import model.Player;
import model.PlayerManager;
import model.ResultOfGameService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<League, List<Player>> leagueListMap = new HashMap<>();
        Game game = new Game();
        String name = "NAME", surname = "SURNAME", age = "AGE", score = "SCORE", league = "LEAGUE";
        System.out.printf("%5s %18s %10s %6s %7s%n", name, surname,age,score,league);
        System.out.println("--------------------------------------------------");

        Generator.createPlayerJunior();
        Generator.createPlayerMiddle();
        Generator.createPlayerSenior();

        for (League l: League.values()) {
            if(l != League.UNDEFINED){
                List<Player> players = PlayerManager.getInstance().getPlayersByLeague(l);
                game.makeGame(players);
                leagueListMap.put(l, players);
                ResultOfGameService.showTopPlayerIntoLeague(players);
            }
        }

        game.movePlayersBetweenLeague(leagueListMap);
    }
}
