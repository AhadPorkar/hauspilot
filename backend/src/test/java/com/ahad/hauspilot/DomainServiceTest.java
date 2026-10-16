package com.ahad.hauspilot;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class DomainServiceTest {
 private final NamedParameterJdbcTemplate jdbc=mock(NamedParameterJdbcTemplate.class);
 private final DomainService service=new DomainService(jdbc);
 @Test void everyModuleHasFields(){for(String module:List.of("properties","requests","approvals","meetings","resolutions","correspondence"))assertFalse(DomainService.FIELDS.get(module).isEmpty());}
 @Test void unknownModuleRejected(){assertThrows(IllegalArgumentException.class,()->service.list("users; DROP TABLE users"));}
 @Test void createRequiresRecognizedFields(){assertThrows(IllegalArgumentException.class,()->service.create("properties",Map.of("evil","x"),"tester"));}
 @Test void statusRequiresAllowedModule(){assertThrows(IllegalArgumentException.class,()->service.status("properties",UUID.randomUUID(),"OPEN","tester"));}
 @Test void statusRequiresExistingRecord(){when(jdbc.queryForList(anyString(),anyMap())).thenReturn(List.of());assertThrows(NoSuchElementException.class,()->service.status("requests",UUID.randomUUID(),"RESOLVED","tester"));}
 @Test void listUsesWhitelistedTable(){when(jdbc.queryForList(anyString(),anyMap())).thenReturn(List.of());assertTrue(service.list("meetings").isEmpty());}
}
