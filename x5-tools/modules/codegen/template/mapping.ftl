<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign type="com."+system+"."+package+".model." +class>
<#assign mangerType="com."+system+"."+package+".dao." +class+"Mapper">
<#assign tableName=model.tableName>
<#assign system=vars.system>
<#assign foreignKey=model.foreignKey>
<#assign sub=model.sub>
<#assign colList=model.columnList>
<#assign commonList=model.commonList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.getPkVar(model) >
<#-- 模板开始  -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mangerType}">
    <resultMap id="${class}" type="${type}">
		<#list colList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		<#if (col.isPK) >
		<id property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
        <#else>
		<result property="${colName}" column="${col.columnName}" jdbcType="${func.getJdbcType(col.colDbType)}"/>
        </#if>
        </#list>
    </resultMap>

    <insert id="create" parameterType="${type}">
        INSERT INTO ${tableName}
        (<#list colList as col>${col.columnName}<#if col_has_next>,</#if></#list>)
        VALUES
        (<#list colList as col><#assign colName=func.convertUnderLine(col.columnName)><#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>, </#if></#list>)
    </insert>



    <select id="getList"  resultType="${mangerType}">
        SELECT * FROM ${tableName}
        <where>
            1=1
            <if test="whereSQL != null and whereSQL != ''" >

                AND (<#noparse>#{whereSQL}</#noparse>)
            </if>
        </where>
        <if test="null != page">
            limit <#noparse>#{page.begin}</#noparse> , <#noparse>#{page.length}</#noparse>
        </if>
    </select>


   <#-- <select id="getList" resultType="org.lsmy.cloud.business.po.PersonWwPO">
        select
        <include refid="query_column_info"></include>
        from cloud_person_ww ww
        <where>
            1=1
            <if test="whereSQL != null and whereSQL != ''" >
                AND (<#noparse>#{</#noparse>whereSQL})
            </if>
        </where>
        <if test="null != page">
            limit #{page.begin} , #{page.length}
        </if>
    </select>-->





    <update id="update" parameterType="${type}">
        UPDATE ${tableName} SET
		<#list commonList as col>
            <#assign colName=func.convertUnderLine(col.columnName)>
            ${col.columnName}=<#noparse>#{</#noparse>${colName},jdbcType=${func.getJdbcType(col.colDbType)}<#noparse>}</#noparse><#if col_has_next>,</#if>
        </#list>
        WHERE state = '10A' AND
    ${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
    </update>
    


    <delete id="remove" parameterType="java.lang.Long">
        UPDATE ${tableName} SET  state = '10X'
        WHERE
    ${pk}=<#noparse>#{</#noparse>${func.convertUnderLine(pk)}}
    </delete>

	<#if sub?exists && sub==true>
        <#assign foreignKeyVar=func.convertUnderLine(foreignKey)>
	<delete id="delByMainId">
        UPDATE ${tableName} SET   state = '10X'
        WHERE
        ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
    </delete>

	<select id="get${class}List" resultMap="${class}">
        SELECT *
        FROM ${tableName}
        WHERE  state = '10A'  AND ${foreignKey}=<#noparse>#{</#noparse>${foreignKeyVar}}
    </select>
    </#if>

</mapper>