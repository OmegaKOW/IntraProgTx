package service;

import Model.Post;
import Model.PostUser;
import persistence.DAO;
import persistence.DAOJpa;

public class PostService {

    private DAO dao;


    public PostService(DAOJpa daoJpa){this.dao = daoJpa;}

    public long createPost(String postData, PostUser user){
        return dao.createPost(postData, user);
    }

    public void addComment(long commentateurId, long postId, String commentaire){
        dao.addComment(commentateurId, postId, commentaire);
    }

    public Post getPost(long postId){
        return dao.getPostById(postId);
    }

    public Post getPostWithComments(long postId){
        return dao.getPostWithComments(postId);
    }
}
