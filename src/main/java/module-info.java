module com.mycompany.fruit.market {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires com.google.gson;

    opens com.mycompany.fruit.market to javafx.fxml;
    opens model;
    exports com.mycompany.fruit.market;
    requires org.apache.poi.ooxml;
}
