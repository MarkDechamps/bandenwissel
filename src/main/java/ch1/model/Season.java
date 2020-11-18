package ch1.model;

public enum Season {
    WINTER,SUMMER;

    public static Season forValue(String seasonParam) {
        return "winter".equals(seasonParam)?WINTER:SUMMER;
    }
}
