package on.logistics.deliverymanagerservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_user_summary")
public class UserSummary {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String slackEmail;

    @Builder
    private UserSummary(UUID id, String nickname, String slackEmail) {
        this.id = id;
        this.nickname = nickname;
        this.slackEmail = slackEmail;
    }

    public static UserSummary create(UUID id, String nickname, String slackEmail) {
        return UserSummary.builder()
            .id(id)
            .nickname(nickname)
            .slackEmail(slackEmail)
            .build();
    }
}
