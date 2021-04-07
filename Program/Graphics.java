
package Program;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;

public class Graphics extends JFrame {

    private JPanel mainPanel;
    private JPanel groupPanel;
    private JPanel productPanel;
    private JPanel nothingFoundPanel;
    private JPanel addCategoryPanel;
    private JPanel addProductPanel;
    private boolean searchFromMainPanel;
    private Store myStore;
    private ArrayList<Group> groups;
    private ArrayList<Product> allProducts;

    public Graphics(Store store) {
        super("Store");
        this.myStore = store;
        this.groups = myStore.getGroups();
        this.allProducts = myStore.getProducts();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700,500));
        init();
        this.add(mainPanel);
        pack();
        this.setVisible(true);
    }

    private void init() {
        mainPanel = new JPanel(new BorderLayout()); //Головна сторінка
        mainPanel.setBackground(new Color(198, 233, 243));

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(198, 233, 243));

        JPanel title = new JPanel(new FlowLayout());
        title.setBackground(new Color(198, 233, 243));

        JTextArea searchText = new JTextArea(1,20);
        searchText.setFont(new Font(Font.SERIF,Font.PLAIN,25));
        String searchGroup = searchText.getText();

        JLabel store = new JLabel("Store");
        store.setForeground(new Color(47, 46, 50));
        store.setFont(new Font(Font.SERIF,Font.PLAIN,30));

        title.add(store);

        searchPanel.add(searchText);

        JPanel northPanel = new JPanel(new GridLayout(3,1));

        northPanel.add(title);
        northPanel.add(searchPanel);

        JButton search = new JButton("Пошук"); //Шукає серед всіх груп та всіх продуктів
        search.setPreferredSize(new Dimension(85,33));
        search.setBackground(new Color(128, 118, 146));
        search.setForeground(new Color(255, 253, 253));
        search.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchFromMainPanel = true;
                String text = searchText.getText();
                int indexOfGroup = -1;
                int indexOfProduct = -1;
                for (int i = 0; i < groups.size(); i++)
                if (text.equals(groups.get(i).getName())) {
                    indexOfGroup = i;
                    break;
                }

                if(indexOfGroup != -1) {
                    initGroupPanel(indexOfGroup);
                    showGroupPanel();
                }
                else {
                    for(int i = 0; i < allProducts.size(); i++)
                        if(text.equals(allProducts.get(i).getName())) {
                            indexOfProduct = i;
                            break;
                        }

                    if(indexOfProduct != -1) {
                        for(int i = 0; i < groups.size(); i++) {
                            for(int j = 0; j < groups.get(i).getProducts().size(); j++) {
                                if(text.equals(groups.get(i).getProducts().get(j).getName())) {
                                    indexOfGroup = i;
                                    break;
                                }
                            }
                        }

                        initProductPanel(indexOfProduct, indexOfGroup);
                        showProductPanel();
                    }
                    else
                        goToNothingFoundPanelFromMain(indexOfGroup);

                }
            }
        });

        searchPanel.add(search);



        JPanel categoriesTitle = new JPanel(new FlowLayout());
        categoriesTitle.setBackground(new Color(198, 233, 243));

        JLabel types = new JLabel("Всі категорії");
        types.setForeground(new Color(47, 46, 50));
        types.setFont(new Font(Font.SERIF,Font.PLAIN,30));

        categoriesTitle.add(types);

        northPanel.add(categoriesTitle);

        mainPanel.add(northPanel, "North");

        JPanel centrePanel = new JPanel(new GridLayout(1,1));
        centrePanel.setBackground(new Color(198, 233, 243));

        JPanel categories = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categories.setBackground(new Color(198, 233, 243));

        JTextArea categoriesList = new JTextArea(8,40);
        categoriesList.setEditable(false);
        categoriesList.setFont(new Font(Font.SERIF,Font.PLAIN,20));

        JScrollPane scroll = new JScrollPane(categoriesList);

        String s = "";
        for(int i = 0; i < groups.size()-1; i++)
            s += groups.get(i).getName() + "\n";
        s += groups.get(groups.size()-1).getName();

        categoriesList.setText(s);

        categories.add(scroll);

        centrePanel.add(categories);

        mainPanel.add(centrePanel,"Center");

        JButton addCat = new JButton("Додати"); //Переносить на сторінку "Додати категорію"
        addCat.setPreferredSize(new Dimension(120,25));
        addCat.setBackground(new Color(128, 118, 146));
        addCat.setForeground(new Color(255, 253, 253));
        addCat.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });

        JButton saveData = new JButton("Зберегти"); //Поки нічого не робить
        saveData.setPreferredSize(new Dimension(120,25));
        saveData.setBackground(new Color(128, 118, 146));
        saveData.setForeground(new Color(255, 253, 253));
        saveData.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel space = new JPanel(new FlowLayout());
        space.setBackground(new Color(198, 233, 243));
        space.setPreferredSize(new Dimension(410,25));


        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setBackground(new Color(198, 233, 243));
        southPanel.add(addCat);
        southPanel.add(space);
        southPanel.add(saveData);

        mainPanel.add(southPanel, "South");
    }

    private void showGroupPanel() { //переносить на сторінку певної групи
        this.remove(mainPanel);
        this.add(groupPanel);
        pack();
    }

    private void initGroupPanel(int index) {
        int indexOfGroup = index;

        groupPanel = new JPanel(new BorderLayout()); //Сторінка групи
        groupPanel.setBackground(new Color(198, 233, 243));

        ArrayList<Product> oneGroupProducts = groups.get(index).getProducts();

        JPanel northPanel = new JPanel(new GridLayout(3,1));
        northPanel.setBackground(new Color(198, 233, 243));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(198, 233, 243));

        JLabel title = new JLabel(groups.get(index).getName());
        title.setForeground(new Color(47, 46, 50));
        title.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        titlePanel.add(title);

        JPanel searchPanel = new JPanel((new FlowLayout()));
        searchPanel.setBackground(new Color(198, 233, 243));

        JTextArea searchText = new JTextArea(1,20);
        searchText.setFont(new Font(Font.SERIF,Font.PLAIN,25));

        searchPanel.add(searchText);

        JButton search = new JButton("Пошук"); //Шукає серед продуктів певної групи
        search.setPreferredSize(new Dimension(85,33));
        search.setBackground(new Color(128, 118, 146));
        search.setForeground(new Color(255, 253, 253));
        search.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFromMainPanel = false;
                String text = searchText.getText();
                int indexOfProduct = -1;
                for(int i = 0; i < oneGroupProducts.size(); i++)
                    if(text.equals(oneGroupProducts.get(i).getName())) {
                        indexOfProduct = i;
                        break;
                    }

                if(indexOfProduct != -1) {
                    initProductPanel(indexOfProduct, indexOfGroup);
                    showProductPanelFromCategoryPanel();
                }
                else
                    goToNothingFoundPanelFromGroup(indexOfGroup);

            }

        });
        searchPanel.add(search);

        JPanel productsTitle = new JPanel(new FlowLayout());
        productsTitle.setBackground(new Color(198, 233, 243));

        JLabel products = new JLabel("Вся продукція");
        products.setForeground(new Color(47, 46, 50));
        products.setFont(new Font(Font.SERIF,Font.PLAIN,30));

        productsTitle.add(products);

        JPanel centrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrePanel.setBackground(new Color(198, 233, 243));

        JTextArea productsList = new JTextArea(8,40);
        productsList.setEditable(false);
        productsList.setFont(new Font(Font.SERIF,Font.PLAIN,20));

        JScrollPane scroll = new JScrollPane(productsList);

        String s = "";
        if (oneGroupProducts!=null) {
            for (int i = 0; i < oneGroupProducts.size(); i++) {
                s += oneGroupProducts.get(i).getName() + "\n";
               // s += oneGroupProducts.get(oneGroupProducts.size() - 1).getName();
            }
            productsList.setText(s);
        }
        else productsList.setText("Товарів нема");


        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.setBackground(new Color(198, 233, 243));

        JButton returnBack = new JButton("Назад");
        returnBack.setPreferredSize(new Dimension(120,25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToTheMainPanel();
            }
        });

        JButton addProduct = new JButton("Додати"); //Переносить на сторінку "Додати продукт"
        addProduct.setPreferredSize(new Dimension(120,25));
        addProduct.setBackground(new Color(128, 118, 146));
        addProduct.setForeground(new Color(255, 253, 253));
        addProduct.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct(indexOfGroup);
            }
        });

        JButton saveData = new JButton("Зберегти"); //Поки нічого не робить
        saveData.setPreferredSize(new Dimension(120,25));
        saveData.setBackground(new Color(128, 118, 146));
        saveData.setForeground(new Color(255, 253, 253));
        saveData.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        JButton deleteCategory = new JButton("Видалити"); //Видаляє групу і повертається на головну панель :)
        deleteCategory.setPreferredSize(new Dimension(120,25));
        deleteCategory.setBackground(new Color(128, 118, 146));
        deleteCategory.setForeground(new Color(255, 253, 253));
        deleteCategory.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        deleteCategory.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                myStore.deleteGroup(groups.get(indexOfGroup).getName());
                goToTheMainPanel();
            }
        });

        northPanel.add(titlePanel);
        northPanel.add(searchPanel);
        northPanel.add(productsTitle);

        centrePanel.add(scroll);

        southPanel.add(returnBack);
        southPanel.add(saveData);
        southPanel.add(addProduct);
        southPanel.add(deleteCategory);

        groupPanel.add(northPanel,"North");
        groupPanel.add(centrePanel, "Center");
        groupPanel.add(southPanel, "South");
    }

    private void initProductPanel(int indexOfProduct, int indexOfGroup) {
        productPanel = new JPanel(new BorderLayout());
        productPanel.setBackground(new Color(198, 233, 243));

        JPanel northPanel = new JPanel(new GridLayout(2,1));
        northPanel.setBackground(new Color(198, 233, 243));

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(new Color(198, 233, 243));

        JLabel title = new JLabel("Результати пошуку");
        title.setForeground(new Color(47, 46, 50));
        title.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        titlePanel.add(title);

        Product p = groups.get(indexOfGroup).getProducts().get(indexOfProduct);

        JLabel product = new JLabel (p.toString());
        productPanel.add(product);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.setBackground(new Color(198, 233, 243));

        JButton returnBack = new JButton("Назад");
        returnBack.setPreferredSize(new Dimension(120,25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchFromMainPanel)
                    goToTheMainPanel();
                else
                    goToTheGroupPanel(indexOfGroup);
            }
        });

        Group g = groups.get(indexOfGroup);

        JButton deleteProduct = new JButton("Видалити"); //Видаляє продукт і повертається на сторінку з іншими продуктами :)
        deleteProduct.setPreferredSize(new Dimension(120,25));
        deleteProduct.setBackground(new Color(128, 118, 146));
        deleteProduct.setForeground(new Color(255, 253, 253));
        deleteProduct.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.deleteProduct(g.products.get(indexOfProduct).getName());
                goToTheGroupPanel(indexOfGroup);
            }
        });

        JPanel space = new JPanel(new FlowLayout());
        space.setBackground(new Color(198, 233, 243));
        space.setPreferredSize(new Dimension(410,25));

        southPanel.add(returnBack);
        southPanel.add(space);
        southPanel.add(deleteProduct);

        northPanel.add(titlePanel);

        productPanel.add(northPanel, "North");
        productPanel.add(southPanel, "South");
    }

    private void showProductPanel() {
        this.remove(mainPanel);
        this.add(productPanel);
        pack();
    }

    private void showProductPanelFromCategoryPanel() {
        this.remove(groupPanel);
        this.add(productPanel);
        pack();
    }

    private void addCategory() {
        this.remove(mainPanel);
        initAddCategoryPanel();
        this.add(addCategoryPanel);
        pack();
    }

    //створює вертикальний лейаут в вікнах для "додати категорію" і "додати продукт" :)
    class VerticalLayout implements LayoutManager
    {
        private Dimension size = new Dimension();

        // Следующие два метода не используются
        public void addLayoutComponent   (String name, Component comp) {}
        public void removeLayoutComponent(Component comp) {}

        // Метод определения минимального размера для контейнера
        public Dimension minimumLayoutSize(Container c) {
            return calculateBestSize(c);
        }
        // Метод определения предпочтительного размера для контейнера
        public Dimension preferredLayoutSize(Container c) {
            return calculateBestSize(c);
        }
        // Метод расположения компонентов в контейнере
        public void layoutContainer(Container container)
        {
            // Список компонентов
            Component list[] = container.getComponents();
            int currentY = 5;
            for (int i = 0; i < list.length; i++) {
                // Определение предпочтительного размера компонента
                Dimension pref = list[i].getPreferredSize();
                // Размещение компонента на экране
                list[i].setBounds(5, currentY, pref.width, pref.height);
                // Учитываем промежуток в 5 пикселов
                currentY += 5;
                // Смещаем вертикальную позицию компонента
                currentY += pref.height;
            }
        }
        // Метод вычисления оптимального размера контейнера
        private Dimension calculateBestSize(Container c)
        {
            // Вычисление длины контейнера
            Component[] list = c.getComponents();
            int maxWidth = 0;
            for (int i = 0; i < list.length; i++) {
                int width = list[i].getWidth();
                // Поиск компонента с максимальной длиной
                if ( width > maxWidth )
                    maxWidth = width;
            }
            // Размер контейнера в длину с учетом левого отступа
            size.width = maxWidth + 5;
            // Вычисление высоты контейнера
            int height = 0;
            for (int i = 0; i < list.length; i++) {
                height += 5;
                height += list[i].getHeight();
            }
            size.height = height;
            return size;
        }
    }



    private void initAddCategoryPanel() {
        addCategoryPanel = new JPanel(new VerticalLayout());
        addCategoryPanel.setBackground(new Color(198, 233, 243));

   //     JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //    northPanel.setBackground(new Color(49, 138, 163));

        JLabel addCategory = new JLabel("Додати категорію");
        addCategory.setForeground(new Color(47, 46, 50));
        addCategory.setFont(new Font(Font.SERIF,Font.PLAIN,30));

        JLabel newC = new JLabel("Введіть назву категорії");
        newC.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        JLabel newD = new JLabel("Введіть опис категорії");
        newD.setFont(new Font(Font.SERIF,Font.PLAIN,20));

        JTextArea newCat = new JTextArea(2, 20);

        JTextArea newDesc = new JTextArea(4, 20);



    //    JPanel southPanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
    //    southPanel.setBackground(new Color(0, 233, 243));

        JButton returnBack = new JButton("Назад");
      //  returnBack.setPreferredSize(new Dimension(120,25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToTheMainPanel();
            }
        });

        JButton addNewCategory = new JButton("Додати"); //Додає категорію до загального списку категорій, якщо вказане
        // ім'я категорії, інакше повертається на початкову сторінку
        addNewCategory.setBackground(new Color(128, 118, 146));
        addNewCategory.setForeground(new Color(255, 253, 253));
        addNewCategory.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        addNewCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newCat.getText();
                String description = newDesc.getText();
                if (!name.equals("")) {
                    Group newGroup = new Group(name);
                    if(description.equals("")){
                        newGroup.setDescription("Опис відсутній");
                    }
                    else newGroup.setDescription(description);
                    myStore.addGroup(newGroup);
                }
                goToTheMainPanel();
            }
        });


   //     addCategoryPanel.add(northPanel, "North");
    //    addCategoryPanel.add(southPanel, "South");

    //    northPanel.add(addCategory);
        addCategoryPanel.add(addCategory);
        addCategoryPanel.add(newC);
        addCategoryPanel.add(newCat);
        addCategoryPanel.add(newD);
        addCategoryPanel.add(newDesc);
        addCategoryPanel.add(returnBack);
        addCategoryPanel.add(addNewCategory);

       // southPanel.add(returnBack);


    }

    private void addProduct(int indexOfGroup) {
        this.remove(mainPanel);
        this.remove(groupPanel);
        initAddProductPanel(indexOfGroup);
        this.add(addProductPanel);
        pack();
    }

    private void initAddProductPanel(int indexOfGroup) {
        addProductPanel = new JPanel(new VerticalLayout());
        addProductPanel.setBackground(new Color(198, 233, 243));

    //    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //    northPanel.setBackground(new Color(198, 233, 243));

        JLabel addProduct = new JLabel("Додати продукт");
        addProduct.setForeground(new Color(47, 46, 50));
        addProduct.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        addProductPanel.add(addProduct);

        JLabel newP = new JLabel("Введіть назву товару");
        newP.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        addProductPanel.add(newP);
        JTextArea enterP = new JTextArea(2, 20);
        addProductPanel.add(enterP);

        JLabel newPr = new JLabel("Введіть ціну");
        newPr.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        addProductPanel.add(newPr);
        JTextArea enterPr = new JTextArea(2, 20);
        addProductPanel.add(enterPr);

        JLabel newNumber = new JLabel("Введіть кількість товару на складі");
        newNumber.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        addProductPanel.add(newNumber);
        JTextArea enterNumber = new JTextArea(2, 20);
        addProductPanel.add(enterNumber);

        JLabel newBr = new JLabel("Введіть назву бренду");
        newBr.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        addProductPanel.add(newBr);
        JTextArea enterBr = new JTextArea(2, 20);
        addProductPanel.add(enterBr);

        JLabel newD = new JLabel("Введіть опис товару");
        newD.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        addProductPanel.add(newD);
        JTextArea enterD = new JTextArea(2, 20);
        addProductPanel.add(enterD);


        JButton addNewProduct = new JButton("Додати"); //додає продукт до загального списку продуктів, якщо введена
        // назва товару, інакше повертається на сторінку групи :)
        addNewProduct.setBackground(new Color(128, 118, 146));
        addNewProduct.setForeground(new Color(255, 253, 253));
        addNewProduct.setFont(new Font(Font.SERIF,Font.PLAIN,17));

        addNewProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = enterP.getText();
                String price = enterPr.getText();
                String newNumber = enterNumber.getText();
                String brand = enterBr.getText();
                String description = enterD.getText();
                if (!name.equals("")) {
                    Product pr = new Product(name);
                    if(price.equals("")) pr.setPrice(0); else pr.setPrice(Integer.valueOf(price));
                    if(newNumber.equals("")) pr.setNumber(0); else pr.setNumber(Integer.valueOf(newNumber));
                    if(brand.equals("")) pr.setBrand("Не вказано"); else pr.setBrand(brand);
                    if(description.equals("")){
                        pr.setDescription("Опис відсутній");
                    }
                    else pr.setDescription(description);

                    if(groups.get(indexOfGroup).getProducts().size() == 0)
                        groups.get(indexOfGroup).getProducts().add(0, pr);

                    else groups.get(indexOfGroup).getProducts().add(groups.get(indexOfGroup).getProducts().size(), pr);
                }
                goToTheGroupPanel(indexOfGroup);
            }
        });

    //    JPanel southPanel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
    //    southPanel.setBackground(new Color(198, 233, 243));

        JButton returnBack = new JButton("Назад");
        returnBack.setPreferredSize(new Dimension(120,25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToTheGroupPanel(indexOfGroup);
            }
        });

        addProductPanel.add(addNewProduct);
        addProductPanel.add(returnBack);

    //    addProductPanel.add(northPanel, "North");
    //    addProductPanel.add(southPanel, "South");
    }

    private void goToTheMainPanel() {
        if(groupPanel != null)
            this.remove(groupPanel);
        if(productPanel != null)
            this.remove(productPanel);
        if(addCategoryPanel != null)
            this.remove(addCategoryPanel);
        if(nothingFoundPanel != null)
            this.remove(nothingFoundPanel);
        init();
        this.add(mainPanel);
        pack();
    }

    private void goToTheGroupPanel(int indexOfGroup) {
        if(productPanel != null)
            this.remove(productPanel);
        if(addProductPanel != null)
            this.remove(addProductPanel);
        if(nothingFoundPanel != null)
            this.remove(nothingFoundPanel);
        initGroupPanel(indexOfGroup);
        this.add(groupPanel);
        pack();

    }

    private void initNothingFoundPanel(int indexOfGroup) {
        nothingFoundPanel = new JPanel(new BorderLayout());
        nothingFoundPanel.setBackground(new Color(198, 233, 243));

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(new Color(198, 233, 243));

        JLabel nothing = new JLabel("Нічого не знайдено :(");
        nothing.setForeground(new Color(47, 46, 50));
        nothing.setFont(new Font(Font.SERIF,Font.PLAIN,30));

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.setBackground(new Color(198, 233, 243));

        JButton returnBack = new JButton("Назад");
        returnBack.setPreferredSize(new Dimension(120,25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF,Font.PLAIN,17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchFromMainPanel)
                    goToTheMainPanel();
                else
                    goToTheGroupPanel(indexOfGroup);
            }
        });

        northPanel.add(nothing);

        southPanel.add(returnBack);

        nothingFoundPanel.add(northPanel, "North");
        nothingFoundPanel.add(southPanel, "South");
    }

    private void goToNothingFoundPanelFromMain(int indexOfGroup) {
        this.remove(mainPanel);
        initNothingFoundPanel(indexOfGroup);
        add(nothingFoundPanel);
        pack();
    }

    private void goToNothingFoundPanelFromGroup(int indexOfGroup) {
        this.remove(groupPanel);
        initNothingFoundPanel(indexOfGroup);
        add(nothingFoundPanel);
        pack();
    }


}
