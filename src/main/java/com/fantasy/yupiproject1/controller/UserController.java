package com.fantasy.yupiproject1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fantasy.yupiproject1.common.BaseResponse;
import com.fantasy.yupiproject1.common.ErrorCode;
import com.fantasy.yupiproject1.common.ResultUtils;
import com.fantasy.yupiproject1.exception.BusinessException;
import com.fantasy.yupiproject1.model.domain.User;
import com.fantasy.yupiproject1.model.domain.request.UserLoginRequest;
import com.fantasy.yupiproject1.model.domain.request.UserRegisterRequest;
import com.fantasy.yupiproject1.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fantasy.yupiproject1.constant.UserConstant.ADMIN_ROLE;
import static com.fantasy.yupiproject1.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://101.133.226.57"},allowCredentials = "true")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            throw  new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw  new BusinessException(ErrorCode.NULL_ERROR);
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        return ResultUtils.success(id);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            throw  new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request == null) throw new BusinessException(ErrorCode.PARAM_ERROR);
        return  ResultUtils.success(userService.userLogout(request));
    }

    /**
     * 获取当前用户
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object object = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) object;
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        User byId = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(byId);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String userName, HttpServletRequest request){
        //仅管理员能查询
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NOT_AUTH,"非管理员权限");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userName != null){
            queryWrapper.like("userName",userName);
        }
        List<User> list = userService.list(queryWrapper);

        List<User> list1 = list.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        ResultUtils.success(list1);
        return ResultUtils.success(list1);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        //仅管理员能查询
        if (!isAdmin(request)){throw new BusinessException(ErrorCode.NOT_AUTH,"非管理员权限") ;}
        if(id <= 0){
            throw new BusinessException(ErrorCode.PARAM_ERROR,"参数为负数");
        }
        return  ResultUtils.success(userService.removeById(id));
    }

    public boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User)userObj;
        if (user !=null || user.getUserRole() != ADMIN_ROLE ) return false;
        return userService.removeById(true);
    }

}
