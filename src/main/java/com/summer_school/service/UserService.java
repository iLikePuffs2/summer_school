package com.summer_school.service;

import com.summer_school.domain.user.AbstractUser;

import java.util.List;

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

    /**
     * 系统内部查询（id+暑期学校id+身份）
     * @param id
     * @param summerSchoolId
     * @param identity
     * @return
     */
    public AbstractUser internalSelect(Integer id,Integer summerSchoolId,String identity);



    /**
     * 查询全部
     * @return
     */
    public List<AbstractUser> getAll();
}
