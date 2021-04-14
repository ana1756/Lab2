package Program;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {

    //повертає, скільки на яку суму грошей міститься товару в групі
    public static long calculateMoney(Group group){
        long sumMoney = 0; //сума грошей
        if( group.getProducts()!=null) {
            for (int i = 0; i < group.getProducts().size(); i++)
                sumMoney += (long)(group.getProducts().get(i).getPrice() * group.getProducts().get(i).getNumber());
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

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("D:/"+ group.getName() + ".txt")))
        {
            bw.write(group.getName().toUpperCase());
            bw.newLine();
            if(group.getProducts()!=null) {
                for (int d = 0; d<group.getProducts().size(); d++){
                    bw.newLine();
                    bw.write(group.getProducts().get(d).getName().toUpperCase()); bw.newLine();
                    bw.write("Ціна: " + group.getProducts().get(d).getPrice()); bw.newLine();
                    bw.write("Кількість: " + Math.round(group.getProducts().get(d).getNumber())); bw.newLine();
                    bw.write("Виробник: " + group.getProducts().get(d).getBrand()); bw.newLine();
                    bw.write("Опис "+group.getProducts().get(d).getDescription()); bw.newLine();
                }
                bw.newLine();
                bw.write("Товари на суму: " +calculateMoney(group)+ " гривень");
                bw.newLine();
                bw.write( "Кількість одиниць товару: " + calculateQuantity(group));
            }
            else bw.write("Ця група пуста!");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    //записує магазин в файл
    public static void addStoreToFile(Store store){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter( "D:/"+"Store.txt")))
        {
            String s =  "Магазин ";  //всі товари
            bw.write(s);
            bw.newLine();
            long sumMoney = 0; //сума грошей
            double q = 0; //кількість одиниць товару
            for(int i = 0; i<store.getGroups().size(); i++) {
                if(store.getGroups().get(i).getProducts()!=null) {
                    bw.newLine();
                    bw.write(store.getGroups().get(i).getName().toUpperCase());
                    bw.newLine();
                    for (int d = 0; d<store.getGroups().get(i).getProducts().size(); d++){
                        bw.newLine();
                        bw.write(store.getGroups().get(i).getProducts().get(d).getName().toUpperCase()); bw.newLine();
                        bw.write("Ціна: " + store.getGroups().get(i).getProducts().get(d).getPrice()); bw.newLine();
                        bw.write("Кількість " + Math.round(store.getGroups().get(i).getProducts().get(d).getNumber())); bw.newLine();
                        bw.write("Виробник: " + store.getGroups().get(i).getProducts().get(d).getBrand()); bw.newLine();
                        bw.write("Опис "+store.getGroups().get(i).getProducts().get(d).getDescription()); bw.newLine();
                    }
                    String[] str = s.split("\\s{3}");
                    sumMoney += calculateMoney(store.getGroups().get(i));
                    q += calculateQuantity(store.getGroups().get(i));

                }
                else {
                    bw.newLine();
                    bw.write(store.getGroups().get(i).getName() + ": ця група пуста");
                    bw.newLine();
                }
            }
            bw.newLine();
            bw.write("В магазині товари на суму: " + sumMoney + " гривень");
            bw.newLine();
            bw.write( "Кількість одиниць товару: " + q);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}