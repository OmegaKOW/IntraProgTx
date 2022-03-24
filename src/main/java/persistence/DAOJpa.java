package persistence;

import Model.Post;
import Model.PostComment;
import Model.PostUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DAOJpa implements DAO{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("examenintra");

    @Override
    public <T> void save(T t){
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public <T> void merge(T t) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(t);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public long createPost(String postData, PostUser user) {
        final Post post = Post.builder().postData(postData).user(user).dateTime(LocalDateTime.now()).build();
        save(post);
        return post.getId();
    }

    @Override
    public long createPostComment(PostUser commentateur, String comment) {
        final PostComment postComment = PostComment.builder().commentateur(commentateur).comment(comment).build();
        save(postComment);
        return postComment.getId();
    }

    @Override
    public long createPostUser(String name) {
        final PostUser postUser = PostUser.builder().name(name).build();
        save(postUser);
        return postUser.getId();
    }

    @Override
    public PostUser getUserById(long posteurId) {
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final PostUser posteur = em.find(PostUser.class, posteurId);

        em.getTransaction().commit();
        em.close();
        return posteur;
    }

    @Override
    public Post getPostById(long postId){
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final Post post = em.find(Post.class, postId);

        em.getTransaction().commit();
        em.close();
        return post;
    }

    @Override
    public PostComment getPostCommentById(long postCommentId){
        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final PostComment postComment = em.find(PostComment.class, postCommentId);

        em.getTransaction().commit();
        em.close();
        return postComment;
    }


    @Override
    public void addComment(long commentateurId, long postId, String commentaire) {

        final Post post = getPostWithComments(postId);
        var commentId = createPostComment(getUserById(commentateurId), commentaire);
        post.addComment(getPostCommentById(commentId));
        merge(post);

    }

    @Override
    public Post getPostWithComments(long postId){

        final EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        final TypedQuery<Post> query = em.createQuery(
                "select p from Post p left join fetch p.comments pc where p.id = :postId"
                , Post.class);
        query.setParameter("postId", postId);
        final Post post = query.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return post;

    }


}
