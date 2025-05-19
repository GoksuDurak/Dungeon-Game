import java.util.Scanner;

public class Inventory {

    private int capacity;
    private Item[] items;
    private int itemCount;
    public Inventory(int capacity)
    {
        this.capacity = capacity;
        items = new Item[capacity];
    }

    public void addItem(Item item)
    {
        boolean found = false;
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] != null)
            {
                if(items[i].getItemName().equals(item.getItemName()) && items[i].getItemID() == item.getItemID())
                {
                    found = true;
                }
            }
        }
        int itemId = 0;
        int itemQuantity = 0;
        if(!found) // ürün yoksa
        {
            if(isFull())
            {
                System.out.println("The inventory is full");
            }else {
                for (int i = 0; i < items.length; i++) {
                    if (items[i] == null) {
                        items[i] = item;
                        break;
                    }
                }
                itemCount++;
            }
        }else
        {
            itemId = item.getItemID();
            for (int i = 0; i < items.length; i++)
            {
                if(items[i] != null) {
                    if (items[i].getItemID() == itemId) // itemi bulduk
                    {
                        itemQuantity = items[i].getQuantity();
                        items[i].setQuantity(itemQuantity + item.getQuantity());
                    }
                }
            }
        }

    }
    public void resetInventory(){
        items = new Item[capacity];
    }
    public void useItems(Item item, int quantity)
    {
        for (int i = 0; i < items.length; i++)
        {
            if(items[i] == item)
            {
                if(items[i].getQuantity() >= quantity) {
                    items[i].setQuantity(items[i].getQuantity() - quantity); // bu kadar kullandık
                    if(items[i].getQuantity() == 0)
                    {
                        items[i] = null;
                        for (int j = i + 1; j < items.length; j++)
                        {
                            items[j - 1] = items[j];
                        }
                    }
                }else
                {
                    System.out.println("You have not enough item");
                }
            }
        }
    }
    public void deleteItem(String name,Scanner scanner)
    {
        int quantity = 0;
        boolean found = false;
        boolean delete = false;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (name.equals(items[i].getItemName())) {
                    quantity = items[i].getQuantity(); // arayacağımız itemin id aldık
                    found = true; //item bulundu
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Item not found");
        } else {
            while (true) {
                System.out.println("You have " + quantity + " " + name + "\nDo you want to delete this item");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                    delete = true;
                    break;
                } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                    break;
                }
            }
            if (delete) {
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null) {
                        if (name.equals(items[i].getItemName())) {
                            items[i] = null;
                            for (int j = i + 1; j < items.length; j++) {
                                items[j - 1] = items[j];
                            }
                            itemCount--;
                            break;
                        }
                    }
                }
            }
        }

    }
    public Item searchItem(String name,int id)
    {
        int quantity = 0;
        boolean found = false;
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] != null) {
                if (items[i].getItemID() == id) {
                    quantity = items[i].getQuantity(); // arayacağımız itemin id aldık
                    System.out.println("You have " + quantity + " "+ name);
                    return items[i];
                }
            }
        }
        if(!found)
        {
            System.out.println("Item not found");
        }
        return null;
    }
    public boolean isFull()
    {
        return itemCount == capacity;
    }

    public int size()
    {
        return itemCount;
    }
    public Item[] getItems()
    {
        return items;
    }
    public void setItems(Item[] items)
    {
        this.items = items;
    }


}
