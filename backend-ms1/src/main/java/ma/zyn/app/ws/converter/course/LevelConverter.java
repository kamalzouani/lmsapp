package  ma.zyn.app.ws.converter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.ws.dto.course.LevelDto;

@Component
public class LevelConverter {



    public Level toItem(LevelDto dto) {
        if (dto == null) {
            return null;
        } else {
        Level item = new Level();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLabel()))
                item.setLabel(dto.getLabel());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public LevelDto toDto(Level item) {
        if (item == null) {
            return null;
        } else {
            LevelDto dto = new LevelDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLabel()))
                dto.setLabel(item.getLabel());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<Level> toItem(List<LevelDto> dtos) {
        List<Level> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LevelDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LevelDto> toDto(List<Level> items) {
        List<LevelDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Level item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LevelDto dto, Level t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Level> copy(List<LevelDto> dtos) {
        List<Level> result = new ArrayList<>();
        if (dtos != null) {
            for (LevelDto dto : dtos) {
                Level instance = new Level();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
