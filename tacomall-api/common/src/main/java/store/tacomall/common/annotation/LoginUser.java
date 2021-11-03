/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 21:10:34
 * @LastEditTime: 2021-10-10 21:10:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/annotation/LoginUser.java
 */
package store.tacomall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    boolean required() default true;
}
