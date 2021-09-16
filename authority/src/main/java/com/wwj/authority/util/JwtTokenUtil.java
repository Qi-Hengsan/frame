package com.wwj.authority.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.wwj.authority.constant.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * @author 阿涛
 * @date 2021-06-01
 */
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.RS256, JwtGenerateKeyUtil.getPrivateKey())
                .compact();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(JwtGenerateKeyUtil.getPublicKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 解密：从token中获取登录用户名（项目使用）
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.get(JwtConstant.CLAIM_KEY_USERNAME,String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 解密：从token中获取登录用户名（项目使用）
     */
    public Integer getUserIdFromToken(String token) {
        Integer userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.get(JwtConstant.CLAIM_KEY_USERID,Integer.class);
        } catch (Exception e) {
            userId = 0;
        }
        return userId;
    }

    /**
     * 加密： 根据用户名生成token（项目使用）
     */
    public String generateUserNameStr(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.CLAIM_KEY_USERNAME,username);
        claims.put(JwtConstant.CLAIM_KEY_author, new Date());
        return generateToken(claims);
    }

    /**
     * 加密： 根据用户名和id生成token（项目使用）
     */
    public String generateUserNameAndIdStr(String username, long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.CLAIM_KEY_USERNAME,username);
        claims.put(JwtConstant.CLAIM_KEY_USERID,id);
        claims.put(JwtConstant.CLAIM_KEY_author, new Date());
        return generateToken(claims);
    }

    /**
     * 加密： 根据供应商账户和id生成token（项目使用）
     */
    public String generateUserNameAndIdStr(String username, long id, Integer sign) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtConstant.CLAIM_KEY_USERNAME,username);
        claims.put(JwtConstant.CLAIM_KEY_USERID,id);
        claims.put(JwtConstant.CLAIM_KEY_SUPPLIER,1);
        claims.put(JwtConstant.CLAIM_KEY_author, new Date());
        return generateToken(claims);
    }

    /**
     * 解密：从token中获取登录供应商标识（项目使用）
     */
    public Integer getSupplierSign(String token) {
        int sign=0;
        try {
            Claims claims = getClaimsFromToken(token);
            sign = claims.get(JwtConstant.CLAIM_KEY_SUPPLIER,Integer.class);
        } catch (Exception e) {
            return sign;
        }
        return sign;
    }


    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if(StrUtil.isEmpty(oldToken)){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(StrUtil.isEmpty(token)){
            return null;
        }
        //token校验不通过
        Claims claims = getClaimsFromToken(token);
        if(claims==null){
            return null;
        }
        //如果token已经过期，不支持刷新
        if(isTokenExpired(token)){
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if(tokenRefreshJustBefore(token,30*60)){
            return token;
        }else{
            claims.put(JwtConstant.CLAIM_KEY_author, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date author = claims.get(JwtConstant.CLAIM_KEY_author, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if(refreshDate.after(author)&&refreshDate.before(DateUtil.offsetSecond(author,time))){
            return true;
        }
        return false;
    }

}
