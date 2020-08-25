package zhangjie.basic.async.compleableFuture;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class CompletableFutureTest {

    //自定义实现异步
    @Test
    public void testAsync() throws IOException {
        Result result = getResult();
        System.out.println("进行其他事情");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取结果值需阻塞
        String msg = result.getMsg();
        System.out.println(msg);

    }

    @Test
    public void getException() throws InterruptedException {
        CompletableFuture<String> future = getFutureResult();
        try{
            String result = future.get();
            System.out.printf(result);
        }catch (ExecutionException e){
            System.out.printf("执行异常");
        }
    }

    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
        System.out.println(result);

        String ExceptionResult = CompletableFuture.supplyAsync(() -> {
            int a = 1/0;
            return "hello";
        }).thenApply(s -> s + " world").join();
        System.out.println(ExceptionResult);
    }

    //并行组合
    @Test
    public void thenCombine(){
        String result = CompletableFuture.supplyAsync(() -> "price:12")
                                        .thenCombine(CompletableFuture.supplyAsync(() -> " rate:0.2"),
                                                (price,rate) -> price + rate).join();
        System.out.println(result);
    }

    //串行组合
    @Test
    public void thenCompose(){
        String result = CompletableFuture.supplyAsync(() -> "hello")
                    .thenApply(s -> s+ " world")
                    .thenCompose(s -> CompletableFuture.supplyAsync(()-> s + " compose"))
                .join();
        System.out.println(result);
    }

    /**
     * 响应CompletableFuture的completion事件
     * 不需要等到findPriceStream()所有结果返回
     * @throws InterruptedException
     */
    @Test
    public void thenAccept() throws InterruptedException {
        CompletableFuture[] futures = findPriceStream()
                                        .map(f -> f.thenAccept(System.out::println))
                                        .toArray(size -> new CompletableFuture[size]);
        Thread.sleep(3000);
    }

    private Stream<CompletableFuture<String>> findPriceStream(){
        List<CompletableFuture<String>> shopPriceList = new ArrayList<>();
        shopPriceList.add(CompletableFuture.supplyAsync(()->getPrice(true)));
        shopPriceList.add(CompletableFuture.supplyAsync(()->getPrice(false)));
        return shopPriceList.stream();
    }

    private String getPrice(boolean flag){
        if(flag){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String randomStr = String.valueOf(new Random().nextInt());

        return randomStr;
    }




    private CompletableFuture<String> getFutureResult(){
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(()->{
            try{
//                int a = 1/0;
                future.complete("任务完成");
            }catch (Exception e){
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }


    private Result getResult(){
        Result result = new Result();
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = "任务完成";
            result.setMsg(msg);
        }).start();

        return result;
    }


    class Result{
        String code;
        String msg ="任务进行时";


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


}
