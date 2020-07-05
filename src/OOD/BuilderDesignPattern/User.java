package OOD.BuilderDesignPattern;

import java.util.Date;

public final class User {
    private final String username, password;
    private final String nickname, email, address, phone;
    private final Date birthday;

    private User(UserBuilder ub) {
        this.username = ub.username;
        this.password = ub.password;
        this.nickname = ub.nickname;
        this.email = ub.email;
        this.address = ub.address;
        this.phone = ub.phone;
        this.birthday = ub.birthday;
    }

    private static class UserBuilder {
        private final String username, password;
        private String nickname, email, address, phone;
        private Date birthday;

        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public UserBuilder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public UserBuilder toUserBuilder() {
        return new UserBuilder(this.username, this.password).
                nickname(this.nickname).
                email(this.email).
                address(this.address).
                phone(this.phone).
                birthday(this.birthday);
    }

    public static void main(String[] args) {
        User user1 = new UserBuilder("testUsername", "testPassword").
                email("123@gmail.com").
                nickname("Zhang San").
                build();
        System.out.println(user1.nickname);
        user1 = user1.toUserBuilder().nickname("Li Si").build();
        System.out.println(user1.nickname);
    }
}
