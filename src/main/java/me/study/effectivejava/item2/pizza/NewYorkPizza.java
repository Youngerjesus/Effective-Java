package me.study.effectivejava.item2.pizza;

public class NewYorkPizza extends Pizza{
    public enum Size {SMALL, MEDIUM, LARGE}
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;
        public Builder(Size size) {
            this.size = size;
        }

        @Override
        Pizza build() {
            return new NewYorkPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public NewYorkPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
