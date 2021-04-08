package Program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {
  
   public static String calculateGroup(Group group){
        String s = "";
        if( group.getProducts()!=null) s = group.getName() + ": \n\n" + group.getProducts().toString();  //всі товари
        else s = "Категорія товарів " + group.getName() + " не містить додного товару";
        return s;
    }

  //повертає, скільки на яку суму грошей міститься товару в групі 
    public static double calculateMoney(Group group){
        double sumMoney = 0; //сума грошей
        if( group.getProducts()!=null) {
            for (int i = 0; i < group.getProducts().size(); i++)
                sumMoney += group.getProducts().get(i).getPrice() * group.getProducts().get(i).getNumber();
        }
        else sumMoney = 0;
        return sumMoney;
    }

  //повертає кількість одиниць товару в групі
    public static int calculateQuantity(Group group){
        int q = 0; //сума грошей
        if( group.getProducts()!=null) {
            for (int i = 0; i < group.getProducts().size(); i++)
                q += group.getProducts().get(i).getNumber();
        }
        else q = 0;
        return q;
    }

  //записує все в файл 
    public static void addGroupToFile(Group group){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(group.getName() + ".txt")))
        {
            bw.write(calculateGroup(group));
            bw.write("\nТовари на суму: " + calculateMoney(group) + " гривень" + "\nКількість одиниць товару: " +
                    calculateQuantity(group));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

  //записує всі групи і товари в них, на яку суму міститься товарів і кількість одиниць товару 
    public static String calculateStore(Store myStore){
        String s =  "Магазин \n";  //всі товари
        double sumMoney = 0; //сума грошей
        double q = 0; //кількість одиниць товару
        for(int i = 0; i<myStore.getGroups().size(); i++) {
            if(myStore.getGroups().get(i).getProducts()!=null) {
                s = s + "\n"+calculateGroup(myStore.getGroups().get(i));
                sumMoney += calculateMoney(myStore.getGroups().get(i));
                q += calculateQuantity(myStore.getGroups().get(i));
            }
            else s = s+"\n"+myStore.getGroups().get(i).getName()+ ": ця група пуста";
        }

        s = s + "\n\nВ магазині товари на суму: " + sumMoney + " гривень" + "\nКількість одиниць товару: " + q;
        return s;
    }

  //записує це все в файл 
    public static void addStoreToFile(Store store){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter( "Store.txt")))
        {
            bw.write(calculateStore(store));
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
