package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/write")
    public String writeCredential (Authentication auth, Credential credential, RedirectAttributes ra) {
        if (credential.getCredentialId() == null) {
            credential.setUserId(userService.getUserId(auth.getName()));
            this.credentialService.addCredential(credential);
            ra.addFlashAttribute("successMessage", "A new credential has been added successfully");
        } else {
            this.credentialService.updateCredential(credential);
            ra.addFlashAttribute("successMessage", "The credential has been edited successfully");
        }
        return "redirect:/home#nav-credentials";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, RedirectAttributes ra){
        credentialService.deleteCredential(credentialId);
        ra.addFlashAttribute("successMessage", "The credential has been deleted successfully");
        return "redirect:/home#nav-credentials";
    }
}
