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
import on.logistics.userservice.application.dtos.CreateUserDto;
import on.logistics.userservice.domain.entity.vo.SlackEmail;
import on.logistics.userservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@Table(name = "p_user")
@SQLDelete(sql = "UPDATE p_user SET is_deleted = true WHERE id = ?")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nickname;
    private SlackEmail slackEmail;

    public static User create(CreateUserDto dto) {
        return User.builder()
            .nickname(dto.nickname())
            .slackEmail(dto.slackEmail())
            .build();
    }

    public String getSlackEmail() {
        return slackEmail.getEmail();
    }

    public void updateNickname(String nickname) {
        if (!this.nickname.equals(nickname) && nickname != null) {
            this.nickname = nickname;
        }
    }

    public void updateSlackEmail(SlackEmail slackEmail) {
        if (!this.slackEmail.equals(slackEmail) && slackEmail.toString() != null) {
            this.slackEmail = slackEmail;
        }
    }
}
