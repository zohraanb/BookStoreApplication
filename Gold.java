package FinalProject;

public class Gold extends State {

    @Override
    public void makeGold(Customer c) {
    }

    @Override
    public void makeSilver(Customer c) {
        c.setState(new Silver());
    }

    @Override
    public String toString() {
        return "Gold";
    }
}
