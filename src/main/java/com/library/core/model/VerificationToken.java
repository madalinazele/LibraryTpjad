package com.library.core.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tokens")
public class VerificationToken {

    public VerificationToken(String value, User user) {
        this.value = value;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "value", nullable = false)
    private String value;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;
}
