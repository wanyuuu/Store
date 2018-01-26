package com.wanyu.mapper;

import com.wanyu.model.Item;
import com.wanyu.model.ItemExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ItemMapper {
    @SelectProvider(type=ItemSqlProvider.class, method="countByExample")
    long countByExample(ItemExample example);

    @DeleteProvider(type=ItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(ItemExample example);

    @Delete({
        "delete from item",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String itemid);

    @Insert({
        "insert into item (itemid, productid, ",
        "listprice, unitcost, ",
        "status, attr1, attr2, ",
        "attr3, attr4, attr5)",
        "values (#{itemid,jdbcType=VARCHAR}, #{productid,jdbcType=VARCHAR}, ",
        "#{listprice,jdbcType=DECIMAL}, #{unitcost,jdbcType=DECIMAL}, ",
        "#{status,jdbcType=VARCHAR}, #{attr1,jdbcType=VARCHAR}, #{attr2,jdbcType=VARCHAR}, ",
        "#{attr3,jdbcType=VARCHAR}, #{attr4,jdbcType=VARCHAR}, #{attr5,jdbcType=VARCHAR})"
    })
    int insert(Item record);

    @InsertProvider(type=ItemSqlProvider.class, method="insertSelective")
    int insertSelective(Item record);

    @SelectProvider(type=ItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="listprice", property="listprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="unitcost", property="unitcost", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr1", property="attr1", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr2", property="attr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr3", property="attr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr4", property="attr4", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr5", property="attr5", jdbcType=JdbcType.VARCHAR),
            @Result(column = "productid",property = "product",
            one = @One(select = "com.wanyu.mapper.ProductMapper.selectByPrimaryKey"))
    })
    List<Item> selectByExample(ItemExample example);

    @Select({
        "select",
        "itemid, productid, listprice, unitcost, status, attr1, attr2, attr3, attr4, ",
        "attr5",
        "from item",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="listprice", property="listprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="unitcost", property="unitcost", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr1", property="attr1", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr2", property="attr2", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr3", property="attr3", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr4", property="attr4", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr5", property="attr5", jdbcType=JdbcType.VARCHAR),
        @Result(column="productid",property = "product",
        one=@One(select = "com.wanyu.mapper.ProductMapper.selectByPrimaryKey"))
    })
    Item selectByPrimaryKey(String itemid);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Item record);

    @Update({
        "update item",
        "set productid = #{productid,jdbcType=VARCHAR},",
          "listprice = #{listprice,jdbcType=DECIMAL},",
          "unitcost = #{unitcost,jdbcType=DECIMAL},",
          "status = #{status,jdbcType=VARCHAR},",
          "attr1 = #{attr1,jdbcType=VARCHAR},",
          "attr2 = #{attr2,jdbcType=VARCHAR},",
          "attr3 = #{attr3,jdbcType=VARCHAR},",
          "attr4 = #{attr4,jdbcType=VARCHAR},",
          "attr5 = #{attr5,jdbcType=VARCHAR}",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Item record);
}