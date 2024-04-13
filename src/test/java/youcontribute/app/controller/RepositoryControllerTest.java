package youcontribute.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import youcontribute.app.controller.request.CreateRepositoryRequest;
import youcontribute.app.model.Repository;
import youcontribute.app.service.RepositoryService;

import java.util.Collections;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RepositoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class RepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private RepositoryService service;



    @Test
    void it_should_list_repositories() throws Exception {
        Repository repo = Repository.builder()
                .repository("youcontribute")
                .organization("ozdemir0ozdemir")
                .build();
        given(this.service.findAll()).willReturn(Collections.singletonList(repo));


        this.mockMvc.perform(get("/repository"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].repository").value("youcontribute"))
                .andExpect(jsonPath("$[0].organization").value("ozdemir0ozdemir"));

    }

    @Test
    void it_should_create_repository() throws Exception {
        //given
        String repository = "octocat";
        String organization = "github";
        CreateRepositoryRequest request = CreateRepositoryRequest.builder()
                .repository(repository)
                .organization(organization)
                .build();

        doNothing().when(this.service).create(repository, organization);

        //when
        this.mockMvc.perform( post("/repository")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect( status().isCreated() );

        //then

    }

}