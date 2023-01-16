package hexlet.code;

public final class Status {

    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String status;
    private Object oldValue;
    private Object newValue;


    public Status(String status1, Object oldValue1, Object newValue1) {
        this.status = status1;
        this.oldValue = oldValue1;
        this.newValue = newValue1;
    }

    public Status(String status1, Object sameValue) {
        this.status = status1;
        if (status.equals(ADDED)) {
            this.newValue = sameValue;
        } else {
            this.oldValue = sameValue;
        }
    }


    public String getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

}
