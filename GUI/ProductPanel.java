package GUI;

import Program.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPanel extends JPanel {

    private Product product;
    private ProgramWindow programWindow;

    ProductPanel(Product product,  ProgramWindow programWindow) {
        this.product = product;

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
        northPanel.setBackground(new Color(198, 233, 243));
        northPanel.add(titlePanel());

        return northPanel;
    }

    private JPanel titlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(198, 233, 243));

        JLabel title = new JLabel(product.getName());
        title.setForeground(new Color(47, 46, 50));
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        titlePanel.add(title);

        return titlePanel;
    }

    private JPanel centerPanel() {
        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(new Color(198, 233, 243));

        centrePanel.add(infoPanel());

        return centrePanel;
    }

    private JPanel infoPanel(){
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(700, 350));

        JPanel info = new JPanel(new GridLayout(0, 1));
        info.add(namePanel());
        info.add(pricePanel());
        info.add(numberPanel());
        info.add(brandPanel());
        info.add(falsePanel());
        infoPanel.add(info, BorderLayout.NORTH);
        infoPanel.add(descriptionPanel(), BorderLayout.CENTER);

        return infoPanel;
    }

    private JPanel namePanel() {
        JPanel namePanel = new JPanel(new GridLayout(1, 0));
        namePanel.setBackground(new Color(236, 234, 232));

        JLabel nameLabel = new JLabel("Назва: ");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JTextArea nameArea = new JTextArea("  "+product.getName() + "");
        nameArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        nameArea.setBorder(new LineBorder(Color.LIGHT_GRAY));


        JButton nameButton = new JButton("Зберегти");
        nameButton.setBackground(new Color(128, 118, 146));
        nameButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        nameButton.setForeground(new Color(250, 250, 250));
        nameButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product.setName(nameArea.getText());
            }
        });


        namePanel.add(nameLabel);
        namePanel.add(nameArea);
        namePanel.add(nameButton);

        return namePanel;
    }

    private JPanel pricePanel() {
        JPanel pricePanel = new JPanel(new GridLayout(1, 0));
        pricePanel.setBackground(new Color(236, 234, 232));

        JLabel priceLabel = new JLabel("Ціна: ");
        priceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JTextArea priceArea = new JTextArea("  "+product.getPrice() + "");
        priceArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        priceArea.setBorder(new LineBorder(Color.LIGHT_GRAY));


        JButton priceButton = new JButton("Зберегти");
        priceButton.setBackground(new Color(128, 118, 146));
        priceButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        priceButton.setForeground(new Color(250, 250, 250));
        priceButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (priceArea.getText().matches("[0-9]*")) {
                    product.setPrice(Integer.valueOf(priceArea.getText()));
                    titlePanel();
                } else {
                    priceArea.setText(product.getPrice()+"");
                }
            }
        });


        pricePanel.add(priceLabel);
        pricePanel.add(priceArea);
        pricePanel.add(priceButton);

        return pricePanel;
    }

    private JPanel numberPanel() {
        JPanel numberPanel = new JPanel(new GridLayout(1, 0));
        numberPanel.setBackground(new Color(236, 234, 232));

        JLabel numberLabel = new JLabel("Кількість: ");
        numberLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JTextArea numberArea = new JTextArea("  "+product.getNumber() + "");
        numberArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        numberArea.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JButton numberButton = new JButton("Зберегти");
        numberButton.setBackground(new Color(128, 118, 146));
        numberButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        numberButton.setForeground(new Color(250, 250, 250));
        numberButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        numberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numberArea.getText().matches("[0-9]*")) {
                    product.setNumber(Integer.valueOf(numberArea.getText()));
                } else {
                    numberArea.setText(product.getNumber()+"");
                }
            }
        });

        numberPanel.add(numberLabel);
        numberPanel.add(numberArea);
        numberPanel.add(numberButton);

        return numberPanel;
    }

    private JPanel brandPanel() {
        JPanel brandPanel = new JPanel(new GridLayout(1, 0));
        brandPanel.setBackground(new Color(236, 234, 232));

        JLabel brandLabel = new JLabel("Виробник: ");
        brandLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandLabel.setHorizontalAlignment(SwingConstants.CENTER);
        brandLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JTextArea brandArea = new JTextArea("  "+product.getBrand() + "");
        brandArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        brandArea.setBorder(new LineBorder(Color.LIGHT_GRAY));


        JButton brandButton = new JButton("Зберегти");
        brandButton.setBackground(new Color(128, 118, 146));
        brandButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        brandButton.setForeground(new Color(250, 250, 250));
        brandButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        brandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    product.setBrand(brandArea.getText());

            }
        });

        brandPanel.add(brandLabel);
        brandPanel.add(brandArea);
        brandPanel.add(brandButton);

        return brandPanel;
    }

    private JPanel descriptionPanel() {
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(new Color(236, 234, 232));
        descriptionPanel.setPreferredSize(new Dimension(500, 100));


        JLabel descriptionLabel = new JLabel("Опис: ");
        descriptionLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));

        JTextArea descriptionArea = new JTextArea("  "+product.getDescription() + "");
        descriptionArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        descriptionArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
        descriptionArea.setSize(300, 100);

        JButton descriptionButton = new JButton("Зберегти");
        descriptionButton.setBackground(new Color(128, 118, 146));
        descriptionButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        descriptionButton.setForeground(new Color(250, 250, 250));
        descriptionButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        descriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product.setDescription(descriptionArea.getText());
            }
        });

        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);
        descriptionPanel.add(descriptionButton, BorderLayout.SOUTH);

        return descriptionPanel;
    }

    private JPanel falsePanel(){
        JPanel falsePanel = new JPanel(new GridLayout(0, 1));
        falsePanel.setBackground(new Color(198, 233, 243));

        return falsePanel;

    }

    private JPanel southPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 0));
        buttonsPanel.setBackground(new Color(198, 233, 243));

        JButton getBackButton = new JButton("Назад");
        getBackButton.setBackground(new Color(128, 118, 146));
        getBackButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        getBackButton.setForeground(new Color(250, 250, 250));
        getBackButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        getBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                programWindow.openGroupWindow(programWindow.getCurrentGroup());
            }
        });

        JButton addNumberButton = new JButton("Додати");
        addNumberButton.setBackground(new Color(128, 118, 146));
        addNumberButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        addNumberButton.setForeground(new Color(250, 250, 250));
        addNumberButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        addNumberButton.addActionListener(new ActionListener() {
             @Override //інтерфейс додавання одиниць товару 
            public void actionPerformed(ActionEvent e) {
                JFrame ad = new JFrame();
                ad.setSize(400, 150);
                ad.setLocationRelativeTo(null);
                JPanel northPanel = new JPanel();
                JPanel centerPanel = new JPanel(new GridLayout(1, 1));
                JPanel southPanel = new JPanel(new GridLayout(1, 2));
                ad.add(northPanel, BorderLayout.NORTH);
                ad.add(centerPanel, BorderLayout.CENTER);
                ad.add(southPanel, BorderLayout.EAST);
                northPanel.setBackground(new Color(236, 234, 232));
                centerPanel.setBackground(new Color(198, 233, 243));
                southPanel.setBackground(new Color(198, 233, 243));
                JLabel howMany = new JLabel("Cкільки одиниць продукту додати?");
                howMany.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                JTextField a = new JTextField();
                a.setSize(50, 50);
                a.setFont(new Font(Font.SERIF, Font.PLAIN, 45));
                JButton addProduct = new JButton("Додати");
                addProduct.setBackground(new Color(128, 118, 146));
                addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                addProduct.setForeground(new Color(250, 250, 250));
                addProduct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(a.getText()!=null){
                            String s = a.getText();
                            if(s.matches("[0-9]*")){
                                int a = product.getNumber() + Integer.valueOf(s);
                                product.setNumber(a);
                                ad.setVisible(false);
                                programWindow.openProductWindow(product);
                            }
                            else {
                                a.setText(null);
                            }
                        }
                        else programWindow.openProductWindow(product);
                    }
                });
                northPanel.add(howMany);
                centerPanel.add(a);
                southPanel.add(addProduct);
                ad.setVisible(true);
            }
        });

        JButton deleteNumberButton = new JButton("Списати");
        deleteNumberButton.setBackground(new Color(128, 118, 146));
        deleteNumberButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        deleteNumberButton.setForeground(new Color(250, 250, 250));
        deleteNumberButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        deleteNumberButton.addActionListener(new ActionListener() {
             @Override //інтерфейс списування товарів 
            //запитує перед тим, як списати: так - списує, скасувати - повертається на сторінку продукту
            //якщо задане значення менше, ніж кількість наявний продуктів, встановлює нуль 
            //якщо намагаються списати продукт, кількість якого нуль - повідомлення, що на складі цей товар відсутній 
            public void actionPerformed(ActionEvent e) {
                JFrame ad = new JFrame();
                ad.setSize(400, 150);
                ad.setLocationRelativeTo(null);
                JPanel northPanel = new JPanel();
                JPanel centerPanel = new JPanel(new GridLayout(1, 1));
                JPanel southPanel = new JPanel(new GridLayout(1, 2));
                ad.add(northPanel, BorderLayout.NORTH);
                ad.add(centerPanel, BorderLayout.CENTER);
                ad.add(southPanel, BorderLayout.EAST);
                northPanel.setBackground(new Color(236, 234, 232));
                centerPanel.setBackground(new Color(198, 233, 243));
                southPanel.setBackground(new Color(198, 233, 243));
                JLabel howMany = new JLabel("Cкільки одиниць продукту списати?");
                howMany.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                JTextField a = new JTextField();
                a.setSize(50, 50);
                a.setFont(new Font(Font.SERIF, Font.PLAIN, 45));
                JButton addProduct = new JButton("Списати");
                addProduct.setBackground(new Color(128, 118, 146));
                addProduct.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                addProduct.setForeground(new Color(250, 250, 250));
                addProduct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = a.getText();
                        if(!s.equals("")){
                            if(s.matches("[0-9]*")) {
                                if (product.getNumber() == 0) {
                                    JFrame ad1 = new JFrame();
                                    ad1.setSize(400, 150);
                                    ad1.setLocationRelativeTo(null);
                                    JPanel northPanel = new JPanel();
                                    northPanel.setBackground(new Color(198, 233, 243));
                                    ad1.add(northPanel);
                                    JPanel sPanel = new JPanel();
                                    sPanel.setBackground(new Color(198, 233, 243));
                                    ad1.add(sPanel, BorderLayout.SOUTH);
                                    JLabel label = new JLabel("Цього товару немає на складі!");
                                    label.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                                    northPanel.add(label);
                                    label.setHorizontalAlignment(SwingConstants.CENTER);
                                    JButton ok = new JButton("OK");
                                    ok.setBackground(new Color(128, 118, 146));
                                    ok.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                                    ok.setForeground(new Color(250, 250, 250));
                                    sPanel.add(ok, BorderLayout.SOUTH);
                                    ok.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ad1.setVisible(false);
                                            ad.setVisible(false);
                                            programWindow.openProductWindow(product);
                                        }
                                    });
                                    ad1.setVisible(true);
                                } else {
                                    JFrame ad1 = new JFrame();
                                    ad1.setSize(400, 150);
                                    ad1.setLocationRelativeTo(null);
                                    JPanel northPanel1 = new JPanel();
                                    JPanel southPanel1 = new JPanel(new GridLayout(1, 1));
                                    ad1.add(northPanel1, BorderLayout.CENTER);
                                    ad1.add(southPanel1, BorderLayout.SOUTH);
                                    northPanel1.setBackground(new Color(236, 234, 232));

                                    southPanel1.setBackground(new Color(198, 233, 243));
                                    JLabel label = new JLabel("Ви впевнені?");
                                    label.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                                    JButton yes = new JButton("Так");
                                    JButton no = new JButton("Скасувати");
                                    yes.setBackground(new Color(128, 118, 146));
                                    yes.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                                    yes.setForeground(new Color(250, 250, 250));
                                    northPanel1.add(label, BorderLayout.CENTER);
                                    southPanel1.add(yes, BorderLayout.WEST);
                                    southPanel1.add(no, BorderLayout.EAST);
                                    ad1.setVisible(true);

                                    yes.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            int a = product.getNumber() - Integer.valueOf(s);
                                            if (a > 0) {
                                                product.setNumber(a);
                                            } else {
                                                product.setNumber(0);
                                            }
                                            Product last = programWindow.getCurrentProduct();
                                            ad.setVisible(false);
                                            ad1.setVisible(false);
                                            programWindow.openProductWindow(last);
                                        }
                                    });
                                    no.setBackground(new Color(128, 118, 146));
                                    no.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                                    no.setForeground(new Color(250, 250, 250));
                                    no.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            ad1.setVisible(false);
                                            ad.setVisible(false);
                                            programWindow.openProductWindow(product);
                                        }
                                    });
                                }
                            }
                        }
                        else programWindow.openProductWindow(product);
                    }
                });
                northPanel.add(howMany);
                centerPanel.add(a);
                southPanel.add(addProduct);
                ad.setVisible(true);
            }
        });

        JButton deleteProductButton = new JButton("Видалити");
        deleteProductButton.setBackground(new Color(128, 118, 146));
        deleteProductButton.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        deleteProductButton.setForeground(new Color(250, 250, 250));
        deleteProductButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 08.04.2021 Створити діалогове вукно підтверження
                //"Ви дійсно хочете видалити цей товар?" Якщо так, тоді наступні дії:
                programWindow.getCurrentGroup().deleteProduct(product.getName());
                programWindow.openGroupWindow(programWindow.getCurrentGroup());
            }
        });

        buttonsPanel.add(getBackButton);
        buttonsPanel.add(addNumberButton);
        buttonsPanel.add(deleteNumberButton );
        buttonsPanel.add(deleteProductButton);

        return buttonsPanel;
    }

}
