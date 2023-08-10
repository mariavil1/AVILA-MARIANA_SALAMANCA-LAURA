package com.backend.integrador.test;


import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {

    private static Connection connection = null;

    @BeforeAll
    static void doBefore(){
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @Test
    public void deberiaHaberUnaListaNoVacia(){
        Odontologo odontologo1 = new Odontologo(12, "camilo", "apellidito");
        Odontologo odontologo2 = new Odontologo(12, "camila", "apellidote");
        Odontologo odontologo3 = new Odontologo(12, "camile", "apellidon");
        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);
        odontologoService.registrarOdontologo(odontologo3);

        List<Odontologo> odontologosTest = odontologoService.listarTodosLosOdontologos();
        assertFalse(odontologosTest.isEmpty());
        assertTrue(odontologosTest.size() >= 1);
        assertTrue(odontologosTest.size() == 3);
    }


}
