package task2;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApplication {
    private static Question[] questions;
    private static int score = 0;
    private static int currentQuestion = 0;
    private static boolean timedOut = false;

    public void runQuiz() {
        questions = new Question[] {
            new Question("What is the capital of France?", new String[] {"1. Berlin", "2. Paris", "3. Madrid", "4. Rome"}, 2),
            new Question("Which planet is known as the Red Planet?", new String[] {"1. Earth", "2. Mars", "3. Venus", "4. Saturn"}, 2)
        };

        Scanner sc = new Scanner(System.in);
        for (Question q : questions) {
            displayQuestion(q);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    timedOut = true;
                }
            }, 10000);  // 10 seconds per question

            int answer = -1;
            while (!timedOut) {
                try {
                    answer = sc.nextInt();
                    if (answer >= 1 && answer <= 4) break;
                } catch (Exception e) {
                    sc.next();
                }
            }

            if (timedOut) {
                System.out.println("\nTime's up!");
            } else {
                checkAnswer(q, answer);
            }
            currentQuestion++;
            timedOut = false;
        }

        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + score + "/" + questions.length);
    }

    private void displayQuestion(Question q) {
        System.out.println("\n" + q.questionText);
        for (String option : q.options) {
            System.out.println(option);
        }
        System.out.print("Your answer (1-4): ");
    }

    private void checkAnswer(Question q, int answer) {
        if (answer == q.correctOption) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong answer.");
        }
    }
}