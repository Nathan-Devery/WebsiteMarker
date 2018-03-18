package model;

import java.io.File;

/**
 * Stores the fields for an unmarkable assignment
 */
public class Malformed {

    private String assignmentName;
    private String reason;

    public Malformed(String assignmentName, String reason) {
        this.assignmentName = assignmentName;
        this.reason = reason;
    }

    public String getAssignmentName(){
        return assignmentName;
    }

    public String getReason() {
        return reason;
    }
}
