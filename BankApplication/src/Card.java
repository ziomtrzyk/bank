public class Card {
    private int idCard;
    private int money;
    private String password;
    private int IdPerson;

    public Card(int idCard, int money, String password, int idPerson) {
        this.idCard = idCard;
        this.money = money;
        this.password = password;
        IdPerson = idPerson;
    }

    @Override
    public String toString() {
        return idCard + " " + money + " " + password + " " + IdPerson;
    }

    public int getIdCard() {
        return idCard;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    public boolean depositMoney(int money) throws IllegalArgumentException{
        System.out.println("Wyplacam pieniadze");
        try {
            if (this.money - money < 0) throw new IllegalArgumentException("You don't have enough money");
        }catch (IllegalArgumentException e){
            return false;
        }
        this.money -= money;

        return true;
    }
    public int getMoney(){
        return money;
    }

}
