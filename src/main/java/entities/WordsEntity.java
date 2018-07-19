package entities;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class WordsEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(name ="word", nullable = false)
        private String word;
        @Column(name="level", nullable= false)
        private String level;

    public WordsEntity() {
    }

    public WordsEntity(String word, String level) {
        this.word = word;
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
        public String toString() {
            return "Course{" +
                    "id=" + id +
                    ", word='" + word + '\'' +
                    ", level='" + level +
                    '}';
        }
    }

