package core;

import core.exceptions.QuestionAlreadyExistsException;
import model.Question;
import ui.MainFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public void start() {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            //updateDb(databaseManager);
            databaseManager.createDbDirectory();
            addQuestionsForDebugOnly(databaseManager);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    private void updateDb(DatabaseManager databaseManager) throws IOException {
        if (Files.exists(Paths.get(databaseManager.getPathToDbs()))) {
            if (!Files.exists(Paths.get(databaseManager.getPathToDbs() + "/new"))) {
                databaseManager.deleteDbDirectory();
            }
        }
    }

    private void addQuestionsForDebugOnly(DatabaseManager databaseManager) throws IOException {

        Files.createFile(Paths.get(databaseManager.getPathToDbs() + "/new"));

        if (databaseManager.getQuestionsSize() == 0) {
            try {
                String[] answers = new String[]{"ArithmeticException", "Double.INFINITY", "NaN", "-0.0"};
                databaseManager.addQuestion(new Question("Что вернет выражение 1.0/0.0?", answers, 1));

                answers = new String[]{"break в try/catch", "Никак", "return в try/catch", "System.exit() в try/catch"};
                databaseManager.addQuestion(new Question("Как избежать выполнения блока finally?", answers, 3));

            } catch (QuestionAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

}
