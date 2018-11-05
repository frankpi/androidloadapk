public class UserService implements IUserService {
    private String username;

    @Override
    public String login(String s, String s1) {
        if (s.equals(s1)) {
            username = s;
            return "后端云A:登录成功!";
        }
        return "后端云A:登录失败...";
    }

    @Override
    public String logout() {
        if (TextUtils.isEmpty(username)) {
            return "后端云A:未登录...";
        }
        username = null;
        return "后端云A:注销成功!";
    }
}

