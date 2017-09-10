package org.yframework.system.web.rest;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yframework.system.SystemApplication;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplication.class)
public class UserResourceTest
{
    private final Logger log = LoggerFactory.getLogger(UserResourceTest.class);
}
