package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);

    @Select("<script>"
            + "SELECT * FROM orders WHERE 1=1"
            + "<if test='status != null'> AND status = #{status} </if>"
            + "</script>")
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
}
