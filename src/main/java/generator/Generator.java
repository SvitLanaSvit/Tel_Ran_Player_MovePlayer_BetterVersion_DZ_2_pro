package generator;
import com.github.javafaker.Faker;
import model.Player;
import model.PlayerManager;
import java.util.Random;
import java.util.UUID;

public class Generator {
    public static void createPlayerJunior(){
        Random rnd = new Random();
        Faker faker = new Faker();
        for(int i = 0; i < 25; i++)

            PlayerManager.getInstance().addIntoListPlayer(new Player(UUID.randomUUID(), faker.name().firstName(),
                    faker.name().lastName(), rnd.nextInt(15, 18)));
    }

    public static void createPlayerMiddle(){
        Random rnd = new Random();
        Faker faker = new Faker();
        for(int i = 0; i < 25; i++)

            PlayerManager.getInstance().addIntoListPlayer(new Player(UUID.randomUUID(), faker.name().firstName(),
                    faker.name().lastName(), rnd.nextInt(18, 26)));
    }

    public static void createPlayerSenior(){
        Random rnd = new Random();
        Faker faker = new Faker();
        for(int i = 0; i < 25; i++)

            PlayerManager.getInstance().addIntoListPlayer(new Player(UUID.randomUUID(), faker.name().firstName(),
                    faker.name().lastName(), rnd.nextInt(26, 41)));
    }
}

