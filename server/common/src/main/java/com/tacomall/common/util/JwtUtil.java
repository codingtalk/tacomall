
package com.tacomall.common.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tacomall.common.exceptionInterceptor.exception.BizException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import cn.hutool.core.date.DateUtil;

public class JwtUtil {

    private String SECRET = "org";
    private String ISSUER = "";

    public JwtUtil setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
        return this;
    }

    public String create(Map<String, String> claims) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.SECRET);
            JWTCreator.Builder builder = JWT.create().withIssuer(this.ISSUER)
                    .withExpiresAt(DateUtil.offsetDay(new Date(), 31));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new BizException("生成token失败");
        }
    }

    public Map<String, String> verify(String token) throws Exception {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(this.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (JWTVerificationException e) {
            throw new BizException("鉴权失败");
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }
}
