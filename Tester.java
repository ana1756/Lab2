import Program.*;
import Utility.DataInput;

import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {

        //створюэму товари
        Product TV = new Product("Телевізор", 37000, 5, "LG", "Діагональ екрана 55 / Роздільна здатність 3840x2160");
        Product fridge = new Product("Холодильник", 19999, 15, "BOSCH", "двокамерний / 400 л");
        Product washingMachine = new Product("Пральна машина", 18300, 22, "Samsung", "7 кг / 84.8 x 59.8 x 59 см");

        //створюємо групу товарів
        Group appliances = new Group("Техніка", "якийсь опис");

        //додаємо вже створені товари до групи
        appliances.addProduct(TV);
        appliances.addProduct(fridge);
        appliances.addProduct(washingMachine);

        //Виводимо список товарів
        //System.out.println(appliances);

        //створюємо інші товари
        Product piano = new Product("Фортепіано", 130000, 2, "Steinway & sons", "Розміри (132-152-68 см) / Вага (305 кг)");
        Product cello = new Product("Віолончель", 21000, 4, "STENTOR", "Розмір 4/4");
        Product violin = new Product("Скрипка", 29999, 15, "Yamaha", "Розмір 4/4");

        //--------------------------------------------------------------------------------------------------

        //створюємо іншу групу
        Group instruments = new Group("Музичні інструменти", "якийсь опис");

        //додаємо товари до іншої групи
        instruments.addProduct(piano);
        instruments.addProduct(cello);
        instruments.addProduct(violin);

        //Виводимо список товарів
        //System.out.println(instruments);

        //--------------------------------------------------------------------------------------------------

        //створюємо склад-магазин
        Store store = new Store();

        //додаємо до складу вже створені групи товарів
        store.addGroup(appliances);
        store.addGroup(instruments);

        //Виводимо список усіх товарів складу
        //System.out.println(store);

        //--------------------------------------------------------------------------------------------------

        //Хочемо вивести інформацію про товар з назвою "Холодильник" із групи "Техніка"
        //System.out.println(store.getGroup("Техніка").getProduct("Холодильник"));

        //Треба видалити товар з назвою "Холодильник" із групи "Техніка"
        //store.getGroup("Техніка").deleteProduct("Холодильник");
        //System.out.println(store.getGroup("Техніка"));


    }
}
