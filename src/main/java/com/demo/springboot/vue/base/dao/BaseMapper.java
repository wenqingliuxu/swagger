package com.demo.springboot.vue.base.dao;

import com.demo.springboot.vue.base.servcie.bo.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础Mapper接口，业务Mapper接口继承BaseMapper
 */
public interface BaseMapper<B extends BaseEntity> {
    /**
     * 分页查询,返回数据
     * @param conditions
     * @return
     */
    List<B> page(Map<String, ? extends Object> conditions);

    /**
     * 分页查询，返回总记录条数
     * @param conditions
     * @return
     */
    long pageCount(Map<String, ? extends Object> conditions);


    /**
     * 普通的查询多行
     *
     * @param map
     * @return
     */
    List<B> selectList(Map<String, ? extends Object> map);


    /**
     * 查询单行数据
     *
     * @param map
     * @return
     */
    B selectOne(Map<String, ? extends Object> map);


    /**
     * 插入数据
     *
     * @return
     */
    int insert(B entity);


    /**
     * 更新数据
     *
     * @return
     */
    int update(B entity);

    /**
     * 删除数据
     *
     * @return
     */
    int delete(long[] ids);

 }
