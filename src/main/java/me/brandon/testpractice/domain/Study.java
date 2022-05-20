package me.brandon.testpractice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Study {
    @Id
    @GeneratedValue
    private Long id;

    private StudyStatus status = StudyStatus.DRAFT;

    private int limitNumber;
    private String name;

    public Study(int limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit 은 0보다 커야한다.");
        }
        this.limitNumber = limit;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Study(int limit, String name) {
        this.limitNumber = limit;
        this.name = name;
    }

    public int getLimit() {
        return limitNumber;
    }

    public void setLimit(int limit) {
        this.limitNumber = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Study{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setOwnerId(Long memberId) {
    }

    public void open() {
    }
}
