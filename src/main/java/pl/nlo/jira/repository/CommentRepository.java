package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Query(value = "SELECT c.id AS id," +
//            " c.content AS content," +
//            " c.created_by AS createdBy," +
//            " c.created_date AS createdDate," +
//            " c.last_modified_date AS lastModifiedDate," +
//            " c.task_id AS taskId" +
//            " FROM comments c", nativeQuery = true)
    List<Comment> getCommentsByTaskId(Integer id);
}
