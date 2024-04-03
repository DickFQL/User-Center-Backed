package com.fantasy.yupiproject1.service;

import com.fantasy.yupiproject1.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mitsuha
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-03-20 20:55:16
 */
public interface UserService extends IService<User> {

    /**
     * @param userAccout    用户账户
     * @param userPassword  用户密码
     * @param checkPassword 确认密码
     * @param planetCode
     * @return
     */
    long userRegister(String userAccout, String userPassword, String checkPassword, String planetCode);

    /**
     *
     * @param userAccout 用户账户
     * @param userPassword  用户密码
     * @return
     */
    User userLogin(String userAccout, String userPassword, HttpServletRequest request);

    /**
     *
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     * @return
     */
    Integer userLogout(HttpServletRequest request);
}
