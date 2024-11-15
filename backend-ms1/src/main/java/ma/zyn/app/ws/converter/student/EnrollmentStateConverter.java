package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.ws.dto.student.EnrollmentStateDto;

@Component
public class EnrollmentStateConverter {



    public EnrollmentState toItem(EnrollmentStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        EnrollmentState item = new EnrollmentState();
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


    public EnrollmentStateDto toDto(EnrollmentState item) {
        if (item == null) {
            return null;
        } else {
            EnrollmentStateDto dto = new EnrollmentStateDto();
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


	
    public List<EnrollmentState> toItem(List<EnrollmentStateDto> dtos) {
        List<EnrollmentState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EnrollmentStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EnrollmentStateDto> toDto(List<EnrollmentState> items) {
        List<EnrollmentStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EnrollmentState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EnrollmentStateDto dto, EnrollmentState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EnrollmentState> copy(List<EnrollmentStateDto> dtos) {
        List<EnrollmentState> result = new ArrayList<>();
        if (dtos != null) {
            for (EnrollmentStateDto dto : dtos) {
                EnrollmentState instance = new EnrollmentState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
