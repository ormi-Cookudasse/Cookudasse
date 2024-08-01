package com.ormi.cookudasse.Service;

import aj.org.objectweb.asm.commons.Remapper;
import com.ormi.cookudasse.Dto.CommentDto;
import com.ormi.cookudasse.Entity.Comment;
import com.ormi.cookudasse.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public static Remapper updateComment(Integer commentId, CommentDto commentDto) {
        return null;
    }

    @Transactional
    public CommentDto createComment(CommentDto commentDto){
        Comment comment = commentDto.toEntityFromDto();
        Comment savedComment = commentRepository.save(comment);
        return CommentDto.fromEntityToDto(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getAllComments(){
        return commentRepository.findAll().stream()
                .map(CommentDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CommentDto> getCommentById(int comment_id){
        return commentRepository.findById(comment_id)
                .map(CommentDto::fromEntityToDto);
    }

    @Transactional
    public boolean deleteComment(int comment_id){
        return commentRepository.findById(comment_id)
                .map(comment->{commentRepository.delete(comment);
                    return true;
                })
                .orElse(false);
    }

//    public Comment save(Long id,CommentDto request, String userName) {
//        Optional<User> userOptional = userRepository.findByEmail(userName);
//        User user;
//        if (userOptional.isPresent()) { // Optional이 값으로 채워져 있는지 확인
//            user = userOptional.get(); // User 객체 추출
//        } else {
//            System.out.println("사용자가 존재하지 않습니다: " + userName);
//            return null;
//        }
//        Article article = blogRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));
//
//        request.setUser(user);
//        request.setArticle(article);
//
//        return commentRepository.save(request.toEntity());
//    }
}
