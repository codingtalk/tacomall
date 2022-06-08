/***
 * @Author: 码上talk|RC
 * @Date: 2021-10-06 14:47:17
 * @LastEditTime: 2021-11-04 13:50:25
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/generator/src/main/java/store/tacomall/generator/Generator.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {
    public static void main(String[] args) {
        ArrayList<Map<String, String>> doGenTableCfg = new ArrayList<>(Arrays.asList(new HashMap<String, String>() {
            {
                put("table", "approve_type");
                put("domain", "approve");
            }
        }));
        for (Map<String, String> cfg : doGenTableCfg) {
            String table = cfg.get("table");
            String domain = cfg.get("domain");
            FastAutoGenerator.create(
                    "jdbc:mysql://localhost:3306/tacomall?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true",
                    "root", "123456").globalConfig(builder -> {
                        builder.author("码上talk").outputDir(System.getProperty("user.dir") + "\\common\\src\\main\\java")
                                .commentDate("yyyy-MM-dd");

                    }).packageConfig(builder -> {
                        builder.parent("store.tacomall").moduleName("common").entity("entity." + domain)
                                .mapper("mapper." + domain).xml("mapper")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")
                                        + "\\common\\src\\main\\resources\\mapper\\" + domain));
                    }).strategyConfig(builder -> {
                        builder.addInclude(table).entityBuilder().enableLombok().logicDeleteColumnName("is_delete")
                                .mapperBuilder().superClass(BaseMapper.class).formatMapperFileName("%sMapper")
                                .enableMapperAnnotation().formatXmlFileName("%sMapper");
                    }).templateConfig(builder -> {
                        builder.disable(TemplateType.CONTROLLER).disable(TemplateType.SERVICE)
                                .disable(TemplateType.SERVICEIMPL);
                    }).templateEngine(new FreemarkerTemplateEngine()).execute();
        }
    }
}