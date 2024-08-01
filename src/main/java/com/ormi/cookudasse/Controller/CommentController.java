package com.ormi.cookudasse.Controller;

import com.ormi.cookudasse.Dto.CommentDto;
import com.ormi.cookudasse.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping    //새 댓글 생성
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        CommentDto createdComment = commentService.createComment(commentDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping     //전체 댓글 가져오기
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

//    @PutMapping("/{comment_id}")    // 해당 id의 댓글 수정
//    public ResponseEntity<CommentDto> updateComment(@PathVariable("comment_id") Integer comment_id, @RequestBody CommentDto commentDto){
//        return CommentService.updateComment(comment_id, commentDto)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{comment_id}") //해당 id의 댓글 삭제
    public ResponseEntity<Void> deleteComment(@PathVariable("comment_id") int comment_id){
        boolean deleted = commentService.deleteComment(comment_id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }



}

