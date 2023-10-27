package fun.pullock.id.core.generator;

public enum IdGeneratorType {
    ZOOKEEPER("zookeeper"),
    REDIS("redis"),
    UUID("uuid"),
    MYSQL("mysql")
    ;


    private final String name;

    IdGeneratorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
