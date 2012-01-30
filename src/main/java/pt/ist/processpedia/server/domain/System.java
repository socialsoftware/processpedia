package pt.ist.processpedia.server.domain;

import pt.ist.processpedia.shared.exception.ProcesspediaException;

public class System extends System_Base {

  public System(String name, String avatarUrl) throws ProcesspediaException {
    init(name, avatarUrl);
  }
}
