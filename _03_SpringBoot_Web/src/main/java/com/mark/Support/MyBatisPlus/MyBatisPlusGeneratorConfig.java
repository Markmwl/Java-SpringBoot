package com.mark.Support.MyBatisPlus;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Scanner;

public class MyBatisPlusGeneratorConfig {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG=new DataSourceConfig
            .Builder("jdbc:oracle:thin:@localhost:1521:orcl","Mark","123456")
            .dbQuery(new OracleQuery())
            .typeConvert(new OracleTypeConvert());

    public static void main(String[] args) {
        String projectPath = "D:\\Coding-Project-仓库\\Java_Project\\Java-SpringBoot\\_03_SpringBoot_Web";
        String outputDir = projectPath +"/src/main/java";
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig((scanner,builder) -> {
                    builder.outputDir(outputDir) // 指定输出目录
                            .author("Mark") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK)//时间策略
                            .commentDate("yyyy-MM-dd")//日期
                            .build();
                })
                .packageConfig((scanner,builder) -> {
                    builder.parent("com.mark") // 设置父包名
                            .entity("Models") // 设置父包模块名
                            .mapper("Mybatis")
                            .controller("Controller")
                            .serviceImpl("Service")
                            .xml("")
                            .build();
                })
                .strategyConfig((scanner,builder) -> {
                    builder.enableCapitalMode()
                            //指定数据库表名，oracle中表名要大写
                            .enableSkipView().addInclude(scanner("T_CDC_DEMO").split(","))
                            .entityBuilder()
                            .enableLombok()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    public static String scanner(String tip){
        Scanner scanner = new Scanner(System.in);
        StringBuilder help =new StringBuilder();
        help.append("请输入："+ tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()){
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)){
                return ipt.toUpperCase();
            }
        }
        throw new MybatisPlusException("请输入正确的：" + tip + "!");
    }
}
