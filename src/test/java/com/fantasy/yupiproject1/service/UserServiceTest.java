package com.fantasy.yupiproject1.service;

import com.fantasy.yupiproject1.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

import java.util.regex.Pattern;

/**
 * 用户服务测试
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    public UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("douyu");
        user.setUserAccount("123");
        user.setAvatarUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.huitu.com%2Fdesign%2Fshow%2F20220805%2F104321059070.html&psig=AOvVaw1GXU8568LNFgdFBaEKjkrB&ust=1711029436069000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOi9vMj_goUDFQAAAAAdAAAAABAE");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("456");
        boolean res = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(res);
    }

    @Test
    public void regUser(){
        String userAccout = "123";
        String regEx = "[`\\s~!@#$%^&*()+=|{}:;\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";

        if (Pattern.compile(regEx).matcher(userAccout).find()) System.out.println("匹配到了");
        else System.out.println("没匹配到");
    }
    @Test
    public void encrypttest(){
        String  string= "123";
        String md5Str = DigestUtils.md5DigestAsHex(string.getBytes());
        System.out.println(md5Str);
    }

    @Test
    public void testUser(){

        String userAccount = "";
        String userPassword = "";
        String checkPassword = "";
        String planetCode = "";
        //账号非空
        long res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //账号不小于4位
        userAccount = "abc";
        userPassword = "password";
        checkPassword = "password";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //e.账户不包含特殊字符
        userAccount = "abc! a";
        userPassword = "password";
        checkPassword = "passwort";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //密码就 不小于8 位吧
        userAccount = "abcd";
        userPassword = "passwor";
        checkPassword = "passwor";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);

        //密码和校验密码相同
        userAccount = "abcd";
        userPassword = "password";
        checkPassword = "passwort";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //星球码不超过5位
        checkPassword = "password";
        planetCode = "123456";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //账户不能重复
        userAccount = "yupi";
        userPassword = "password";
        checkPassword = "password";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);
        //星球码不重复
        planetCode = "12345";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1,res);

        planetCode = "1234";
        userAccount = "yupi3";
        res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertTrue(res>0);
    }
    @Test
    public void usertest(){

        String userAccount = "";
        String userPassword = "";
        String checkPassword = "";
        String planetCode = "";
        //账号非空
        planetCode = "1234";
        userAccount = "fantasy";
        userPassword = "123456789";
        checkPassword = "123456789";
        long res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertTrue(res>0);
    }
}