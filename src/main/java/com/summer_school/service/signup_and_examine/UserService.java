package com.summer_school.service.signup_and_examine;

import com.summer_school.pojo.user.lasting.AbstractUser;

public interface UserService {


    /**
     * 增加账号
     * @param abstract_user
     * @return
     */
    public boolean add(AbstractUser abstract_user);

    /**
     * 删除账号
     * @param abstract_user
     * @return
     */
    public boolean delete(AbstractUser abstract_user);

    /**
     * 修改账号
     * @param abstract_user
     * @return
     */
    public boolean update(AbstractUser abstract_user);

/*    *//**
     * 按id查询1个
     * @param id
     * @return
     *//*
    public AbstractUser selectById(Integer id);

    *//**
     * 查询全部
     * @return
     *//*
    public List<AbstractUser> selectAll();*/
}
