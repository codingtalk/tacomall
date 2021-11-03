/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-19 22:03:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/test/java/store/tacomall/apiadmin/RandomTest.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin;

import org.junit.jupiter.api.Test;

import store.tacomall.common.util.RandomUtil;

public class RandomTest {

  @Test
  public void test1() {
    Integer idx = 0;
    while (true) {
      System.out.printf(RandomUtil.createBigSmallLetterStrOrNumberRadom(16));
      idx++;
      if (idx > 100000) {
        break;
      }
    }
  }
}
