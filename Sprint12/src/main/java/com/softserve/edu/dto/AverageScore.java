package com.softserve.edu.dto;

public class AverageScore {
    private final String studentName;
    private final double avgScore;

    public AverageScore(String studentName, double avgScore) {
        this.studentName = studentName;
        this.avgScore = avgScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getAvgScore() {
        return avgScore;
    }
    // TODO

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AverageScore)) return false;

        AverageScore that = (AverageScore) o;

        if (Double.compare(that.avgScore, avgScore) != 0) return false;
        return studentName != null ? studentName.equals(that.studentName) : that.studentName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = studentName != null ? studentName.hashCode() : 0;
        temp = Double.doubleToLongBits(avgScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
