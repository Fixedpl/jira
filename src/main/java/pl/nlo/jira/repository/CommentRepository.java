package pl.nlo.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nlo.jira.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
