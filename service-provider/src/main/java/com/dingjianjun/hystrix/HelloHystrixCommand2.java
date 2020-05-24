package com.dingjianjun.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.Random;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/24
 */
public class HelloHystrixCommand2 extends HystrixCommand {
    private String name;

    public HelloHystrixCommand2(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withCircuitBreakerRequestVolumeThreshold(9) // 10秒内请求数达到9个
                .withCircuitBreakerErrorThresholdPercentage(50) // 错误率达到50%
                .withCircuitBreakerSleepWindowInMilliseconds(5000) // 隔5s之后，熔断器会尝试半开(关闭)，重新放进来请求
                ));

        this.name = name;
    }


    @Override
    protected Object run() throws Exception {
        Random random = new Random();
        if (0 == random.nextInt(2)) {
            int i = 1/0;
        }
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

    public static void main(String[] args) throws InterruptedException {
        /**
         * 这个例子，休眠半秒，一共执行15秒。
         * 10秒以内超过9次请求，才开始计算 是否要熔断。
         * 然后 判断 失败率，是否超过5%。
         * 熔断后，
         * 5秒，是 熔断开关打开，直接返回的。
         * 再后来，放一部分请求过去。
         */
        for(int i = 0; i < 30; i++){
            Thread.sleep(500);
            HystrixCommand<String> command = new HelloHystrixCommand2("testCircuitBreaker");
            String result = command.execute();
            //本例子中从第10次，熔断器开始打开
            System.out.println("调用次数:"+(i+1)+"   结果:"+result +" 开关是否打开: "+command.isCircuitBreakerOpen());
            //本例子中5s以后，熔断器尝试关闭，放开新的请求进来
        }
    }
}
