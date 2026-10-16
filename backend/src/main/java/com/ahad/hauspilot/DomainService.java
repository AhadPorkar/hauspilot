package com.ahad.hauspilot;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service public class DomainService {
 private final NamedParameterJdbcTemplate db;
 public DomainService(NamedParameterJdbcTemplate db){this.db=db;}
 public static final Map<String,List<String>> FIELDS=Map.of(
 "properties",List.of("name","address","city","postal_code","units","kind"),
 "requests",List.of("property_id","title","description","requester","category","priority","status"),
 "approvals",List.of("property_id","title","amount","requested_by","notes","status"),
 "meetings",List.of("property_id","title","meeting_date","location","minutes","status"),
 "resolutions",List.of("meeting_id","resolution_no","wording","announced_on","result","challenged"),
 "correspondence",List.of("property_id","subject","body","recipient","channel","status"));
 public List<Map<String,Object>> list(String table){check(table);return db.queryForList("SELECT * FROM "+table+" ORDER BY created_at DESC",Map.of());}
 @Transactional public Map<String,Object> create(String table,Map<String,Object> input,String actor){check(table);var fields=FIELDS.get(table).stream().filter(input::containsKey).toList();if(fields.isEmpty())throw new IllegalArgumentException("No valid fields supplied");var params=new HashMap<String,Object>();fields.forEach(f->params.put(f,input.get(f)));String sql="INSERT INTO "+table+" ("+String.join(",",fields)+") VALUES ("+String.join(",",fields.stream().map(f->f.endsWith("_id")?"CAST(:"+f+" AS uuid)":(f.equals("meeting_date")||f.equals("announced_on"))?"CAST(:"+f+" AS date)":":"+f).toList())+") RETURNING *";var result=db.queryForMap(sql,params);audit(table,(UUID)result.get("id"),"CREATED",actor);return result;}
 @Transactional public Map<String,Object> status(String table,UUID id,String value,String actor){if(!Set.of("requests","approvals","meetings","correspondence").contains(table))throw new IllegalArgumentException("Status unsupported");String sql="UPDATE "+table+" SET status=:value"+(table.equals("requests")?",updated_at=now()":"")+" WHERE id=:id RETURNING *";var r=db.queryForList(sql,Map.of("id",id,"value",value));if(r.isEmpty())throw new NoSuchElementException("Not found");audit(table,id,"STATUS_"+value,actor);return r.getFirst();}
 public Map<String,Object> overview(){var r=new LinkedHashMap<String,Object>();for(var t:List.of("properties","requests","approvals","meetings","correspondence","resolutions"))r.put(t,db.queryForObject("SELECT count(*) FROM "+t,Map.of(),Long.class));r.put("urgent",db.queryForObject("SELECT count(*) FROM requests WHERE priority='URGENT' AND status<>'RESOLVED'",Map.of(),Long.class));r.put("pendingApprovals",db.queryForObject("SELECT count(*) FROM approvals WHERE status='PENDING'",Map.of(),Long.class));return r;}
 private void check(String t){if(!FIELDS.containsKey(t))throw new IllegalArgumentException("Unknown module: "+t);}
 private void audit(String t,UUID id,String action,String actor){db.update("INSERT INTO activity(entity_type,entity_id,action,actor) VALUES (:t,:id,:a,:u)",Map.of("t",t,"id",id,"a",action,"u",actor));}
}
