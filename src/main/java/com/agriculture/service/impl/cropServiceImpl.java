package com.agriculture.service.impl;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.context.BaseContext;
import com.agriculture.common.exception.CategoryExistErrorException;
import com.agriculture.common.result.PageResult;
import com.agriculture.mapper.CropMapper;
import com.agriculture.mapper.ProvinceMapper;
import com.agriculture.pojo.DTO.*;
import com.agriculture.pojo.VO.CropOptionVO;
import com.agriculture.pojo.VO.CropVO;
import com.agriculture.pojo.VO.RecommendCropVO;
import com.agriculture.pojo.entity.Crop;
import com.agriculture.pojo.entity.Province;
import com.agriculture.service.CategoryService;
import com.agriculture.service.CropService;
import com.agriculture.service.FieldService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class cropServiceImpl implements CropService {
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public PageResult pageQuery(CropPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<CropVO> page = cropMapper.pageQuery(dto);
        page.forEach(cropVO -> {
            List<Province> list = provinceMapper.getProvinceByCrop(cropVO.getId());
            cropVO.setSuitableProvince(list);
        });
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional
    public void add(CropDTO cropDTO) {
        Crop crop = new Crop();
        BeanUtils.copyProperties(cropDTO, crop);
        cropMapper.add(crop);
        Long cropId = crop.getId();
        List<Long> provinceIds = cropDTO.getProvinceIds();
        if (provinceIds != null && !provinceIds.isEmpty())
            for (Long pi : provinceIds)
                provinceMapper.addCropWithProvince(cropId, pi);
    }

    @Override
    public CropVO getById(Long id) {
        CropVO cropVO = cropMapper.getById(id);
        List<Province> provinceByCrop = provinceMapper.getProvinceByCrop(id);
        cropVO.setSuitableProvince(provinceByCrop);
        return cropVO;
    }

    @Override
    @Transactional
    public void update(CropDTO cropDTO) {
        Crop crop = new Crop();
        BeanUtils.copyProperties(cropDTO, crop);
        cropMapper.update(crop);
        List<Long> provinceIds = cropDTO.getProvinceIds();
        Long cropId = cropDTO.getId();
        provinceMapper.deleteByCropId(cropId);
        if (provinceIds != null && !provinceIds.isEmpty()) {
            for (Long provinceId : provinceIds) {
                provinceMapper.addCropWithProvince(cropId, provinceId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            // 删除农作物
            cropMapper.deleteById(id);
            // 删除农作物省份关系表
            provinceMapper.deleteByCropId(id);
            // 更新田地为未耕种模式
            fieldService.updateToUncultivatedByCropId(id);
        }
    }

    @Override
    public List<RecommendCropVO> recommendCropByUserId(RecommendQueryDTO dto) {
        UserRecommendQueryDTO userRecommendQueryDTO = new UserRecommendQueryDTO();
        BeanUtils.copyProperties(dto, userRecommendQueryDTO);
        userRecommendQueryDTO.setUserId(BaseContext.getCurrentId());
        List<RecommendCropVO> list = cropMapper.recommendCropByUserId(userRecommendQueryDTO);
        return list;
    }

    @Override
    public List<CropVO> getByCategoryId(Long id) {
        CropCategoryDTO category = categoryService.getById(id);
        if (category == null)
            throw new CategoryExistErrorException(MessageConstant.NO_CATEGORY);
        List<CropVO> list = cropMapper.getByCategoryId(id);
        return list;
    }

    @Override
    public List<CropOptionVO> listAll() {
        return cropMapper.listAll();
    }
}
