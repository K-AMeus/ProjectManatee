package ee.cyber.manatee.model;

import ee.cyber.manatee.statemachine.InterviewType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.OffsetDateTime;


/**
 * Represents an interview entity as part of a candidate application process.
 * This entity stores information about individual interviews scheduled for applications.
 * Each interview is linked to a specific application.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview {

    @Id
    @GeneratedValue
    private Integer id;


    /**
     * Represents the many-to-one relationship between interviews and applications.
     * Each interview must be associated with one application, hence the @NotNull and @JoinColumn annotations.
     * 'application_id' is a foreign key in the Interview table that references the 'id' of an Application.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;


    @NotNull
    private OffsetDateTime interviewTime;

    @NotNull
    private String interviewerName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InterviewType type;
}
