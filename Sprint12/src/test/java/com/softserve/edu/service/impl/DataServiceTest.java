package com.softserve.edu.service.impl;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
//Unit testing
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DataServiceTest {
    private MarathonService marathonService;
    private DataService dataService;
    private static List<String> sprints ;
    private static List<String> students ;
    private static List<String> mentors ;
    @Autowired
    public DataServiceTest(MarathonService marathonService, DataService dataService) {
        this.marathonService = marathonService;
        this.dataService = dataService;
    }

    @BeforeEach
    void setUp() {
        dataService.setStudents(new ArrayList<>());
        dataService.setMentors(new ArrayList<>());
        dataService.setSprints(new ArrayList<>());
        dataService.setSolutions(new ArrayList<>());
        dataService.setCommunication(new ArrayList<>());
        sprints = List.of("Sprint01", "Sprint02", "Sprint03");
        students= List.of("Oleksandr", "Taras", "Petro");
        mentors = List.of("Mykola", "Nataliia", "Yaroslav");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addStudent() {
        students.stream().forEach(s->dataService.addStudent(s));
        List<String > expected = new ArrayList<>(students);
        assertThrows(IllegalArgumentException.class,()->dataService.getStudentId("None"));
        assertEquals(expected,dataService.getStudents()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList()));
    }

    @Test
    void addMentor() {
        dataService.addMentor("Oleg");
        dataService.addMentor("Misha");
        dataService.addMentor("Andriy");

        assertThrows(IllegalArgumentException.class,()->dataService.getMentorId("None"));
        List<String> expected = new ArrayList<>(Arrays.asList("Oleg","Misha","Andriy") );

        assertEquals(expected,dataService.getMentors()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList()));
    }

    @Test
    void addSprint() {
        for (String sprint : sprints ){
            dataService.addSprint(sprint);
        }
        assertEquals(sprints,dataService.getSprints()
                .stream()
                .map(Entity::getName)
                .collect(Collectors.toList()));
    }

    @Test
    void addCommunication() {
        students.forEach(e->dataService.addStudent(e));
        mentors.forEach(e->dataService.addMentor(e));
        HashMap<Integer,Integer> expectedMap = new HashMap<>();
        for(int i=0;i<3;i++){
            dataService.addCommunication(students.get(i),mentors.get(i));
            expectedMap.put(dataService.getStudentId(students.get(i)),
                    dataService.getMentorId(mentors.get(i)));
        }
        assertEquals(expectedMap,dataService.getCommunication()
                .stream()
                .collect(Collectors.toMap(Communication::getIdStudent,(Communication::getIdMentor))));
    }

    @Test
    void addSolution() {
    }
}