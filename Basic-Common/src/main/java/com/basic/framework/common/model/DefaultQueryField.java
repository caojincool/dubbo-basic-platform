package com.basic.framework.common.model;


import com.basic.framework.common.api.query.QueryField;
import com.basic.framework.common.api.query.QueryOP;
import com.basic.framework.common.mybatis.util.SQLConst;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.StringPool;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 默认条件接口实现类。
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-11-28-上午11:15:12
 * 版权：company
 * </pre>
 */
public class DefaultQueryField implements QueryField
{

	// 字段
	private String field;
	// 比较符
	private QueryOP compare;
	// 比较值
	private Object value;
	

	// 字段前缀名，一般用于表的别名，如user.
	// private String preFieldName="";

	public DefaultQueryField()
	{
	}

	public DefaultQueryField(String field, Object value)
	{
		this.field = field;
		this.value = value;
	}

	public DefaultQueryField(String field, QueryOP compare, Object value)
	{
		this.field = field;
		this.compare = compare;
		this.value = value;

		if (QueryOP.IN.equals(compare) || QueryOP.NOTIN.equals(compare))
		{
			this.value = getInValueSql();
		} else
		{
			this.value = value;
		}
		//针对直接在代码中通过queryFilter.addFilter方式添加相似查找时，未添加查询规则的处理
		if(BeanUtils.isNotEmpty(value)){
			if((QueryOP.LIKE.equals(compare) || QueryOP.NOT_LIKE.equals(compare))&&!value.toString().startsWith("%")&&!value.toString().endsWith("%")){
				this.value = "%"+value+"%";
			}else if(QueryOP.LEFT_LIKE.equals(compare)&&!value.toString().startsWith("%")){
				this.value = "%"+value;
			}else if(QueryOP.RIGHT_LIKE.equals(compare)&&!value.toString().endsWith("%")){
				this.value = value+"%";
			}
		}

	}

	/**
	 * 针对in查询方式，根据传入的value的类型做不同的处理。 value 是 string，则分隔字符串，然后合并为符合in规范的字符串。
	 * value 是 list，则直接合并为符合in规范的字符串。
	 * 
	 * @return
	 */
	private String getInValueSql()
	{
		if (value instanceof String)
		{ 
			if(BeanUtils.isEmpty(value)) {
                return "('')";//
            }
			// 字符串形式，通过逗号分隔
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			StringTokenizer st = new StringTokenizer(value.toString(), ",");
			while (st.hasMoreTokens())
			{
				sb.append("\"");
				sb.append(st.nextToken());
				sb.append("\"");
				sb.append(",");
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
			sb.append(")");
			return sb.toString();
		} else if (value instanceof List)
		{ // 列表形式
			List<Object> objList = (List<Object>) value;
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (Object obj : objList)
			{
				sb.append("\"");
				sb.append(obj.toString());
				sb.append("\"");
				sb.append(",");
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 1));
			sb.append(")");
			return sb.toString();
		}
		return "";
	}

	@Override
    public String getField()
	{
		return field;
	}

	@Override
    public void setField(String field)
	{
		this.field = field;
	}

	@Override
    public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	@Override
    public QueryOP getCompare()
	{
		return compare;
	}

	public void setCompare(QueryOP compare)
	{
		this.compare = compare;
	}

	@Override
    public String getSql()
	{
		if (compare == null)
		{
			compare = QueryOP.EQUAL;
		}
		String fieldParam;
		if (field.indexOf(".") > -1)
		{
			fieldParam = "#{" + field.substring(field.indexOf(".") + 1) + "}";
		} else
		{
			fieldParam = "#{" + field + "}";
		}
		if (field.indexOf("#") > -1) {
			field = field.substring(0, field.indexOf("#"));
		}
		String sql = field;
		if (sql.lastIndexOf("^") != -1)
		{
			sql = sql.substring(0, sql.lastIndexOf("^"));
		}
		if (QueryOP.EQUAL.equals(compare))
		{
			sql += " = " + fieldParam;
		} else if (QueryOP.LESS.equals(compare))
		{
			sql += " < " + fieldParam;
		} else if (QueryOP.LESS_EQUAL.equals(compare))
		{
			sql += " <= " + fieldParam;
		} else if (QueryOP.GREAT.equals(compare))
		{
			sql += " > " + fieldParam;
		} else if (QueryOP.GREAT_EQUAL.equals(compare))
		{
			sql += " >= " + fieldParam;
		} else if (QueryOP.NOT_EQUAL.equals(compare))
		{
			sql += " != " + fieldParam;
		} else if (QueryOP.LEFT_LIKE.equals(compare))
		{
			sql += " like " + fieldParam;
		} else if (QueryOP.RIGHT_LIKE.equals(compare))
		{
			sql += " like  " + fieldParam;
		} else if (QueryOP.LIKE.equals(compare))
		{
			sql += " like  " + fieldParam;
		} else if (QueryOP.NOT_LIKE.equals(compare))
		{
			sql += " not like  " + fieldParam;
		} else if (QueryOP.IS_NULL.equals(compare))
		{
			sql += " is null ";
		} else if (QueryOP.NOTNULL.equals(compare))
		{
			sql += " is not null";
		} else if (QueryOP.IN.equals(compare))
		{
			sql += " in  " + this.value;
		}  else if (QueryOP.NOTIN.equals(compare))
		{
			sql += " not in  " + this.value;
		} else if (QueryOP.BETWEEN.equals(compare))
		{
			sql += getBetweenSql();
		} else
		{
			sql += " =  " + fieldParam;
		}
		return sql;
	}

	private String getBetweenSql()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(" between ");
		String dbType =SQLConst.DB_MYSQL;
		if (this.value instanceof List)
		{
			List<Object> objList = (List<Object>) value;
			for (int i = 0; i < objList.size(); i++)
			{
				Object obj = objList.get(i);
				if (i == 1)
				{
					sb.append(" and ");
				}
				if (obj instanceof Date)
				{
					String dateString = DateFormatUtils.format((Date) obj, StringPool.DATE_FORMAT_DATETIME);
					if(SQLConst.DB_ORACLE.equals(dbType)){
						sb.append("TO_DATE(substr('" + dateString + "',1,19),'yyyy-mm-dd hh24:mi:ss')");
					}else{
						sb.append("\"" + dateString + "\"");
					}
				} else
				{
					if(SQLConst.DB_ORACLE.equals(dbType)){
						sb.append("TO_DATE(substr('" + obj.toString() + "',1,19),'yyyy-mm-dd hh24:mi:ss')");
					}else{
						sb.append("\"" + obj.toString() + "\"");
					}
				}
			}
		}
		sb.append(" ");
		return sb.toString();
	}
}