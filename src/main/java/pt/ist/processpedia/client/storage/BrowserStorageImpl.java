package pt.ist.processpedia.client.storage;

import java.util.HashMap;
import java.util.Map;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Timer;

public class BrowserStorageImpl implements BrowserStorage {

  private static final BrowserStorage INSTANCE = new BrowserStorageImpl();

  private static int DEFAULT_TTL_MS = 30000;
  
  private Map<String,Timer> currentTimerMap = new HashMap<String,Timer>();
  
  public BrowserStorageImpl() {}
  
  public void setValue(String key, String value) {
    setValue(key, value, DEFAULT_TTL_MS);
  }
  
  public void setValue(final String key, String value, int ttl) {
    Storage.getLocalStorageIfSupported().setItem(key, value);
    Timer existingTimer = currentTimerMap.get(key);
    if(existingTimer != null) {
      existingTimer.cancel();
      existingTimer.schedule(ttl);
    } else {
      Timer timer = new Timer() {
        public void run() {
          Storage.getLocalStorageIfSupported().removeItem(key);
        }
      };
      timer.schedule(ttl);
      currentTimerMap.put(key, timer);
    }
  }

  public String getValue(String key) {
    return Storage.getLocalStorageIfSupported().getItem(key);
  }
  
  public void clear() {
    Storage.getLocalStorageIfSupported().clear();
  }

  public static BrowserStorage getInstance() {
    return INSTANCE;
  }

  @Override
  public void setDefaultTtl(int ttlMillis) {
    DEFAULT_TTL_MS = ttlMillis;
  }

  @Override
  public int getItemCount() {
    return Storage.getLocalStorageIfSupported().getLength();
  }
}
