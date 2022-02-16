package com.example.my.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 写自己的通用Mapper: MyMapper, 要继承Mapper
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
