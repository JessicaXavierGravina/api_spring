package br.com.jessica.gestao_cursos.modules.professor.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.jessica.gestao_cursos.modules.professor.dto.CreateCursoDTO;
import br.com.jessica.gestao_cursos.modules.professor.entities.ProfessorEntity;
import br.com.jessica.gestao_cursos.modules.professor.repositories.ProfessorRepository;
import br.com.jessica.gestao_cursos.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestCreateCursoController {
private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProfessorRepository professorRepository;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception{

        var company = ProfessorEntity.builder()
            .description("PROFESSOR_DESCRIPTION")
            .email("email@professor.com")
            .password("1234567890")
            .username("PROFESSOR_USERNAME")
            .name("PROFESSOR_NAME").build();

        company = professorRepository.saveAndFlush(company);

        var createdCursoDTO = CreateCursoDTO.builder()
        .skill_necessaria("SKILL_NECESSARIA_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/professor/curso/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createdCursoDTO))
            .header("Authorization", TestUtils.generateToken(company.getId(), "J4V4curs0s"))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception{
        var createdJobDTO = CreateCursoDTO.builder() 
        .skill_necessaria("SKILL_NECESSARIA_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        mvc.perform(MockMvcRequestBuilders.post("/professor/curso/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createdJobDTO))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "J4V4curs0s")))    
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }    
}