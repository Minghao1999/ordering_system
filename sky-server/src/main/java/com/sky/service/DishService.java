package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * ADD NEW DISH AND FLAVOR DATA
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * DISH PAGE QUERY
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * DISH BATCH DELETE
     * @param ids
     * @return
     */
    void deleteBatch(List<Long> ids);
}
