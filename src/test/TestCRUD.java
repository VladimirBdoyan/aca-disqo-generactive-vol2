package test;

import am.aca.generactive.model.Group;
import am.aca.generactive.model.Item;
import am.aca.generactive.repository.GroupRepository;
import am.aca.generactive.repository.ItemRepository;
import am.aca.generactive.util.filereader.ItemFileReader;
import am.aca.generactive.util.idgenerator.GroupIdGenerator;
import org.junit.jupiter.api.Test;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

public class TestCRUD {
    static int GROUPING = 3;
    static String DIRECORY = "src/resources/Item.csv";

    GroupRepository groupRepository = GroupRepository.getInstance();

    @Test
    public void testIdGeneration() {
        int currentIdBefore = GroupIdGenerator.getCURRENT();
        Group group = new Group(GroupIdGenerator.getNextId(), "group1");
        int currentIdAfter = GroupIdGenerator.getCURRENT();
        assertEquals(currentIdBefore + 1, currentIdAfter);
    }

    @Test
    public void testReadCsvFileDirectory() {
        assertThrows(RuntimeException.class, () -> ItemFileReader.readScv("wrong Directory"));
    }

    @Test
    void testReadCsvAndCreateItem() {
        GroupIdGenerator.setCURRENT(0);
        for (int i = 1; i <= GROUPING; i++) {
            groupRepository.addGroup(new Group(GroupIdGenerator.getNextId(), "Group " + i));
        }
        ItemFileReader.readScv(DIRECORY);

        assertEquals(GROUPING, groupRepository.size());

        // Test the show if Items add in Repository by their constructor

        Optional<Item> o = ItemRepository.getInstance().getItems().stream().findFirst();
        if (o.isPresent()) {
            Item item = o.get();
            assertEquals(1, item.getId());
            assertEquals(100, item.getBasePrice());
            assertEquals("Test1", item.getName());
        }
    }
}