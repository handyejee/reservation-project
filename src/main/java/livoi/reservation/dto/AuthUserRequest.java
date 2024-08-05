package livoi.reservation.dto;

import livoi.reservation.domain.MemberEntity;
import livoi.reservation.domain.PartnerEntity;
import lombok.Data;

public class AuthUserRequest {


    @Data
    public static class signIn { // 로그인
        private String username;
        private String password;

    }

    @Data
    public static class signUp {
        private String username;
        private String password;
        private String role;

        // SignUp 객체 -> MemberEntity 객체로 변환
        public MemberEntity toMemberEntity() {
            return MemberEntity.builder()
                    .userId(this.username)
                    .userPwd(this.password)
                    .role(this.role)
                    .build();
        }

        // SignUp 객체 -> PartnerEntity 객체로 변환
        public PartnerEntity toPartnerEntity() {
            return PartnerEntity.builder()
                    .partnerId(this.username)
                    .partnerPwd(this.password)
                    .role(this.role)
                    .build();
        }
    }
}
