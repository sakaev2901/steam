package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String htmlText;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    @ToString.Exclude
    private Game game;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "manual")
    private List<Rating> ratings = new ArrayList<>();
    @ManyToOne
    private User user;
    private Double averageRating;

    public Double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        } else {
            double sum = 0;
            for (Rating rating: ratings) {
                sum+= rating.getValue();
            }
            return sum / ratings.size();
        }

    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
