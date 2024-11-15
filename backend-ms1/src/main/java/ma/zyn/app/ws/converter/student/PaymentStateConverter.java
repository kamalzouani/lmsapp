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
import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.ws.dto.student.PaymentStateDto;

@Component
public class PaymentStateConverter {



    public PaymentState toItem(PaymentStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaymentState item = new PaymentState();
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


    public PaymentStateDto toDto(PaymentState item) {
        if (item == null) {
            return null;
        } else {
            PaymentStateDto dto = new PaymentStateDto();
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


	
    public List<PaymentState> toItem(List<PaymentStateDto> dtos) {
        List<PaymentState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaymentStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaymentStateDto> toDto(List<PaymentState> items) {
        List<PaymentStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaymentState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaymentStateDto dto, PaymentState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<PaymentState> copy(List<PaymentStateDto> dtos) {
        List<PaymentState> result = new ArrayList<>();
        if (dtos != null) {
            for (PaymentStateDto dto : dtos) {
                PaymentState instance = new PaymentState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
