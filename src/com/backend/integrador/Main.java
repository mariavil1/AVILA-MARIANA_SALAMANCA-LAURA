package com.backend.integrador;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.dao.impl.OdontologoDaoEnMemoria;
import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public OdontologoService logica;
    public static void main(String[] args) {
        IDao<Odontologo> dao = new OdontologoDaoH2();

        OdontologoService logica = new OdontologoService(dao);

        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

            Odontologo odontologo = new Odontologo( 123, "Jose", "Perez");
            Odontologo odontologoCreado = logica.registrarOdontologo(odontologo);
            System.out.println(odontologoCreado.toString());

            Odontologo odontologoBuscado = logica.buscarOdontologPorId(odontologoCreado.getId());
            System.out.println(odontologoBuscado.toString());

            List<Odontologo> odontologosBuscados = logica.listarTodosLosOdontologos();
            System.out.println(odontologosBuscados);

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
}
