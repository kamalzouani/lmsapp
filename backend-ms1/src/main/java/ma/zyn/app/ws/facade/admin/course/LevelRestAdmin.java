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

import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.dao.criteria.core.course.LevelCriteria;
import ma.zyn.app.service.facade.admin.course.LevelAdminService;
import ma.zyn.app.ws.converter.course.LevelConverter;
import ma.zyn.app.ws.dto.course.LevelDto;
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
@RequestMapping("/api/admin/level/")
public class LevelRestAdmin {




    @Operation(summary = "Finds a list of all levels")
    @GetMapping("")
    public ResponseEntity<List<LevelDto>> findAll() throws Exception {
        ResponseEntity<List<LevelDto>> res = null;
        List<Level> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all levels")
    @GetMapping("optimized")
    public ResponseEntity<List<LevelDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<LevelDto>> res = null;
        List<Level> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a level by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LevelDto> findById(@PathVariable Long id) {
        Level t = service.findById(id);
        if (t != null) {
            LevelDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a level by label")
    @GetMapping("label/{label}")
    public ResponseEntity<LevelDto> findByLabel(@PathVariable String label) {
	    Level t = service.findByReferenceEntity(new Level(label));
        if (t != null) {
            LevelDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  level")
    @PostMapping("")
    public ResponseEntity<LevelDto> save(@RequestBody LevelDto dto) throws Exception {
        if(dto!=null){
            Level myT = converter.toItem(dto);
            Level t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LevelDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  level")
    @PutMapping("")
    public ResponseEntity<LevelDto> update(@RequestBody LevelDto dto) throws Exception {
        ResponseEntity<LevelDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Level t = service.findById(dto.getId());
            converter.copy(dto,t);
            Level updated = service.update(t);
            LevelDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of level")
    @PostMapping("multiple")
    public ResponseEntity<List<LevelDto>> delete(@RequestBody List<LevelDto> dtos) throws Exception {
        ResponseEntity<List<LevelDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Level> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified level")
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


    @Operation(summary = "Finds a level and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LevelDto> findWithAssociatedLists(@PathVariable Long id) {
        Level loaded =  service.findWithAssociatedLists(id);
        LevelDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds levels by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LevelDto>> findByCriteria(@RequestBody LevelCriteria criteria) throws Exception {
        ResponseEntity<List<LevelDto>> res = null;
        List<Level> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<LevelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated levels by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LevelCriteria criteria) throws Exception {
        List<Level> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<LevelDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets level data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LevelCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LevelDto> findDtos(List<Level> list){
        List<LevelDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LevelDto> getDtoResponseEntity(LevelDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LevelRestAdmin(LevelAdminService service, LevelConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LevelAdminService service;
    private final LevelConverter converter;





}
