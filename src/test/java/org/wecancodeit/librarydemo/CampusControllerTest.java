package org.wecancodeit.librarydemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wecancodeit.librarydemo.models.Campus;
import org.wecancodeit.librarydemo.repositories.BookRepository;
import org.wecancodeit.librarydemo.repositories.CampusRepository;
import org.wecancodeit.librarydemo.rest.controllers.CampusRestController;


import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CampusControllerTest {
    @Mock
    private CampusRepository campusRepo;
    @Mock
    private BookRepository bookRepo;

    @InjectMocks
    private CampusRestController underTest;

    private Campus testCampus;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCampus = new Campus("TestCampus");
    }

    @Test
    public void shouldReturnAllCampuses() {
        when(campusRepo.findAll()).thenReturn(Collections.singletonList(testCampus));
        Collection<Campus> result = underTest.getCampuses();
        assertThat(result).containsOnly(testCampus);
    }

    @Test
    public void fetchAllEndpointReturnsAllCampuses() throws Exception {
        when(campusRepo.findAll()).thenReturn(Collections.singletonList(testCampus));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/campuses/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].location", is("TestCampus")));

    }

    @Test
    public void retrieveByIdShouldReturnASpecificCampusById() {
        when(campusRepo.findById(1L)).thenReturn(Optional.of(testCampus));
        Campus result = underTest.getCampus(1L);
        assertThat(result).isEqualTo(testCampus);
    }

    @Test
    public void fetchByIdEndpointReturnASpecificBook() throws Exception {
        when(campusRepo.findById(1L)).thenReturn(Optional.of(testCampus));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/campuses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.location", is("TestCampus")));
    }

}