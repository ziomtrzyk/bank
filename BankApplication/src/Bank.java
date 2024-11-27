import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private List<Person> personList = new ArrayList<>();
    private List<Card> cardList = new ArrayList<>();
    private String schema;
    private String user;
    private String password;

    private Connection connection;

    private MyFrame myFrame;

    private Card signedCard = null;

    public Bank(String schema, String user, String password) {
        this.schema = schema;
        this.user = user;
        this.password = password;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+schema,user,password);// connection with database mySQL
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Person");

            while (resultSet.next()) { // save all Persons from MySQL
                Person person = new Person(
                        Integer.parseInt(resultSet.getString("IdPerson")),
                        resultSet.getString("Name"),
                        resultSet.getString("Surname")
                );
                personList.add(person);
            }

            ResultSet resultSet2 = statement.executeQuery("select * from Card");

            while (resultSet2.next()) { // save all Cards from MySQL
                Card card = new Card(
                        resultSet2.getInt("IdCard"),
                        resultSet2.getInt("Money"),
                                resultSet2.getString("Password"),
                resultSet2.getInt("IdPerson")
                );
                cardList.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        myFrame = new MyFrame(this);
    }
    public void showPersonList() {
        personList.stream().forEach(person -> System.out.println(person));
    }
    public void showCardList() {
        cardList.stream().forEach(card -> System.out.println(card));
    }
    public boolean signIn(int id, String password) {
        Optional<Card> findCard = cardList.stream().filter(card -> card.getIdCard() == id).findFirst();
        if (findCard.isPresent()) {
            signedCard = findCard.get();
            return findCard.get().checkPassword(password);
        }else return false;
    }
    public boolean deposit(int money) {
        if(signedCard.depositMoney(money)){
            myFrame.labelDeposit.setText("Deposit\nMoney: "+showMoneyfromSignedCard());
            return true;
        }
        return false;
    }
    public int showMoneyfromSignedCard(){
        if(signedCard != null)
        return signedCard.getMoney();
        else return 0;
    }
}

