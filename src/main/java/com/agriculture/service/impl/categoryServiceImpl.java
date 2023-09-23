package com.agriculture.service.impl;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.exception.CategoryExistErrorException;
import com.agriculture.common.result.PageResult;
import com.agriculture.mapper.CropCategoryMapper;
import com.agriculture.mapper.CropMapper;
import com.agriculture.mapper.GrowthCycleMapper;
import com.agriculture.mapper.ProvinceMapper;
import com.agriculture.pojo.DTO.CropCategoryDTO;
import com.agriculture.pojo.DTO.CropCategoryPageQueryDTO;
import com.agriculture.pojo.VO.CropCategoryVO;
import com.agriculture.pojo.entity.Crop;
import com.agriculture.pojo.entity.CropCategory;
import com.agriculture.service.CategoryService;
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
public class categoryServiceImpl implements CategoryService {
    @Autowired
    private CropCategoryMapper cropCategoryMapper;
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private GrowthCycleMapper growthCycleMapper;
    @Autowired
    private FieldService fieldService;

    @Override
    public void add(CropCategoryDTO cropCategoryDTO) {
        CropCategory cropCategory = new CropCategory();
        BeanUtils.copyProperties(cropCategoryDTO, cropCategory);
        cropCategoryMapper.add(cropCategory);
    }

    @Override
    public void update(CropCategoryDTO cropCategoryDTO) {
        CropCategory cropCategory = cropCategoryMapper.getById(cropCategoryDTO.getId());
        if (cropCategory == null)
            throw new CategoryExistErrorException(MessageConstant.NO_CATEGORY);
        BeanUtils.copyProperties(cropCategoryDTO,cropCategory);
        cropCategoryMapper.update(cropCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CropCategory cropCategory = cropCategoryMapper.getById(id);
        if (cropCategory == null)
            throw new CategoryExistErrorException(MessageConstant.NO_CATEGORY);

        List<Crop> crops = cropMapper.listByCategoryId(id);
        if (crops!=null&&!crops.isEmpty()){
            for (Crop crop : crops) {
                Long cropId = crop.getId();
                // 删除农作物&&省份关系表
                provinceMapper.deleteByCropId(cropId);
                //更新田地为未耕种的状态
                fieldService.updateToUncultivatedByCropId(cropId);
            }
        }
        // 根据种类删除农作物
        cropMapper.deleteByCategoryId(id);
        // 根据种类删除周期表
        growthCycleMapper.deleteByCategoryId(id);
        // 删除种类
        cropCategoryMapper.deleteById(id);
    }

    @Override
    public PageResult pageQuery(CropCategoryPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<CropCategoryVO> page = cropCategoryMapper.pageQuery(dto);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CropCategoryDTO getById(Long id) {
        CropCategory cropCategory = cropCategoryMapper.getById(id);
        CropCategoryDTO cropCategoryDTO = new CropCategoryDTO();
        BeanUtils.copyProperties(cropCategory,cropCategoryDTO);
        return cropCategoryDTO;
    }
}
