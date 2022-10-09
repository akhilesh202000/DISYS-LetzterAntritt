module jdbc {
    requires java.sql;
    requires lombok;
    exports org.jdbc;
    exports org.jdbc.entities;

    opens org.jdbc.entities to com.google.gson;
}