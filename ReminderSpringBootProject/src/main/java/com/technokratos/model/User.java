package com.technokratos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, name = "username")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

    @Column(unique = true, name = "telegram_contact")
    private String telegramContact;

    @Column(unique = true, name = "telegram_chat_id")
    private String telegramChatId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reminder> reminders;
}
