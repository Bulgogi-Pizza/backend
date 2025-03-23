package on.logistics.slackservice.domain.entity;

import jakarta.persistence.Column;
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
import on.logistics.slackservice.application.dtos.UpdateSlackMessageRequestDto;
import on.logistics.slackservice.domain.dtos.CreateSlackMessageDto;
import on.logistics.slackservice.global.domain.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "p_slack")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE p_slack SET is_deleted = true WHERE id = ?")
public class Slack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "slack_send_email", nullable = false)
    private String slackSendEmail;

    @Column(name = "slack_receive_email", nullable = false)
    private String slackReceiveEmail;

    @Column(name = "user_send_id", nullable = false)
    private UUID userSendId;

    @Column(name = "user_receive_id", nullable = false)
    private UUID userReceiveId;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    public static Slack create(CreateSlackMessageDto dto) {
        return
            on.logistics.slackservice.domain.entity.Slack.builder()
                .slackSendEmail(dto.slackSendEmail())
                .slackReceiveEmail(dto.slackReceiveEmail())
                .userSendId(dto.userSendId())
                .userReceiveId(dto.userReceiveId())
                .message(dto.message())
                .build();
    }

    public void updateMessage(UpdateSlackMessageRequestDto dto) {
        this.message = dto.message();
    }

    public void deleteSoftly() {
        super.deleteSoftly();
    }
}
