package pl.mikolajlignar.quiz;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mikolajlignar.quiz.database.entities.PlayerEntity;
import pl.mikolajlignar.quiz.database.repositories.PlayerRepository;
import pl.mikolajlignar.quiz.services.QuizDataService;
import java.util.List;

@Component
@Log
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private PlayerRepository playerRepository;
    private QuizDataService quizDataService;

    public StartupRunner(QuizDataService quizDataService) {
        this.quizDataService = quizDataService;
    }

    @Override
    public void run(String...args) throws Exception{
        log.info("Executing startup actions...");
        playerRepository.save(new PlayerEntity("Mikolaj"));
        quizDataService.getQuizCategories();
        log.info("List of players from database:");
        List<PlayerEntity> playersFromDatabase = playerRepository.findAll();
        for (PlayerEntity player : playersFromDatabase) {
            log.info("Retrieved player: " + player);
        }
    }
}