package com.example.orderspringboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("resorder")
@Data
public class Order {

    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String tableid;
    private String menuname;
    private Integer menuprice;
}
