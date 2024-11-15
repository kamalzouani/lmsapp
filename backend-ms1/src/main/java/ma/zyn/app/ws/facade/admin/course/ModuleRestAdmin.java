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

import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.dao.criteria.core.course.ModuleCriteria;
import ma.zyn.app.service.facade.admin.course.ModuleAdminService;
import ma.zyn.app.ws.converter.course.ModuleConverter;
import ma.zyn.app.ws.dto.course.ModuleDto;
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
@RequestMapping("/api/admin/module/")
public class ModuleRestAdmin {




    @Operation(summary = "Finds a list of all modules")
    @GetMapping("")
    public ResponseEntity<List<ModuleDto>> findAll() throws Exception {
        ResponseEntity<List<ModuleDto>> res = null;
        List<Module> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ModuleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all modules")
    @GetMapping("optimized")
    public ResponseEntity<List<ModuleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ModuleDto>> res = null;
        List<Module> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ModuleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a module by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ModuleDto> findById(@PathVariable Long id) {
        Module t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ModuleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a module by label")
    @GetMapping("label/{label}")
    public ResponseEntity<ModuleDto> findByLabel(@PathVariable String label) {
	    Module t = service.findByReferenceEntity(new Module(label));
        if (t != null) {
            converter.init(true);
            ModuleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  module")
    @PostMapping("")
    public ResponseEntity<ModuleDto> save(@RequestBody ModuleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Module myT = converter.toItem(dto);
            Module t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ModuleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  module")
    @PutMapping("")
    public ResponseEntity<ModuleDto> update(@RequestBody ModuleDto dto) throws Exception {
        ResponseEntity<ModuleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Module t = service.findById(dto.getId());
            converter.copy(dto,t);
            Module updated = service.update(t);
            ModuleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of module")
    @PostMapping("multiple")
    public ResponseEntity<List<ModuleDto>> delete(@RequestBody List<ModuleDto> dtos) throws Exception {
        ResponseEntity<List<ModuleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Module> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified module")
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

    @Operation(summary = "find by curriculum id")
    @GetMapping("curriculum/id/{id}")
    public List<ModuleDto> findByCurriculumId(@PathVariable Long id){
        return findDtos(service.findByCurriculumId(id));
    }
    @Operation(summary = "delete by curriculum id")
    @DeleteMapping("curriculum/id/{id}")
    public int deleteByCurriculumId(@PathVariable Long id){
        return service.deleteByCurriculumId(id);
    }

    @Operation(summary = "Finds a module and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ModuleDto> findWithAssociatedLists(@PathVariable Long id) {
        Module loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ModuleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds modules by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ModuleDto>> findByCriteria(@RequestBody ModuleCriteria criteria) throws Exception {
        ResponseEntity<List<ModuleDto>> res = null;
        List<Module> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ModuleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated modules by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModuleCriteria criteria) throws Exception {
        List<Module> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ModuleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets module data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ModuleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ModuleDto> findDtos(List<Module> list){
        converter.initList(false);
        converter.initObject(true);
        List<ModuleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ModuleDto> getDtoResponseEntity(ModuleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ModuleRestAdmin(ModuleAdminService service, ModuleConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ModuleAdminService service;
    private final ModuleConverter converter;





}
