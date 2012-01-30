package pt.ist.processpedia.server.domain;

import pt.ist.processpedia.shared.exception.ProcesspediaException;

public abstract class Party extends Party_Base {

  public void init(String name, String avatarUrl) throws ProcesspediaException {
    setName(name);
    setAvatarUrl(avatarUrl);
  }
}
