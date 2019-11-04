<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign count=8><#-- 一行多少个字段 -->
<#assign selectListStr="">
<#assign valuesListStr="">
<#list columnList as col> 
<#assign selectListStr=(selectListStr + col.columnName + (columnList?size != col_index+1)?string(",", "") )>
<#assign selectListStr=(selectListStr + ((col_index+1) % count = 0)?string("\r\n				", ""))>
<#assign valuesListStr=(valuesListStr + "#{" +col.fieldName+"}"+ (columnList?size != col_index+1)?string(",", "") )>
<#assign valuesListStr=(valuesListStr + ((col_index+1) % count = 0)?string("\r\n				", ""))>
</#list> 
	
<!--【${tableName} ${tableComment}】sql -->
<mapper namespace="${fullPkgSubProj}.dao.${capClassName}Dao">

    <resultMap id="${capClassName}Map" type="${fullPkgSubProj}.pojo.${capClassName}Pojo">
	<#list columnList as row>
		<#if row.columnName == "id" >
            <id property="${row.fieldName}" column="${row.columnName}" />
		<#else>
            <result property="${row.fieldName}" column="${row.columnName}" />
		</#if>
	</#list>
    </resultMap>

    <!-- 基础查询条件 -->
    <sql id="base_column">
		${selectListStr}
    </sql>

    <sql id="base_column_params">
		${valuesListStr}
    </sql>

    <!-- 基础查询条件 -->
    <sql id="base_where">
        <where>
		<#list columnList as row>
            <if test="${row.fieldName} != null" >
                AND ${row.columnName} = ${r"#"}{${row.fieldName}}
            </if>
		</#list>
        </where>
    </sql>


	<!-- ==============================基础操作======================================= -->
	<!--根据条件得到列表 -->
	<select id="selectList" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo" resultMap="${capClassName}Map">
			SELECT <include refid="base_column"/>
			FROM ${tableName}
        	<include refid="base_where"/>
	</select>

	<!--根据id得到记录 -->
	<select id="selectUnique" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo" resultMap="${capClassName}Map">
			SELECT <include refid="base_column"/>
			FROM ${tableName}
			<include refid="base_where"/>
	</select>

	<!-- 新增记录 -->
	<insert id="insert" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo" 
		useGeneratedKeys="true"	keyProperty="id" keyColumn="id">
			INSERT INTO ${tableName} (
        		<include refid="base_column"/>
			) VALUES (
				<include refid="base_column_params"/>
			)
	</insert>
	
	<!-- 批量新增记录 -->
	<insert id="insertList" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo">
			INSERT INTO ${tableName} (
        		<include refid="base_column"/>
			) VALUES (
       			 <include refid="base_column_params"/>
			)
	</insert>
	
	<!-- 修改记录 -->
	<update id="update" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo">
		<![CDATA[ UPDATE ${tableName}]]>
		<set>
		<#list columnList as col> 
	 		<if test="${col.fieldName} != null<#if col.fieldType = "String"> and ${col.fieldName} != ''</#if>"><![CDATA[ ${col.columnName} = ${"#{"}${col.fieldName}${"}"},]]></if>
		</#list> 
		</set>
		 <![CDATA[ WHERE id = ${r"#{id}"} ]]>
	</update>
	
	<!-- 删除记录 -->
	<update id="delete" parameterType="${fullPkgSubProj}.pojo.${capClassName}Pojo">
		<![CDATA[ DELETE FROM ${tableName} where id = ${r"#{id}"} ]]>
	</update>
</mapper> 
