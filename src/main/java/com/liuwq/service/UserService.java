package com.liuwq.service;

import com.liuwq.po.RspInfoBO;
import com.liuwq.po.User;

public interface UserService {

    RspInfoBO queryListByCondition(User bo);

    User selectUser(Integer userId);

    RspInfoBO insertUser(User bo);

    RspInfoBO updateUser(User bo);

    RspInfoBO deleteUserById(Integer id);
}
