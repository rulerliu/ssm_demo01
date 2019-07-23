package com.liuwq.controller;

import com.liuwq.po.User;
import com.liuwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/23 0023 上午 11:40
 * @version: V1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/showUser.do")
    @ResponseBody
    public User selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = userService.selectUser(id);
        return user;
    }

}
