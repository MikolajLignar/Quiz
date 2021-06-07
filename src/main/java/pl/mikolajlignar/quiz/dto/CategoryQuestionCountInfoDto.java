package pl.mikolajlignar.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mikolajlignar.quiz.frontend.Difficulty;
import pl.mikolajlignar.quiz.rest.RestTemplate;
import java.net.URI;

@NoArgsConstructor
@ToString
@Log
public class CategoryQuestionCountInfoDto {


    private CategoryQuestionCountInfoDto getCategoryQuestionCount(int categoryId) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api_count.php")
                .queryParam("category", categoryId)
                .build().toUri();
        log.info("Quiz category question count retrieve URL: " + uri);
        CategoryQuestionCountInfoDto result = restTemplate.getForObject(uri, CategoryQuestionCountInfoDto.class);
        log.info("Quiz category question count content: " + result);
        return result;
    }
    public CategoryQuestionCountInfoDto(int easyQuestionCount, int mediumQuestionCount, int hardQuestionCount) {
        this.categoryQuestionCount = new CategoryQuestionCountDto(easyQuestionCount+mediumQuestionCount+hardQuestionCount,
                easyQuestionCount, mediumQuestionCount, hardQuestionCount);
    }

    public int getTotalQuestionCount() {
        return categoryQuestionCount.totalQuestionCount;
    }

    public int getQuestionCountForDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return categoryQuestionCount.totalEasyQuestionCount;
            case MEDIUM:
                return categoryQuestionCount.totalMediumQuestionCount;
            case HARD:
                return categoryQuestionCount.totalHardQuestionCount;
        }
        return 0;
    }


    @JsonProperty("category_id")
    private int categoryId;

    @JsonProperty("category_question_count")
    private CategoryQuestionCountDto categoryQuestionCount;


    @NoArgsConstructor
    @Getter
    @ToString
    public static class CategoryQuestionCountDto {
        @JsonProperty("total_question_count")
        private int totalQuestionCount;
        @JsonProperty("total_easy_question_count")
        private int totalEasyQuestionCount;
        @JsonProperty("total_medium_question_count")
        private int totalMediumQuestionCount;
        @JsonProperty("total_hard_question_count")
        private int totalHardQuestionCount;


        public CategoryQuestionCountDto(int i, int easyQuestionCount, int mediumQuestionCount, int hardQuestionCount) {
        }
    }
}