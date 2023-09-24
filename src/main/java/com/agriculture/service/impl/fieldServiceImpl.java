package com.agriculture.service.impl;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.exception.CropExistErrorException;
import com.agriculture.common.exception.FieldExistErrorException;
import com.agriculture.mapper.FieldMapper;
import com.agriculture.pojo.DTO.FieldDTO;
import com.agriculture.pojo.entity.Field;
import com.agriculture.pojo.entity.GrowthCycle;
import com.agriculture.service.CropCycleService;
import com.agriculture.service.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class fieldServiceImpl implements FieldService {
    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private CropCycleService cropCycleService;

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
    public void updateMoisture(Long id, String moisture) {
        Field field = fieldMapper.getById(id);
        if (field.getCropId() != null) {
            Field field1 = Field.builder()
                    .soilMoisture(moisture)
                    .id(id)
                    .build();
            fieldMapper.updateWithField(field1);
        } else throw new CropExistErrorException(MessageConstant.NO_CROP);
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
    public Field getById(Long id) {
        return fieldMapper.getById(id);
    }

    @Override
    public List<Field> getByUserId(Long userId) {
        return fieldMapper.listByUserId(userId);
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
        double currentMoisture = Double.parseDouble(field.getSoilMoisture());
        String targetMoisture = growthCycle.getSoilMoisture();
        String[] split = targetMoisture.split("-");
        double minMoisture = Double.parseDouble(split[0]);
        double maxMoisture = Double.parseDouble(split[1]);
        if (currentMoisture < minMoisture) {
            return MessageConstant.LOW_MOISTURE;
        } else if (currentMoisture <= maxMoisture) {
            return MessageConstant.SUITABLE_MOISTURE;
        } else {
            return MessageConstant.HIGH_MOISTURE;
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
