package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework0302 {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ExecutorService ex =  Executors.newCachedThreadPool();
        ex.submit(new Thread("subthread"){

            @Override
            public void run() {
                int result = sum(); //这是得到的返回值
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);
                System.out.println("sub thread finished");

            }
        });

        ex.shutdown();
        try {
            // 设置等待时间等待子线程运行完毕
            if(!ex.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                // 等待时间内子线程并未全部运行完毕就直接关闭
                ex.shutdownNow();
            }
        }catch(InterruptedException e){
            ex.shutdownNow();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("main thread finished");
        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}