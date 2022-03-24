package service;

import Model.PostComment;
import Model.PostUser;
import persistence.DAO;
import persistence.DAOJpa;

import java.util.List;

public class PostUserService {
    private DAO dao;


    public PostUserService(DAOJpa daoJpa){this.dao = daoJpa;}

    public long createPostUser(String name){
        return dao.createPostUser(name);
    }

    public PostUser getPostUser(long posteurId) {
        return dao.getUserById(posteurId);
    }
}
