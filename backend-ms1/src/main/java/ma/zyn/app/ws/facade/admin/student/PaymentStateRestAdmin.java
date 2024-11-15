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

import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.dao.criteria.core.student.PaymentStateCriteria;
import ma.zyn.app.service.facade.admin.student.PaymentStateAdminService;
import ma.zyn.app.ws.converter.student.PaymentStateConverter;
import ma.zyn.app.ws.dto.student.PaymentStateDto;
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
@RequestMapping("/api/admin/paymentState/")
public class PaymentStateRestAdmin {




    @Operation(summary = "Finds a list of all paymentStates")
    @GetMapping("")
    public ResponseEntity<List<PaymentStateDto>> findAll() throws Exception {
        ResponseEntity<List<PaymentStateDto>> res = null;
        List<PaymentState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaymentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all paymentStates")
    @GetMapping("optimized")
    public ResponseEntity<List<PaymentStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PaymentStateDto>> res = null;
        List<PaymentState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaymentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a paymentState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PaymentStateDto> findById(@PathVariable Long id) {
        PaymentState t = service.findById(id);
        if (t != null) {
            PaymentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a paymentState by label")
    @GetMapping("label/{label}")
    public ResponseEntity<PaymentStateDto> findByLabel(@PathVariable String label) {
	    PaymentState t = service.findByReferenceEntity(new PaymentState(label));
        if (t != null) {
            PaymentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  paymentState")
    @PostMapping("")
    public ResponseEntity<PaymentStateDto> save(@RequestBody PaymentStateDto dto) throws Exception {
        if(dto!=null){
            PaymentState myT = converter.toItem(dto);
            PaymentState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PaymentStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  paymentState")
    @PutMapping("")
    public ResponseEntity<PaymentStateDto> update(@RequestBody PaymentStateDto dto) throws Exception {
        ResponseEntity<PaymentStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            PaymentState t = service.findById(dto.getId());
            converter.copy(dto,t);
            PaymentState updated = service.update(t);
            PaymentStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of paymentState")
    @PostMapping("multiple")
    public ResponseEntity<List<PaymentStateDto>> delete(@RequestBody List<PaymentStateDto> dtos) throws Exception {
        ResponseEntity<List<PaymentStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<PaymentState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified paymentState")
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


    @Operation(summary = "Finds a paymentState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PaymentStateDto> findWithAssociatedLists(@PathVariable Long id) {
        PaymentState loaded =  service.findWithAssociatedLists(id);
        PaymentStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds paymentStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PaymentStateDto>> findByCriteria(@RequestBody PaymentStateCriteria criteria) throws Exception {
        ResponseEntity<List<PaymentStateDto>> res = null;
        List<PaymentState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PaymentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated paymentStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PaymentStateCriteria criteria) throws Exception {
        List<PaymentState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PaymentStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets paymentState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PaymentStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PaymentStateDto> findDtos(List<PaymentState> list){
        List<PaymentStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PaymentStateDto> getDtoResponseEntity(PaymentStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public PaymentStateRestAdmin(PaymentStateAdminService service, PaymentStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final PaymentStateAdminService service;
    private final PaymentStateConverter converter;





}
