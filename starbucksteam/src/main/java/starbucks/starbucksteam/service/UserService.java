package starbucks.starbucksteam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starbucks.starbucksteam.repository.UserRepository;
import starbucks.starbucksteam.model.User;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        user.setActive(1);
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        userRepository.save(user);
    }

}
