package com.dhanesh.social.controllers;

import com.dhanesh.social.models.FriendRequest;
import com.dhanesh.social.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend-requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @GetMapping
    public List<FriendRequest> getAllFriendRequests() {
        return friendRequestService.getAllFriendRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendRequest> getFriendRequestById(@PathVariable Long id) {
        return friendRequestService.getFriendRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FriendRequest createFriendRequest(@RequestBody FriendRequest friendRequest) {
        return friendRequestService.createFriendRequest(friendRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FriendRequest> updateFriendRequest(@PathVariable Long id, @RequestBody FriendRequest friendRequest) {
        return friendRequestService.getFriendRequestById(id)
                .map(existingRequest -> {
                    existingRequest.setRequestTo(friendRequest.getRequestTo());
                    existingRequest.setRequestFrom(friendRequest.getRequestFrom());
                    existingRequest.setRequestStatus(friendRequest.getRequestStatus());
                    return ResponseEntity.ok(friendRequestService.updateFriendRequest(existingRequest));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long id) {
        if (friendRequestService.getFriendRequestById(id).isPresent()) {
            friendRequestService.deleteFriendRequest(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}