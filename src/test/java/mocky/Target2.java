package mocky;

public class Target2 {

    public String doSomething(Integer thingId) {

        return String.format("Doing thing %s", thingId);
    }

    public Target getTarget(String targetName){
        return new Target(targetName);

    }

}
