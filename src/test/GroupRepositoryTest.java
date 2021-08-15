package test;

import am.aca.generactive.model.Group;
import am.aca.generactive.repository.GroupRepository;
import am.aca.generactive.util.idgenerator.GroupIdGenerator;
import am.aca.generactive.util.idgenerator.IdGenerator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupRepositoryTest {
    GroupRepository groupRepository = GroupRepository.getInstance();
    @Test
    public void testAdd(){
        int sizeBefore = groupRepository.size();
        int currentIdBefore = GroupIdGenerator.getCURRENT();
        Group group = new Group(GroupIdGenerator.getNextId(), "ss");
        groupRepository.addGroup(group);
        int sizeAfter = groupRepository.size();
        int currentIdAfter = GroupIdGenerator.getCURRENT();
        assertEquals(sizeBefore+1,sizeAfter);
        assertEquals(currentIdBefore+1,currentIdAfter);
    }
    @Test
    public void testFindGroupById(){
        Group group = new Group(GroupIdGenerator.getNextId(), "ss");
        groupRepository.addGroup(group);
        assertEquals(group, groupRepository.findGroupById(GroupIdGenerator.getCURRENT()));
    }

    @Test
    public void testFindGroupByName(){
        String name = "ss";
        Group group = new Group(GroupIdGenerator.getNextId(), name);
        groupRepository.addGroup(group);
        assertEquals(group, groupRepository.findGroupByName(name));
    }

}
