package hms_backend.service;


import hms_backend.model.User;

public interface UserService extends BaseService<User,Long>{

    User getUserByEmail(String email);
}
