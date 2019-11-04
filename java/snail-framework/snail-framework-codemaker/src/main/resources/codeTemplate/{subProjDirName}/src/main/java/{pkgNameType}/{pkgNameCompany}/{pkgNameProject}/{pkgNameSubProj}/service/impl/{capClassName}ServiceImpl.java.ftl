/** */
package ${fullPkgSubProj}.service.impl;

import org.springframework.stereotype.Service;
import ${frameworkBasePkg}.base.service.BaseService;
import ${fullPkgSubProj}.dao.${capClassName}Dao;
import ${fullPkgSubProj}.pojo.${capClassName}Pojo;
import ${fullPkgSubProj}.service.${capClassName}Service;

/**
 * @功能:【${tableName} ${tableComment}】Service
 * @项目名:${projectDirName}
 * @作者:${autherName}
 * @日期:${createTime?string('yyyy-MM-dd HH:mm:ss')}
 * @说明：<pre></pre>
 */
@Service
public class ${capClassName}ServiceImpl extends BaseService<${capClassName}Pojo, ${capClassName}Dao> implements ${capClassName}Service {

}
