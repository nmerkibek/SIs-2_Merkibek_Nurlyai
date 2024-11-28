package ExamChecker;

import java.util.concurrent.atomic.AtomicInteger;

class ExamChecking extends Thread {
    private static final int TOTAL_SHEETS = 500;
    private static AtomicInteger examSheets = new AtomicInteger(TOTAL_SHEETS);
    private int limit;

    public ExamChecking(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int i = 0; i < limit; i++) {
            synchronized (ExamChecking.class) {
                if (examSheets.get() <= 0) {
                    System.out.println(" все документы проверились и листов не осталось!");
                    System.exit(0);
                }
                int remaining = examSheets.addAndGet(-50);
                System.out.println(Thread.currentThread().getName() + " закончил(-а) проверку в: " +
                        java.time.LocalDateTime.now() +
                        " количество экзаменационных листов на данный момент: " + remaining);
                try {
                    Thread.sleep(1000); // Simulate time taken for checking
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("thread прервано: " + e.getMessage());
                }
            }
        }
    }
}

