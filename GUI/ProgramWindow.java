package GUI;

import Program.*;

import javax.swing.*;
import java.awt.*;

public class ProgramWindow extends JFrame {

    private Store store;
    private Group currentGroup;
    private Product currentProduct;

    private StorePanel storePanel;
    private GroupPanel groupPanel;
    private ProductPanel productPanel;

    public ProgramWindow(Store store) {
        super("Склад");
        this.store = store;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(120, 20);
        setPreferredSize(new Dimension(1000, 650));
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        storePanel = new StorePanel(store, this);
        add(storePanel);
    }

    public void openStoreWindow() {
        remove(groupPanel);
        storePanel = new StorePanel(store, this);
        add(storePanel);
        revalidate();
    }

    public void openGroupWindow(Group group) {
        currentGroup = group;
        if (storePanel != null) {
            remove(storePanel);
        }
        if (productPanel != null) {
            remove(productPanel);
        }
        groupPanel = new GroupPanel(group, this);
        add(groupPanel);
        revalidate();
    }


    public void openProductWindow(Product product) {
        currentProduct = product;
        remove(groupPanel);
        productPanel = new ProductPanel(product, this);
        add(productPanel);
        revalidate();
    }


    public void openStatisticsWindow() {
        // TODO: 07.04.2021 Вікно статистики
    }

    public void createStoreFile() {
        // TODO: 08.04.2021 Створення файлу х усіма товарами
    }

    public void createGroupFile(Group group) {
        // TODO: 08.04.2021 Створення файлу з товарами окремої групи
    }

    public void openSearchResults() {
        // TODO: 08.04.2021 Відкриття вікна з результатими пошуку 
    }

    public Store getStore() {
        return store;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public Group getCurrentGroup() {
        return currentGroup;
    }
}
