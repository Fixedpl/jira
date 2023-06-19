package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.entity.Comment;
import pl.nlo.jira.mapper.CommentMapper;
import pl.nlo.jira.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    @PersistenceContext
    private final EntityManager entityManager;

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public void createComment(CommentDTO commentDTO, Integer id) {
        commentDTO.setTaskId(id);
        commentRepository.save(commentMapper.toEntity(commentDTO));
    }

    public List<CommentDTO> getCommentsByTaskId(Integer id) {
        List<Comment> comments = commentRepository.getCommentsByTaskId(id);
        return commentMapper.toDTOs(comments);
        //return comments;
    }

//    @Transactional
//    public List<CommentDTO> getCommentsByTaskIdWithAudit(Integer taskId) {
//        AuditReader auditReader = AuditReaderFactory.get(entityManager);
//
//        List<Comment> comments = commentRepository.getCommentsByTaskId(taskId);
//
//        // Utwórz listę identyfikatorów komentarzy
//        List<Integer> commentIds = new ArrayList<>();
//        for (Comment comment : comments) {
//            commentIds.add(comment.getId());
//        }
//
//        comments = auditReader.createQuery()
//                .forRevisionsOfEntity(Comment.class, false, true)
//                .add(AuditEntity.id().in(commentIds))
//                .getResultList();
//
//        for (Comment com : comments) {
//            System.out.println(com);
//        }
//        return null;
//    }

}
