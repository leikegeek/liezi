package org.liezi;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Auther: Lake
 * @Date: 2018/7/22 00:27
 * @Description: 代码生成器-->工业时代非手工的没有灵魂的代码!!!
 */
public class GeneratorServiceEntity {
    private static String authorName="lake.lei";
    private static String packageName = "org.liezi.modules";
    private static String moduleName = "system";
    private static String basePath = "C:\\Users\\Lake\\IdeaProjects\\liezi";
    private static String javaPath = basePath+"\\src\\main\\java";
    @Test
    public void generateCode() {
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = true;
        generateByTables(serviceNameStartWithI, packageName+"."+moduleName, "system_config","system_role","system_menu");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        //全局基础配置
        config.setActiveRecord(false)
                .setAuthor(authorName)
                .setOutputDir(javaPath)
                .setFileOverride(true)
                .setDateType(DateType.ONLY_DATE)
                .setBaseColumnList(true)
                .setOpen(false)
                .setBaseResultMap(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/wf_sec?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("xxx")
                .setPassword("xxx")
                .setDriverName("com.mysql.jdbc.Driver");
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setNaming(NamingStrategy.underline_to_camel)//命名方式 驼峰
                .setInclude(tableNames)
                .setTablePrefix(new String[] { "system"})//项目DB表前缀
                .setSuperEntityClass("SuperEntity")//指定实体父类
                .setSuperEntityColumns(new String[] { "create_dt", "update_dt","create_by","update_by","delete_flag"});//指定公共字段
        //全局配置
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setMapper("dao")
                ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return basePath+"/src/main/resources/mapper/"+moduleName+"/"+ tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
                ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
               ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}

