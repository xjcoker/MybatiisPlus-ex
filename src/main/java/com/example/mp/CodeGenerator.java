package com.example.mp;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.H2Query;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 基于H2 FILE文件存储(自动识别数据连接及驱动)
 * <p>
 * mp代码自动生成器
 *
 * @author xiangjun.song
 * @date 2020/3/4
 */
public class CodeGenerator {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xiangjun.song");
        gc.setOpen(false);
        // gc.setSwagger2(true); 需进行实体属性Swagger2注解时开启
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:h2:file:~/.h2/testdb");
        dsc.setDriverName("org.h2.Driver");
        dsc.setUsername("root");
        dsc.setPassword("test");
        dsc.setDbQuery(new H2Query() {
            /**
             * 重写父类预留查询自定义字段<br>
             * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
             * 模板中调用：  table.fields 获取所有字段信息，
             * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
             */
            @Override public String[] fieldCustom() {
                return new String[] {"NULL", "PRIVILEGES"};
            }
        });
        // 可手动配置数据库信息获取sql
        dsc.setDbQuery(new IDbQuery() {

            @Override public String tablesSql() {
                return "SELECT * FROM information_schema.tables where 1 = 1 ";
            }

            @Override public String tableFieldsSql() {
                return "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME= '%s' ";
            }

            @Override public String tableName() {
                return "TABLE_NAME";
            }

            @Override public String tableComment() {
                return "REMARKS";
            }

            @Override public String fieldName() {
                return "COLUMN_NAME";
            }

            @Override public String fieldType() {
                return "TYPE_NAME";
            }

            @Override public String fieldComment() {
                return "REMARKS";
            }

            @Override public String fieldKey() {
                return "PRIMARY_KEY";
            }

            @Override public boolean isKeyIdentity(ResultSet results) throws SQLException {
                return "auto_increment".equals(results.getString("Extra"));
            }

            @Override public String[] fieldCustom() {
                return new String[0];
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.mp");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo
                    .getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntitySerialVersionUID(true);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //        strategy.setSuperEntityClass("");
        //        公共父类
        //        strategy.setSuperControllerClass("");
        //        写于父类中的公共字段
        //        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("大写的表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
