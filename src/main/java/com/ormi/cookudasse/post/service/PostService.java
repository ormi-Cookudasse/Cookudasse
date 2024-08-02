package com.ormi.cookudasse.post.service;


import com.ormi.cookudasse.post.dto.request.PostRequest;
import com.ormi.cookudasse.post.dto.response.PostSaveResponse;
import com.ormi.cookudasse.post.entitiy.Post;
import com.ormi.cookudasse.post.entitiy.PostDetail;
import com.ormi.cookudasse.post.entitiy.User;
import com.ormi.cookudasse.post.repository.PostDetailRepository;
import com.ormi.cookudasse.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;

    public PostSaveResponse createPost(PostRequest postRequest, User user, MultipartFile file) {
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

        Post savedPost = postRepository.save(post);

        return new PostSaveResponse(savedPost.getId());

    }
    private String saveFileAndGetUrl(MultipartFile file) {
        // 실제 파일 저장 로직 구현
        // 예: 파일 시스템에 저장하거나 클라우드 스토리지에 업로드
        // 저장된 파일의 URL을 반환
        return "file_url_placeholder";
    }



    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(Long id, PostDetail updatedPostDetail) {
        Post post = getPostById(id);
        PostDetail postDetail = post.getPostDetail();
        postDetail.setPostTitle(updatedPostDetail.getPostTitle());
        postDetail.setFoodCategory(updatedPostDetail.getFoodCategory());
        postDetail.setIngredients(updatedPostDetail.getIngredients());
        postDetail.setRecipe(updatedPostDetail.getRecipe());
        postDetailRepository.save(postDetail);
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePost(Long id, PostRequest postRequest, User user, MultipartFile file) {


    }
}
