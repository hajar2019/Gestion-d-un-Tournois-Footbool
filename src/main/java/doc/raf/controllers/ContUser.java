package doc.raf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import doc.raf.dao.UserRepositoey;
import doc.raf.entities.User;

@Controller
public class ContUser {
    @Autowired 
    UserRepositoey userRepo;

    @GetMapping(value = "/user")
    public String gettAllEquipe(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("lesUsers", users);
        return "user";
    }
    //////////////// Add User ////////

    @GetMapping(value = "/userAdd")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @PostMapping("/registerUser")
    public String saveEquipe(User user) {
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        userRepo.save(user);
        return "redirect:/user";
    }

    ///////////////// Fin Add User ////////

    /// ######## SUPPRIMER UN user PAR SON ID 0

    @GetMapping(value = "/deleteUser")
    public String deleteById(Long id) {
        userRepo.deleteById(id);
        return "redirect:/user";
    }

    /// ######## Edit UN user PAR SON ID 0

    @GetMapping(value = "/editUser")
    public String showEditEquipe(Model model, Long id) {
        User user = userRepo.findById(id).get();
        model.addAttribute("user", user);
        return "userEdit";
    }

    @GetMapping(value = "userLogin")
    public String loginUser() {

        return "userLogin";
    }

    @GetMapping(value = "userRegister")
    public String registerNewUser() {

        return "userRegister";
    }
}
