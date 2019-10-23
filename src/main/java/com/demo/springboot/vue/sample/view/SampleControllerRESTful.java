package com.demo.springboot.vue.sample.view;

import com.demo.springboot.vue.base.servcie.bo.Page;
import com.demo.springboot.vue.base.view.BaseRESTfulController;
import com.demo.springboot.vue.sample.service.ISampleService;
import com.demo.springboot.vue.sample.service.bo.Sample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * RESTful的使用
 *
 * http://localhost:8080/swagger-ui.html
 *
 * @author 王庆丰
 */
@RestController
@RequestMapping("/api/v1/samples")
public class SampleControllerRESTful extends BaseRESTfulController<ISampleService, Sample>implements SampleControllerAPI{

    private static final Log log = LogFactory.getLog(SampleControllerRESTful.class);

    /*Controller在同时继承和实现情况下，接口中的API注解无效，所以重写对应方法*/

    @Override
    public Sample add(Sample entity, HttpServletRequest request) {
        return super.add(entity, request);
    }

    @Override
    public Sample delete(long id) {
        return super.delete(id);
    }

    @Override
    public Sample update(Sample entity, long id, HttpServletRequest request) {
        return super.update(entity, id, request);
    }

    @Override
    public Sample patch(Sample entity, long id, HttpServletRequest request) {
        return super.patch(entity, id, request);
    }

    @Override
    public Sample get(long id) {
        return super.get(id);
    }

    @Override
    public Page<Sample> find( Map<String, String> map) {
        return super.find(map);
    }
}
