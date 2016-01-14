package mycompany.myinterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by Wesb on 3/17/15.
 */
public class BehaviouralQuestionManager {

    List<String> questions = new ArrayList<String>();
    private String behaviouralQuestionsURLKey = "1m4EjzofTQSzCUyrOsJC1bgGRnd8Xg1OrsmQoKfA-yA8";

    public BehaviouralQuestionManager() {

        FetchQuestions fQ = new FetchQuestions(behaviouralQuestionsURLKey);

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
        if (questions.get(0) == "Empty"){
            questions.remove(0);
            runAnalogQuestions();
        }

    }

    public void runAnalogQuestions(){
        //List of Questions
        String q1 = "By providing an example, convince me that you can adapt to a wide variety of people, situations, and environments.";
        String q2 = "Tell of some situation in which you have had to adjust quickly to changes over which you had no control.";
        String q3 = "Give an example of a time in which you had to be relatively quick in coming to a decision.";
        String q4 = "Tell me about a time when you had to make a decision, but didn't have all the information you needed.";
        String q5 = "Give me an example of a time you had to persuade other people to take action.  Were you successful";
        String q6 = "What has been your most rewarding accomplishment?";
        String q7 = "What accomplishment are you most proud of?";
        String q8 = "Give me an example of a time when you set a goal for yourself and successfully pursued it.";
        String q9 = "Describe a time when you stood your ground for a principle you believed in.";
        String q10 = "Tell me about a situation that tested your coping skills.";
        String q11 = "Tell me about a time when you made the best of a negative situation";
        String q12 = "Recall a time from your work experience when your manager or supervisor was unavailable and a problem arose";

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

    //Currently unused, but included for future functionality
    public void addQuestion(String q){
        questions.add(q);
    }

    public String runRandom(){
        int randomIndex = new Random().nextInt(questions.size());
        return questions.get(randomIndex);


    }
}