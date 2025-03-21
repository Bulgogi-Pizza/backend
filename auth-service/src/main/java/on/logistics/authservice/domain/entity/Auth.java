package on.logistics.authservice.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import on.logistics.authservice.application.dtos.AuthSignupRequestDto;
import on.logistics.authservice.domain.vo.Username;
import on.logistics.authservice.enums.AuthRole;
import on.logistics.authservice.global.domain.BaseEntity;
import on.logistics.authservice.infrastructure.feign.dtos.UserCreateResponse;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "p_auth")
@Builder(access = AccessLevel.PROTECTED)
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

    @Embedded
    private Username username;
    private String password;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private AuthRole role;

    public static Auth from(
        AuthSignupRequestDto authRequestDto,
        UserCreateResponse userResponse,
        String encodedPassword
    ) {
        return Auth.builder()
            .userId(userResponse.userId())
            .username(authRequestDto.username())
            .password(encodedPassword)
            .role(authRequestDto.role())
            .build();
    }

}
