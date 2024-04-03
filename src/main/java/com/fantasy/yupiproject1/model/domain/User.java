package com.fantasy.yupiproject1.model.domain;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import java.util.Date;
//import io.swagger.annotations.ApiModelProperty;
//import org.hibernate.validator.constraints.Length;

/**
* 用户
* @TableName user
*/
public class User implements Serializable {

    /**
    * id
    */
//    @NotNull(message="[id]不能为空")
//    @ApiModelProperty("id")
    private Long id;
    /**
    * 用户昵称
    */
//    @Size(max= 256,message="编码长度不能超过256")
//    @ApiModelProperty("用户昵称")
//    @Length(max= 256,message="编码长度不能超过256")
    private String username;
    /**
    * 账号
    */
//    @Size(max= 256,message="编码长度不能超过256")
//    @ApiModelProperty("账号")
//    @Length(max= 256,message="编码长度不能超过256")
    private String userAccount;
    /**
    * 用户头像
    */
//    @Size(max= 1024,message="编码长度不能超过1024")
//    @ApiModelProperty("用户头像")
//    @Length(max= 1,024,message="编码长度不能超过1,024")
    private String avatarUrl;
    /**
    * 性别
    */
//    @ApiModelProperty("性别")
    private Integer gender;
    /**
    * 密码
    */
//    @NotBlank(message="[密码]不能为空")
//    @Size(max= 512,message="编码长度不能超过512")
//    @ApiModelProperty("密码")
//    @Length(max= 512,message="编码长度不能超过512")
    private String userPassword;
    /**
    * 电话
    */
//    @Size(max= 128,message="编码长度不能超过128")
//    @ApiModelProperty("电话")
//    @Length(max= 128,message="编码长度不能超过128")
    private String phone;
    /**
    * 邮箱
    */
//    @Size(max= 512,message="编码长度不能超过512")
//    @ApiModelProperty("邮箱")
//    @Length(max= 512,message="编码长度不能超过512")
    private String email;
    /**
    * 状态 0 - 正常
    */
//    @NotNull(message="[状态 0 - 正常]不能为空")
//    @ApiModelProperty("状态 0 - 正常")
    private Integer userStatus;
    /**
    * 创建时间
    */
//    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 
    */
//    @ApiModelProperty("")
    private Date updateTime;
    /**
    * 是否删除
    */
//    @NotNull(message="[是否删除]不能为空")
//    @ApiModelProperty("是否删除")
    @TableLogic
    private Integer isDelete;
    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
//    @NotNull(message="[用户角色 0 - 普通用户 1 - 管理员]不能为空")
//    @ApiModelProperty("用户角色 0 - 普通用户 1 - 管理员")
    private Integer userRole;
    /**
    * 星球编号
    */
//    @Size(max= 512,message="编码长度不能超过512")
//    @ApiModelProperty("星球编号")
//    @Length(max= 512,message="编码长度不能超过512")
    private String planetCode;

    /**
    * id
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 用户昵称
    */
    public void setUsername(String username){
    this.username = username;
    }

    /**
    * 账号
    */
    public void setUserAccount(String userAccount){
    this.userAccount = userAccount;
    }

    /**
    * 用户头像
    */
    public void setAvatarUrl(String avatarUrl){
    this.avatarUrl = avatarUrl;
    }

    /**
    * 性别
    */
    public void setGender(Integer gender){
    this.gender = gender;
    }

    /**
    * 密码
    */
    public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
    }

    /**
    * 电话
    */
    public void setPhone(String phone){
    this.phone = phone;
    }

    /**
    * 邮箱
    */
    public void setEmail(String email){
    this.email = email;
    }

    /**
    * 状态 0 - 正常
    */
    public void setUserStatus(Integer userStatus){
    this.userStatus = userStatus;
    }

    /**
    * 创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 
    */
    public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }

    /**
    * 是否删除
    */
    public void setIsDelete(Integer isDelete){
    this.isDelete = isDelete;
    }

    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    public void setUserRole(Integer userRole){
    this.userRole = userRole;
    }

    /**
    * 星球编号
    */
    public void setPlanetCode(String planetCode){
    this.planetCode = planetCode;
    }


    /**
    * id
    */
    public Long getId(){
    return this.id;
    }

    /**
    * 用户昵称
    */
    public String getUsername(){
    return this.username;
    }

    /**
    * 账号
    */
    public String getUserAccount(){
    return this.userAccount;
    }

    /**
    * 用户头像
    */
    public String getAvatarUrl(){
    return this.avatarUrl;
    }

    /**
    * 性别
    */
    public Integer getGender(){
    return this.gender;
    }

    /**
    * 密码
    */
    public String getUserPassword(){
    return this.userPassword;
    }

    /**
    * 电话
    */
    public String getPhone(){
    return this.phone;
    }

    /**
    * 邮箱
    */
    public String getEmail(){
    return this.email;
    }

    /**
    * 状态 0 - 正常
    */
    public Integer getUserStatus(){
    return this.userStatus;
    }

    /**
    * 创建时间
    */
    public Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 
    */
    public Date getUpdateTime(){
    return this.updateTime;
    }

    /**
    * 是否删除
    */
    public Integer getIsDelete(){
    return this.isDelete;
    }

    /**
    * 用户角色 0 - 普通用户 1 - 管理员
    */
    public Integer getUserRole(){
    return this.userRole;
    }

    /**
    * 星球编号
    */
    public String getPlanetCode(){
    return this.planetCode;
    }

}
