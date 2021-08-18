package test;

import am.aca.generactive.model.Group;
import am.aca.generactive.model.Item;
import am.aca.generactive.model.StockItem;
import am.aca.generactive.repository.GroupRepository;
import am.aca.generactive.repository.ItemRepository;
import am.aca.generactive.util.filereader.ItemFileReader;
import am.aca.generactive.util.idgenerator.GroupIdGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.*;

public class TestCRUD {

    GroupRepository groupRepository = GroupRepository.getInstance();
    Item item ;

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
        for (int i = 1; i <= 3; i++) {
            groupRepository.addGroup(new Group(GroupIdGenerator.getNextId(), "Group " + i));
        }
        ItemFileReader.readScv("src/resources/Item.csv");

        assertEquals(3, groupRepository.size());

        // Test the show if Items add in Repository by their constructor

        for (int i = 1; i <= 9; i++) {
            assertEquals(i, ItemRepository.getInstance().getItems().get(i - 1).getId());
            assertEquals(i*100, ItemRepository.getInstance().getItems().get(i - 1).getBasePrice());
            assertEquals("Test" + i, ItemRepository.getInstance().getItems().get(i - 1).getName());
        }
    }
}
