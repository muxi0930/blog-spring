package fun.muxi.community.controller;

import fun.muxi.community.dto.GithubAccessTokenDto;
import fun.muxi.community.dto.GithubUser;
import fun.muxi.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController{

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam String code,
                           @RequestParam String state) {
        GithubAccessTokenDto githubAccessTokenDto = new GithubAccessTokenDto();
        githubAccessTokenDto.setCode(code);
        githubAccessTokenDto.setState(state);
        githubAccessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        githubAccessTokenDto.setClient_id("91d04e55f0825492f8d9");
        githubAccessTokenDto.setClient_secret("930b326dcad9b9562112ab8d50a784c797e52815");
        String accessToken = githubProvider.getAccessToken(githubAccessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.toString());
        return "index";
    }
}
