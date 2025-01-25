package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * QUERY SET MEAL ID BY DISH ID
     * @param dishIds
     * @return
     */
    List<Long> getSetmealDishIdsByDishIds(List<Long> dishIds);
}
