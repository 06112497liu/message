/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.bbd.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试dao层的基础类
 *
 * @author tjwang
 * @version $Id: BaseDaoTest.java, v 0.1 2017/1/25 0025 17:29 tjwang Exp $
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@ContextConfiguration("classpath:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseJobTest extends Assert {

    protected Logger logger = LoggerFactory.getLogger(getClass());

}
