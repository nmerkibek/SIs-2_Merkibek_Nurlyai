package ExamChecker;
public class Main {
    public static void main(String[] args) {
        ExamChecking m = new ExamChecking(6);
        ExamChecking m2 = new ExamChecking(4);

        m.setName("Амир");
        m2.setName("Аиша");
        m2.setPriority(Thread.MAX_PRIORITY);

        m.start();
        m2.start();
    }
}
