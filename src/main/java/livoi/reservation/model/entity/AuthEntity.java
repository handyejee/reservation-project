package livoi.reservation.model.entity;

import lombok.Data;

import java.util.List;

public class AuthEntity {


    @Data
    public static class signIn { // 로그인
        private String username;
        private String password;

    }

    @Data
    public static class signUp {
        private String username;
        private String password;
        private List<String> roles;

        // SignUp 객체 -> MemberEntity 객체로 변환
        public MemberEntity toMemberEntity() {
            return MemberEntity.builder()
                    .userId(this.username)
                    .userPwd(this.password)
                    .roles(this.roles)
                    .build();
        }

        // SignUp 객체 -> PartnerEntity 객체로 변환
        public PartnerEntity toPartnerEntity() {
            return PartnerEntity.builder()
                    .partnerId(this.username)
                    .partnerPwd(this.password)
                    .roles(this.roles)
                    .build();
        }
    }
}
