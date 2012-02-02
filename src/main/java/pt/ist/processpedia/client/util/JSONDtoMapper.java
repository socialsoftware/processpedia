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
import pt.ist.processpedia.shared.dto.domain.ProcessDtoImpl;
import pt.ist.processpedia.shared.dto.domain.QueueDtoImpl;
import pt.ist.processpedia.shared.dto.domain.RequestDto;
import pt.ist.processpedia.shared.dto.domain.RequestDtoImpl;
import pt.ist.processpedia.shared.dto.domain.OperatingParty;
import pt.ist.processpedia.shared.dto.domain.UserDtoImpl;

public class JSONDtoMapper implements DtoMapper {

  private static class Key {
    private static String OID = "oid";
    private static String NAME = "name";
    private static String SUBJECT = "subject";
    private static String TITLE = "title";
    private static String INITIATOR = "initiator";
    private static String EXECUTOR = "executor";
    private static String CREATION_TIMESTAMP = "creation";
    private static String LAST_UPDATE_TIMESTAMP = "last-update";
    private static String PROCESS = "process";
    private static String PUBLISHED_QUEUES = "published-queues";
  }
  
  private JSONValue parse(String jsonString) {
    return JSONParser.parseStrict(jsonString);
  }
  
  @Override
  public String externalize(Dto dto) {
    if(dto instanceof OperatingParty) {
      return externalizeUserDto((OperatingParty)dto).toString();
    } else if(dto instanceof RequestDto) {
      return externalizeRequestDto((RequestDto)dto).toString();
    }
    
    return null;
  }

  public OperatingParty internalizeUserDto(String externalizedDto) {
    return internalizeUserDto((JSONObject)parse(externalizedDto));
  }
  
  public OperatingParty internalizeUserDto(JSONObject userJsonObject) {
    Long oid = new Long(((JSONString)userJsonObject.get(Key.OID)).stringValue());
    String name = ((JSONString)userJsonObject.get(Key.NAME.toString())).stringValue();
    return new UserDtoImpl(oid, name);
  }
  
  public JSONObject externalizeUserDto(OperatingParty userDto) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(Key.OID, new JSONNumber(userDto.getOid()));
    jsonObject.put(Key.NAME, new JSONString(userDto.getName()));
    return jsonObject;
  }
  
  public RequestDto internalizeRequestDto(String externalizedDto) {
    JSONObject jsonObject = (JSONObject)parse(externalizedDto);
    Long oid = new Long(((JSONString)jsonObject.get(Key.OID)).stringValue());
    String subject = ((JSONString)jsonObject.get(Key.SUBJECT)).stringValue();
    OperatingParty initiatorDto = internalizeUserDto((JSONObject)jsonObject.get(Key.INITIATOR));
    OperatingParty executorDto = internalizeUserDto((JSONObject)jsonObject.get(Key.EXECUTOR));
    Set<QueueDtoImpl> publishedQueueDtoSet = internalizeQueueDtoSet((JSONArray)jsonObject.get(Key.PUBLISHED_QUEUES));
    Date creationTimestamp = new Date(new Long(((JSONNumber)jsonObject.get(Key.CREATION_TIMESTAMP)).toString()));
    Date lastUpdateTimestamp = new Date(new Long(((JSONNumber)jsonObject.get(Key.LAST_UPDATE_TIMESTAMP)).toString()));
    ProcessDtoImpl processDto = internalizeProcessDto((JSONObject)jsonObject.get(Key.PROCESS));
    return new RequestDtoImpl(oid, subject, initiatorDto, executorDto, publishedQueueDtoSet, creationTimestamp, lastUpdateTimestamp, processDto);
  }
  
  public QueueDtoImpl internalizeQueueDto(String externalizedDto) {
    return internalizeQueueDto((JSONObject)parse(externalizedDto));
  }
  
  public QueueDtoImpl internalizeQueueDto(JSONObject queueDtoJsonObject) {
    Long oid = new Long(((JSONNumber)queueDtoJsonObject.get(Key.OID)).toString());
    String title = ((JSONString)queueDtoJsonObject.get(Key.TITLE.toString())).stringValue();
    return new QueueDtoImpl(oid, title);
  }
  
  public Set<QueueDtoImpl> internalizeQueueDtoSet(JSONArray queueSetJsonArray) {
    Set<QueueDtoImpl> queueDtoSet = new HashSet<QueueDtoImpl>();
    for(int i = 0; i < queueSetJsonArray.size(); i++) {
      queueDtoSet.add(internalizeQueueDto(((JSONObject)queueSetJsonArray.get(i))));
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
    return new ProcessDtoImpl(oid, title);
  }
  
  public ProcessDtoImpl internalizeProcessDto(String externalizedDto) {
    return internalizeProcessDto((JSONObject)parse(externalizedDto));
  }
  
  private JSONObject externalizeRequestDto(RequestDto requestDto) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(Key.OID, new JSONNumber(requestDto.getOid()));
    jsonObject.put(Key.SUBJECT, new JSONString(requestDto.getSubject()));
    jsonObject.put(Key.INITIATOR, externalizeUserDto(requestDto.getInitiatorDto()));
    return jsonObject;
  }

  @Override
  public Dto internalize(String externalizedDto) {
    // TODO Auto-generated method stub
    return null;
  }

  
  
}
