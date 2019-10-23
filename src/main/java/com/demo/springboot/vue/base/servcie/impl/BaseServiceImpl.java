package com.demo.springboot.vue.base.servcie.impl;


import com.demo.springboot.vue.base.dao.BaseMapper;
import com.demo.springboot.vue.base.servcie.IBaseService;
import com.demo.springboot.vue.base.servcie.bo.BaseEntity;
import com.demo.springboot.vue.base.servcie.bo.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础Service
 *
 * @param <M>：具体的Mapper
 * @param <B>：具体的实体类
 */
public abstract class BaseServiceImpl<M extends BaseMapper<B >, B extends BaseEntity> implements IBaseService<B> {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    protected M mapper;

    /**
     * 分页查询
     *
     * @param conditions 查询条件,由VIEW层传入
     * @return 查询数据和分页数据
     */
    @Override
    public Page<B> page(Map<String, ? extends  Object> conditions) {
        Page<B> page = new Page<>();
        if (conditions.get("pageNum") != null) {
            page.setPageNum(Integer.parseInt(conditions.get("pageNum").toString()));
        }
        if (conditions.get("pageSize") != null) {
            page.setPageSize(Integer.parseInt(conditions.get("pageSize").toString()));
        }
        //页面中传入的是pageNum、pageSize，需要转为查询条件start、endRow，如果是MySQL需要pageSize
        Map<String, Object> conditionsTemp = new HashMap<>();

        //放入原有参数
        conditionsTemp.putAll(conditions);
        //MySQL的分页参数
        conditionsTemp.put("startIndex", page.getStartRow() - 1);
        conditionsTemp.put("pageSize", page.getPageSize());

        //Oracle的分页参数
       /* conditionsTemp.put("startRow", page.getStartRow());
        conditionsTemp.put("endRow", page.getEndRow());*/

        //查询数据
        page.setResult(mapper.page(conditionsTemp));
        page.setTotal(mapper.pageCount(conditionsTemp));
        return page;
    }

    /**
     * 普通查询
     *
     * @param conditions conditions 查询条件,由VIEW层传入
     * @return
     */
    @Override
    public List<B> list(Map<String, ? extends Object> conditions) {
        return mapper.selectList(conditions);
    }

    /**
     * 查询单个对象
     *
     * @param conditions
     * @return
     */
    @Override
    public B get(Map<String, ? extends Object> conditions) {
        return mapper.selectOne(conditions);
    }

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public B  add(B entity) {
        mapper.insert(entity);
        return entity;
    }

    /**
     * 批量插入数据
     *
     * @param entities
     * @return
     */
    @Override
    @Transactional
    public List<B>  addBatch(List<B> entities) {
        for (B entity : entities) {
            mapper.insert(entity);
        }
        return entities ;
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public B update(B entity) {
        mapper.update(entity);
        return entity;
    }

    /**
     * 批量更新数据
     *
     * @param entities
     * @return
     */
    @Override
    @Transactional
    public List<B> updateBatch(List<B> entities) {
        for (B entity : entities) {
            mapper.update(entity);
        }
        return entities;
    }

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public void delete(long[] ids) {
       mapper.delete(ids);
    }

    /**
     * 判断数据是否存在
     *
     * @param conditions
     * @return
     */
    @Override
    public boolean exist(Map<String, ? extends Object> conditions) {
        return mapper.selectOne(conditions)!=null;
    }


}