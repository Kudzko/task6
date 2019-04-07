package by.epam.javawebtraining.kudzko.task06.model.entity.generated;

public enum  FlowerEnum {
    GREENHOUSE("Greenhouse"),
    FLOWER("Flower"),
    NAME("Name"),
    SOIL("Soil"),
    ORIGIN("Origin"),
    VISUAL_PARAM("Visual_param"),
    SIZE("Size"),
    COLOR("Color"),
    LEAF_COLOR("Leaf_color"),
    GROWING_TIPS("Growing_tips"),
    TEMPERATURE("Temperature"),
    LIGHTING("Lighting"),
    WATERING("Watering"),
    MULTIPLYING("Multiplying");

    private String value;

    private FlowerEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}

   /* GREENHOUSE("Greenhouse"),
    FLOWER("Flower"),
    NAME("Name"),
    SOIL("Soil"),
    ORIGIN("Origin"),
    VISUAL_PARAM("Visual_param"),
    GROWING_TIPS("Growing_tips"),
    MULTIPLYING("Multiplying");

    private String value;

    private FlowerEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }*/
