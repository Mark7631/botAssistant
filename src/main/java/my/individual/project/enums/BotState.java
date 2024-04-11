package my.individual.project.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum BotState {
    NO_STATE(0),
    START_STATE( 0),
    FREQUENT_QUESTIONS_STATE(1),
    SOLVE_QUESTION1_STATE(2),
    SOLVE_QUESTION2_STATE(2),
    SOLVE_QUESTION3_STATE(2),
    SOLVE_QUESTION3_YES_STATE(3),
    SOLVE_QUESTION3_NO_STATE(3),
    FREQUENT_PROBLEMS_STATE(1),
    SOLVE_PROBLEM1_STATE(2),
    SOLVE_PROBLEM2_STATE(2),
    SOLVE_PROBLEM3_STATE(2),
    SOLVE_PROBLEM3_YES_STATE(3),
    SOLVE_PROBLEM3_NO_STATE(3);

    private final int levelInTreeOfMessage;
}
