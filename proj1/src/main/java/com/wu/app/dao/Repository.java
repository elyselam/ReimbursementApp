package com.wu.app.dao;

public interface Repository <KEY, O> {
    O findByID(KEY id);
    O update(O obj);
    KEY add(O obj);
    void delete(O obj);
}