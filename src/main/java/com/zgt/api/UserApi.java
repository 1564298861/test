package com.zgt.api;

import com.alibaba.fastjson.JSONObject;
import com.zgt.annotation.UserLoginToken;
import com.zgt.annotation.UserLoginToken;
import com.zgt.entity.User;
import com.zgt.service.TokenService;
import com.zgt.service.UserService;
import com.zgt.util.TokenUtil;
import com.zgt.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
 * @author qiaoyn
 * @date 2019/06/14
 * @version 1.0
 */
@RestController
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    // 登录
    @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public Object login(User user, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        User userForBase = new User();
        userForBase.setId(userService.findByUsername(user).getId());
        userForBase.setUsername(userService.findByUsername(user).getUsername());
        userForBase.setPassword(userService.findByUsername(user).getPassword());
        if (!userForBase.getPassword().equals(user.getPassword())) {
            jsonObject.put("message", "登录失败,密码错误");
            return jsonObject;
        } else {
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);

            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            return jsonObject;

        }
    }
    /***
     * 这个请求需要验证token才能访问
     *
     * @author: qiaoyn
     * @date 2019/12/3
     * @return String 返回类型
     */
    @UserLoginToken
    @ApiOperation(value = "获取信息", notes = "获取信息")
    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());

        return "您已通过验证";
    }
}
