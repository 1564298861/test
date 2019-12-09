package com.zgt.service;

import com.zgt.entity.User;
import com.zgt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @author qiaoyn
 * @date 2019/06/14
 * @version V1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUsername(User user){
        return userMapper.findByUsername(user.getUsername());
    }
    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }
}
