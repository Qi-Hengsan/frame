/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wwj.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.wwj.generator.config.GeneratorStrategy;
import com.wwj.generator.constant.GeneratorConstant;
import com.wwj.generator.properties.GeneratorProperties;
import org.springframework.stereotype.Component;

/**
 * spring-boot-plus代码生成器入口类
 **/
@Component
public class MybatisGenerator {

    /**
     * 生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
        GeneratorProperties generatorProperties = new GeneratorProperties();

        // 设置生成信息
        generatorProperties
                .setMavenModuleName("common") // 模块名称
                .setParentPackage("com.wwj.common.module") //父包名称
                .setModuleName("abc") //生成包名
                .setAuthor("12243195@qq.com")
                .setFileOverride(true);

        // 设置生成表信息
//        generatorProperties.addTable("config_cruise_ship", "id");
//        generatorProperties.addTable("config_ship_room_detail", "id");
//        generatorProperties.addTable("config_floor_distribution", "id");
//        generatorProperties.addTable("config_route", "id");
//        generatorProperties.addTable("config_route_ship", "id");
//        generatorProperties.addTable("config_stop_port", "id");
//        generatorProperties.addTable("config_add_pro_rel", "id");
//        generatorProperties.addTable("config_additional_product", "id");
//        generatorProperties.addTable("config_card_prefix", "id");
//        generatorProperties.addTable("config_product_type", "id");
        generatorProperties.addTable("orders_temp", "id");

        // 设置表前缀
        //generatorProperties.setTablePrefix(Arrays.asList("report_"));

        // 数据源配置
        generatorProperties.getDataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/heitu?useUnicode=true&characterEncoding=UTF-8&useSSL=false");

        // 生成配置
        generatorProperties.getGeneratorConfig()
                .setGeneratorStrategy(GeneratorStrategy.ALL)
                .setGeneratorEntity(true)
                .setGeneratorController(false)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true)
                .setGeneratorPageParam(false)
                .setGeneratorQueryVO(false)
                .setPageListOrder(false)
                //.setParamValidation(true)
                .setSwaggerTags(true);

        // 全局配置
        generatorProperties.getMybatisGeneratorConfig().getGlobalConfig()
                .setOpen(true)
                .setSwagger2(true)
                .setIdType(IdType.AUTO)
                .setDateType(DateType.TIME_PACK);

        // 策略配置
        generatorProperties.getMybatisGeneratorConfig().getStrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setVersionFieldName(GeneratorConstant.VERSION)
                .setLogicDeleteFieldName(GeneratorConstant.DELETED);

        // 生成代码
        com.wwj.generator.CodeGenerator codeGenerator = new com.wwj.generator.CodeGenerator();

        codeGenerator.generator(generatorProperties);

    }


}
