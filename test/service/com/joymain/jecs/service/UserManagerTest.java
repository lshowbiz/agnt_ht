package com.joymain.jecs.service;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.RoleDao;
import com.joymain.jecs.dao.UserDao;
import com.joymain.jecs.model.Role;
import com.joymain.jecs.model.User;
import com.joymain.jecs.service.impl.RoleManagerImpl;
import com.joymain.jecs.service.impl.UserManagerImpl;
import org.jmock.Mock;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;


public class UserManagerTest extends BaseManagerTestCase {
    //~ Instance fields ========================================================

    private UserManagerImpl userManager = new UserManagerImpl();
    private RoleManagerImpl roleManager = new RoleManagerImpl();
    private Mock userDao = null;
    private Mock roleDao = null;

    //~ Methods ================================================================

    protected void setUp() throws Exception {
        super.setUp();
        userDao = new Mock(UserDao.class);
        userManager.setUserDao((UserDao) userDao.proxy());
        roleDao = new Mock(RoleDao.class);
        roleManager.setRoleDao((RoleDao) roleDao.proxy());
    }
    
    public void testGetUser() throws Exception {
        User testData = new User("1");
        testData.getRoles().add(new Role("user"));
        // set expected behavior on dao
        userDao.expects(once()).method("getUser")
               .with(eq(new Long(1))).will(returnValue(testData));
        
        User user = userManager.getUser("1");
        assertTrue(user != null);
        assertTrue(user.getRoles().size() == 1);
        userDao.verify();
    }

    public void testSaveUser() throws Exception {
        User testData = new User("1");
        testData.getRoles().add(new Role("user"));
        // set expected behavior on dao
        userDao.expects(once()).method("getUser")
               .with(eq(new Long(1))).will(returnValue(testData));
        
        User user = userManager.getUser("1");
        user.setPhoneNumber("303-555-1212");
        userDao.verify();
        
        // reset expectations
        userDao.reset();
        userDao.expects(once()).method("saveUser").with(same(user));
        
        userManager.saveUser(user);
        assertTrue(user.getPhoneNumber().equals("303-555-1212"));
        assertTrue(user.getRoles().size() == 1);
        userDao.verify();
    }

    public void testAddAndRemoveUser() throws Exception {
        User user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);
        
        // set expected behavior on role dao
        roleDao.expects(once()).method("getRoleByName")
               .with(eq("user")).will(returnValue(new Role("user")));
        
        Role role = roleManager.getRole(Constants.USER_ROLE);
        roleDao.verify();
        user.addRole(role);

        // set expected behavior on user dao
        userDao.expects(once()).method("saveUser").with(same(user));
        
        userManager.saveUser(user);
        assertTrue(user.getUsername().equals("john"));
        assertTrue(user.getRoles().size() == 1);
        userDao.verify();
        
        // reset expectations
        userDao.reset();
        
        userDao.expects(once()).method("removeUser").with(eq(new Long(5)));
        userManager.removeUser("5");
        userDao.verify();

        // reset expectations
        userDao.reset();
        userDao.expects(once()).method("getUser").will(returnValue(null));
        user = userManager.getUser("5");
        assertNull(user);
        userDao.verify();
    }
    
    public void testUserExistsException() {
        // set expectations
        User user = new User("admin");
        user.setEmail("matt@raibledesigns.com");
        List users;
        users = new ArrayList();

        users.add(user);
        Exception ex = new DataIntegrityViolationException("");
        userDao.expects(once()).method("saveUser").with(same(user))
               .will(throwException(ex));
        
        // run test
        try {
            userManager.saveUser(user);
            fail("Expected UserExistsException not thrown");
        } catch (UserExistsException e) {
            log.debug("expected exception: " + e.getMessage());
            assertNotNull(e);
        }
    }
}
