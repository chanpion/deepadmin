package com.chenpp.deepadmin.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author April.Chen
 * @date 2024/9/30 17:20
 */
public interface CommonService<DTO extends Serializable, DO> extends IService<DO> {

    /**
     * 插入记录
     *
     * @param dto 记录
     * @return 插入条数
     */
    boolean add(DTO dto);

    /**
     * 查询单条记录
     *
     * @param params 参数
     * @return 记录
     */
    DTO selectOne(Map<String, Object> params);

    /**
     * 查询多条记录
     *
     * @param params 参数
     * @return 结果列表
     */
    List<DTO> list(Map<String, Object> params);

    /**
     * 查询多条记录
     *
     * @param dto 参数
     * @return 结果列表
     */
    List<DTO> list(DTO dto);

    /**
     * DO转为DTO
     *
     * @param d DO
     * @return DTO
     */
    DTO convertToDTO(DO d);

    /**
     * DTO转为DO
     *
     * @param t DTO
     * @return DO
     */
    DO convertToDO(DTO t);
}
