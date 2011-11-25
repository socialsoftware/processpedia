package pt.ist.processpedia.server.util;

import pt.ist.fenixframework.DomainObject;

public class Urn {

  public static String getUrn(DomainObject domainObject) {
    return "urn:pt.ist.processpedia:"+domainObject.getClass().getSimpleName().toLowerCase()+":"+domainObject.getOid();
  }

  public static long getOid(String urn) {
    String[] tokens = urn.split(":");
    return Long.parseLong(tokens[3]);
  }

}
