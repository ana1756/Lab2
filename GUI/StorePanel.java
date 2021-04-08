package GUI;

import Program.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StorePanel extends JPanel {

    private Store store;
    private ProgramWindow programWindow;


    public StorePanel(Store store, ProgramWindow programWindow) {
        this.store = store;
        this.programWindow = programWindow;
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        setBackground(new Color(198, 233, 243));
        add(northPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(southPanel(), BorderLayout.SOUTH);
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel(new GridLayout(3, 1));
        northPanel.add(titlePanel());
        northPanel.add(searchPanel());
        northPanel.add(categoriesTitlePanel());

        return northPanel;
    }

    private JPanel titlePanel() {
        JPanel title = new JPanel(new FlowLayout());
        title.setBackground(new Color(198, 233, 243));

        JLabel store = new JLabel("Склад");
        store.setForeground(new Color(47, 46, 50));
        store.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        title.add(store);

        return title;
    }

    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout());
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
                // TODO: 08.04.2021 Вікно результатів пошуку
                programWindow.openSearchResults();
            }
        });

        searchPanel.add(searchText);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel categoriesTitlePanel() {
        JPanel categoriesTitle = new JPanel(new FlowLayout());
        categoriesTitle.setBackground(new Color(198, 233, 243));

        JLabel types = new JLabel("Всі категорії");
        types.setForeground(new Color(47, 46, 50));
        types.setFont(new Font(Font.SERIF, Font.PLAIN, 30));

        categoriesTitle.add(types);

        return categoriesTitle;
    }

    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel(new GridLayout(1, 1));
        centrePanel.setBackground(new Color(198, 233, 243));

        centrePanel.add(categoriesPanel());

        return centrePanel;
    }

    private JPanel categoriesPanel() {
        JPanel categories = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categories.setBackground(new Color(198, 233, 243));

        categories.add(scroll());
        return categories;
    }

    private JScrollPane scroll() {

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(198, 233, 243));

        for (int i = 0; i < store.getGroups().size(); i++) {
            panel.add(oneCategoryPanel(store.getGroups().get(i)));
        }

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBackground(new Color(198, 233, 243));
        scroll.setPreferredSize(new Dimension(700, 350));
        return scroll;
    }

    private JButton oneCategoryPanel(Group group) {
        JButton button = new JButton(group.getName());
        button.setFont(new Font("Century", Font.PLAIN, 18));
//        button.setToolTipText(group.getDescription());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                programWindow.openGroupWindow(group);
            }
        });

        button.setPreferredSize(new Dimension(130, 50));
        button.setBackground(new Color(250, 250, 250));
        button.setForeground(new Color(0, 0, 0));
        button.setFont(new Font("Arial", Font.PLAIN, 17));
        button.setBorder(new LineBorder(new Color(220, 220, 220)));

        return button;
    }

    private JPanel southPanel() {
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(new Color(198, 233, 243));
        southPanel.setBorder(new LineBorder(Color.WHITE));
        southPanel.setPreferredSize(new Dimension(getWidth(), 33));

        southPanel.add(addCategoryButton());
        southPanel.add(createFileButton());
        southPanel.add(statisticsButton());

        return southPanel;
    }

    private JButton addCategoryButton() {
        JButton addCat = new JButton("Додати"); //Переносить на сторінку "Додати категорію"
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(new Color(128, 118, 146));
        addCat.setForeground(new Color(255, 253, 253));
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(addCategoryPanel());
                revalidate();
            }
        });

        return addCat;
    }

    private JButton statisticsButton() {
        JButton addCat = new JButton("Статистика");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(new Color(128, 118, 146));
        addCat.setForeground(new Color(255, 253, 253));
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Open Statistics Window
                programWindow.openStatisticsWindow();

            }
        });

        return addCat;
    }

    private JButton createFileButton() {
        JButton addCat = new JButton("Зберегти");
        addCat.setPreferredSize(new Dimension(120, 25));
        addCat.setBackground(new Color(128, 118, 146));
        addCat.setForeground(new Color(255, 253, 253));
        addCat.setFont(new Font(Font.SERIF, Font.PLAIN, 17));
        addCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 07.04.2021 Створити файл
                programWindow.createStoreFile();
            }
        });

        return addCat;
    }


    //----------------------------------------------------------------------------
    // ПАНЕЛЬ ДЛЯ ДОДАВАННЯ КАТЕГОРІЇ
    //----------------------------------------------------------------------------


    private JPanel addCategoryPanel() {
        JPanel addCategoryPanel = new JPanel(new BorderLayout());
        addCategoryPanel.setBackground(new Color(198, 233, 243));

        addCategoryPanel.add(addCatNorthPanel(), BorderLayout.NORTH);
        addCategoryPanel.add(addCatMenu(), BorderLayout.CENTER);
        addCategoryPanel.add(buttonsPanel(), BorderLayout.SOUTH);

        return addCategoryPanel;
    }

    private JPanel addCatNorthPanel() {
        JPanel addCatNorthPanel = new JPanel(new BorderLayout());
        addCatNorthPanel.setBackground(new Color(128, 118, 146));
        addCatNorthPanel.setPreferredSize(new Dimension(200, 70));

        JLabel store = new JLabel("Додати категорію");
        store.setForeground(Color.white);
        store.setBorder(new LineBorder(Color.LIGHT_GRAY));
        store.setHorizontalAlignment(SwingConstants.CENTER);
        store.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        addCatNorthPanel.add(store, BorderLayout.CENTER);

        return addCatNorthPanel;
    }

    private JPanel addCatMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(new Color(198, 233, 243));
        menu.add(addCategoryName());
        menu.add(addCategoryDetails());
        return menu;
    }

    JTextArea newCategory;

    private JPanel addCategoryName() {
        JPanel namePanel = new JPanel(new GridLayout(0, 1));
        namePanel.setPreferredSize(new Dimension(700, 150));
        namePanel.setBackground(new Color(198, 233, 243));

        JLabel addCategory = new JLabel("Введіть назву");
        addCategory.setHorizontalAlignment(SwingConstants.CENTER);
        addCategory.setVerticalAlignment(SwingConstants.CENTER);
        addCategory.setForeground(Color.BLACK);
        addCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 22));

        newCategory = new JTextArea(1, 20);
        newCategory.setBorder(new LineBorder(Color.LIGHT_GRAY));
        newCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        namePanel.add(addCategory, BorderLayout.NORTH);
        namePanel.add(newCategory, BorderLayout.SOUTH);
        return namePanel;
    }

    JTextArea newDetails;

    private JPanel addCategoryDetails() {
        JPanel namePanel = new JPanel(new GridLayout(0, 1));
        namePanel.setPreferredSize(new Dimension(700, 150));
        namePanel.setBackground(new Color(198, 233, 243));

        JLabel addCategory = new JLabel("Введіть опис");
        addCategory.setBackground(new Color(128, 118, 146));
        addCategory.setHorizontalAlignment(SwingConstants.CENTER);
        addCategory.setForeground(Color.BLACK);
        addCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 22));


        newDetails = new JTextArea(3, 20);
        newDetails.setBorder(new LineBorder(Color.LIGHT_GRAY));
        newDetails.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JScrollPane scroll = new JScrollPane(newDetails);
        scroll.setBackground(new Color(198, 233, 243));
        scroll.setPreferredSize(new Dimension(700, 250));

        namePanel.add(addCategory, BorderLayout.NORTH);
        namePanel.add(scroll, BorderLayout.SOUTH);
        return namePanel;

    }

    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        buttonsPanel.setPreferredSize(new Dimension(getWidth(), 30));

        buttonsPanel.add(getBackButton());
        buttonsPanel.add(saveNewCategoryButton());

        return buttonsPanel;
    }

    private JButton getBackButton() {
        JButton getBack = new JButton("Назад");
        getBack.setBackground(new Color(128, 118, 146));
        getBack.setForeground(Color.white);
        getBack.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        getBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                programWindow.openStoreWindow();
                revalidate();
            }
        });
        return getBack;
    }

    private JButton saveNewCategoryButton() {
        JButton saveCategory = new JButton("Зберегти");
        saveCategory.setBackground(new Color(128, 118, 146));
        saveCategory.setForeground(Color.white);
        saveCategory.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        saveCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Group newGroup = new Group(newCategory.getText());
                newGroup.setDescription(newDetails.getText());
                store.addGroup(newGroup);
                removeAll();
                revalidate();
                programWindow.openStoreWindow();

            }
        });
        return saveCategory;

    }

}
