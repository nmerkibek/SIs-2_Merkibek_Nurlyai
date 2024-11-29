package ExamChecker;
import java.time.LocalDateTime;

class ExamChecking extends Thread {
    private static final int TOTAL_SHEETS = 500;
    private static int examSheets = TOTAL_SHEETS;
    private int limit;

    public ExamChecking(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < limit; i++) {
            synchronized (ExamChecking.class) {
                if (examSheets <= 0) {
                    System.out.println("Все документы проверены и листов не осталось");
                    System.exit(0);
                }

                examSheets -= 50;

                System.out.println(Thread.currentThread().getName() + " закончил(-а) проверку в: " +
                        LocalDateTime.now() +
                        " кол-во экз листов на данный момент: " + examSheets);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("поток прерван: " + e.getMessage());
                }
            }
        }
    }
}

