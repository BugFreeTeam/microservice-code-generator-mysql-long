<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packagePath}.mapper.${mapperName}">
    <resultMap id="ResultMap" type="${packagePath}.model.${entityName}">
<#list columnList as column>
    <#if column.feildName == "id">
        <id column="id" property="id"/>
    <#else>
        <result column="${column.columnName}" property="${column.feildName}" />  <!-- ${column.columnComment} ${column.columnType} -->
    </#if>
</#list>
    </resultMap>
    <!-- 自定义操作 -->


    <!-- 根据主键查询 -->
    <select id="selectById" resultMap="ResultMap" parameterType="java.lang.Long">
        select <#list columnList as column>${column.columnName}<#if column_has_next>, </#if></#list>
        from ${tableName} where id = ${r'#{id}'}
    </select>
    <!-- 根据主键查询数据列表 -->
    <select id="selectByIds" resultMap="ResultMap" parameterType="java.util.List">
        select <#list columnList as column>${column.columnName}<#if column_has_next>, </#if></#list>
        from ${tableName} where	id in
        <foreach collection="list" item="item" index="index" open="(" close=")" separator=",">
            ${r'#{item}'}
        </foreach>
        order by id
    </select>
    <!-- 根据条件查询 -->
    <select id="selectList" resultMap="ResultMap" parameterType="${packagePath}.model.${entityName}">
        select <#list columnList as column>${column.columnName}<#if column_has_next>, </#if></#list>
        from ${tableName}
        <where>
        <#list columnList as column>
        <#if column.columnType?lower_case == "date" || column.columnType?lower_case == "timestamp">
            <if test="${column.feildName} != null">and ${column.columnName} = ${r"#{"}${column.feildName}}</if>
            <if test="${column.feildName}Start != null"><![CDATA[and ${column.columnName} >= ${r"#{"}${column.feildName}Start}]]></if>
            <if test="${column.feildName}End != null"><![CDATA[and ${column.columnName} <= ${r"#{"}${column.feildName}End}]]></if>
        <#else>
            <if test="${column.feildName} != null">and ${column.columnName} = ${r"#{"}${column.feildName}}</if>
        </#if>
        </#list>
        </where>
        <choose>
            <when test="order != null">
                order by ${r'${order}'}
            </when>
            <otherwise>
                order by id
            </otherwise>
        </choose>
    </select>
    <!-- 根据条件判断数据是否存在 -->
    <select id="exists" resultMap="ResultMap" parameterType="${packagePath}.model.${entityName}">
        select <#list columnList as column>${column.columnName}<#if column_has_next>, </#if></#list>
        from ${tableName}
        <where>
        <#list columnList as column>
            <if test="${column.feildName} != null">and ${column.columnName} = ${r"#{"}${column.feildName}}</if>
        </#list>
        </where>
    </select>
    <!-- 新增数据 -->
    <insert id="save" parameterType="${packagePath}.model.${entityName}" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list columnList as column>
            <if test="${column.feildName} != null">${column.columnName}, </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list columnList as column>
            <if test="${column.feildName} != null">${r"#{"}${column.feildName}}, </if>
        </#list>
        </trim>
    </insert>
    <!-- 批量新增数据 -->
    <insert id="saveBatch" parameterType="java.util.List" useGeneratedKeys="true" keyProperty="id">
        <foreach collection="list" item="item" index="index" separator=";">
            insert into ${tableName}
            <trim prefix="(" suffix=")" suffixOverrides="," >
            <#list columnList as column>
                <if test="item.${column.feildName} != null">${column.columnName}, </if>
            </#list>
            </trim>
            <trim prefix="values (" suffix=")" suffixOverrides="," >
            <#list columnList as column>
                <if test="item.${column.feildName} != null">${r"#{item."}${column.feildName}}, </if>
            </#list>
            </trim>
        </foreach>
    </insert>
    <!-- 更新数据 -->
    <update id="update" parameterType="${packagePath}.model.${entityName}">
        update ${tableName}
        <set>
        <#list columnList as column>
            <if test="${column.feildName} != null">${column.columnName} = ${r"#{"}${column.feildName}}, </if>
        </#list>
        </set>
        where id = ${r'#{id}'}
    </update>
    <!-- 批量更新数据 -->
    <update id="updateBatch" parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" separator=";">
            update ${tableName}
            <set>
            <#list columnList as column>
                <if test="item.${column.feildName} != null">${column.columnName} = ${r"#{"}item.${column.feildName}}, </if>
            </#list>
            </set>
            where id = ${r'#{item.id}'}
        </foreach>
    </update>
    <!-- 逻辑删除数据 -->
    <update id="cancel" parameterType="${packagePath}.model.${entityName}">
        update ${tableName}
        <set>
            flag = 0,
        </set>
        where id = ${r'#{id}'}
    </update>
    <!-- 批量逻辑删除数据 -->
    <update id="cancelBatch" parameterType="java.util.List">
        <foreach collection="list" item="item" index="index" separator=";">
            update ${tableName}
            <set>
                flag = 0,
            </set>
            where id = ${r'#{item.id}'}
        </foreach>
    </update>
    <!-- 物理删除数据 -->
    <delete id="delete" parameterType="java.lang.Long">
        delete from ${tableName} where id = ${r'#{id}'}
    </delete>
    <!-- 批量物理删除数据 -->
    <delete id="deleteBatch" parameterType="java.util.List">
        delete from ${tableName} where id in
        <foreach collection="list" item="item" index="index" open="(" close=")" separator=",">
            ${r'#{item}'}
        </foreach>
    </delete>
</mapper>