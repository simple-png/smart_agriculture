package com.agriculture.service.impl;

import com.agriculture.constant.MessageConstant;
import com.agriculture.exception.CropExistErrorException;
import com.agriculture.exception.FieldExistErrorException;
import com.agriculture.exception.GrowthCycleExistErrorException;
import com.agriculture.result.PageResult;
import com.agriculture.mapper.CropMapper;
import com.agriculture.mapper.FieldMapper;
import com.agriculture.mapper.GrowthCycleMapper;
import com.agriculture.DTO.GrowthCyclePageQueryDTO;
import com.agriculture.entity.Crop;
import com.agriculture.entity.Field;
import com.agriculture.entity.GrowthCycle;
import com.agriculture.service.CropCycleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class CropCycleServiceImpl implements CropCycleService {
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private GrowthCycleMapper growthCycleMapper;
    @Override
    public GrowthCycle getGrowthCycleByFieldId(Long id) {
        Field field = fieldMapper.getById(id);
        if (field == null) {
            throw new FieldExistErrorException(MessageConstant.NO_FIELD);
        }

        Crop crop = cropMapper.getByFieldId(id);
        if (crop == null) {
            throw new CropExistErrorException(MessageConstant.NO_CROP);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plantingTime = field.getPlantingTime();
        long days = ChronoUnit.DAYS.between(plantingTime, now);

        String cropGrowthCycle = crop.getGrowthCycle();
        double percent = days / Double.parseDouble(cropGrowthCycle);

        List<GrowthCycle> growthCycle = growthCycleMapper.getByCategoryId(crop.getCropCategoryId());
        if (growthCycle == null || growthCycle.isEmpty()) {
            throw new GrowthCycleExistErrorException(MessageConstant.NO_GROWTH_CYCLE);
        }

        return growthCycle.stream()
                .filter(cycle -> {
                    String[] strings = cycle.getCyclePercent().split("-");
                    double before = Double.parseDouble(strings[0]);
                    double last = Double.parseDouble(strings[1]);
                    return before <= percent && percent <= last;
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(GrowthCycle growthCycle) {
        growthCycleMapper.add(growthCycle);
    }

    @Override
    public PageResult pageQuery(GrowthCyclePageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getPageSize());
        Page<GrowthCycle> page = growthCycleMapper.pageQuery(dto);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            growthCycleMapper.deleteById(id);
        }
    }

    @Override
    public void update(GrowthCycle growthCycle) {
        GrowthCycle growthCycle1=growthCycleMapper.getById(growthCycle.getId());
        if (growthCycle1==null)
            throw new GrowthCycleExistErrorException(MessageConstant.GROWTH_CYCLE_ERROR);
        growthCycleMapper.update(growthCycle);
    }

}
