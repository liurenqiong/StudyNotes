/** */
package ${fullPkgSubProj}.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ${fullPkgSubProj}.pojo.${capClassName}Pojo;
import ${fullPkgSubProj}.service.${capClassName}Service;


/**
 * @功能:【${tableName} ${tableComment}】controller
 * @项目名:${projectDirName}
 * @作者:${autherName}
 * @日期:${createTime?string('yyyy-MM-dd HH:mm:ss')}
 * @说明：<pre></pre>
 */
@RestController
@RequestMapping("/${className}")
public class ${capClassName}Controller{
	/** ${tableName} ${tableComment}service*/
    @Autowired
    private ${capClassName}Service ${className}Service;
    
  	/**
	 * get
	 * @param ${className}
	 * @param request
	 * @param response
	 */
	@RequestMapping("/get")
	public void get(${capClassName}Pojo ${className}, HttpServletRequest request, HttpServletResponse response) {

	}
}
