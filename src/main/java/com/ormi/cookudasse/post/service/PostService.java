package com.ormi.cookudasse.post.service;


import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.post.dto.request.PostRequest;
import com.ormi.cookudasse.post.dto.response.PostSaveResponse;
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


    public void createPost(PostRequest postRequest, User user, MultipartFile file) {
//        User findUser = userRepository.findById(user.getUserId~); // TODO 추후 로그인 방식 정해지면 수정 필요(entity X dto 생성하거나 user의 id 만 받아온 뒤, 해당 UserRepository 에서 조회해서 가져온 user를 저장!

        PostDetail postDetail = PostDetail.builder()
                .postTitle(postRequest.getPostTitle())
                .foodCategory(postRequest.getFoodCategory())
                .ingredients(postRequest.getIngredients())
                .recipe(postRequest.getRecipe())
                .build();

        Post post = Post.builder()
//                        .user(findUser)
                .postDetail(postDetail)
                // 생성 시간 설정
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
        postDetail.setPostLike(postDetail.getPostLike() + 1);
        postDetailRepository.save(postDetail);
        return postDetail.getPostLike();
    }
    @Transactional
    public int incrementView(Long postId) {
        Post post = getPostById(postId);
        PostDetail postDetail = post.getPostDetail();
        postDetail.setPostView(postDetail.getPostView() + 1);
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
        PostDetail updatedPostDetail = post.getPostDetail();
        updatedPostDetail.setPostTitle(request.getPostTitle());
        updatedPostDetail.setFoodCategory(request.getFoodCategory());
        updatedPostDetail.setIngredients(request.getIngredients());
        updatedPostDetail.setRecipe(request.getRecipe());
        postDetailRepository.save(updatedPostDetail);
        post.setPostDetail(updatedPostDetail);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
