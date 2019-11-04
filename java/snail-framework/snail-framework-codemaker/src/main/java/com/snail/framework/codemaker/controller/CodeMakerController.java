package com.snail.framework.codemaker.controller;

import com.snail.framework.codemaker.dao.CodeMakerJDBCDao;
import com.snail.framework.codemaker.pojo.CodeMakerCfg;
import com.snail.framework.codemaker.service.CodeMakerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Api(value = "代码自动生成器" , tags = {"代码自动生成器"})
@RestController
public class CodeMakerController {


    @Autowired
    private CodeMakerService codeMakerService;

    /**
     * 自动生成代码
     */
    @PostMapping(value = "/generateCode")
    @ApiOperation(value = "自动生成代码" , notes = "自动生成代码")
    public String codeMaker(
            @RequestParam(value = "driverClassName")  @ApiParam(value = "数据库驱动" , name = "driverClassName" , required = true) String driverClassName,
            @RequestParam(value = "url")  @ApiParam(value = "数据库URL" , name = "url" , required = true) String url,
            @RequestParam(value = "userName")  @ApiParam(value = "数据库用户名" , name = "userName" , required = true) String userName,
            @RequestParam(value = "password")  @ApiParam(value = "数据库密码" , name = "password" , required = true) String password,
            @RequestParam(value = "dbName")  @ApiParam(value = "数据库名称" , name = "dbName" , required = true) String dbName,
            @RequestParam(value = "authorName")  @ApiParam(value = "创建人名称" , name = "authorName" , required = true) String authorName,
            @RequestParam(value = "subProjectPackage")  @ApiParam(value = "目标包名" , name = "subProjectPackage" , required = true) String subProjectPackage,
            @RequestParam(value = "tableNames")  @ApiParam(value = "需要自动生成的表列表" , name = "tableNames" , required = true) List<String> tableNames,
            @RequestParam(value = "baseRepositoryPkg")  @ApiParam(value = "DAO层实体包路径" , name = "baseRepositoryPkg" , required = true) String baseRepositoryPkg,
            @RequestParam(value = "targetPath")  @ApiParam(value = "目标绝对路径" , name = "targetPath" , required = true) String targetPath

    ) throws Exception {

        String notGenerateFields = "id,createrId,createrName,createTime,modId,modName,modTime,";
        notGenerateFields += "telEnc,bankCardCodeEnc,bankCardTelEnc,idCardCodeEnc,idCardNameEnc";

        String[] notGenerateTemplates = {"Service.jave", "Test.java"};
        Pattern pattern = Pattern.compile("^([a-z]*)");

        CodeMakerJDBCDao codeMakerJDBCDao = new CodeMakerJDBCDao(driverClassName , url , userName , password);

        for (String tableName : tableNames) {
            Matcher m = pattern.matcher(tableName);
            if (m.find()) {
                String modlePackage = subProjectPackage + "." + m.group();
                if (!StringUtils.isEmpty(tableName)) {
                    CodeMakerCfg cfg = new CodeMakerCfg();
                    cfg.setDb(dbName);
                    cfg.setTableName(tableName);
                    cfg.setAutherName(authorName);
                    cfg.setModlePackage(modlePackage);
                    cfg.setNotGenerateFields(notGenerateFields);
                    cfg.setNotGenerateTemplates(notGenerateTemplates);
                    cfg.setBaseRepositoryPkg(baseRepositoryPkg);
                    cfg.setTargetPath(targetPath);
                    cfg.setFrameworkBasePkg("com.snail.framework.jdbc");
                    codeMakerService.generateCode(cfg , codeMakerJDBCDao);
                }
            }
        }

        return "generateCode OK";
    }
}
