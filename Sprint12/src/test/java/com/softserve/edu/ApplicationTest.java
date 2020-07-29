package com.softserve.edu;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTest {
    // integration test. Tests beans autowiring
    // beans
    private MarathonService marathonService;
    private DataService dataService;
    // test data
    private static List<String> students = List.of("Oleksandr", "Taras", "Petro");
    private static List<String> mentors = List.of("Mykola", "Nataliia", "Yaroslav");
    private static List<String> sprints = List.of("Sprint01", "Sprint02", "Sprint03");
    private static List<List<Integer>> scores = List.of(
            List.of(100, 95, 90),
            List.of(90, 92, 100),
            List.of(100, 80, 90)
    );
    private static List<Double> avgScores = List.of(95.0, 94.0, 90.0);

    @Autowired
    public ApplicationTest(MarathonService marathonService, DataService dataService
    ){
        this.marathonService = marathonService;
        this.dataService = dataService;
        initData();
    }

    private void initData() {
        students.forEach(s -> dataService.addStudent(s));
        mentors.forEach(m -> dataService.addMentor(m));
        sprints.forEach(s -> dataService.addSprint(s));

        for (int i = 0; i < students.size(); i++) {
            String student = students.get(i);
            Iterator<Integer> it = scores.get(i).iterator();
            sprints.forEach(s -> dataService.addSolution(student, s, it.next()));
        }

        String mentor1 = mentors.get(0);
        students.forEach(s -> dataService.addCommunication(s, mentor1));
    }

    @Test
    void checkGetStudents() {
        assertEquals(
                new HashSet<>(students),
                new HashSet<>(marathonService.getStudents())
        );
    }
    @Test
    void checkGetMentors() {
        assertEquals(
                new HashSet<>(mentors),
                new HashSet<>(marathonService.getMentors())
        );
    }

    @BeforeEach
    void restart(){
        dataService.setCommunication(new ArrayList<>());
        dataService.setMentors(new ArrayList<>());
        dataService.setSolutions(new ArrayList<>());
        dataService.setSprints(new ArrayList<>());
        dataService.setStudents(new ArrayList<>());
        initData();
    }
    @Test
    void checkStudentResult() {
        List<SprintScore> listSprintScore1  = new ArrayList<>();
        List<SprintScore> listSprintScore2 = new ArrayList<>();
        List<SprintScore> listSprintScore3 = new ArrayList<>();
        for(int i=0;i<sprints.size();i++){
            listSprintScore1.add(new SprintScore(sprints.get(i),scores.get(0).get(i)));
            listSprintScore2.add(new SprintScore(sprints.get(i),scores.get(1).get(i)));
            listSprintScore3.add(new SprintScore(sprints.get(i),scores.get(2).get(i)));
        }
        assertEquals(
                new StudentScore("Oleksandr",listSprintScore1),
                marathonService.studentResult("Oleksandr")
        );
        assertEquals(
                new StudentScore("Taras",listSprintScore2),
                marathonService.studentResult("Taras")
        );
        assertEquals(
                new StudentScore("Petro",listSprintScore3),
                marathonService.studentResult("Petro")
        );
    }
    @Test
    void checkAllStudentResults(){
        List<StudentScore> list = new ArrayList<>();
        for(int i=0;i<students.size();i++){
            List<Integer> studentScores = scores.get(i);
            String studentName = students.get(i);
            List<SprintScore> sprintScoreList = new ArrayList<>();
            for(int j=0;j<sprints.size();j++){
                sprintScoreList.add(new SprintScore(sprints.get(j),studentScores.get(j)));
            }
            list.add(new StudentScore(studentName,sprintScoreList));
        }
        assertEquals(list,marathonService.allStudentsResult());

    }
    @Test
    void checkStudentAverage(){
        for (int i =0;i<students.size();i++){
            AverageScore expected = new AverageScore(students.get(i),avgScores.get(i));
            assertEquals(expected,marathonService.studentAverage(students.get(i)));
        }
    }
    @Test
    void checkAllStudentsAverage(){
        List<AverageScore> expected = new ArrayList<>();
        for(int i=0;i<students.size();i++){
            expected.add(new AverageScore(students.get(i),avgScores.get(i)));
        }
        assertEquals(expected,marathonService.allStudentsAverage());
    }
    @Test
    void checkMentorStudent(){

        MentorStudent expected = new MentorStudent("Mykola",students);
        assertEquals(expected,marathonService.mentorStudents("Mykola"));
        MentorStudent expected1 = new MentorStudent("Yaroslav",new ArrayList<>());
        assertEquals(expected1,marathonService.mentorStudents("Yaroslav"));
    }


}
