package fun.muxi.community.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class GithubAccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
