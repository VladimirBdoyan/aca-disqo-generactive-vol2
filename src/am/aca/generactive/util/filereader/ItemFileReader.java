package am.aca.generactive.util.filereader;

import am.aca.generactive.model.StockItem;
import am.aca.generactive.repository.GroupRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemFileReader {
    public void readScv(String url) {
        File file = new File(url);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String[] itemData = sc.nextLine().split(",");
            int id = Integer.parseInt(itemData[0]);
            int basePrice = Integer.parseInt(itemData[1]);
            String name = itemData[2];
            String imageUrl = itemData[3];
            int groupId = Integer.parseInt(itemData[4]);
            StockItem item = new StockItem(id, basePrice, name);
            item.setImageUrl(imageUrl);
            GroupRepository.getInstance().findGroupById(groupId).addItem(item);
        }
    }
}
