package com.dhanesh.social.services;

import com.dhanesh.social.models.Reply;
import com.dhanesh.social.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    public Optional<Reply> getReplyById(Long id) {
        return replyRepository.findById(id);
    }

    public Reply createReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public Reply updateReply(Reply reply) {
        return replyRepository.save(reply);
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}