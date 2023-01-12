package study.spring.WebServiceWithSpringBootAWS.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.WebServiceWithSpringBootAWS.domain.posts.Posts;
import study.spring.WebServiceWithSpringBootAWS.domain.posts.PostsRepository;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsResponseDto;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsSaveRequestDto;
import study.spring.WebServiceWithSpringBootAWS.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }


    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
