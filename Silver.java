package FinalProject;

public class Silver extends State {

    @Override
    public void makeGold(Customer c) {
        c.setState(new Gold());
    }

    @Override
    public void makeSilver(Customer c) {
    }

    @Override
    public String toString() {
        return "Silver";
    }
}
