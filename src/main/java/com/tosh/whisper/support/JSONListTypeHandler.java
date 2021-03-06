/** Title: [描述产品的名称和版本]<br>
 * Description: [描述模块的功能、作用、使用方法和注意事项]<br>
 * Copyright: Copyright(c) 1991-2016<br>
 * Company: TaoShuang Tech.Co.,Ltd<br>
 * @author taoshuang
 * @version 1.0
 * @since 2017年1月22日
 */
package com.tosh.whisper.support;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.tosh.whisper.utils.JsonUtil;

/**
 * ClassName: ListTypeHandler<br>
 * Description: [描述类的功能、作用、使用方法和注意事项]<br>
 * 
 * @author taoshuang
 * @since 2017年1月22日
 */
public class JSONListTypeHandler<T extends Object> extends
        BaseTypeHandler<List<T>>
{
    private Class<T> claszz;
    
    /**
     * 构造函数<br>
     *
     * @author taoshuang
     * @since 2017年1月22日
     */
    public JSONListTypeHandler(Class<T> claszz)
    {
        this.claszz = claszz;
    }
    
    /**
     * 覆盖方法/实现方法<br>
     * 
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement,
     *      int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     * @author taoshuang
     * @since 2017年1月22日
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int index,
            List<T> content, JdbcType jdbcType) throws SQLException
    {
        ps.setString(index, toJson(content));
    }
    
    /**
     * 覆盖方法/实现方法<br>
     * 
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      java.lang.String)
     * @author taoshuang
     * @since 2017年1月22日
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName)
            throws SQLException
    {
        return getList(rs.getString(columnName));
    }
    
    /**
     * 覆盖方法/实现方法<br>
     * 
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      int)
     * @author taoshuang
     * @since 2017年1月22日
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException
    {
        return getList(rs.getString(columnIndex));
    }
    
    /**
     * 覆盖方法/实现方法<br>
     * 
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement,
     *      int)
     * @author taoshuang
     * @since 2017年1月22日
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException
    {
        return getList(cs.getString(columnIndex));
    }
    
    private List<T> getList(String content)
    {
        return JsonUtil.parseList(content, claszz);
    }
    
    private String toJson(List<T> content)
    {
        return JsonUtil.toJSONString(content);
    }
    
}
