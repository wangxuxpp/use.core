package use.common.spring.ExceptionResolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import use.common.json.JSONResult;
import use.common.util.ExceptionUtil;

/**
 * Spring 请求异常处理
 * 项目名称:bss
 * 类型名称:SpringExceptionDispath
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年5月11日
 * @version:
 */
public class SpringExceptionDispath extends SpringExceptionAbstract implements HandlerExceptionResolver{

	protected final Logger log = LoggerFactory.getLogger(SpringExceptionDispath.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handle, Exception er) {
		
		response.setDateHeader("Expires", 0);  
		response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Prama", "no-cache"); 
		if(isAjax(request))
		{
			return ajaxException(request , response , handle , er);
		} else {
			return htmlException(request , response , handle , er);
		}
		
	}
	/**
	 * 访问页面时出现异常的处理
	 * @param request
	 * @param response
	 * @param handle
	 * @param er
	 * @return
	 */
	private ModelAndView htmlException(HttpServletRequest request, HttpServletResponse response, Object handle, Exception er)
	{
		
		ExceptionUtil.throwError(er,log);
		ModelAndView r = new ModelAndView();
		
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(getHtml(handle , er));
			response.getWriter().flush();
		} catch (IOException e) {
			ExceptionUtil.throwError(e,log);
		}
		r.clear();
		return r;
	}
	/**
	 * 异步ajax出现异常处理
	 * @param request
	 * @param response
	 * @param handle
	 * @param er
	 * @return
	 */
	private ModelAndView ajaxException(HttpServletRequest request, HttpServletResponse response, Object handle, Exception er)
	{
		ModelAndView r = new ModelAndView();
		
		JSONResult json = new JSONResult();
		json.setJsonType("error");
		json.setJsonMessage(er.getMessage());
		json.write(response);
		
		ExceptionUtil.throwError(er,log);
		
		r.clear();
		return r;
	}

    
    public String getHtml(Object handle, Exception er)
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("<!DOCTYPE html>")
    		.append("<html lang='en'>")
    		.append("<head>")
    		.append("<meta charset=\"UTF-8\">")
    		.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
    		.append("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">")
    		.append("<title>出错了</title>")
	    		.append("<style>")
	    		.append("*{margin: 0;padding: 0;}")
	    		.append(".error_content {width: 100%;max-width: 1000px;margin: 0 auto;text-align: center;padding-top: 100px;}")
	    		.append(".error_content img {display: block;margin: 0 auto;}")
	    		.append(".error_content p {font-size: 32px;line-height: 80px;}")
	    		.append(".error_content span {display: inline-block;font-size: 22px; color: #000;text-decoration: none;}")
	    		.append("</style>")
    		.append("</head>")
    		.append("<body>")
    		.append("<div class=\"error_content\">")
    		.append("<p>出错了</p>")
    		.append("<span>").append(SpringExceptionAbstract.getThrowableMessage(er)).append("</span>")
    		.append("</div>")
    		.append("</body>")
    		.append(" </html>");
    	return sb.toString();

    }
}
