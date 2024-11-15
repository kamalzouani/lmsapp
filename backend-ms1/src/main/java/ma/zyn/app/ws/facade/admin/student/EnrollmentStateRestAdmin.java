package  ma.zyn.app.ws.facade.admin.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.dao.criteria.core.student.EnrollmentStateCriteria;
import ma.zyn.app.service.facade.admin.student.EnrollmentStateAdminService;
import ma.zyn.app.ws.converter.student.EnrollmentStateConverter;
import ma.zyn.app.ws.dto.student.EnrollmentStateDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/enrollmentState/")
public class EnrollmentStateRestAdmin {




    @Operation(summary = "Finds a list of all enrollmentStates")
    @GetMapping("")
    public ResponseEntity<List<EnrollmentStateDto>> findAll() throws Exception {
        ResponseEntity<List<EnrollmentStateDto>> res = null;
        List<EnrollmentState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EnrollmentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all enrollmentStates")
    @GetMapping("optimized")
    public ResponseEntity<List<EnrollmentStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EnrollmentStateDto>> res = null;
        List<EnrollmentState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EnrollmentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a enrollmentState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EnrollmentStateDto> findById(@PathVariable Long id) {
        EnrollmentState t = service.findById(id);
        if (t != null) {
            EnrollmentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a enrollmentState by label")
    @GetMapping("label/{label}")
    public ResponseEntity<EnrollmentStateDto> findByLabel(@PathVariable String label) {
	    EnrollmentState t = service.findByReferenceEntity(new EnrollmentState(label));
        if (t != null) {
            EnrollmentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  enrollmentState")
    @PostMapping("")
    public ResponseEntity<EnrollmentStateDto> save(@RequestBody EnrollmentStateDto dto) throws Exception {
        if(dto!=null){
            EnrollmentState myT = converter.toItem(dto);
            EnrollmentState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EnrollmentStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  enrollmentState")
    @PutMapping("")
    public ResponseEntity<EnrollmentStateDto> update(@RequestBody EnrollmentStateDto dto) throws Exception {
        ResponseEntity<EnrollmentStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EnrollmentState t = service.findById(dto.getId());
            converter.copy(dto,t);
            EnrollmentState updated = service.update(t);
            EnrollmentStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of enrollmentState")
    @PostMapping("multiple")
    public ResponseEntity<List<EnrollmentStateDto>> delete(@RequestBody List<EnrollmentStateDto> dtos) throws Exception {
        ResponseEntity<List<EnrollmentStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EnrollmentState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified enrollmentState")
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


    @Operation(summary = "Finds a enrollmentState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EnrollmentStateDto> findWithAssociatedLists(@PathVariable Long id) {
        EnrollmentState loaded =  service.findWithAssociatedLists(id);
        EnrollmentStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds enrollmentStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EnrollmentStateDto>> findByCriteria(@RequestBody EnrollmentStateCriteria criteria) throws Exception {
        ResponseEntity<List<EnrollmentStateDto>> res = null;
        List<EnrollmentState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EnrollmentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated enrollmentStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EnrollmentStateCriteria criteria) throws Exception {
        List<EnrollmentState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EnrollmentStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets enrollmentState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EnrollmentStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EnrollmentStateDto> findDtos(List<EnrollmentState> list){
        List<EnrollmentStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EnrollmentStateDto> getDtoResponseEntity(EnrollmentStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EnrollmentStateRestAdmin(EnrollmentStateAdminService service, EnrollmentStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EnrollmentStateAdminService service;
    private final EnrollmentStateConverter converter;





}
