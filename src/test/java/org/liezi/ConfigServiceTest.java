package org.liezi;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lake
 * @description: ConfigService测试类
 * @date 2019/6/24
 */
@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LieziApplication.class)
public class ConfigServiceTest {



}
