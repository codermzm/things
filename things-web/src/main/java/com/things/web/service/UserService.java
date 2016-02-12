package com.things.web.service;

import com.things.frame.core.base.dao.IBaseDao;
import com.things.frame.core.base.service.AbstractService;
import com.things.web.entity.UserEntity;

public class UserService extends AbstractService<UserEntity> {



    @Override
    protected IBaseDao<UserEntity> getBaseDao() {
        return null;
    }
}
