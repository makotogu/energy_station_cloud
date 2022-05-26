package com.gujiacheng.dataService.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String TOKEN = "@1231daw!TOKEN?21J$LKN@JOADI;HIOBjlij;acn1u092u41l4n3h1po9u4";
    private static final int EXPIRATION = 60*60*24;

    /**
     * 生成token 依据登陆的用户信息
     * @param payload 传递的用户信息map,也就是jwt的payload部分数据
     * @return 生产的token字符串
     */
    public static String getToken(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();
        payload.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, EXPIRATION);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 验证token合法性
     * @param token token字符串
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 解析token内容
     * @param token 传输的token
     * @return 返回解析后的内容
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 为用户设置jwt的payload
     * @param userId 用户id
     * @param username 用户昵称
     * @return 返回设置好的payload
     */
    public static Map<String, String> setupPayload(Integer userId, String username) {
        Map<String, String> payload = new HashMap<>(3);
        payload.put("id", userId.toString());
        payload.put("username", username);
        return payload;
    }
}
