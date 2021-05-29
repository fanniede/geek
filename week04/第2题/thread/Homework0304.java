package thread;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework0304 {

    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        //Thread.currentThread().getThreadGroup().list();
        /*主线程所在的线程组中排除子线程后还剩主线程和叫Monitor Ctrl-Break 的线程,
        所以defaultthreadnum 设置的是2*/
        int defaultThreadNum = 2;
        // 在这里创建一个线程或线程池，
        new Thread(){
            @Override
            public void run() {
                int result = sum(); //这是得到的返回值
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);
                System.out.println("sub thread finished");
            }
        }.start();

        while(Thread.activeCount() > defaultThreadNum){
            // 当活跃线程数大于设定的默认线程数的时候 主线程让步
            Thread.yield();
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