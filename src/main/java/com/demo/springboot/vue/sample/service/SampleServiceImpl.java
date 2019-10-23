package com.demo.springboot.vue.sample.service;

import com.demo.springboot.vue.base.servcie.impl.BaseServiceImpl;
import com.demo.springboot.vue.sample.dao.SampleMapper;
import com.demo.springboot.vue.sample.service.bo.Sample;
import org.springframework.stereotype.Service;

/**
 * Service示例
 */
@Service
public class SampleServiceImpl extends BaseServiceImpl<SampleMapper, Sample> implements ISampleService {

}
