package pl.nlo.jira.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.mapper.CommentMapper;
import pl.nlo.jira.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public void createComment(CommentDTO commentDTO) {
        commentRepository.save(commentMapper.toEntity(commentDTO));
    }
}
