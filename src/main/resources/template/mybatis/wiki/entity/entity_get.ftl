[Home](/Home) / [${tableComment}](/${tableName}/${tableName})
### 根据主键查询${tableComment}

---

#### 1.基本信息
* 请求类型：***HTTP***
* 请求方式：***GET***
* 请求地址：***${requestUrl}/{id}***
* 数据类型：***application/json***
* 响应类型：***JSON***

#### 2.请求参数
|序号|参数名称|参数类型|长度|可否为空|描述|默认值|
|:--:|:------:|:------:|:------:|:--:|----|:----:|
| 1 | id | String | 50 | NO | 主键编号 | |

#### 3.响应参数
|序号|参数名称|描述|
|:--:|:------:|:------|
|1   |status  |登录状态，0未登录，1正常，2未授权    |
|2   |result  |操作结果，0失败，1成功      |
|3   |data    |返回数据JSON格式     |
|4   |code    |消息编码      |
|5   |message |消息内容      |

#### 4.请求示例
```
${requestUrl}/651cdb91-8bd7-4652-8055-7ead00f08818
```
#### 5.返回示例
* 正确返回示例

```json
{
    "status": "1",
    "result": "1",
    "data": {
    <#list columnList as column>
        "${column.feildName}":""<#if column_has_next>,</#if>
    </#list>
    },
    "code": "000000",
    "message": "操作成功"
}
```
* 错误返回示例

```json
{
    "status":"1",
    "result":"0",
    "data":"",
    "code":"000001",
    "message":"操作失败"
}
```
