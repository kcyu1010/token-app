package com.kcyu.springtoken;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.kcyu.springtoken.service.HistoryTableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTokenApplicationTests {

    @Autowired
    private HistoryTableService historyTableService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenerator() {

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)   //是否支持AR模式
                .setAuthor("kcyu") //作者
                .setOutputDir("D:\\Users\\yukc001\\Downloads\\token-app-master\\src\\main\\java") //生成路径
                .setFileOverride(false)//文件覆盖
                .setServiceName("%sService")  //设置生成的service接口名 首字母是否为I
                .setIdType(IdType.AUTO); //主键策略       ;

        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://www.kcyu.top:3306/token")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("4404050");

        //策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)  // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据 库表映射到实体的命名策略
                .setInclude("check_table"); //生成的表

        //包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.kcyu.springtoken")
                .setController("controller")
                .setEntity("entity")
                .setService("service");
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        ag.execute();

    }


}
