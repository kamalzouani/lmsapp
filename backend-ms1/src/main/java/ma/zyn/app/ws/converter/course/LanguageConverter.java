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
import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.ws.dto.course.LanguageDto;

@Component
public class LanguageConverter {



    public Language toItem(LanguageDto dto) {
        if (dto == null) {
            return null;
        } else {
        Language item = new Language();
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


    public LanguageDto toDto(Language item) {
        if (item == null) {
            return null;
        } else {
            LanguageDto dto = new LanguageDto();
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


	
    public List<Language> toItem(List<LanguageDto> dtos) {
        List<Language> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LanguageDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LanguageDto> toDto(List<Language> items) {
        List<LanguageDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Language item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LanguageDto dto, Language t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Language> copy(List<LanguageDto> dtos) {
        List<Language> result = new ArrayList<>();
        if (dtos != null) {
            for (LanguageDto dto : dtos) {
                Language instance = new Language();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
