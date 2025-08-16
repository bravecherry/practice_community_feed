package org.fastcampus.user.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.GetUserListResDto;
import org.fastcampus.user.application.dto.GetUserResDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.jpa.JpaUserListRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JpaUserListRepository jpaUserListRepository;

    @GetMapping("/{userId}")
    public Response<GetUserResDto> createUser(@PathVariable Long userId) {
        GetUserResDto resDto = userService.getUserProfile(userId);
        return Response.ok(resDto);
    }

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserReqDto reqDto) {
        User user = userService.createUser(reqDto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResDto>> getFollowerList(@PathVariable Long userId) {
        List<GetUserListResDto> results = jpaUserListRepository.getFollwerUserList(userId);
        return Response.ok(results);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResDto>> getFollowingList(@PathVariable Long userId) {
        List<GetUserListResDto> results = jpaUserListRepository.getFollwingUserList(userId);
        return Response.ok(results);
    }

}
