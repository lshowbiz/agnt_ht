package com.joymain.jecs.webapp.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.jecs.Constants;
import org.springframework.web.servlet.ModelAndView;

public class UserControllerTest extends BaseControllerTestCase {

    public void testHandleRequest() throws Exception {
        UserController c = (UserController) ctx.getBean("userController");
        ModelAndView mav =
            c.handleRequest((HttpServletRequest) null,
                            (HttpServletResponse) null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.USER_LIST));
        assertEquals(mav.getViewName(), "userList");
    }
}
