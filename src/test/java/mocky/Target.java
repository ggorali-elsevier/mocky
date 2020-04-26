package mocky;

public class Target {

    private String targetName;

    public Target(String targetName) {

        this.targetName = targetName;
    }

    public String doSomething(int taskNumber) {

        return String.format("Done task number %d", taskNumber);
    }


    public Integer saySomething(int taskNumber) {

        return String.format("Saying number %d", taskNumber).length();
    }

    public String doIt(String thing) {
        return String.format("Done number %s", thing);
    }

    public String doThings(int taskNumber, String name) {

        return String.format("Doing number %d name: %s", taskNumber, name);
    }

    public String getTargetName() {
        return targetName;
    }
}
