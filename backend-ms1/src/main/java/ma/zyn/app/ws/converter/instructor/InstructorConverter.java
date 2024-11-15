package  ma.zyn.app.ws.converter.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.ws.dto.instructor.InstructorDto;

@Component
public class InstructorConverter {



    public Instructor toItem(InstructorDto dto) {
        if (dto == null) {
            return null;
        } else {
        Instructor item = new Instructor();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getBio()))
                item.setBio(dto.getBio());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public InstructorDto toDto(Instructor item) {
        if (item == null) {
            return null;
        } else {
            InstructorDto dto = new InstructorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getBio()))
                dto.setBio(item.getBio());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());


        return dto;
        }
    }


	
    public List<Instructor> toItem(List<InstructorDto> dtos) {
        List<Instructor> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InstructorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InstructorDto> toDto(List<Instructor> items) {
        List<InstructorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Instructor item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InstructorDto dto, Instructor t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Instructor> copy(List<InstructorDto> dtos) {
        List<Instructor> result = new ArrayList<>();
        if (dtos != null) {
            for (InstructorDto dto : dtos) {
                Instructor instance = new Instructor();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
