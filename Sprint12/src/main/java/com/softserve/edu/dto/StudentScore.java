package com.softserve.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentScore {
    private final String studentName;
    private List<SprintScore> sprintScore;

    public StudentScore(String studentName, List<SprintScore> sprintScore) {
        this.studentName = studentName;
        this.sprintScore = new ArrayList<>(sprintScore);
    }

    public String getStudentName() {
        return studentName;
    }

    public List<SprintScore> getSprintScore() {
        return sprintScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentScore)) return false;

        StudentScore that = (StudentScore) o;

        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        return sprintScore != null ? sprintScore.equals(that.sprintScore) : that.sprintScore == null;
    }

    @Override
    public int hashCode() {
        int result = studentName != null ? studentName.hashCode() : 0;
        result = 31 * result + (sprintScore != null ? sprintScore.hashCode() : 0);
        return result;
    }
    // TODO

    @Override
    public String toString() {
        return "StudentScore{" +
                "studentName='" + studentName + '\'' +
                ", sprintScore=" + sprintScore +
                '}';
    }
}
