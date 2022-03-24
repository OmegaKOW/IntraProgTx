import Model.Post;
import persistence.DAOJpa;
import service.PostService;
import service.PostUserService;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        final DAOJpa dao = new DAOJpa();

        PostService ps = new PostService(dao);
        PostUserService pus = new PostUserService(dao);

        var posteurId = pus.createPostUser("Posteur 1");
        var commentateurId = pus.createPostUser("Commentateur 1");
        var postId = ps.createPost("Mon post", pus.getPostUser(posteurId));

        ps.addComment(commentateurId, postId, "Mon commentaire 1");

        final Post post = ps.getPostWithComments(postId);
        System.out.println(post.toString());
    }
}
