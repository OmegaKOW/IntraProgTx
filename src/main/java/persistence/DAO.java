package persistence;

import Model.Post;
import Model.PostComment;
import Model.PostUser;

public interface DAO {
    <T> void save(T t);
    <T> void merge(T t);

    long createPost(String postData, PostUser user);

    long createPostComment(PostUser commentateur, String comment);

    long createPostUser(String name);

    PostUser getUserById(long posteurId);

    Post getPostById(long postId);

    PostComment getPostCommentById(long postCommentId);

    void addComment(long commentateurId, long postId, String commentaire);

    Post getPostWithComments(long postId);
}
