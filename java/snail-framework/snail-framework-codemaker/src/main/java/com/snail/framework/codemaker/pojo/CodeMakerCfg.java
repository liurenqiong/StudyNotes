/** */
package com.snail.framework.codemaker.pojo;

import java.util.Date;
import java.util.Map;

/**
 * @功能:
 * @作者:snail
 * @日期:2017年8月4日下午6:34:05
 * @说明：<pre></pre>
 */
public class CodeMakerCfg {
	/** 作者 */
	private String autherName;
	/** 创建时间 */
	private Date createTime;

	/** 公司类型(输入) */
	private String pkgNameType = "com";
	/** 公司包名(输入) */
	private String pkgNameCompany;
	/** 项目包名(输入) */
	private String pkgNameProject;
	/** 子项目包名(输入) */
	private String pkgNameSubProj;
	/** 模块包名 (输入) */
	private String pkgNameModel;

	/** 自定义参数 */
	private Map<String, Object> customParam;

	/** 数据库 */
	private String db;
	/** 表名 */
	private String tableName;
	/** 不生java属性的列信息 */
	private String notGenerateFields = "";

	/** 生成代码的模板路径 */
	private String templateBasePath = "/codeTemplate";
	/** 不生的模板 */
	private String[] notGenerateTemplates = {};
	/** 文件编码 */
	private String fileEncoding = "UTF-8";
	/**模板本机路径*/
	private String templateNativePath;
	/**dao层实体包路径*/
	private String baseRepositoryPkg;

	/**目标路径*/
	private String targetPath;

	/** 基础JDBC基础包 **/
	private String frameworkBasePkg;

	/**
	 * 解析模块包名：类型.公司.项目.子项目.模块
	 * 
	 * @param pkage
	 */
	public void setModlePackage(String pkage) throws Exception {
		String[] pkages = pkage.split("\\.");
		if (pkages.length != 5) {
			throw new Exception("解析模块包名失败：类型.公司.项目.子项目.模块");
		}
		this.pkgNameType = pkages[0];
		this.pkgNameCompany = pkages[1];
		this.pkgNameProject = pkages[2];
		this.pkgNameSubProj = pkages[3];
		this.pkgNameModel = pkages[4];
	}

	/** @取得 作者 */
	public String getAutherName() {
		return autherName;
	}

	/** @设置 作者 */
	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}

	/** @取得 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** @设置 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** @取得 公司类型(输入) */
	public String getPkgNameType() {
		return pkgNameType;
	}

	/** @设置 公司类型(输入) */
	public void setPkgNameType(String pkgNameType) {
		this.pkgNameType = pkgNameType;
	}

	/** @取得 公司包名(输入) */
	public String getPkgNameCompany() {
		return pkgNameCompany;
	}

	/** @设置 公司包名(输入) */
	public void setPkgNameCompany(String pkgNameCompany) {
		this.pkgNameCompany = pkgNameCompany;
	}

	/** @取得 项目包名(输入) */
	public String getPkgNameProject() {
		return pkgNameProject;
	}

	/** @设置 项目包名(输入) */
	public void setPkgNameProject(String pkgNameProject) {
		this.pkgNameProject = pkgNameProject;
	}

	/** @取得 子项目包名(输入) */
	public String getPkgNameSubProj() {
		return pkgNameSubProj;
	}

	/** @设置 子项目包名(输入) */
	public void setPkgNameSubProj(String pkgNameSubProj) {
		this.pkgNameSubProj = pkgNameSubProj;
	}

	/** @取得 模块包名(输入) */
	public String getPkgNameModel() {
		return pkgNameModel;
	}

	/** @设置 模块包名(输入) */
	public void setPkgNameModel(String pkgNameModel) {
		this.pkgNameModel = pkgNameModel;
	}

	/** @取得 自定义参数 */
	public Map<String, Object> getCustomParam() {
		return customParam;
	}

	/** @设置 自定义参数 */
	public void setCustomParam(Map<String, Object> customParam) {
		this.customParam = customParam;
	}

	/** @取得 数据库 */
	public String getDb() {
		return db;
	}

	/** @设置 数据库 */
	public void setDb(String db) {
		this.db = db;
	}

	/** @取得 表名 */
	public String getTableName() {
		return tableName;
	}

	/** @设置 表名 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** @取得 不生java属性的列信息 */
	public String getNotGenerateFields() {
		return notGenerateFields;
	}

	/** @设置 不生java属性的列信息 */
	public void setNotGenerateFields(String notGenerateFields) {
		this.notGenerateFields = notGenerateFields;
	}

	/** @取得 生成代码的模板路径 */
	public String getTemplateBasePath() {
		return templateBasePath;
	}

	/** @设置 生成代码的模板路径 */
	public void setTemplateBasePath(String templateBasePath) {
		this.templateBasePath = templateBasePath;
	}

	/** @取得 不生的模板 */
	public String[] getNotGenerateTemplates() {
		return notGenerateTemplates;
	}

	/** @设置 不生的模板 */
	public void setNotGenerateTemplates(String[] notGenerateTemplates) {
		this.notGenerateTemplates = notGenerateTemplates;
	}

	/** @取得 文件编码 */
	public String getFileEncoding() {
		return fileEncoding;
	}

	/** @设置 文件编码 */
	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public String getTemplateNativePath() {
		return templateNativePath;
	}

	public void setTemplateNativePath(String templateNativePath) {
		this.templateNativePath = templateNativePath;
	}

    public String getBaseRepositoryPkg() {
        return baseRepositoryPkg;
    }

    public void setBaseRepositoryPkg(String baseRepositoryPkg) {
        this.baseRepositoryPkg = baseRepositoryPkg;
    }

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

    public String getFrameworkBasePkg() {
        return frameworkBasePkg;
    }

    public void setFrameworkBasePkg(String frameworkBasePkg) {
        this.frameworkBasePkg = frameworkBasePkg;
    }
}
