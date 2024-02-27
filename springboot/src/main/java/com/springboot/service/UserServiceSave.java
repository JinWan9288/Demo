package com.springboot.service;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceSave {
    @Resource
    private UserMapper userMapper;

    public int save(User user) {
        if (user.getId() == null) { // user没有id, 找不到id,则做新增 insert 操作
            return userMapper.insert(user);
        } else { // 找到了user的id,做更新操作 update
            return userMapper.update(user);
        }
    }

//    public List<User> list() {
//        return userMapper.list();
//    }

}