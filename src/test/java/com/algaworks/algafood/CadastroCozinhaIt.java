package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIt {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
              .when()
                .get()
              .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void deveConter2Cozinhas_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", hasSize(2))
                .body("nome", hasItems("Americana", "Tailandesa"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha(){
        given()
                .body("{\"nome\" : \"Chinesa\"}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente(){
        given()
                .pathParam("cozinhaId", 2)
                .accept(ContentType.JSON)
            .when()
                .get("{cozinhaId}")
            .then()
                .body("nome", equalTo("Americana"))
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente(){
        given()
                .pathParam("cozinhaId", 100)
                .accept(ContentType.JSON)
            .when()
                .get("{cozinhaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados(){
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaService.salvar(cozinha1);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaService.salvar(cozinha2);
    }

}
