package com.SpringProject.RedditClone.service;

import com.SpringProject.RedditClone.dto.SubredditDto;
import com.SpringProject.RedditClone.exceptions.SpringRedditException;
import com.SpringProject.RedditClone.mapper.SubredditMapper;
import com.SpringProject.RedditClone.model.Subreddit;
import com.SpringProject.RedditClone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//@Service
//@AllArgsConstructor
//@Slf4j
//public class SubredditService {
//
//
//    private final SubredditRepository subredditRepository;
//    private final SubredditMapper subredditMapper;
//
//    @Transactional
//    public SubredditDto save(SubredditDto subredditDto) {
//        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
//        subredditDto.setId(save.getId());
//        return subredditDto;
//    }
//
//    @Transactional(readOnly = true)
//    public List<SubredditDto> getAll() {
//        return subredditRepository.findAll()
//                .stream()
//                .map(subredditMapper::mapSubredditToDto)
//                .collect(toList());
//    }
//
////    private SubredditDto mapToDto(Subreddit subreddit){
////        return SubredditDto.builder().name(subreddit.getName())
////                .id(subreddit.getId())
////                .numberOfPosts(subreddit.getPosts().size())
////                .build();
////    }
////    private Subreddit mapSubredditDto(SubredditDto subredditDto){
////        return Subreddit.builder().name(subredditDto.getName())
////                .description(subredditDto.getDescription())
////                .build();
////
////    }
//    public SubredditDto getSubreddit(Long id) {
//        Subreddit subreddit = subredditRepository.findById(id)
//                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
//        return subredditMapper.mapSubredditToDto(subreddit);
//    }
//}


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}