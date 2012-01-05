package pt.ist.processpedia.client.storage;

public interface BrowserStorage {

  public void setValue(String key, String value);
  public void setValue(String key, String value, int ttl);

  public String getValue(String key);
  
  public void clear();
  
  public void setDefaultTtl(int ttlMillis);

  public int getItemCount();
  
}
