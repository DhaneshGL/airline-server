package com.dhanesh.social.services;

import com.dhanesh.social.models.FriendRequest;
import com.dhanesh.social.repositories.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    public List<FriendRequest> getAllFriendRequests() {
        return friendRequestRepository.findAll();
    }

    public Optional<FriendRequest> getFriendRequestById(Long id) {
        return friendRequestRepository.findById(id);
    }

    public FriendRequest createFriendRequest(FriendRequest friendRequest) {
        return friendRequestRepository.save(friendRequest);
    }

    public FriendRequest updateFriendRequest(FriendRequest friendRequest) {
        return friendRequestRepository.save(friendRequest);
    }

    public void deleteFriendRequest(Long id) {
        friendRequestRepository.deleteById(id);
    }
}