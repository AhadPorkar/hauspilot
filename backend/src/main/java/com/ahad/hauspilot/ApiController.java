package com.ahad.hauspilot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;
@RestController @RequestMapping("/api") public class ApiController {
 private final DomainService service;
 public ApiController(DomainService service){this.service=service;}
 @GetMapping("/overview") public Map<String,Object> overview(){return service.overview();}
 @GetMapping("/{module}") public List<Map<String,Object>> list(@PathVariable String module){return service.list(module);}
 @PostMapping("/{module}") public ResponseEntity<Map<String,Object>> create(@PathVariable String module,@RequestBody Map<String,Object> data,Principal p){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(module,data,p.getName()));}
 public record StatusRequest(@NotBlank String status){}
 @PatchMapping("/{module}/{id}/status") public Map<String,Object> status(@PathVariable String module,@PathVariable UUID id,@Valid @RequestBody StatusRequest req,Principal p){return service.status(module,id,req.status(),p.getName());}
 @ExceptionHandler({IllegalArgumentException.class,org.springframework.dao.DataIntegrityViolationException.class}) @ResponseStatus(HttpStatus.BAD_REQUEST) public Map<String,String> bad(Exception e){return Map.of("error","Invalid request or violated data constraint");}
 @ExceptionHandler(NoSuchElementException.class) @ResponseStatus(HttpStatus.NOT_FOUND) public Map<String,String> notFound(Exception e){return Map.of("error","Record not found");}
}
