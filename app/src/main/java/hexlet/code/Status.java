package hexlet.code;

public class Status {

    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    String status;
    Object oldValue;
    Object newValue;
    Object sameValue;

    public Status(String status, Object oldValue, Object newValue) {
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Status(String status, Object sameValue) {
        this.status = status;
        this.sameValue = sameValue;
    }

    public String getStatus() {
        return this.status;
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        return this.newValue;
    }

    public Object getSameValue() {
        return this.sameValue;
    }
}
