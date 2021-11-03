/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-09-18 14:01:06
 * @LastEditTime: 2021-10-23 10:12:15
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/annotation/NoRepeatSubmit.java
 */

package store.tacomall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    long seconds() default 5;

    String location() default "NoRepeatSubmit";

    int argIndex() default 0;

    String name() default "";
}