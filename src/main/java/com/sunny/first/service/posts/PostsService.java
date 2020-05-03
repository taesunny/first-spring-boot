package com.sunny.first.service.posts;

import com.sunny.first.domain.posts.Posts;
import com.sunny.first.domain.posts.PostsRepository;
import com.sunny.first.web.dto.PostsResponseDto;
import com.sunny.first.web.dto.PostsSaveRequestDto;
import com.sunny.first.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No post. id=" + id));

        return new PostsResponseDto(entity);
    }
}
