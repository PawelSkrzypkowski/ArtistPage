package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pawelskrzypkowski.entity.MailingMember;
import pl.pawelskrzypkowski.repository.MailingMemberRepository;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailingMemberRepository mailingMemberRepository;

    @PostMapping("/member/add")
    @ResponseBody
    public MailingMember addMember(MailingMember mailingMember){
        return mailingMemberRepository.save(mailingMember);
    }
}
