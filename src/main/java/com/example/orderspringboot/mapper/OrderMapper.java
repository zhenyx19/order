package com.example.orderspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.orderspringboot.dto.TableOrder;
import com.example.orderspringboot.entity.Menu;
import com.example.orderspringboot.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    @Select("select sum(menuprice) from resorder o where o.tableid=#{tableid}")
    Integer countprice(String tableid);

    @Select("select tableid,sum(menuprice) as price from resorder o group by tableid ")
    List<TableOrder> getorder();
}
