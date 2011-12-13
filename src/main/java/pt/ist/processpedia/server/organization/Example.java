package pt.ist.processpedia.server.organization;

public class Example {

  
  public static void main(String[] args) {
    OrganizationalUnit ist = new OrganizationalUnit("IST");
    OrganizationalUnit dei = new OrganizationalUnit("DEI");
    OrganizationalUnit dsi = new OrganizationalUnit("DSI");
    OrganizationalUnit alunosDoutoramento = new OrganizationalUnit("Alunos Doutoramento");
    
    Person david = new Person("David Martinho", "d@g.com", "avatar", "ist155371");
    Person rito = new Person("António Rito Silva", "email", "avatar", "ist3943");
    
    ist.addChildParty(dei);
    ist.addChildParty(dsi);
    
    dsi.addChildParty(david);
    dei.addChildParty(rito);
    dei.addChildParty(alunosDoutoramento);

    alunosDoutoramento.addChildParty(david);
    
    System.out.println(ist);
    
  }

}
