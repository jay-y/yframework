package org.yframework.system.web.rest;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.yframework.system.SystemApplication;
import org.yframework.system.domain.User;
import org.yframework.system.repository.UserRepository;
import org.yframework.system.service.UserService;
import org.yframework.system.service.mapper.UserMapper;
import org.yframework.system.web.errors.ExceptionTranslator;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemApplication.class)
public class UserResourceTest
{
    private static final String DEFAULT_ID = "1";

    private static final String DEFAULT_LOGIN = "johndoe";
    private static final String UPDATED_LOGIN = "jhipster";
    private final Logger log = LoggerFactory.getLogger(UserResourceTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restUserMockMvc;

    private User user;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        UserResource userResource = new UserResource(userService);
        this.restUserMockMvc = MockMvcBuilders.
                standaloneSetup(userResource).
                setCustomArgumentResolvers(pageableArgumentResolver).
                setControllerAdvice(exceptionTranslator).
                setMessageConverters(jacksonMessageConverter).
                build();
    }

    @Before
    public void initTest()
    {
        user = this.createEntity();
    }

    /**
     * Create a User.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the User entity.
     */
    private User createEntity()
    {
        User user = new User();
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(RandomStringUtils.random(60));
//        user.setActivated(true);
//        user.setEmail(DEFAULT_EMAIL);
//        user.setFirstName(DEFAULT_FIRSTNAME);
//        user.setLastName(DEFAULT_LASTNAME);
//        user.setImageUrl(DEFAULT_IMAGEURL);
//        user.setLangKey(DEFAULT_LANGKEY);
        return user;
    }
}
