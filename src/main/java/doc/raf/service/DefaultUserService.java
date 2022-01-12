package doc.raf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import doc.raf.dao.UserRepositoey;
import doc.raf.entities.User;
@Service("UserService")
public class DefaultUserService implements UserService {

    @Autowired
    UserRepositoey userRepo;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void register(User user) {
                
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRoles("ROLE_USER");
        userRepo.save(user);

    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepo.findByUsername(email)!=null ? true : false;
    }
    
}
