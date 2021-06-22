package me.study.effectivejava.item2;

public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int cabohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수는 기본값 세팅을 한다.
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int cabohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder setFat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder setSodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder setCabohydrate(int cabohydrate) {
            this.cabohydrate = cabohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(servingSize, servings, calories, fat, sodium, cabohydrate);
        }
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int cabohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.cabohydrate = cabohydrate;
    }
}
