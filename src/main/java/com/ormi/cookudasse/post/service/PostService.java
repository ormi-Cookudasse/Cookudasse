package com.ormi.cookudasse.post.service;


import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.repository.UserRepository;
import com.ormi.cookudasse.post.dto.request.PostRequest;
import com.ormi.cookudasse.post.entitiy.Post;
import com.ormi.cookudasse.post.entitiy.PostDetail;
import com.ormi.cookudasse.post.repository.PostDetailRepository;
import com.ormi.cookudasse.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;
    private final UserRepository userRepository;


    public void createPost(PostRequest postRequest, User user, MultipartFile file) {
    User findUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "사용자를 찾을 수 없습니다."));

        PostDetail postDetail = PostDetail.builder()
                .postTitle(postRequest.getPostTitle())
                .foodCategory(postRequest.getFoodCategory())
                .ingredients(postRequest.getIngredients())
                .recipe(postRequest.getRecipe())
                .build();

        Post post = Post.builder()
                .user(findUser)
                .postDetail(postDetail)
                .build();

        if (file != null && !file.isEmpty()) {
            // 파일 처리 로직
            String fileName = file.getOriginalFilename();
            // 파일을 저장하고 URL을 가져오는 로직
            String fileUrl = saveFileAndGetUrl(file);
            post.setImageUrl(fileUrl);
        }
      
        postRepository.save(post);
    }
    private String saveFileAndGetUrl(MultipartFile file) {
        // 실제 파일 저장 로직 구현
        // 예: 파일 시스템에 저장하거나 클라우드 스토리지에 업로드
        // 저장된 파일의 URL을 반환
        return "file_url_placeholder";
    }

    @Transactional
    public int incrementLike(Long postId) {
        Post post = getPostById(postId);
        PostDetail postDetail = post.getPostDetail();
        postDetail.addPostLike();
        postDetailRepository.save(postDetail);
        return postDetail.getPostLike();
    }
    @Transactional
    public int incrementView(Long postId) {
        Post post = getPostById(postId);
        PostDetail postDetail = post.getPostDetail();
        postDetail.addPostView();
        postDetailRepository.save(postDetail);
        return postDetail.getPostView();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void updatePost(Long id, PostRequest request) {
        Post post = getPostById(id);
        PostDetail postDetail = post.getPostDetail();
    postDetail.updatePostDetail(request);
        postDetailRepository.save(postDetail);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
