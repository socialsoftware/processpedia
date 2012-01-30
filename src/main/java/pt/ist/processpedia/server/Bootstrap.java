/**
 * Copyright 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/

package pt.ist.processpedia.server;

import jvstm.Atomic;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.domain.Processpedia;
import pt.ist.processpedia.server.domain.TagManager;
import pt.ist.processpedia.shared.exception.ProcesspediaException;

public class Bootstrap {

  public static void init() {
    FenixFramework.initialize(new Config() {{
      domainModelPath = PropertiesManager.getProperty("dml.filename");
      dbAlias = PropertiesManager.getProperty("sql.alias");
      dbUsername = PropertiesManager.getProperty("sql.username");
      dbPassword = PropertiesManager.getProperty("sql.password");
      rootClass = Processpedia.class;
    }});
    setup();
  }
  
  @Atomic
  public static void setup() {
    //OrganizationConnector organizationConnector = new FenixOrganizationConnector();
    //OrganizationalUnit rootOrganizationalUnit = organizationConnector.loadOrganizationData();
    //Processpedia.getInstance().installOrganizationalUnit(rootOrganizationalUnit);
    
    try {
      Processpedia.getInstance().createUserWithPasswordCredentialInfo("David Martinho", "d@g.com", "avatar", "xpto");
      Processpedia.getInstance().setTagManager(new TagManager());
    } catch (ProcesspediaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
