package com.dingjianjun.security;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

/**
 * @author : Jianjun.Ding
 * @description:
 * @date 2020/5/27
 */
@Data
@ToString
public class Token {
    /**
     * 生成时间,ms时间戳
     */
    private long generateTime;
    /**
     * 过期时间,ms时间戳
     */
    private long expireTime;
    /**
     * 有效期，ms
     */
    private long expireIn;
    /**
     * 令牌
     */
    private String accessToken;

    public static Token createToken(long expireIn){
        Token token = new Token();
        token.setAccessToken(UUID.randomUUID().toString().replace("-",""));
        token.setGenerateTime(System.currentTimeMillis());
        token.setExpireIn(expireIn);
        token.setExpireTime(token.getGenerateTime()+expireIn);
        return token;
    }

    public static void main(String[] args) {
        // 随机字符串
        Token token = createToken(2 * 3600 * 1000);
        System.out.println("token:" + token);
        // sign签名生成机制
        String appKey = "abddhh123$1";
        String appSecret = "gkclwhh3#!";
        long timestamp = System.currentTimeMillis();
        String encodeStr = Sha256Utils.sha256_HMAC(appKey + timestamp, appSecret);
        System.out.println(encodeStr);
        long timestamp2 = System.currentTimeMillis() + 1;
        String encodeStr2 = Sha256Utils.sha256_HMAC(appKey + timestamp2, appSecret);
        System.out.println(encodeStr2);

    }
}