package com.descards.flashcards.model.nonentity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public class IntervalFactory {

  private final SchedulingAlgorithm schedulingAlgorithm;

  public RepetitionInterval getObject() {
    return new RepetitionInterval(schedulingAlgorithm);
  }
}