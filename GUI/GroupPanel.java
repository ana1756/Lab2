package GUI;

import Program.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupPanel extends JPanel {

    private Group group;
    private ProgramWindow programWindow;

    GroupPanel(Group group, ProgramWindow programWindow) {
        this.programWindow = programWindow;

        this.group = group;
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        setBackground(new Color(198, 233, 243));

        add(northPanel(), "North");
        add(centerPanel(), "Center");
        add(southPanel(), "South");
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel(new GridLayout(3, 1));

        northPanel.add(titlePanel());
        northPanel.add(searchPanel());
        northPanel.add(productsTitle());

        return northPanel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(198, 233, 243));

        JLabel title = new JLabel(group.getName());
        title.setForeground(new Color(47, 46, 50));
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);

        return titlePanel;
    }

    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel((new FlowLayout()));
        searchPanel.setBackground(new Color(198, 233, 243));


        JTextArea searchText = new JTextArea(1, 30);
        searchText.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        searchText.setBorder(new LineBorder(Color.LIGHT_GRAY));
        searchText.setToolTipText("Введіть назву групи або товару");

        JButton searchButton = new JButton("Пошук"); //Шукає серед всіх груп та всіх продуктів
        searchButton.setPreferredSize(new Dimension(110, 33));
        searchButton.setBackground(new Color(128, 118, 146));
        searchButton.setForeground(new Color(255, 253, 253));
        searchButton.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Вдкривати вікно пошуку
                programWindow.openSearchResults();
            }
        });

        searchPanel.add(searchText);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel productsTitle() {
        JPanel productsTitle = new JPanel(new FlowLayout());
        productsTitle.setBackground(new Color(198, 233, 243));

        JLabel products = new JLabel("Наявні товари");
        products.setForeground(new Color(47, 46, 50));
        products.setFont(new Font(Font.SERIF, Font.PLAIN, 24));

        productsTitle.add(products);

        return productsTitle;
    }


    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrePanel.setBackground(new Color(198, 233, 243));

        centrePanel.add(scrollProductsPanel());

        return centrePanel;
    }

    private JScrollPane scrollProductsPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(198, 233, 243));
        panel.setBackground(Color.WHITE);
        panel.add(header(), BorderLayout.NORTH);

        JPanel prodPanel = new JPanel(new GridLayout(0, 1));
        if (group.getProducts() != null) {
            for (int i = 0; i < group.getProducts().size(); i++) {
                prodPanel.add(oneProductPanel(group.getProducts().get(i)));
            }
        }

        panel.add(prodPanel, BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(new Color(198, 233, 243));
        scroll.setPreferredSize(new Dimension(700, 400));
        return scroll;
    }

    private JPanel header() {
        JPanel headerTable = new JPanel(new GridLayout(1, 0));
        headerTable.setPreferredSize(new Dimension(200, 40));
        headerTable.setBackground(new Color(128, 118, 146));

        JLabel productName = new JLabel("Назва");
        productName.setHorizontalAlignment(SwingConstants.CENTER);
        productName.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productName.setFont(new Font("Century", Font.PLAIN, 15));
        productName.setForeground(Color.white);

        JLabel productPrice = new JLabel("Ціна");
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setSize(30, 20);
        productPrice.setFont(new Font("Century", Font.PLAIN, 15));
        productPrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productPrice.setForeground(Color.white);

        JLabel productNumber = new JLabel("Кількість");
        productNumber.setHorizontalAlignment(SwingConstants.CENTER);
        productNumber.setFont(new Font("Century", Font.PLAIN, 15));
        productNumber.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productNumber.setForeground(Color.white);

        JLabel productBrand = new JLabel("Виробник");
        productBrand.setHorizontalAlignment(SwingConstants.CENTER);
        productBrand.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productBrand.setFont(new Font("Century", Font.PLAIN, 15));
        productBrand.setForeground(Color.white);

        JLabel emptyButton = new JLabel();
        emptyButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        emptyButton.setPreferredSize(new Dimension(120, 15));
        emptyButton.setBackground(new Color(80, 80, 80));
        emptyButton.setForeground(new Color(255, 253, 253));
        emptyButton.setFont(new Font("Century", Font.PLAIN, 15));

        headerTable.add(productName);
        headerTable.add(productPrice);
        headerTable.add(productNumber);
        headerTable.add(productBrand);
        headerTable.add(emptyButton);

        return headerTable;
    }

    private JPanel fullProductPanel(Product product) {
        JPanel productPanel = new JPanel(new GridLayout(1, 0));
        productPanel.setPreferredSize(new Dimension(200, 20));
        productPanel.setBackground(Color.WHITE);

        JLabel productName = new JLabel("  " + product.getName());
        productName.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productName.setFont(new Font("Century", Font.PLAIN, 16));

        JLabel productPrice = new JLabel("" + product.getPrice() + " грн");
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);
        productPrice.setSize(30, 20);
        productPrice.setFont(new Font("Century", Font.PLAIN, 16));
        productPrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productPrice.setBackground(Color.WHITE);

        JLabel productNumber = new JLabel(+product.getNumber() + "");
        productNumber.setHorizontalAlignment(SwingConstants.CENTER);
        productNumber.setFont(new Font("Century", Font.PLAIN, 16));
        productNumber.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JLabel productBrand = new JLabel(product.getBrand());
        productBrand.setHorizontalAlignment(SwingConstants.CENTER);
        productBrand.setBorder(new LineBorder(Color.LIGHT_GRAY));
        productBrand.setFont(new Font("Century", Font.PLAIN, 16));

        JButton button = new JButton("Детальніше");
        button.setBorder(new LineBorder(Color.LIGHT_GRAY));
        button.setPreferredSize(new Dimension(120, 15));
        button.setBackground(new Color(128, 118, 146));
        button.setForeground(new Color(255, 253, 253));
        button.setFont(new Font("Century", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programWindow.openProductWindow(product);
            }
        });

        productPanel.add(productName);
        productPanel.add(productPrice);
        productPanel.add(productNumber);
        productPanel.add(productBrand);
        productPanel.add(button);

        return productPanel;
    }

    private JPanel oneProductPanel(Product product) {
        JPanel oneProductPanel = new JPanel(new BorderLayout());
//        oneProductPanel.setToolTipText(product.getDescription());
        oneProductPanel.setBorder(new LineBorder(Color.WHITE));
        oneProductPanel.setPreferredSize(new Dimension(100, 40));
        oneProductPanel.setBackground(new Color(255, 253, 253));

        oneProductPanel.add(fullProductPanel(product), BorderLayout.CENTER);
        return oneProductPanel;
    }

    private JPanel southPanel() {
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(new Color(198, 233, 243));
        southPanel.setPreferredSize(new Dimension(1200, 30));


        southPanel.add(getBackButton());
        southPanel.add(createFileButton());
        southPanel.add(addProductButton());
        southPanel.add(deleteCategoryButton());

        return southPanel;
    }

    private JButton getBackButton() {
        JButton returnBack = new JButton("Назад");
        returnBack.setPreferredSize(new Dimension(120, 25));
        returnBack.setBackground(new Color(128, 118, 146));
        returnBack.setForeground(new Color(255, 253, 253));
        returnBack.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        returnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programWindow.openStoreWindow();
            }
        });

        return returnBack;
    }

    private JButton createFileButton() {
        JButton saveData = new JButton("Зберегти"); //Поки нічого не робить
        saveData.setPreferredSize(new Dimension(120, 25));
        saveData.setBackground(new Color(128, 118, 146));
        saveData.setForeground(new Color(255, 253, 253));
        saveData.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Створити файл лише з товарами цієї категорії
                programWindow.createGroupFile(group);
            }
        });

        return saveData;
    }

    private JButton addProductButton() {
        JButton addProduct = new JButton("Додати"); //Переносить на сторінку "Додати продукт"
        addProduct.setPreferredSize(new Dimension(120, 25));
        addProduct.setBackground(new Color(128, 118, 146));
        addProduct.setForeground(new Color(255, 253, 253));
        addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Створення панелі з додаванням товару

            }
        });

        return addProduct;
    }

    private JButton deleteCategoryButton() {
        JButton deleteCategory = new JButton("Видалити"); //Поки нічого не робить, але має видаляти групу
        deleteCategory.setPreferredSize(new Dimension(120, 25));
        deleteCategory.setBackground(new Color(128, 118, 146));
        deleteCategory.setForeground(new Color(255, 253, 253));
        deleteCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        deleteCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Створити діалогове вукно підтверження
                //"Ви дійсно хочете видалити цю категорію?" Якщо так, тоді наступні дії:
                programWindow.getStore().deleteGroup(group.getName());
                programWindow.openStoreWindow();
            }
        });

        return deleteCategory;
    }

}


