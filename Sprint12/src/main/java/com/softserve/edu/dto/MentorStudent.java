package com.softserve.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class MentorStudent {
    private final String mentortName;
    private List<String> studentNames;

    public MentorStudent(String mentortName, List<String> studentNames) {
        this.mentortName = mentortName;
        this.studentNames = new ArrayList<>(studentNames);
    }

    public String getMentortName() {
        return mentortName;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MentorStudent)) return false;

        MentorStudent that = (MentorStudent) o;

        if (mentortName != null ? !mentortName.equals(that.mentortName) : that.mentortName != null) return false;
        return studentNames != null ? studentNames.equals(that.studentNames) : that.studentNames == null;
    }

    @Override
    public int hashCode() {
        int result = mentortName != null ? mentortName.hashCode() : 0;
        result = 31 * result + (studentNames != null ? studentNames.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MentorStudent{" +
                "mentortName='" + mentortName + '\'' +
                ", studentNames=" + studentNames +
                '}';
    }
    // TODO
}
