package org.liezi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liezi.modules.common.service.IGeneratorIDService;
import org.liezi.modules.system.entity.Config;
import org.liezi.modules.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lake
 * @description: Config 单元测试
 * @date 2019/6/24
 */
@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LieziApplication.class)
public class ConfigServiceTest {

    @Autowired
    private IConfigService configService;
    @Autowired
    private IGeneratorIDService generatorIDService;

    @Test
    public void CRUDTest() {

        //CREATE
        Config config = new Config();
        config.setConfigId(generatorIDService.generatorStringID());
        config.setParamKey("test");
        config.setParamValue("test_value");
        config.setStatus(1);
        boolean addFlag = configService.save(config);
        Assert.assertTrue(addFlag);

    }

}
