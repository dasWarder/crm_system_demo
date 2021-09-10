package com.example.model.report;

import com.example.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class Report {

  @Id
  @Column
  @SequenceGenerator(
      name = "rep_seq",
      sequenceName = "rep_seq",
      initialValue = 1,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rep_seq")
  private Long id;

  @Column(name = "topic")
  @Enumerated(EnumType.STRING)
  private ReportTopic reportTopic;

  @Column
  private String comment;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ReportStatus reportStatus;

  @Column(name = "create_at")
  private LocalDateTime createdAt;

  @Column(name = "status_changed")
  private LocalDateTime statusChanged;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column
  private String response;
}
