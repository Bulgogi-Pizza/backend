package on.logistics.userservice.domain.entity;

import jakarta.persistence.Entity;
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
import on.logistics.userservice.application.dtos.UserCreateRequestDto;
import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@Table(name = "p_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nickname;
    private SlackEmail slackEmail;

    public static User create(UserCreateRequestDto dto) {
        return User.builder()
            .nickname(dto.nickname())
            .slackEmail(dto.slackEmail())
            .build();
    }

    public String getSlackEmail() {
        return slackEmail.getEmail();
    }

}
