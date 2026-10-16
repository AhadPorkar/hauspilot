package com.ahad.hauspilot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
class ModuleContractTest {
 private final NamedParameterJdbcTemplate jdbc=mock(NamedParameterJdbcTemplate.class);
 private final DomainService service=new DomainService(jdbc);
 @ParameterizedTest(name="{0} module supports listing")
 @CsvSource({"properties,name","requests,title","approvals,title","meetings,title","resolutions,wording","correspondence,subject"})
 void listEachModule(String module,String field){when(jdbc.queryForList(anyString(),anyMap())).thenReturn(List.of(Map.of(field,"example")));var rows=service.list(module);assertEquals("example",rows.getFirst().get(field));verify(jdbc).queryForList(startsWith("SELECT * FROM "+module),anyMap());}
 @ParameterizedTest(name="{0} module supports audited creation")
 @CsvSource({"properties,name","requests,title","approvals,title","meetings,title","resolutions,wording","correspondence,subject"})
 void createEachModule(String module,String field){UUID id=UUID.randomUUID();when(jdbc.queryForMap(anyString(),anyMap())).thenReturn(Map.of("id",id,field,"example"));var created=service.create(module,Map.of(field,"example"),"tester");assertEquals(id,created.get("id"));verify(jdbc).update(startsWith("INSERT INTO activity"),anyMap());}
 @ParameterizedTest(name="{0} allows status workflow")
 @CsvSource({"requests,RESOLVED","approvals,APPROVED","meetings,HELD","correspondence,READY"})
 void statusEachWorkflow(String module,String target){when(jdbc.queryForList(anyString(),anyMap())).thenReturn(List.of(Map.of("status",target)));var updated=service.status(module,UUID.randomUUID(),target,"tester");assertEquals(target,updated.get("status"));verify(jdbc).update(startsWith("INSERT INTO activity"),anyMap());}
 @Test void rejectingUnknownResourcePreventsSqlInjection(){assertThrows(IllegalArgumentException.class,()->service.create("properties;DELETE FROM properties",Map.of("name","x"),"tester"));verifyNoInteractions(jdbc);}
}
