package q2.commands;



public class CurrentState extends CommandStatus{
    public void findCurState() {
        System.out.println(this.toString());
    }

    public String getCurState() {
        return this.toString();
    }

}
