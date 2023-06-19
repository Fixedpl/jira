package pl.nlo.jira.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nlo.jira.dto.CommentDTO;
import pl.nlo.jira.entity.Comment;
import pl.nlo.jira.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> createComment(@RequestBody CommentDTO commentDTO, @PathVariable("id") Integer id) {
        commentService.createComment(commentDTO, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsByTaskId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.getCommentsByTaskId(id));
    }
}
