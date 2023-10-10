package com.agriculture.service.impl;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.context.BaseContext;
import com.agriculture.common.exception.BaseException;
import com.agriculture.common.exception.CropExistErrorException;
import com.agriculture.common.exception.FieldExistErrorException;
import com.agriculture.mapper.CropMapper;
import com.agriculture.mapper.FieldMapper;
import com.agriculture.mapper.ProvinceMapper;
import com.agriculture.pojo.DTO.AddFieldDTO;
import com.agriculture.pojo.DTO.FieldDTO;
import com.agriculture.pojo.VO.FieldVO;
import com.agriculture.pojo.entity.Field;
import com.agriculture.pojo.entity.GrowthCycle;
import com.agriculture.service.CropCycleService;
import com.agriculture.service.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class fieldServiceImpl implements FieldService {
    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private CropCycleService cropCycleService;
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public void addField(FieldDTO fieldDTO) {
        Field field = new Field();
        BeanUtils.copyProperties(fieldDTO, field);
        if (fieldDTO.getCropId() != null) {
            field.setStatus(1);
            field.setPlantingTime(LocalDateTime.now());
        } else {
            field.setStatus(2);
        }
        fieldMapper.addField(field);
    }


    @Override
    public void userAddField(AddFieldDTO addFieldDTO) {
        Field field = new Field();
        BeanUtils.copyProperties(addFieldDTO, field);
        field.setUserId(BaseContext.getCurrentId());
        if (addFieldDTO.getCropId() != null) {
            field.setStatus(1);
            field.setPlantingTime(LocalDateTime.now());
        } else {
            field.setStatus(2);
        }
        fieldMapper.addField(field);
    }

    @Override
    public void updateMoisture(Long id, String moisture) {
        Field field = fieldMapper.getById(id);
        if (field == null)
            throw new FieldExistErrorException(MessageConstant.NO_FIELD);
        if (field.getCropId() == null)
            throw new CropExistErrorException(MessageConstant.NO_CROP);
        Field field1 = Field.builder()
                .soilMoisture(moisture)
                .id(id)
                .build();
        fieldMapper.updateWithField(field1);

    }

    @Override
    public void deleteFieldById(Long id) {
        Field field = fieldMapper.getById(id);
        if (field == null)
            throw new FieldExistErrorException(MessageConstant.NO_FIELD);
        fieldMapper.deleteFieldById(id);
    }

    @Override
    public void updateField(FieldDTO fieldDTO) {
        Field fieldBefore = fieldMapper.getById(fieldDTO.getId());
        Field field = new Field();
        BeanUtils.copyProperties(fieldDTO, field);
        if (fieldBefore.getCropId() == null && fieldDTO.getCropId() != null) {
            field.setStatus(1);
            field.setPlantingTime(LocalDateTime.now());
        }
        fieldMapper.updateWithField(field);
    }

    @Override
    public FieldVO getById(Long id) {
        Field field = fieldMapper.getById(id);
        FieldVO fieldVO = new FieldVO();
        BeanUtils.copyProperties(field, fieldVO);
        Long cropId = field.getCropId();
        if (cropId != null) {
            fieldVO.setName(cropMapper.getById(cropId).getName());
        }
        fieldVO.setProvinceName(provinceMapper.getById(Long.valueOf(field.getProvinceId())).getName());
        return fieldVO;
    }

    @Override
    public List<FieldVO> getByUserId(Long userId) {
        List<Field> fields = fieldMapper.listByUserId(userId);
        ArrayList<FieldVO> list = new ArrayList<>();
        fields.forEach(field -> {
            FieldVO fieldVO = new FieldVO();
            Long cropId = field.getCropId();
            if (cropId != null) {
                String cropName = cropMapper.getById(cropId).getName();
                fieldVO.setCropName(cropName);
            }
            Integer provinceId = field.getProvinceId();
            if (provinceId != null) {
                String provinceName = provinceMapper.getById(Long.valueOf(provinceId)).getName();
                fieldVO.setProvinceName(provinceName);
            }
            BeanUtils.copyProperties(field, fieldVO);
            list.add(fieldVO);
        });
        return list;
    }

    @Override
    public String isWatering(Long id) {
        GrowthCycle growthCycle = cropCycleService.getGrowthCycleByFieldId(id);
        Field field = fieldMapper.getById(id);
        if (field == null)
            throw new FieldExistErrorException(MessageConstant.NO_FIELD);
        if (field.getStatus() == 2) {
            String errorMessage = id + "田地" + MessageConstant.NOT_CULTIVATED;
            throw new CropExistErrorException(errorMessage);
        }
        String soilMoisture = field.getSoilMoisture();
        if (soilMoisture == null)
            throw new BaseException(MessageConstant.FAILED_TO_GET_MOISTURE);
        double currentMoisture = Double.parseDouble(soilMoisture);
        String targetMoisture = growthCycle.getSoilMoisture();
        String[] split = targetMoisture.split("-");
        double minMoisture = Double.parseDouble(split[0]);
        double maxMoisture = Double.parseDouble(split[1]);
        if (currentMoisture < minMoisture) {
            return MessageConstant.LOW_MOISTURE + targetMoisture;
        } else if (currentMoisture <= maxMoisture) {
            return MessageConstant.SUITABLE_MOISTURE;
        } else {
            return MessageConstant.HIGH_MOISTURE + targetMoisture;
        }
    }

    @Override
    public void updateToUncultivatedByCropId(Long cropId) {
        List<Field> fields = fieldMapper.listByCropId(cropId);
        for (Field field : fields) {
            field.setStatus(2);
            field.setCropId(null);
            field.setPlantingTime(null);
            fieldMapper.updateWithField(field);
        }
    }

}
