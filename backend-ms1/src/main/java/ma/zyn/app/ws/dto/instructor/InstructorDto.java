package  ma.zyn.app.ws.dto.instructor;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import ma.zyn.app.zynerator.security.bean.Role;
import java.util.Collection;
import ma.zyn.app.zynerator.security.ws.dto.UserDto;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorDto  extends UserDto {

    private String bio  ;




    private Collection<Role> roles;
    public InstructorDto(){
        super();
    }




    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }

















    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
