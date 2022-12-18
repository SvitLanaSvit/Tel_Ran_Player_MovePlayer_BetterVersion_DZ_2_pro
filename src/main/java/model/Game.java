package model;
import enumLeague.League;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {
    public static void printPlayers(List<Player> players){
        System.out.println("---------------------"+players.get(0).getLeague()+"-----------------------");
        for (Player p:players) {
            System.out.println(String.format("%-15s %-15s %-5d %-5d %-10s",
                    p.getName(), p.getSurname(), p.getAge(), p.getScore(), p.getLeague()));
        }
    }

    public void makeGame(List<Player> players){
        for(int k = 0; k < 5; k++){
            for (int i = 0; i < players.size() - 1; i++){
                Player p1 = players.get(i);
                for(int j = i + 1; j < players.size(); j++){
                    Player p2 = players.get(j);
                    resultGame(p1,p2);
                }
            }
        }
        Collections.sort(players, (o1, o2) -> {
            int res = o2.getScore() - o1.getScore();
            if(res == 0)
                res = o1.getName().compareTo(o2.getName());
            return res;
        });
        printPlayers(players);
    }

    public void resultGame(Player p1, Player p2){
        if(Math.random() > 0.5)
            p2.addScore(1);
        else p1.addScore(1);
    }

    public void movePlayersBetweenLeague(Map<League, List<Player>> map){
        //Iterates via map from each League take 3 beste players
        //and reassign league in using playerManager function with changeLeague function
        List<Player> junior = map.get(League.JUNIOR);
        List<Player> middle = map.get(League.MIDDLE);
        List<Player> senior = map.get(League.SENIOR);
        System.out.println("-----------------THREE BESTE PLAYERS--------------");
        List<Player> besteJunior = getThreeBestePlayers(junior);
        List<Player> besteMiddle = getThreeBestePlayers(middle);
        List<Player> besteSenior = getThreeBestePlayers(senior);
        printPlayers(besteJunior);
        printPlayers(besteMiddle);
        printPlayers(besteSenior);
        System.out.println("----------------THREE WORST PLAYERS--------------");
        List<Player> worstJunior = getThreeWorstPlayers(junior);
        List<Player> worstMiddle = getThreeWorstPlayers(middle);
        List<Player> worstSenior = getThreeWorstPlayers(senior);
        printPlayers(worstJunior);
        printPlayers(worstMiddle);
        printPlayers(worstSenior);
        System.out.println("------------------------MOVE----------------------");

        changeMapWithMovePlayers(worstMiddle, League.JUNIOR);
        changeMapWithMovePlayers(worstSenior, League.MIDDLE);

        changeMapWithMovePlayers(besteJunior, League.MIDDLE);
        changeMapWithMovePlayers(besteMiddle, League.SENIOR);

        map.clear();
        for (League l: League.values()) {
            if(l != League.UNDEFINED){
                List<Player> players = PlayerManager.getInstance().getPlayersByLeague(l);
                map.put(l, players);
            }
        }
        showMap(map);
    }

    private List<Player> getThreeBestePlayers(List<Player> list){
        list.sort((o1,o2) -> {
            int res = o2.getScore() - o1.getScore();
            if(res == 0)
                res = o2.getName().compareTo(o1.getName());
            return  res;
        });
        return list.subList(0,3);
    }
    private List<Player> getThreeWorstPlayers(List<Player> list){
        list.sort((o1,o2) -> {
            int res = o2.getScore() - o1.getScore();
            if(res == 0)
                res = o2.getName().compareTo(o1.getName());
            return  res;
        });
        int lastIndex = list.size();
        return list.subList(lastIndex - 3,lastIndex);
    }

    private void changeMapWithMovePlayers(List<Player> player, League newLeague){
        for (Player p : player) {
            PlayerManager.getInstance().changeLeague(p, newLeague);
        }
    }

    private void showMap(Map<League, List<Player>> map){
        for (Map.Entry<League, List<Player>> e : map.entrySet()) {
            List<Player> players = e.getValue();
            System.out.println("-------------------" + e.getKey() + "-------------------------");
            for (Player p : players) {
                System.out.printf("%-15s %-15s %-5d %-5d %-10s%n",
                        p.getName(), p.getSurname(), p.getAge(), p.getScore(), p.getLeague());
            }
        }
    }
}
