package binaryTree;

import enumLeague.League;
import generator.Generator;
import model.Player;
import model.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Generator.createPlayerJunior();
        Generator.createPlayerMiddle();
        Generator.createPlayerSenior();
        List<Player> players = new ArrayList<>();
        players.addAll(PlayerManager.getInstance().getPlayersByLeague(League.JUNIOR));
        players.addAll(PlayerManager.getInstance().getPlayersByLeague(League.MIDDLE));
        players.addAll(PlayerManager.getInstance().getPlayersByLeague(League.SENIOR));

        Node<Player> node = new Node<>();
        node.createNode(node, players.get(0));
        for (int i = 1; i < players.size(); i++) {
            node.insert(node, players.get(i));
        }

        node.inOrderTraversal(node);
        System.out.println("--------------------------------------------------");
        System.out.println("Min:\n" + node.getMin(node).getValue());
        System.out.println("--------------------------------------------------");
        System.out.println("Max:\n" + node.getMax(node).getValue());
        System.out.println("--------------------------------------------------");
        System.out.println("Count of players:\n" + node.getSize(node));
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------POST------------------------");
        node.postOrderTraversal(node);
        System.out.println("---------------------DIRECT-----------------------");
        node.directOrderTraversal(node);
        System.out.println("--------------------------------------------------");
        System.out.println(players.get(10));
        System.out.println(node.remove(node, players.get(10)));
        System.out.println("--------------------------------------------------");
        node.inOrderTraversal(node);
        System.out.println("Count of players:\n" + node.getSize(node));
    }
}
