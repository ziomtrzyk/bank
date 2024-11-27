import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    Bank bank;
    CardLayout cardLayout;
    private JPanel mainPanel;

    JLabel labelDeposit;
    public MyFrame(Bank bank) {
        this.bank = bank;
        //cardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        //frame
        this.setTitle("My Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //add pages
        mainPanel.add(getMainPanel(),"Main" );
        mainPanel.add(getDepositScreen(),"Deposit");
        add(mainPanel);
        //finish frame
        this.pack();
        this.setVisible(true);

    }
    public JPanel getMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        //image bank
        ImageIcon bankIcon = new ImageIcon("bank.png");
        Image bankIconImage = bankIcon.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        bankIcon =new ImageIcon(bankIconImage);

        //Jlabel with bank image and text
        JLabel jLabelWelcome = new JLabel();
        jLabelWelcome.setIcon(bankIcon);
        jLabelWelcome.setText("Welcome to My Bank");
        jLabelWelcome.setFont(new Font("Helvetica", Font.BOLD, 60));
        jLabelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(jLabelWelcome, BorderLayout.NORTH);

        //CARD NUMBER
        JLabel jlabelIdDard = new JLabel("Card Number");
        jlabelIdDard.setFont(new Font("Helvetica", Font.BOLD, 20));
        JTextField textField = new JTextField(10);
        JPanel panelIdCard = new JPanel();
        panelIdCard.add(jlabelIdDard);
        panelIdCard.add(textField);

        //CARD PASSWORD
        JLabel jlabelPassCard = new JLabel("Password");
        jlabelPassCard.setFont(new Font("Helvetica", Font.BOLD, 20));
        JPasswordField passwordField = new JPasswordField(10);
        JPanel panelPassCard = new JPanel();
        panelPassCard.add(jlabelPassCard);
        panelPassCard.add(passwordField);

        //button submit number and password card
        JButton buttonSignIn = new JButton("Sign in");
        buttonSignIn.setPreferredSize(new Dimension(100,50));
        buttonSignIn.setHorizontalAlignment(SwingConstants.CENTER);
        buttonSignIn.addActionListener(e ->{
            int idCard = Integer.parseInt(textField.getText());
            String pass = String.valueOf(passwordField.getPassword());
            if(bank.signIn(idCard,pass)){
                JOptionPane.showMessageDialog(this, "Wprowadzone ID Card: "+ idCard + "\nWprowadzone password: " + pass);
                labelDeposit.setText("Deposit\nMoney: "+bank.showMoneyfromSignedCard());
                pack();
                cardLayout.show(mainPanel, "Deposit");
            }
            else JOptionPane.showMessageDialog(this,"ZLE DANE");
        });
        JPanel panelSignIn = new JPanel();
        panelSignIn.add(buttonSignIn);

        //PanelCard
        JPanel panelCard = new JPanel(new GridLayout(3,1,10,10));
        panelCard.add(panelIdCard);
        panelCard.add(panelPassCard);
        panelCard.add(panelSignIn);
        panel.add(panelCard, BorderLayout.CENTER);

        return panel;
    }

    public JPanel getDepositScreen(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        //image deposit
        labelDeposit = new JLabel("Deposit\nMoney: "+bank.showMoneyfromSignedCard());
        labelDeposit.setHorizontalTextPosition(SwingConstants.CENTER);
        labelDeposit.setVerticalTextPosition(SwingConstants.BOTTOM);
        ImageIcon bankIcon = new ImageIcon("deposit.png");
        labelDeposit.setIcon(bankIcon);
        labelDeposit.setFont(new Font("Helvetica", Font.BOLD, 50));
        panel.add(labelDeposit, BorderLayout.NORTH);

        //textfield money
        JPanel panelMoney = new JPanel();
        panel.add(panelMoney, BorderLayout.CENTER);
        TextField moneyField = new TextField(10);
        panelMoney.add(moneyField);

        //buttons
        JPanel panelButtons = new JPanel();
        panel.add(panelButtons, BorderLayout.SOUTH);
        JButton buttonDeposit = new JButton("Deposit");
        panelButtons.add(buttonDeposit);
        buttonDeposit.addActionListener(e -> {
            if(!bank.deposit( Integer.parseInt(moneyField.getText())))
                JOptionPane.showMessageDialog(this, "YOU DONT HAVE ENOUGH MONEY");
            System.out.println("Deposit button clicked: " + moneyField.getText());
        });

        return panel;
    }
}
