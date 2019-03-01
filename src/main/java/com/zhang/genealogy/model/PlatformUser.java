package com.zhang.genealogy.model;

import java.util.Date;

/**
 * 平台运营人员实体类
 * 
 * @author zhangchao
 * @date 2019-01-29
 */
public class PlatformUser {
	
	/**
	 * 用户编号，主键
	 */
	private Long id;
	
	/**
	 * 用户显示名称
	 */
	private String userShowName;
	
	/**
	 * 登录用户名
	 */
	private String loginName;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户状态，0-正常，1-禁用
	 */
	private Integer userStatus;
	
	/**
	 * 用户类型，0-管理员，1-普通运营人员
	 */
	private Integer userType;
	
	/**
	 * 登录时间
	 */
	private Date loginTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
