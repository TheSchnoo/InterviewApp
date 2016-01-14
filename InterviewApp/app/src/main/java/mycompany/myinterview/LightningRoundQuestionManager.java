package mycompany.myinterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Wesb on 3/21/15.
 */
public class LightningRoundQuestionManager {

    private List<String> questions = new ArrayList<String>();
    private String lightningRoundQuestionsURLKey = "1C1aUIai9R_DfNs2TYBAlcB1IKRkkAJ1-Mpi6sT8TIxU";

    public LightningRoundQuestionManager() {

        FetchQuestions fQ = new FetchQuestions(lightningRoundQuestionsURLKey);

        //Try to fetch questions from the online database
        try {
            questions = fQ.getQuestionsDatabase();
        } catch (ExecutionException e) {
            e.printStackTrace();
            runAnalogQuestions();
        } catch (InterruptedException e) {
            e.printStackTrace();
            runAnalogQuestions();
        }

        //If the FetchQuestions class cannot connect with the database, it will only add a single question
        //containing the string "Empty".  If this is the case, we revert to the analog questions
        if (questions.get(0) == "Empty") {
            questions.remove(0);
            runAnalogQuestions();
        }
    }

    public void runAnalogQuestions() {
        //List of hard-coded questions
        String q1 = "Five strengths";
        String q2 = "Five weaknesses";
        String q3 = "Two favorite accomplishments";
        String q4 = "Two 'difficult times'";
        String q5 = "Persuasive example";
        String q6 = "Why do you want to work here?";
        String q7 = "Tell me about yourself.";
        String q8 = "Goals?";
        String q9 = "Three projects and your roles.";
        String q10 = "Two disagreements.  How were they handled?";
        String q11 = "Three factors that make you a valuable asset to a team";
        String q12 = "Interests?";

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);
        questions.add(q10);
        questions.add(q11);
        questions.add(q12);
    }

    //Not yet used, but possible future functionality: manual addition of questions through the app itself?
    public void addQuestion(String q) {
        questions.add(q);
    }

    public String runRandom() {
        int randomIndex = new Random().nextInt(questions.size());
        return questions.get(randomIndex);
    }
}
