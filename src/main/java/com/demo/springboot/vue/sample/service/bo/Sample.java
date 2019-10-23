package com.demo.springboot.vue.sample.service.bo;


import com.demo.springboot.vue.base.servcie.bo.BaseEntity;

/**
 * 实体类示例
 */
public class Sample extends BaseEntity {

	private long id=0;

	private String name = null;

	@Override
	public long getKey() {
		return id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
