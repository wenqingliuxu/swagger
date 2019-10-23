package com.demo.springboot.vue.base.servcie;

import com.demo.springboot.vue.base.servcie.bo.BaseEntity;
import com.demo.springboot.vue.base.servcie.bo.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service基础接口
 *
 * @param <B>:对应实体类型
 */
public interface IBaseService<B extends BaseEntity> {
    /**
     * 分页查询
     *
     * @param conditions 查询条件,由VIEW层传入
     * @return 查询数据和分页数据
     */
    Page<B> page(Map<String, ? extends Object> conditions);

    /**
     * 普通查询
     *
     * @param conditions conditions 查询条件,由VIEW层传入
     * @return
     */
    List<B> list(Map<String, ? extends Object> conditions);

    /**
     * 查询单个对象
     *
     * @param conditions
     * @return
     */
    B get(Map<String, ? extends Object> conditions);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Transactional
    B add(B entity);

    /**
     * 批量插入数据
     *
     * @param entities
     * @return
     */
    @Transactional
    List<B> addBatch(List<B> entities);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Transactional
    B update(B entity);

    /**
     * 批量更新数据
     *
     * @param entities
     * @return
     */
    @Transactional
    List<B>  updateBatch(List<B> entities);

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @Transactional
    void delete(long[] ids);

    /**
     * 判断数据是否存在
     *
     * @param conditions
     * @return
     */
    boolean exist(Map<String, ? extends Object> conditions);

}
