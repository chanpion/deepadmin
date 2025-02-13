package com.chenpp.deepadmin.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenpp.deepadmin.auth.service.CommonService;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author April.Chen
 * @date 2024/9/30 17:21
 */
public abstract class AbstractCommonService<M extends BaseMapper<DO>, DTO extends Serializable, DO> extends ServiceImpl<M, DO> implements CommonService<DTO, DO> {

    protected Class<?> dtoClass;
    protected Class<?> doClass;

    protected AbstractCommonService() {
        Type superClass = this.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
        dtoClass = (Class<?>) types[1];
        doClass = (Class<?>) types[2];
    }

    @Override
    public boolean add(DTO dto) {
        return super.save(convertToDO(dto));
    }

    public DTO selectOne(Map<String, Object> params) {
        QueryWrapper<DO> query = new QueryWrapper<DO>().allEq(params).select("*");
        DO d = baseMapper.selectOne(query);
        return convertToDTO(d);
    }

    @Override
    public List<DTO> list(Map<String, Object> params) {
        QueryWrapper<DO> query = new QueryWrapper<DO>().allEq(params).select("*");
        List<DO> list = baseMapper.selectList(query);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DTO> list(DTO dto) {
        DO d = convertToDO(dto);
        QueryWrapper<DO> query = new QueryWrapper<DO>().select("*");
        List<DO> list = baseMapper.selectList(query);
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DTO convertToDTO(DO d) {
        try {
            if (d == null) {
                return null;
            }
            DTO t = (DTO) dtoClass.newInstance();
            BeanUtils.copyProperties(d, t);
            return t;
        } catch (Exception e) {
            log.error("DO convert to DTO error", e);
            return null;
        }
    }

    @Override
    public DO convertToDO(DTO t) {
        try {
            if (t == null) {
                return null;
            }
            DO d = (DO) doClass.newInstance();
            BeanUtils.copyProperties(t, d);
            return d;
        } catch (Exception e) {
            log.error("DTO convert to DO error", e);
            return null;
        }
    }

}
