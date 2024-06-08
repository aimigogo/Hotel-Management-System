package hms_backend.service;

import hms_backend.model.BaseModel;
import hms_backend.model.Stock;
import hms_backend.model.User;
import hms_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User>implements UserService{

    private UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return null;
    }


    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User user) {

    }



    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
