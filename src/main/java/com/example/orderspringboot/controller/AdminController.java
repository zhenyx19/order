package com.example.orderspringboot.controller;


import com.alibaba.druid.pool.vendor.OracleExceptionSorter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.orderspringboot.config.Result;
import com.example.orderspringboot.dto.TableOrder;
import com.example.orderspringboot.entity.Admin;
import com.example.orderspringboot.entity.Menu;
import com.example.orderspringboot.entity.Order;
import com.example.orderspringboot.mapper.AdminMapper;
import com.example.orderspringboot.mapper.MenuMapper;
import com.example.orderspringboot.mapper.OrderMapper;
import javafx.scene.input.Mnemonic;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;

    @Resource
    MenuMapper menuMapper;

    @Resource
    OrderMapper orderMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin){
        Admin res=adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getName,admin.getName()).
                eq(Admin::getPassword,admin.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody Map<String, String>map){
        UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",Integer.parseInt(map.get("id")));
        Menu menu=new Menu();
        if(map.get("name")==null)
        {
            menu.setPrice(Integer.parseInt(map.get("price")));
            menuMapper.update(menu,updateWrapper);
        }
        else
        {
            menu.setName(map.get("name"));
            menu.setPrice(Integer.parseInt(map.get("price")));
            menuMapper.update(menu,updateWrapper);
        }
        return Result.success();
    }

    @GetMapping("/getorder")
    public Result<?> getorder(){
        List<TableOrder> tableOrderlist=orderMapper.getorder();
        return Result.success(tableOrderlist);
    }

    @GetMapping("/delete")
    public Result<?> delete(@RequestParam String tableid){
        orderMapper.delete(new LambdaQueryWrapper<Order>().eq(Order::getTableid,tableid));
        return Result.success();
    }
}
