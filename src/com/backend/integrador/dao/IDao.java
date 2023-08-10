package com.backend.integrador.dao;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);
    //metodo pedido en mesas
    List<T> listarTodos();

}

