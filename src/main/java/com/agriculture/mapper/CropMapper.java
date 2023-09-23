package com.agriculture.mapper;

import com.agriculture.annotation.AutoFill;
import com.agriculture.common.enumeration.OperationType;
import com.agriculture.pojo.DTO.CropPageQueryDTO;
import com.agriculture.pojo.VO.CropVO;
import com.agriculture.pojo.entity.Crop;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CropMapper {
    /**
     * 农作物分页查询
     *
     * @param dto
     * @return
     */
    Page<CropVO> pageQuery(CropPageQueryDTO dto);

    /**
     * 添加农作物
     *
     * @param crop
     */
    @AutoFill(OperationType.INSERT)
    void add(Crop crop);


    /**
     * 根据id查询农作物
     *
     * @param id
     * @return
     */
    @Select("select crop.*,crop_category.name as crop_category_name from crop join crop_category on crop.crop_category_id=crop_category.id where crop.id=#{id}")
    CropVO getById(Long id);

    /**
     * 根据种类获取所有农作物
     *
     * @return
     */
    @Select("select * from crop where crop_category_id=#{id}")
    List<Crop> listByCategoryId(Long id);

    /**
     * 更新农作物
     *
     * @param crop
     */
    @AutoFill(OperationType.INSERT)
    void update(Crop crop);

    /**
     * 根据id删除农作物
     *
     * @param id
     */
    @Delete("delete from crop where id=#{id}")
    void deleteById(Long id);

    /**
     * 根据田地id获取农作物
     *
     * @param id
     * @return
     */
    @Select("select c.* from crop c join field f on f.crop_id=c.id where f.id=#{id}")
    Crop getByFieldId(Long id);

    /**
     * 根据种类id删除农作物
     *
     * @param id
     */
    @Delete("delete from crop where crop_category_id=#{id}")
    void deleteByCategoryId(Long id);
}
