package com.liuwq.service.impl;

import com.liuwq.dao.UserMapper;
import com.liuwq.po.RspInfoBO;
import com.liuwq.po.User;
import com.liuwq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/23 0023 上午 11:41
 * @version: V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RspInfoBO queryListByCondition(User bo) {
        return new RspInfoBO(userMapper.queryUserPage(bo));
    }

    @Override
    public User selectUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public RspInfoBO insertUser(User bo) {
        userMapper.insertSelective(bo);
        return new RspInfoBO();
    }

    @Override
    public RspInfoBO updateUser(User bo) {
        userMapper.updateByPrimaryKeySelective(bo);
        return new RspInfoBO();
    }

    @Override
    public RspInfoBO deleteUserById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        return new RspInfoBO();
    }

}
