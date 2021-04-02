package Program;

import java.util.ArrayList;

public class Store {

    ArrayList<Group> groups; //список груп товарів

    /**
     * Конструктор створення складу
     */
    public Store(){
        groups = new ArrayList<>(1);
    }

    /**
     * Додає групу товарів до складу
     * @param group група товарів, яку необхідно додати
     * @return true, якщо додавання успішне, в іншому випадку - false
     */
    public boolean addGroup(Group group){
        return groups.add(group);
    }

    /**
     * Видаляє групу товарів зі складу
     * @param name назва групи товаів, яку необхідно видалити
     * @return true, якщо видалення успішне, в іншому випадку - false
     */
    public boolean deleteGroup(String name){
        return groups.remove(new Group(name));
    }

    public Group getGroup(String name){
        try {
            return groups.get(groups.indexOf(new Group(name)));
        } catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    /**
     * Повертає список усіх продуктів складу
     */
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>(1);
        for (Group group: groups){
            for (Product product: group.products){
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String toString(){
        String str = "";
        for (Group group: groups){
            str += group +"\n\n";
        }
        return str;
    }


}
