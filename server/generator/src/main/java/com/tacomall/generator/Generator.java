package com.tacomall.generator;

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
        put("table", "ma_carousel");
        put("domain", "ma");
      }
    }));
    for (Map<String, String> cfg : doGenTableCfg) {
      String table = cfg.get("table");
      String domain = cfg.get("domain");
      FastAutoGenerator.create(
          "jdbc:mysql://localhost:3306/tacomall?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true",
          "root", "123456").globalConfig(builder -> {
            builder.author("码上talk").outputDir(System.getProperty("user.dir") + "\\server\\common\\src\\main\\java")
                .commentDate("yyyy-MM-dd");

          }).packageConfig(builder -> {
            builder.parent("com.tacomall").moduleName("common").entity("entity." + domain)
                .mapper("mapper." + domain).xml("mapper")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")
                    + "\\server\\common\\src\\main\\resources\\mapper\\" + domain));
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