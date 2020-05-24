package com.dingjianjun.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
public class HelloHystrixCommand extends HystrixCommand {
    private String name;
    public HelloHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Object run() throws Exception {
      //  int i = 1/0;
        return "Hello~ name:" + name;
    }

    /**
     * 服务降级
     * @return
     */
    @Override
    protected Object getFallback() {
        return "熔断：fallback,name:" + name;
    }

    public static void main(String[] args) {
        /**
         * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，
         * hystrix先创建一个新线程运行run()，
         * 	接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
         */
		//String result = (String)new HelloHystrixCommand("hystrix").execute();


        /**
         * queue()：以异步非阻塞方式执行run()。以demo为例，
         * 	一调用queue()就直接返回一个Future对象，
         * 	同时hystrix创建一个新线程运行run()，
         * 	调用程序通过Future.get()拿到run()的返回结果，
         * 	而Future.get()是阻塞执行的
         */
        Future<String> futureResult = new HelloHystrixCommand("msb").queue();
        String result = "";
        try {
            result = futureResult.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("程序结果："+result);

    }
}
