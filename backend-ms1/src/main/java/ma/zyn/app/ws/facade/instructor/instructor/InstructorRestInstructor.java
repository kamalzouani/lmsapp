package  ma.zyn.app.ws.facade.instructor.instructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;
import ma.zyn.app.service.facade.instructor.instructor.InstructorInstructorService;
import ma.zyn.app.ws.converter.instructor.InstructorConverter;
import ma.zyn.app.ws.dto.instructor.InstructorDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import ma.zyn.app.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/instructor/instructor/")
public class InstructorRestInstructor {




    @Operation(summary = "Finds a list of all instructors")
    @GetMapping("")
    public ResponseEntity<List<InstructorDto>> findAll() throws Exception {
        ResponseEntity<List<InstructorDto>> res = null;
        List<Instructor> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InstructorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all instructors")
    @GetMapping("optimized")
    public ResponseEntity<List<InstructorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InstructorDto>> res = null;
        List<Instructor> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InstructorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a instructor by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InstructorDto> findById(@PathVariable Long id) {
        Instructor t = service.findById(id);
        if (t != null) {
            InstructorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a instructor by email")
    @GetMapping("email/{email}")
    public ResponseEntity<InstructorDto> findByEmail(@PathVariable String email) {
	    Instructor t = service.findByReferenceEntity(new Instructor(email));
        if (t != null) {
            InstructorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  instructor")
    @PostMapping("")
    public ResponseEntity<InstructorDto> save(@RequestBody InstructorDto dto) throws Exception {
        if(dto!=null){
            Instructor myT = converter.toItem(dto);
            Instructor t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InstructorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  instructor")
    @PutMapping("")
    public ResponseEntity<InstructorDto> update(@RequestBody InstructorDto dto) throws Exception {
        ResponseEntity<InstructorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Instructor t = service.findById(dto.getId());
            converter.copy(dto,t);
            Instructor updated = service.update(t);
            InstructorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of instructor")
    @PostMapping("multiple")
    public ResponseEntity<List<InstructorDto>> delete(@RequestBody List<InstructorDto> dtos) throws Exception {
        ResponseEntity<List<InstructorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Instructor> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified instructor")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a instructor and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InstructorDto> findWithAssociatedLists(@PathVariable Long id) {
        Instructor loaded =  service.findWithAssociatedLists(id);
        InstructorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds instructors by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InstructorDto>> findByCriteria(@RequestBody InstructorCriteria criteria) throws Exception {
        ResponseEntity<List<InstructorDto>> res = null;
        List<Instructor> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<InstructorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated instructors by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InstructorCriteria criteria) throws Exception {
        List<Instructor> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<InstructorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets instructor data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InstructorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InstructorDto> findDtos(List<Instructor> list){
        List<InstructorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InstructorDto> getDtoResponseEntity(InstructorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public InstructorRestInstructor(InstructorInstructorService service, InstructorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final InstructorInstructorService service;
    private final InstructorConverter converter;





}
