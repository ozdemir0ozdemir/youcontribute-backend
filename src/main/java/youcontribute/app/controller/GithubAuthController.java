package youcontribute.app.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;
import youcontribute.app.model.GithubAccessTokenReponse;
import youcontribute.app.model.GithubGetAccessTokenRequest;
import youcontribute.app.service.GithubService;

@RestController
@RequestMapping("/auth/github")
public record GithubAuthController ( GithubService githubService ) {


    @GetMapping
    public void authorize(HttpServletResponse response) {
        response.setHeader("Location", githubService.getAuthorizeUrl());
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }

    @PostMapping
    public GithubAccessTokenReponse getAccessToken(@RequestBody GithubGetAccessTokenRequest request) {
        return this.githubService.getAccessToken(request.getCode());

    }

}
