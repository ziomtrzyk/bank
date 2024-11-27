public class Person {
    private int IdPerson;
    private String Name;
    private String Surname;

    public Person(int idPerson, String name, String surname) {
        IdPerson = idPerson;
        Name = name;
        Surname = surname;
    }

    @Override
    public String toString() {
        return IdPerson + " " + Name + " " + Surname;
    }
}
