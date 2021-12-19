package com.example.orderspringboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("menu")
@Data
public class Menu {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String name;
    private Integer price;
    private String photo;
}
