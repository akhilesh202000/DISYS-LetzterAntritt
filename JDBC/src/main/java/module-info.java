module jdbc {
    requires java.sql;
    requires lombok;
    exports org.jdbc;
    exports org.jdbc.dto;

    opens org.jdbc.dto to com.google.gson;
}