package com.fantasy.yupiproject1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fantasy.yupiproject1.common.ErrorCode;
import com.fantasy.yupiproject1.exception.BusinessException;
import com.fantasy.yupiproject1.model.domain.User;
import com.fantasy.yupiproject1.service.UserService;
import com.fantasy.yupiproject1.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import static com.fantasy.yupiproject1.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author Mitsuha
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-03-20 20:55:16
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    UserMapper userMapper;
    /**
     * 用户登录态键
     */

    @Override
    public long userRegister(String userAccout, String userPassword, String checkPassword, String planetCode) {
        //1.校验用户账户、密码、确认密码、是否符合要求
        //判断三者非空，apache.commons.lang3包下的函数
        if(StringUtils.isAnyBlank(userAccout,userPassword,checkPassword)){
            //todo 修改为自定义异常
            throw new BusinessException(ErrorCode.PARAM_ERROR,"参数为空");
        }
        //账号长度不小于4位
        if (userAccout.length()<4) throw new BusinessException(ErrorCode.PARAM_ERROR,"账号过短");
        //账户不包含特殊字符
        String regEx = "[`\\s~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        if (Pattern.compile(regEx).matcher(userAccout).find()) throw new BusinessException(ErrorCode.PARAM_ERROR,"账号含有敏感字符");
        //账号密码不小于8位
        if (userPassword.length()<8 || checkPassword.length()<8) throw new BusinessException(ErrorCode.PARAM_ERROR,"密码过短");

//       密码和校验密码相同
        if(!userPassword.equals(checkPassword)) throw new BusinessException(ErrorCode.PARAM_ERROR,"参数为空");
        //星球不大于5位
        if (planetCode.length() > 5) {
            throw new BusinessException(ErrorCode.PARAM_ERROR,"星球编号过长");
        }
        //账户不能重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount",userAccout);
        long count = this.count(queryWrapper);
        if (count > 0){
            return -1;
        }
        // 星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planetCode", planetCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAM_ERROR,"星球编号重复");
        }
        //对密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        //向数据库插入数据
        User user = new User();
        user.setUserAccount(userAccout);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean save = this.save(user);
        if (!save) return -1;
        return user.getId();
    }

    @Override
    public User userLogin(String userAccout, String userPassword, HttpServletRequest request) {

        //1.校验用户账户、密码、确认密码、是否符合要求
        //判断三者非空，apache.commons.lang3包下的函数
        if(StringUtils.isAnyBlank(userAccout,userPassword)){
            return null;
        }
        //账号长度不小于4位
        if (userAccout.length()<4) return null;
        //账号密码不小于8位
        if (userPassword.length()<8 ) return null;
        //账户不包含特殊字符
        String regEx = "[`\\s~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        if (Pattern.compile(regEx).matcher(userAccout).find()) return null;
        //对密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        //账户密码是否输入正确
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount",userAccout);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user ==null) {
            log.info("user login fail,userAccount donot match userPassword");
            return null;
        }

        //3.数据脱敏
        User safetyUser = getSafetyUser(user);
        //4.记录用户态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser){
        User safetyuser = new User();
        safetyuser.setId(originUser.getId());
        safetyuser.setUsername(originUser.getUsername());
        safetyuser.setUserAccount(originUser.getUserAccount());
        safetyuser.setAvatarUrl(originUser.getAvatarUrl());
        safetyuser.setGender(originUser.getGender());
//        safetyuser.setUserPassword(originUser.getUserPassword());
        safetyuser.setPhone(originUser.getPhone());
        safetyuser.setEmail(originUser.getEmail());
        safetyuser.setUserRole(originUser.getUserRole());
        safetyuser.setUserStatus(originUser.getUserStatus());
        safetyuser.setCreateTime(originUser.getCreateTime());
//        safetyuser.setUpdateTime(originUser.getUpdateTime());
//        safetyuser.setIsDelete(originUser.getIsDelete());
        return safetyuser;
    }
    @Override
    public Integer userLogout(HttpServletRequest request){
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }


}




