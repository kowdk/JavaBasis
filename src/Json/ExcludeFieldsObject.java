package Json;

public class ExcludeFieldsObject {
    @Exclude private final int annotatedField;
    private final String stringField;
    private final long longField;
    //private Class<?> clazzField;

    public ExcludeFieldsObject() {
      annotatedField = 5;
      stringField = "someDefaultValue";
      longField = 1234;
    }
}
