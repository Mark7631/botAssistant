package my.individual.project.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CallbackData {
    START("start", false, BotState.START_STATE, null),
    FREQUENT_QUESTIONS("frequent_questions", false, BotState.FREQUENT_QUESTIONS_STATE, CallbackData.START),
    SOLVE_QUESTION1("solve_question1", false, BotState.SOLVE_QUESTION1_STATE, CallbackData.FREQUENT_QUESTIONS),
    SOLVE_QUESTION2("solve_question2", false, BotState.SOLVE_QUESTION2_STATE, CallbackData.FREQUENT_QUESTIONS),
    SOLVE_QUESTION3("solve_question3", false, BotState.SOLVE_QUESTION3_STATE, CallbackData.FREQUENT_QUESTIONS),
    SOLVE_QUESTION3_YES("solve_question3_yes", false, BotState.SOLVE_QUESTION3_YES_STATE, CallbackData.SOLVE_QUESTION3),
    SOLVE_QUESTION3_NO("solve_question3_no", false, BotState.SOLVE_QUESTION3_NO_STATE, CallbackData.SOLVE_QUESTION3)
    ,
    FREQUENT_PROBLEMS("frequent_problems", false, BotState.FREQUENT_PROBLEMS_STATE, CallbackData.START),
    SOLVE_PROBLEM1("solve_problem1", false, BotState.SOLVE_PROBLEM1_STATE, CallbackData.FREQUENT_PROBLEMS),
    SOLVE_PROBLEM2("solve_problem2", false, BotState.SOLVE_PROBLEM2_STATE, CallbackData.FREQUENT_PROBLEMS),
    SOLVE_PROBLEM3("solve_problem3", false, BotState.SOLVE_PROBLEM3_STATE, CallbackData.FREQUENT_PROBLEMS),
    SOLVE_PROBLEM3_YES("solve_problem3_yes", false, BotState.SOLVE_PROBLEM3_YES_STATE, CallbackData.SOLVE_PROBLEM3),
    SOLVE_PROBLEM3_NO("solve_problem3_no", false, BotState.SOLVE_PROBLEM3_NO_STATE, CallbackData.SOLVE_PROBLEM3);

    private final String callbackDataValue;
    private final boolean isNeedAuth;
    private final BotState state;
    private final CallbackData lastCallbackData;

    public static CallbackData findCallbackDataByValue(String callbackDataValue) {
        List<CallbackData> callbackDataList = Arrays.stream(CallbackData.values())
                .filter(callbackData -> callbackData.getCallbackDataValue().equals(callbackDataValue))
                .toList();
        if (callbackDataList.size() != 1) {
            throw new IllegalStateException("There is no callback data for \"%s\" value!".formatted(callbackDataValue));
        }
        return callbackDataList.get(0);
    }
}
