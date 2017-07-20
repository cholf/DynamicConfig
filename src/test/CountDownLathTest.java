import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-19
 * Time: 上午11:53
 * 类描述: CountDownLatch wait测试
 */
public class CountDownLathTest {

    public static void main(String[] args) throws InterruptedException {
        testLatch();
    }
    @Test
    public  void test() throws InterruptedException {
        testLatch();
    }

    private static void  testLatch() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2l);
                    System.out.println("continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("exit after sleep");
                latch.countDown();
            }
        }).start();
        if (latch.await(1l, TimeUnit.SECONDS)) {
            System.out.println("wait 1 sec");
        } else {
            System.out.println("exit 1 sec");
        }
    }
}
