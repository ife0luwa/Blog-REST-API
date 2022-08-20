package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.PostRepository;
import com.example.myblogtask.repositories.UserRepository;
import com.example.myblogtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public boolean registerUser(UserDetails user) {
        UserDetails userDb = userRepository.findByEmail(user.getEmail());
        if(userDb == null){
            userRepository.save(user);
            System.out.println(user+" Successfully saved");
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public UserDetails getUserById(Long id){
       return userRepository.findById(id).get();
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
//    @Scheduled(cron = "0 * * * * ?")
    public void deactivateUser(Long id) {
        //todo implement scheduling
        if (id != null) userRepository.deleteById(id);
    }
}
