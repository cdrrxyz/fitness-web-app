package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Groups;
import com.example.elec5619fitnesswebapp.repository.GroupRepository;
import com.example.elec5619fitnesswebapp.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private Groups groups;

    @BeforeEach
    public void setUp() {
        Groups groups = new Groups();
        groups.setName("Group test");
    }

    @Test
    public void findallTest() {
        List<Groups> expected = new ArrayList<>();
        expected.add(groups);

        when(groupRepository.findAll()).thenReturn(expected);

        List<Groups> result = groupService.findall();
        assertEquals(expected, result);
    }

    @Test
    public void saveTest() {
        when(groupRepository.save(groups)).thenReturn(groups);

        Groups result = groupService.save(groups);
        assertEquals(groups, result);
    }

    @Test
    public void editTest() {
        when(groupRepository.save(groups)).thenReturn(groups);

        Groups result = groupService.edit(groups);
        assertEquals(groups, result);
    }

}
