package com.demo.springboot.vue.base.view;

import com.demo.springboot.vue.base.servcie.IBaseService;
import com.demo.springboot.vue.base.servcie.bo.BaseEntity;
import com.demo.springboot.vue.base.servcie.bo.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 符合RESTful设计规则的方式：
 * 1:接口设计<br>
 * -->A:GET（SELECT）：从服务器取出资源（一项或多项）。
 * -->B:POST（CREATE）：在服务器新建一个资源。
 * -->C:PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
 * -->D:PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
 * -->E:DELETE（DELETE）：从服务器删除资源。
 * <p>
 * 2:错误处理<br>
 * HTTP状态码是HTTP协议的一部分，应用层的API是不应该和下层协议过量耦合的，而且响应状态码4、5的响应可能为被web服务器强制处理，
 * 如：重定向，所以应该定义自己的错误码，HTTP的状态码重置为200。为了客户端能够统一处理响应，所以成功的结果也应该二次包装.
 * <p>
 * {success:true|false, data:[]|{} [, error_code:, error_message:] } 通过success字段来表明这个请求的成功或失败；如果
 * 成功，则可以读取data数据进行后续处理；如果失败，可以读取error_code和error_message进行错误的处理。这样的好处就是，既不
 * 用关心HTTP状态码，也不用对成功或失败来处理不同的JSON结构。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * RESTfulController的基类，可以提供通用方法
 * <p>
 * <p>
 * B：业务实体类型
 * T：Service类型
 *
 * @author 王庆丰
 */
public class BaseRESTfulController<T extends IBaseService<B>, B extends BaseEntity> {
    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * Service
     */
    @Autowired
    protected T service;

    /**
     * 创建资源
     * url:子类类级别的url
     *
     * @param entity  json反序列化的实体
     * @param request
     * @return 返回创建的实体
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public B add(@RequestBody B entity, HttpServletRequest request) {
        entity.setCreateTime(new Date());
        entity.setModifyTime(entity.getCreateTime());
        /*应该去系统登录用户*/
        entity.setCreateUser("create_user");
        entity.setModifyUser(entity.getCreateUser());
        this.service.add(entity);
        return entity;
    }


    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public B delete(@PathVariable("id") long id) {
        this.service.delete(new long[]{id});
        return null;
    }

    /**
     * 更新资源
     *
     * @param entity
     * @param request
     * @return 更新后的完整数据
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public B update(@RequestBody B entity, @PathVariable("id") long id,HttpServletRequest request) {
        entity.setModifyTime(new Date());
        /*应该取登录用户*/
        entity.setModifyUser("update_user");
        return this.service.update(entity);
    }

    /**
     * 部分更新
     *
     * @param entity
     * @param request
     * @return 更新后的部分数据
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public B patch(@RequestBody B entity, @PathVariable("id") long id,HttpServletRequest request) {
        entity.setModifyTime(new Date());
        /*应该取登录用户*/
        entity.setModifyUser("update_user");
        return this.service.update(entity);
    }


    /**
     * 查询单个：
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public B get(@PathVariable("id") long id) {
        Map<String, Long> map = new HashMap() {{
            put("id", id);
        }};

        return service.get(map);
    }

    /**
     * 查询多个：
     * url:子类,类级别的url
     * <p>
     * ?pageNum=1&pageSize=10：指定第几页，以及每页的记录数。
     * ?sortby=name&order=asc：指定返回结果按照哪个属性排序，以及排序顺序。
     * ?sample_id=1：指定筛选条件
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Page<B> find(@RequestParam Map<String,String> map) {
        logger.info("find............................");
        return service.page(map);
    }

}
