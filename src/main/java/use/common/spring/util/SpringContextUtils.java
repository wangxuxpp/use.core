package use.common.spring.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * 项目名称:use.core
 * 类型名称:SpringContextUtils
 * 类型描述:ApplicationContextAwarespring中，普通bean可以通过实现ApplicationContextAware得到ApplicationContext，
 * 需要重写setApplicationContext和getApplicationContext两个方法。
 * 我们知道，是通过setApplicationContext将spring的当前的applicationContext得到，
 * 那么spring是什么时候执行setApplicationContext方法的呢？
 * Spring源码中ApplicationContextAwareProcessor.postProcessBeforeInitialization()，
 * 对继承自ApplicationContextAware的bean进行处理，调用其setApplicationContext。
 * 而ApplicationContextAwareProcessor是在spring容器start的时候生成的。
 * 严格上来说，方法类SpringContextUtils是一个bean,
 * 因为spring能够为我们自动地执行了setApplicationContext。
 * 但是，spring不会无缘无故地为某个类执行它的方法的，所以，就很有必要通过注册方法类AppUtil的方式告知spring有这样子一个类的存在。
 * 其实，方法很简单，就是将方法类SpringContextUtils作为一个普通的bean在spring的配置文件中进行注册：
 * 作者:wx
 * 创建时间:2017年8月30日
 * @version:
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		// TODO Auto-generated method stub
		applicationContext = ac;
	}

	/**
	 * 
	* 方法描述:	
	* 作者: wx
	* @Title: getApplicationContext 
	* TODO(获取applicationContext对象) 
	* @return
	* ApplicationContext    返回类型 
	* @throws
	 */
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	/**
	 * 
	* 方法描述:	
	* 作者: wx
	* @Title: getBeanById 
	* TODO(根据bean的id来查找对象) 
	* @param id
	* @return
	* Object    返回类型 
	* @throws
	 */
	public static Object getBeanById(String id){
		return applicationContext.getBean(id);
	}
	/**
	 * 
	* 方法描述:	
	* 作者: wx
	* @Title: getBeanByClass 
	* TODO(根据bean的class来查找对象) 
	* @param c
	* @return
	* Object    返回类型 
	* @throws
	 */
	public static Object getBeanByClass(Class c){
		return applicationContext.getBean(c);
	}
	/**
	 * 
	* 方法描述:	
	* 作者: wx
	* @Title: getBeansByClass 
	* TODO(根据bean的class来查找所有的对象(包括子类)) 
	* @param c
	* @return
	* Map    返回类型 
	* @throws
	 */
	public static Map getBeansByClass(Class c){
		return applicationContext.getBeansOfType(c);
	}
}
