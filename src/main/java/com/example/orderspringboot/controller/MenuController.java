package com.example.orderspringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.orderspringboot.config.Result;
import com.example.orderspringboot.entity.Menu;
import com.example.orderspringboot.entity.Order;
import com.example.orderspringboot.mapper.MenuMapper;
import com.example.orderspringboot.mapper.OrderMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuMapper menuMapper;
    @Resource
    OrderMapper orderMapper;



    @GetMapping
    public Result<?> getMenu(){
        List<Menu> menulist=menuMapper.selectList(null);
        return Result.success(menulist);
    }

    @GetMapping("/detail")
    public Result<?> getdetail(@RequestParam Integer id){
        String src=menuMapper.selectOne(new LambdaQueryWrapper<Menu>().eq(Menu::getId,id)).getPhoto();
        return Result.success(src);
    }

    @PostMapping("/order")
    public Result<?>order(@RequestBody Map<String,String> map){
       Order order=new Order();
       order.setTableid(map.get("tableid"));
       order.setMenuprice(Integer.parseInt(map.get("menuprice")));
       order.setMenuname(map.get("menuname"));
       orderMapper.insert(order);
       return Result.success();
    }
    @GetMapping("/myorder")
    public Result<?>getmyorder(@RequestParam String tableid){
        List<Order> orderlist=orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getTableid,tableid));
        return Result.success(orderlist);
    }

    @GetMapping("/delete")
    public Result<?>delorder(@RequestParam String tableid,@RequestParam String menuname){
        orderMapper.delete(new LambdaQueryWrapper<Order>().eq(Order::getTableid,tableid).eq(Order::getMenuname,menuname));
        return Result.success();
    }

    @GetMapping("/count")
    public Result<?>count(@RequestParam String tableid){
        Integer price=orderMapper.countprice(tableid);
        return Result.success(price);
    }

}
