package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springbootlesson3.services.IssueService;

@Controller
public class IssuesUIController {

    private final IssueService issueService;

    @Autowired
    public IssuesUIController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/ui/issues")
    public String getIssuesPage(Model model) {
        model.addAttribute("issues", issueService.getAllIssues());
        return "issues";
    }
}
