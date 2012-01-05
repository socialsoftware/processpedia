package pt.ist.processpedia.server.domain;

public class TagManager extends TagManager_Base {

  public Tag getTagForKeyword(String keyword) {
    for(Tag existingTag : getExistingTagSet()) {
      if(existingTag.hasKeyword(keyword)) {
        return existingTag;
      }
    }
    Tag newTag = new Tag(new Concept(), keyword);
    addExistingTag(newTag);
    return newTag;
  }
}
