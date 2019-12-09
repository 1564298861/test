package com.zgt.mapper;

import com.zgt.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qiaoyn
 * @date 2019/06/14
 */
@Mapper
public interface UserMapper {

    User findByUsername(String username);

    User findUserById(String id);
}
