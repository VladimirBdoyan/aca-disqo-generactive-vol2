package am.aca.generactive;

import am.aca.generactive.mock.ConfigurationMock;
import am.aca.generactive.mock.GroupMock;
import am.aca.generactive.mock.ItemMock;
import am.aca.generactive.model.Basket;
import am.aca.generactive.model.BasketItem;
import am.aca.generactive.model.Group;
import am.aca.generactive.model.Item;
import am.aca.generactive.repository.GroupRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Group> groups = GroupMock.getGroupsHierarchy();
        List<Item> items = ItemMock.getItems(4);

        groups.get(groups.size() - 1).addItems(items);

        GroupRepository groupRepository = GroupRepository.getInstance();
        groupRepository.addGroupAll(groups);

        for (Group group : groupRepository.getGroupsHierarchy()) {
            group.print(0);
        }

        Basket basket = new Basket();
        for (Item item : items) {
            basket.add(new BasketItem(item, ConfigurationMock.getConfiguration()));
        }

        basket.print();
    }
}
