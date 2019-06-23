package test.org.liezi.modules.system.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liezi.LieziApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/** 
* ConfigController Tester. 
* 
* @author <Authors name>
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LieziApplication.class)
public class ConfigControllerTest {
    /**
     * 模拟MVC测试对象
      */
   private MockMvc mockMvc;


@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: add(@RequestBody @Validated(value= ValidatorAddGroup.class) Config config, BindingResult result) 
* 
*/ 
@Test
public void testAdd() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: update(@RequestBody @Validated(value= ValidatorUpdateGroup.class) Config config, BindingResult result) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: find(@RequestBody @Validated(value= ValidatorPageGroup.class) Config config, BindingResult result) 
* 
*/ 
@Test
public void testFind() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findList(@RequestBody Config config) 
* 
*/ 
@Test
public void testFindList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delete(@RequestBody String id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here... 
} 


} 
