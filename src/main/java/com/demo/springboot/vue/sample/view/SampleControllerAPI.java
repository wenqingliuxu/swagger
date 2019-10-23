package com.demo.springboot.vue.sample.view;


import com.demo.springboot.vue.base.servcie.bo.Page;
import com.demo.springboot.vue.base.view.PackResponse;
import com.demo.springboot.vue.sample.service.bo.Sample;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 有关API的注解比较多，如果直接写在Controller中，会导致Controller臃肿，可以将API相关的内容放在接口中
 *
 * 注意：MVC中有关参数的注解需要加入，否则MVC和Swagger都会出现问题。
 */
@Api(value = "示例Controller", tags = {"演示Vue.js的API"})
public interface SampleControllerAPI {

  /*参数需要加入注解：@RequestBody表示请求的数据是json格式，通过请求体传入，否则需要使用@ApiImplicitParam*/
  /*PackResponse表示响应使用的实体*/
  @ApiOperation(value = "创建", notes = "创建示例",response = PackResponse.class)
  public Sample add(@ApiParam(name="entity",value="json格式",required=true)@RequestBody Sample entity, HttpServletRequest request);

  @ApiOperation(value = "删除", notes = "删除示例")
  /*注意，如果要通过明确说明参数，则@ApiParam的name要和参数名称一致，否则Swagger的测试工具用不了*/
  public Sample delete(@ApiParam(name="id",value="整形ID",required=true)@PathVariable long id);

  @ApiOperation(value = "更新", notes = "更新示例")
  public Sample update(@ApiParam(name="entity",value="json格式，id必须有值",required=true) @RequestBody Sample entity, @PathVariable("id") long id,HttpServletRequest request) ;


  @ApiOperation(value = "部分更新", notes = "部分更新示例")
  /*参数也可以不描述，会自动生成*/
  public Sample patch(@RequestBody Sample entity,@PathVariable("id") long id, HttpServletRequest request) ;

  @ApiOperation(value = "查询单个", notes = "查询单个示例")
  @ApiImplicitParams({
          @ApiImplicitParam(name="id",value="整形ID",required=true,dataType="Long",paramType="path")
  })
  public Sample get(@PathVariable("id") long id) ;

  @ApiOperation(value = "按照条件查询", notes = "按照条件查询")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "name", value = "模糊查询的字符串", required = false, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "long", paramType = "query"),
          @ApiImplicitParam(name = "pageSize", value = "每页多少行", required = true, dataType = "long", paramType = "query")
  })
  @ApiResponses(value = { @ApiResponse(code = 200, message = "成功",response = PackResponse.class) })
  //@ApiResponse必须嵌套在@ApiResponses中
  public Page<Sample> find(@ApiIgnore @RequestParam Map<String,String> map);
}
