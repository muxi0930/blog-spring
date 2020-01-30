package fun.muxi.community.controller;

import fun.muxi.community.dto.GithubAccessTokenDto;
import fun.muxi.community.dto.GithubUser;
import fun.muxi.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController{

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam String code,
                           @RequestParam String state) {
        GithubAccessTokenDto githubAccessTokenDto = new GithubAccessTokenDto().builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .code(code)
                .state(state)
                .redirect_uri(redirectUri).build();
        String accessToken = githubProvider.getAccessToken(githubAccessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.toString());
        return "index";
    }
}
