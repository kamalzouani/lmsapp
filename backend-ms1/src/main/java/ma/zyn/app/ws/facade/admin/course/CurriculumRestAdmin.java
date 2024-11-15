package  ma.zyn.app.ws.facade.admin.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.dao.criteria.core.course.CurriculumCriteria;
import ma.zyn.app.service.facade.admin.course.CurriculumAdminService;
import ma.zyn.app.ws.converter.course.CurriculumConverter;
import ma.zyn.app.ws.dto.course.CurriculumDto;
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
@RequestMapping("/api/admin/curriculum/")
public class CurriculumRestAdmin {




    @Operation(summary = "Finds a list of all curriculums")
    @GetMapping("")
    public ResponseEntity<List<CurriculumDto>> findAll() throws Exception {
        ResponseEntity<List<CurriculumDto>> res = null;
        List<Curriculum> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<CurriculumDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a curriculum by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CurriculumDto> findById(@PathVariable Long id) {
        Curriculum t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CurriculumDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  curriculum")
    @PostMapping("")
    public ResponseEntity<CurriculumDto> save(@RequestBody CurriculumDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Curriculum myT = converter.toItem(dto);
            Curriculum t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CurriculumDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  curriculum")
    @PutMapping("")
    public ResponseEntity<CurriculumDto> update(@RequestBody CurriculumDto dto) throws Exception {
        ResponseEntity<CurriculumDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Curriculum t = service.findById(dto.getId());
            converter.copy(dto,t);
            Curriculum updated = service.update(t);
            CurriculumDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of curriculum")
    @PostMapping("multiple")
    public ResponseEntity<List<CurriculumDto>> delete(@RequestBody List<CurriculumDto> dtos) throws Exception {
        ResponseEntity<List<CurriculumDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Curriculum> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified curriculum")
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


    @Operation(summary = "Finds a curriculum and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CurriculumDto> findWithAssociatedLists(@PathVariable Long id) {
        Curriculum loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CurriculumDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds curriculums by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CurriculumDto>> findByCriteria(@RequestBody CurriculumCriteria criteria) throws Exception {
        ResponseEntity<List<CurriculumDto>> res = null;
        List<Curriculum> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CurriculumDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated curriculums by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CurriculumCriteria criteria) throws Exception {
        List<Curriculum> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<CurriculumDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets curriculum data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CurriculumCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CurriculumDto> findDtos(List<Curriculum> list){
        converter.initList(false);
        converter.initObject(true);
        List<CurriculumDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CurriculumDto> getDtoResponseEntity(CurriculumDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CurriculumRestAdmin(CurriculumAdminService service, CurriculumConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CurriculumAdminService service;
    private final CurriculumConverter converter;





}
