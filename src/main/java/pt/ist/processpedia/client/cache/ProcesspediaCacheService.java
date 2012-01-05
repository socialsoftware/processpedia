package pt.ist.processpedia.client.cache;

import pt.ist.processpedia.client.service.DataSwitch;

public interface ProcesspediaCacheService extends DataSwitch {

  void clear();
  
}
