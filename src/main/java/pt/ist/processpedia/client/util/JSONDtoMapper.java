package pt.ist.processpedia.client.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import pt.ist.processpedia.shared.dto.Dto;
import pt.ist.processpedia.shared.dto.domain.OperatingPartyDto;
import pt.ist.processpedia.shared.dto.domain.ProcessDtoImpl;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import pt.ist.processpedia.shared.dto.domain.QueueDtoImpl;
import pt.ist.processpedia.shared.dto.domain.RequestDto;
import pt.ist.processpedia.shared.dto.domain.RequestDtoImpl;
import pt.ist.processpedia.shared.dto.domain.OperatingParty;
import pt.ist.processpedia.shared.dto.domain.SystemDtoImpl;
import pt.ist.processpedia.shared.dto.domain.UserDto;
import pt.ist.processpedia.shared.dto.domain.UserDtoImpl;

public class JSONDtoMapper implements DtoMapper {

  private static class Key {
    private static String OID = "oid";
    private static String NAME = "name";
    private static String SUBJECT = "subject";
    private static String TITLE = "title";
    private static String ORIGINAL_INITIATOR = "original-initiator";
    private static String INITIATOR = "initiator";
    private static String EXECUTOR = "executor";
    private static String CREATION_TIMESTAMP = "creation";
    private static String LAST_UPDATE_TIMESTAMP = "last-update";
    private static String PROCESS = "process";
    private static String PUBLISHED_QUEUES = "published-queues";
    private static String AVATAR_URL = "avatar-url";
    private static String DESCRIPTION = "description";
    private static String TYPE = "type";
  }
  
  private JSONValue parse(String jsonString) {
    return JSONParser.parseStrict(jsonString);
  }
  
  @Override
  public String externalize(Dto dto) {
    if(dto instanceof OperatingParty) {
      return externalizeOperatingPartyDto((OperatingPartyDto)dto).toString();
    } else if(dto instanceof RequestDto) {
      return externalizeRequestDto((RequestDto)dto).toString();
    }
    
    return null;
  }

  public OperatingPartyDto internalizeOperatingPartyDto(String externalizedDto) {
    return internalizeOperatingPartyDto((JSONObject)parse(externalizedDto));
  }
  
  public OperatingPartyDto internalizeOperatingPartyDto(JSONObject jsonObject) {
    Long oid = new Long(((JSONString)jsonObject.get(Key.OID)).stringValue());
    String type = ((JSONString)jsonObject.get(Key.TYPE.toString())).stringValue();
    String name = ((JSONString)jsonObject.get(Key.NAME.toString())).stringValue();
    String avatarUrl = ((JSONString)jsonObject.get(Key.AVATAR_URL.toString())).stringValue();
    if(type.equals("user")) {
      return new UserDtoImpl(oid, name, avatarUrl);
    } else {
      return new SystemDtoImpl(oid, name, avatarUrl);
    }
  }
  
  public JSONObject externalizeOperatingPartyDto(OperatingPartyDto operatingParty) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(Key.OID, new JSONNumber(operatingParty.getOid()));
    jsonObject.put(Key.NAME, new JSONString(operatingParty.getName()));
    jsonObject.put(Key.AVATAR_URL, new JSONString(operatingParty.getAvatarUrl()));
    return jsonObject;
  }
  
  public RequestDto internalizeRequestDto(String externalizedDto) {
    JSONObject jsonObject = (JSONObject)parse(externalizedDto);
    Long oid = new Long(((JSONString)jsonObject.get(Key.OID)).stringValue());
    String subject = ((JSONString)jsonObject.get(Key.SUBJECT)).stringValue();
    OperatingPartyDto originalInitiator = internalizeOperatingPartyDto((JSONObject)jsonObject.get(Key.ORIGINAL_INITIATOR));
    OperatingPartyDto initiator = internalizeOperatingPartyDto((JSONObject)jsonObject.get(Key.INITIATOR));
    OperatingPartyDto executor = internalizeOperatingPartyDto((JSONObject)jsonObject.get(Key.EXECUTOR));
    Set<QueueDto> publishedQueueSet = internalizeQueueDtoSet((JSONArray)jsonObject.get(Key.PUBLISHED_QUEUES));
    Date creationTimestamp = new Date(new Long(((JSONNumber)jsonObject.get(Key.CREATION_TIMESTAMP)).toString()));
    Date lastUpdateTimestamp = new Date(new Long(((JSONNumber)jsonObject.get(Key.LAST_UPDATE_TIMESTAMP)).toString()));
    ProcessDtoImpl processDto = internalizeProcessDto((JSONObject)jsonObject.get(Key.PROCESS));
    RequestDtoImpl request = new RequestDtoImpl(oid, originalInitiator, initiator);
    request.setSubject(subject);
    request.setExecutor(executor);
    request.setPublishedQueueSet(publishedQueueSet);
    
    return request;
  }
  
  public QueueDtoImpl internalizeQueueDto(String externalizedDto) {
    return internalizeQueueDto((JSONObject)parse(externalizedDto));
  }
  
  public QueueDtoImpl internalizeQueueDto(JSONObject queueDtoJsonObject) {
    Long oid = new Long(((JSONNumber)queueDtoJsonObject.get(Key.OID)).toString());
    String title = ((JSONString)queueDtoJsonObject.get(Key.TITLE.toString())).stringValue();
    return new QueueDtoImpl(oid, title);
  }
  
  public Set<QueueDto> internalizeQueueDtoSet(JSONArray jsonArray) {
    Set<QueueDto> queueDtoSet = new HashSet<QueueDto>();
    for(int i = 0; i < jsonArray.size(); i++) {
      queueDtoSet.add(internalizeQueueDto(((JSONObject)jsonArray.get(i))));
    }
    return queueDtoSet;  
  }
  
  public JSONArray externalizeQueueDtoSet(Set<QueueDtoImpl> queueDtoSet) {
    JSONArray queueSetJsonArray = new JSONArray();
    int i = 0;
    for(QueueDtoImpl queueDto : queueDtoSet) {
      queueSetJsonArray.set(i++, externalizeQueueDto(queueDto));
    }
    return queueSetJsonArray;
  }
  
  public JSONObject externalizeQueueDto(QueueDtoImpl queueDto) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(Key.OID, new JSONNumber(queueDto.getOid()));
    jsonObject.put(Key.TITLE, new JSONString(queueDto.getTitle()));
    return jsonObject;
  }
  
  public ProcessDtoImpl internalizeProcessDto(JSONObject processJsonObject) {
    Long oid = new Long(((JSONNumber)processJsonObject.get(Key.OID)).toString());
    String title = ((JSONString)processJsonObject.get(Key.TITLE.toString())).stringValue();
    String description = ((JSONString)processJsonObject.get(Key.DESCRIPTION.toString())).stringValue();
    return new ProcessDtoImpl(oid, title, description);
  }
  
  public ProcessDtoImpl internalizeProcessDto(String externalizedDto) {
    return internalizeProcessDto((JSONObject)parse(externalizedDto));
  }
  
  private JSONObject externalizeRequestDto(RequestDto request) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(Key.OID, new JSONNumber(request.getOid()));
    jsonObject.put(Key.SUBJECT, new JSONString(request.getSubject()));
    jsonObject.put(Key.ORIGINAL_INITIATOR, externalizeOperatingPartyDto(request.getOriginalInitiator()));
    jsonObject.put(Key.INITIATOR, externalizeOperatingPartyDto(request.getInitiator()));
    jsonObject.put(Key.EXECUTOR, externalizeOperatingPartyDto(request.getExecutor()));
    return jsonObject;
  }

  @Override
  public Dto internalize(String externalizedDto) {
    // TODO Auto-generated method stub
    return null;
  }

}
