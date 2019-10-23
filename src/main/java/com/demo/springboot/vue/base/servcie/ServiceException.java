package com.demo.springboot.vue.base.servcie;

/**
 * 业务逻辑层异常的父类，主要用来在Service层抛出业务逻辑异常，如注册验证的邮箱无法连接等，而对于Dao层抛出的异常，可以不进行处理，在view统一处理<br/>
 * 
 * 业务逻辑层采用了AOP生成代理，默认的声明式事务遇到RuntimeException会回滚事务,<br/>
 * 而如果Dao层是采用Spring的数据访问集成模块整合的，则数据访问层的异常体系是由<br/>
 * Spring转化过的，这些异常都是由DataAccessException继承而来，而该DataAccessException间接从<br/>
 * RuntimeException继承而来，如：SQL语法异常， BadSqlGrammarException就是从DataAccessException间接继承而来<br/>
 * 
 * 
 * @author 王庆丰
 * @version 2018年4月17日 下午12:04:41
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -1;

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
